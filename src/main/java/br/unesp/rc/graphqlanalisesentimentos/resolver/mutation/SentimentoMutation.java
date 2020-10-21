package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import br.unesp.rc.graphqlanalisesentimentos.entity.Sentimento;
import br.unesp.rc.graphqlanalisesentimentos.entity.Imagem;
import br.unesp.rc.graphqlanalisesentimentos.repository.AnaliseRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.SentimentoRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.ImagemRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SentimentoMutation implements GraphQLMutationResolver {

    @Autowired
    private SentimentoRepository sentimentoRepository;

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @NotNull
    public boolean addSentimento(String nome, String descricao, Integer id_imagem) {
        if(sentimentoRepository.existsByNome(nome))
        {
            return false;
        }
        Imagem imagem = imagemRepository.findById(id_imagem).orElseGet(null);

        Sentimento sentimento = new Sentimento();
        sentimento.setNome(nome);
        sentimento.setDescricao(descricao);
        sentimento.setImagem(imagem);

        sentimentoRepository.saveAndFlush(sentimento);

        return true;
    }

    public Boolean deleteSentimento(Integer id) {
        Sentimento sentimento = sentimentoRepository.findById(id).orElseGet(null);
        analiseRepository.deleteAllBySentimento(sentimento);
        sentimentoRepository.deleteById(id);

        return true;
    }

}
