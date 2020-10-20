package br.unesp.rc.graphqlanalisesentimentos.resolver.query;

import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import br.unesp.rc.graphqlanalisesentimentos.repository.LoginRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioQuery implements GraphQLQueryResolver {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    public List<Usuario> allUsuarios(){
            return usuarioRepository.findAll();
    }

    public Usuario getUsuarioLogin(Integer id_login)
    {
        return usuarioRepository.findUsuarioByLogin(loginRepository.findById(id_login).orElseGet(null));
    }

}
