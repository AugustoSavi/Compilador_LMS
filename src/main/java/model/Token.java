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
    private Integer codigo;
    private int numeroLinha;
    private String palavra;

    public String getPalavra() {
        if (codigo.equals(25)) {
            return "Identificador";
        }
        return palavra;
    }
}
