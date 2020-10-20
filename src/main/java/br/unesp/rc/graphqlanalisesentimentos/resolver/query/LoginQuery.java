package br.unesp.rc.graphqlanalisesentimentos.resolver.query;

import br.unesp.rc.graphqlanalisesentimentos.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import br.unesp.rc.graphqlanalisesentimentos.repository.LoginRepository;

@Component
public class LoginQuery implements GraphQLQueryResolver{

    @Autowired
    private LoginRepository loginRepository;

    public Login autenticarLogin(String nomeUsuario, String senha){
        return loginRepository.findLoginByNomeUsuarioAndSenha(nomeUsuario, senha);
    }
}