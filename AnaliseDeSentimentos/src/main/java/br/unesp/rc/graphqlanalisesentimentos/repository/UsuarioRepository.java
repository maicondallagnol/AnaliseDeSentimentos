package br.unesp.rc.graphqlanalisesentimentos.repository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Usuario findUsuarioByLogin(Login id_login);

}
