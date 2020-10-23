package br.unesp.rc.graphqlanalisesentimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Sentimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SentimentoRepository extends JpaRepository<Sentimento, Integer>{

    @Query(value="select id from sentimento where nome=?1")
    Integer findIdByNome(String classe);

    boolean existsByNome(String nome);
    
}
