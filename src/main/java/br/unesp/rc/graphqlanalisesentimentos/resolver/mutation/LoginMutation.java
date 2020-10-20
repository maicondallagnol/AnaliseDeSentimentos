package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import br.unesp.rc.graphqlanalisesentimentos.entity.Login;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.LoginRepository;

@Component
public class LoginMutation implements GraphQLMutationResolver{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    public Login addLogin(String nomeUsuario, String senha, Integer id_usuario) {
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseGet(null);

        Login login = new Login();
        login.setNomeUsuario(nomeUsuario);
        login.setSenha(senha);
        login.setUsuario(usuario);

        return loginRepository.saveAndFlush(login);
    }
}