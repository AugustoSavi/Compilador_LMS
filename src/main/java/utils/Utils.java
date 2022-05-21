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

    public Utils(Queue<Token> tokens, Queue<NotificacaoConsole> notificacaoConsoles) {
        this.tokens = tokens;
        this.notificacaoConsoles = notificacaoConsoles;
    }

    public String[] getSplitedLine(Linha line) {
        return line.getLinha().trim().split(" ");
    }

    public int getSimboloPrimario(char caracter) {
        return terminais.getSimboloPrimario(caracter);
    }

    public int getSimboloCombinado(char caracter, char proxCaracter) {
        return terminais.getSimboloCombinado(caracter, proxCaracter);
    }

    public int getPalavraReservada(String palavra) {
        return terminais.getPalavraReservada(palavra);
    }

    public boolean isLetter(Character caracter) {
        return Character.isLetter(caracter) || caracter == '_';
    }

    public boolean isNumber(Character caracter) {
        return Character.isDigit(caracter);
    }

    public void addToken(int codigo, int numeroLinha, String palavra) {
        this.tokens.add(new Token(codigo, numeroLinha, palavra));
    }

    public void addNotificacaoConsole(int numeroLinha, String mensagem) {
        this.notificacaoConsoles.add(new NotificacaoConsole(numeroLinha, mensagem));
    }
}
