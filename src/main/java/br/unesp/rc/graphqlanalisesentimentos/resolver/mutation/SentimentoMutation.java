package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import br.unesp.rc.graphqlanalisesentimentos.entity.Sentimento;
import br.unesp.rc.graphqlanalisesentimentos.repository.AnaliseRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.SentimentoRepository;
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

    @NotNull
    public boolean addSentimento(String nome, String emoticon, String descricao) {
        if(sentimentoRepository.existsByNome(nome))
        {
            return false;
        }

        Sentimento sentimento = new Sentimento();
        sentimento.setNome(nome);
        sentimento.setEmoticon(emoticon);
        sentimento.setDescricao(descricao);

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
