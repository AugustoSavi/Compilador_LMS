package utils;

import analisadorLexico.SimbolosTerminais;
import model.Linha;
import model.NotificacaoConsole;
import model.Token;
import java.util.Queue;

public class Utils {
    private final Queue<Token> tokens;
    private final Queue<NotificacaoConsole> notificacaoConsoles;
    private final SimbolosTerminais terminais = new SimbolosTerminais();

    public Utils( Queue<Token> tokens, Queue<NotificacaoConsole> notificacaoConsoles){
        this.tokens = tokens;
        this.notificacaoConsoles = notificacaoConsoles;
    }

    public String[] getSplitedLine(Linha line){
        return line.getLinha().trim().split(" ");
    }

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
        return caracter >= 'a' && caracter <= 'z' || caracter >= 'A' && caracter <= 'Z' || caracter == '_';
    }

    public boolean isNumero(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }

    public void addToken(int codigo, int numeroLinha, String palavra){
        this.tokens.add(new Token(codigo,numeroLinha,palavra));
    }

    public void addNotificacaoConsole(int numeroLinha, String mensagem){
        this.notificacaoConsoles.add(new NotificacaoConsole(numeroLinha,mensagem));
    }
}
