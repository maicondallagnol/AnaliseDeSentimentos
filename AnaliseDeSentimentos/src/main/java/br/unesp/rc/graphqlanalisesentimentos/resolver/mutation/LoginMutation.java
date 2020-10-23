package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import br.unesp.rc.graphqlanalisesentimentos.entity.Login;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.LoginRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LoginMutation implements GraphQLMutationResolver{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    public Login addLogin(String username, String senha) {

        if(username.equals("") || senha.equals(""))
        {
            return null;
        }
        Login login = new Login();
        login.setUsername(username);
        login.setSenha(senha);

        LocalDateTime horarioAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        login.setUltimo_acesso(horarioAtual.format(formatter));

        return loginRepository.saveAndFlush(login);
    }

}