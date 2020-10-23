package br.unesp.rc.graphqlanalisesentimentos.resolver.query;

import br.unesp.rc.graphqlanalisesentimentos.entity.Sentimento;
import br.unesp.rc.graphqlanalisesentimentos.repository.SentimentoRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weka.core.Attribute;

import java.util.ArrayList;
import java.util.List;

@Component
public class SentimentoQuery implements GraphQLQueryResolver {

    @Autowired
    private SentimentoRepository sentimentoRepository;


    public List<Sentimento> getAllSentimento()
    {
        return sentimentoRepository.findAll();
    }


}
