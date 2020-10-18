package br.unesp.rc.graphqlanalisesentimentos.resolver.query;

import br.unesp.rc.graphqlanalisesentimentos.entity.Frase;
import br.unesp.rc.graphqlanalisesentimentos.repository.FraseRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FraseQuery implements GraphQLQueryResolver{

    @Autowired
    private FraseRepository fraseRepository;

    public List<Frase> getAllFrase()
    {
        return fraseRepository.findAll();
    }
}
