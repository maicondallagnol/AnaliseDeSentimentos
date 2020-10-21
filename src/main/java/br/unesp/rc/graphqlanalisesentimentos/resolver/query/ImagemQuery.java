package br.unesp.rc.graphqlanalisesentimentos.resolver.query;

import br.unesp.rc.graphqlanalisesentimentos.entity.Imagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import br.unesp.rc.graphqlanalisesentimentos.repository.ImagemRepository;

import java.util.List;

@Component
public class ImagemQuery implements GraphQLQueryResolver{

    @Autowired
    private ImagemRepository imagemRepository;

    public List<Imagem> getImages()
    {
        return imagemRepository.findAll();
    }
}
