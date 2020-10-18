package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMutation implements GraphQLMutationResolver {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario addUsuario(String nome) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);

        return usuarioRepository.saveAndFlush(usuario);
    }
}
