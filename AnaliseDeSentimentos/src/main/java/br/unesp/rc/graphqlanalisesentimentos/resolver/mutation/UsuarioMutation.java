package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import br.unesp.rc.graphqlanalisesentimentos.repository.ContatoRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.EnderecoRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.LoginRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMutation implements GraphQLMutationResolver {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    public Usuario addUsuario(String nome, String sobrenome, String data_nasc, String sexo, Integer id_contato, Integer id_endereco, Integer id_login) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setData_nasc(data_nasc);
        usuario.setSobrenome(sobrenome);
        usuario.setSexo(sexo);

        usuario.setContato(contatoRepository.findById(id_contato).orElseGet(null));
        usuario.setLogin(loginRepository.findById(id_login).orElseGet(null));
        usuario.setEndereco(enderecoRepository.findById(id_endereco).orElseGet(null));

        return usuarioRepository.saveAndFlush(usuario);
    }

}
