package br.unesp.rc.graphqlanalisesentimentos.repository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraseRepository extends JpaRepository<Frase, Integer> {
    List<Frase> findAllById(Integer id_frase);
}
