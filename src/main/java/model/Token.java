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

    public String getPalavra() {
        if (codigo == 25) {
            return "Identificador";
        } else if (codigo == 48) {
            return "Literal";
        }
        return palavra;
    }
}
