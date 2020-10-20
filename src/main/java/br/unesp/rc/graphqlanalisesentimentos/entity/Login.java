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
@Entity(name="login")
public class Login {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "senha")
    private String senha;

    @OneToOne
    @JoinColumn(name = "id_usuario",nullable = false, updatable = false)
    private Usuario usuario;

}
