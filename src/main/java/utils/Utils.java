package utils;

import analisadorLexico.SimbolosTerminais;
import model.NotificacaoConsole;
import model.Token;

import java.util.Queue;

public class Utils {
    Queue<Token> tokens;
    Queue<NotificacaoConsole> notificacaoConsoles;
    public Utils( Queue<Token> tokens, Queue<NotificacaoConsole> notificacaoConsoles){
        this.tokens = tokens;
        this.notificacaoConsoles = notificacaoConsoles;
    }
    private SimbolosTerminais terminais = new SimbolosTerminais();
    public int getSimboloPrimario(char caracter) {
        return terminais.getSimboloPrimario(caracter);
    }

    public int getSimbolosSecundarios(char caracter, char proxCaracter) {
        return terminais.getSimbolosSecundarios(caracter, proxCaracter);
    }

    public int getPalavraReservada(String palavra) {
        return terminais.getPalavraReservada(palavra);
    }

    public boolean isLetra(char caracter) {
        if (caracter >= 'a' && caracter <= 'z' || caracter >= 'A' && caracter <= 'Z' || caracter == '_') {
            return true;
        }
        return false;
    }

    public boolean isNumero(char caracter) {
        if(caracter >= '0' && caracter <= '9') {
            return true;
        }
        return false;
    }

    public void addToken(int codigo, int numeroLinha, String palavra){
        this.tokens.add(new Token(codigo,numeroLinha,palavra));
    }

    public void addNotificacaoConsole(int numeroLinha, String mensagem){
        this.notificacaoConsoles.add(new NotificacaoConsole(numeroLinha,mensagem));
    }
}
