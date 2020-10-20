package br.unesp.rc.graphqlanalisesentimentos.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import br.unesp.rc.graphqlanalisesentimentos.entity.Endereco;
import br.unesp.rc.graphqlanalisesentimentos.repository.UsuarioRepository;
import br.unesp.rc.graphqlanalisesentimentos.repository.EnderecoRepository;

@Component
public class EnderecoMutation implements GraphQLMutationResolver{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco addEndereco(String logradouro, String numero, String complemento,
                                String bairro, String cidade, String cep, Integer id_usuario) {

        Usuario usuario = usuarioRepository.findById(id_usuario).orElseGet(null);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        endereco.setUsuario(usuario);

        return enderecoRepository.saveAndFlush(endereco);
    }
}
