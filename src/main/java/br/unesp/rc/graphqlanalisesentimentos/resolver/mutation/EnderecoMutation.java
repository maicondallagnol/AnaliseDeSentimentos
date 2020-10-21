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

    public Endereco addEndereco(String rua, String numero, String cep,
                                String cidade, String estado) {


        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setCep(cep);

        return enderecoRepository.saveAndFlush(endereco);
    }
}
