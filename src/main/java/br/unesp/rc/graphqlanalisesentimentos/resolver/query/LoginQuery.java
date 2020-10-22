package br.unesp.rc.graphqlanalisesentimentos.resolver.query;

import br.unesp.rc.graphqlanalisesentimentos.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import br.unesp.rc.graphqlanalisesentimentos.repository.LoginRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LoginQuery implements GraphQLQueryResolver{

    @Autowired
    private LoginRepository loginRepository;

    public Login autenticarLogin(String username, String senha){

        Login login_auth = loginRepository.findLoginByUsernameAndSenha(username, senha);

        if(login_auth!=null)
        {
            LocalDateTime horarioAtual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            login_auth.setUltimo_acesso(horarioAtual.format(formatter));
            loginRepository.saveAndFlush(login_auth);

            return login_auth;
        }

        return null;
    }

    public boolean existsUsername(String username)
    {
        return loginRepository.existsLoginByUsername(username);
    }
}