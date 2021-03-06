package br.unesp.rc.graphqlanalisesentimentos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sobrenome")
	private String sobrenome;

	@Column(name = "data_nasc")
	private String data_nasc;

	@Column(name = "sexo")
	private String sexo;

	@OneToOne
	@JoinColumn(name = "id_contato", nullable=true, insertable=true, updatable=true)
	private Contato contato;

	@OneToOne
	@JoinColumn(name = "id_login", nullable=true, insertable=true, updatable=true)
	private Login login;

	@OneToOne
	@JoinColumn(name = "id_endereco", nullable=true, insertable=true, updatable=true)
	private Endereco endereco;
}
