package unesp.projeto.entity;

import unesp.projeto.entity.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Frase {
    private long idFrase;
    private String frase;
    private Pessoa idPessoa;
    private Sentimento idSentimento;
}
