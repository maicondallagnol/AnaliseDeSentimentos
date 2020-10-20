package br.unesp.rc.graphqlanalisesentimentos.repository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{
    Login findLoginByUsernameAndSenha(String nome, String senha);

    boolean existsLoginByUsername(String username);
}
