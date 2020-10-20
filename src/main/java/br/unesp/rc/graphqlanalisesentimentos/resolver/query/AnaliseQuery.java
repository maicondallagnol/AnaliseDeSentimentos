package br.unesp.rc.graphqlanalisesentimentos.resolver.query;

import br.unesp.rc.graphqlanalisesentimentos.entity.Analise;
import br.unesp.rc.graphqlanalisesentimentos.repository.AnaliseRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.SentimentoRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class AnaliseQuery implements GraphQLQueryResolver {
    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SentimentoRepository sentimentoRepository;

    public List<Analise> getAnalises(Integer id_usuario)
    {
        return analiseRepository.findByUsuario(usuarioRepository.findById(id_usuario).orElseGet(null));
    }

    public Analise getRandomAnalise(Integer id_sentimento)
    {
        List<Analise> analiseRepository1 = analiseRepository.findAllBySentimento(sentimentoRepository.findById(id_sentimento).orElseGet(null));
        Random rnd = new Random();
        int rnd_pos = rnd.nextInt(analiseRepository1.size());

        return analiseRepository1.get(rnd_pos);
    }

    public Analise getAnalise(Integer id_analise)
    {
        return analiseRepository.findById(id_analise).orElseGet(null);
    }
}
