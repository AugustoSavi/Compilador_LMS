package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private int codigo;
    private int numeroLinha;
    private String palavra;

    public Token(int codigo, String palavra) {
        this.codigo = codigo;
        this.palavra = palavra;
    }
}
