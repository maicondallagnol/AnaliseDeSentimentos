package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import br.unesp.rc.graphqlanalisesentimentos.entity.*;
import br.unesp.rc.graphqlanalisesentimentos.repository.AnaliseRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.FraseRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.SentimentoRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class AnaliseMutation implements GraphQLMutationResolver {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FraseRepository fraseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SentimentoRepository sentimentoRepository;

    private final ML machineLearning = new ML();


    public Analise makeAnalise(Integer id_frase, Integer id_usuario) throws Exception {
        Frase frase = fraseRepository.findById(id_frase).orElseGet(null);

        Integer id_sentimento = machineLearning.make_classification(frase.getTexto(), sentimentoRepository);

        return addAnalise(id_frase, id_sentimento, id_usuario, false);
    }

    public Analise addAnalise(Integer id_frase, Integer id_sentimento, Integer id_usuario, Boolean correto) throws Exception {
        Analise analise = new Analise();
        analise.setFrase(fraseRepository.findById(id_frase).orElseGet(null));
        analise.setSentimento(sentimentoRepository.findById(id_sentimento).orElseGet(null));
        analise.setUsuario(usuarioRepository.findById(id_usuario).orElseGet(null));
        analise.setCorreto(correto);
        analise.setNovo(true);

        LocalDateTime horarioAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        analise.setDataAnalise(horarioAtual.format(formatter));


        Analise saved = analiseRepository.saveAndFlush(analise);

        updateClassifier();

        return saved;
    }

    public Boolean updateCorreto(Integer id_analise, Boolean correto)
    {
        Analise analise;
        analise = analiseRepository.findById(id_analise).orElse(null);

        analise.setCorreto(correto);
        analiseRepository.saveAndFlush(analise);

        return true;
    }

    private void updateClassifier() throws Exception {
        int num_novos = analiseRepository.countAllByNovoAndCorreto(true,true);
        if(num_novos >= 25) {
            machineLearning.makeNewModel();
            updateAllAnalise();
        }
    }

    private void updateAllAnalise()
    {
        analiseRepository.setNovoToZero();
    }
}
