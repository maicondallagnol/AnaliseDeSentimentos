package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;
import br.unesp.rc.graphqlanalisesentimentos.repository.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import br.unesp.rc.graphqlanalisesentimentos.entity.Frase;
import br.unesp.rc.graphqlanalisesentimentos.repository.FraseRepository;

@Component
public class FraseMutation implements GraphQLMutationResolver{

    @Autowired
    private FraseRepository fraseRepository;

    @Autowired
    private AnaliseRepository analiseRepository;

    public Frase addFrase(String texto) {
        Frase frase = new Frase();
        frase.setTexto(texto);

        return fraseRepository.saveAndFlush(frase);
    }

    public boolean deleteFrase(Integer id) {
        analiseRepository.deleteAllByFrase(fraseRepository.findById(id).orElseGet(null));
        fraseRepository.deleteById(id);
        return true;
    }
}