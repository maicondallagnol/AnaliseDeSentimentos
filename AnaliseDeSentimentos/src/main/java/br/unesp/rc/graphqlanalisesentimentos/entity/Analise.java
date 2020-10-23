package br.unesp.rc.graphqlanalisesentimentos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="analise")
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_frase")
    private Frase frase;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_sentimento")
    private Sentimento sentimento;

    @Column(name = "correto")
    private boolean correto;

    @Column(name = "novo")
    private boolean novo;

    @Column(name = "data_analise")
    private String dataAnalise;

}