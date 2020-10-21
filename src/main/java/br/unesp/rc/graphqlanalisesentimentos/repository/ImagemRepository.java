package br.unesp.rc.graphqlanalisesentimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unesp.rc.graphqlanalisesentimentos.entity.Imagem;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer>{
}

