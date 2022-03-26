package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificacaoConsole {
    private int numeroLinha;
    private String mensagem;

    public String getMensagem() {
        return  "[!]Linha: "+ numeroLinha + " : " + mensagem;
    }
}
