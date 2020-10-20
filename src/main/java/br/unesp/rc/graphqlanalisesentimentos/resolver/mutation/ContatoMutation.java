package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import br.unesp.rc.graphqlanalisesentimentos.entity.Contato;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.ContatoRepository;

@Component
public class ContatoMutation implements GraphQLMutationResolver{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato addContato(String email, String celular, Integer id_usuario) {
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseGet(null);

        Contato contato = new Contato();
        contato.setEmail(email);
        contato.setCelular(celular);
        contato.setUsuario(usuario);

        return contatoRepository.saveAndFlush(contato);
    }
}