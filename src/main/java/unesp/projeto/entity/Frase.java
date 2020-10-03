package unesp.projeto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="frase")
public class Frase {

    @Id
    private long id_frase;

    private String frase;
    private String sentimento;

    //    private Pessoa idPessoa;
//    private Sentimento idSentimento;
}
