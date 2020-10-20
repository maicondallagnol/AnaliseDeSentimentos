package br.unesp.rc.graphqlanalisesentimentos.repository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Frase;
import br.unesp.rc.graphqlanalisesentimentos.entity.Sentimento;
import br.unesp.rc.graphqlanalisesentimentos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import br.unesp.rc.graphqlanalisesentimentos.entity.Analise;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer> {

    int countAllByNovoAndCorreto(boolean novo, boolean correto);

    @Transactional
    @Modifying
    @Query(value = "update analise set novo=0")
    void setNovoToZero();

    List<Analise> findAllBySentimento(Sentimento sentimento);

    List<Analise> findByUsuario(Usuario usuario);

    @Transactional
    @Modifying
    void deleteAllBySentimento(Sentimento sentimento);

    @Transactional
    @Modifying
    void deleteAllByFrase(Frase frase);
}
