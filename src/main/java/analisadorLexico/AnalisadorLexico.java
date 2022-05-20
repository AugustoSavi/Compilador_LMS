package analisadorLexico;

import model.LexicoReturn;
import model.Linha;
import model.NotificacaoConsole;
import model.Token;
import utils.Utils;
import java.util.LinkedList;
import java.util.Queue;

public class AnalisadorLexico {
    int codigo = 0;
    int linhaComentario = 0, linhaLiteral = 0, tamanhoComentario = 0;
    boolean isComentario = false, isLiteral = false, isPalavra = false, isNumero = false, isUltimoCaracter = false;

    Queue<Token> tokens = new LinkedList<>();
    Queue<NotificacaoConsole> notificacaoConsoles = new LinkedList<>();
    Utils utils = new Utils(tokens,notificacaoConsoles);

    public LexicoReturn analisadorLexico(Queue<Linha> linhas ){
        Linha line = linhas.remove();

        while (line != null) {
            for (String palavraSplit: utils.getSplitedLine(line)){
                String palavraUpperCase = palavraSplit.toUpperCase();
                if (!palavraUpperCase.isEmpty()) {
                    lexicoByWord(palavraSplit, line);
                }
            }
            if(!linhas.isEmpty()) {
                line = linhas.remove();
            } else {
                utils.addNotificacaoConsole(line.getNumeroLinha(),"Fim da compilação");
                break;
            }
        }
        if(isComentario) {
            utils.addNotificacaoConsole(linhaComentario,"Não fechamento de comentário");
        }
        if(isLiteral) {
            utils.addNotificacaoConsole(linhaLiteral,"Não fechamento de literal");
        }

        return new LexicoReturn(tokens,notificacaoConsoles);
    }

    private void lexicoByWord(String palavraSplit, Linha line){
        char[] caracteres = palavraSplit.toCharArray();
        String palavra = "";

        for(int i = 0; i < caracteres.length; i++) {
            char charAtual = caracteres[i];
            char charProx = ' ';
            try {
                charProx = caracteres [i + 1];
            } catch (Exception e) {
                isUltimoCaracter = true;
            }

            // comentario
            if(charAtual == '(' && charProx == '*' && !isComentario) { //inicia comentario
                isComentario = true;
                linhaComentario = line.getNumeroLinha();
                i++;
                continue;
            } else if(charAtual == '*' && charProx == ')' && isComentario) { //fim comentario
                i += 2;
                isComentario = false;
                continue;
            } else if(isComentario) { //continua sendo comentario
                tamanhoComentario++;
                continue;
            }

            // literais
            if(charAtual == '\'' && !isLiteral) { //inicia bloco de literais
                isLiteral = true;
                linhaLiteral = line.getNumeroLinha();
                palavra += charAtual;
            } else if(charAtual != '\'' && isLiteral) { //adiciona no bloco de literais
                palavra += charAtual;
            } else if(charAtual == '\'') { //fecha bloco de literais
                palavra += charAtual;
                if (palavra.length() > 255 ){
                    utils.addNotificacaoConsole(line.getNumeroLinha(),"Literal com mais de 255 caracteres");
                }
                isLiteral = false;
                tokens.add(new Token(48,  line.getNumeroLinha(), palavra));
                palavra = "";
            }

            if(!isLiteral) {
                if(charAtual == '-' && utils.isNumber(charProx)) {
                    isNumero = true;
                    palavra += charAtual + "" + charProx;
//                    System.out.println(palavra);
                    i++;
                    continue;
                }
                if(utils.isNumber(charAtual)) {
                    isNumero = true;
                    palavra += charAtual;
                }
                if(isNumero) {
                    try {
                        while(utils.isNumber(caracteres[i + 1])) {
                            if(charAtual == '.') {
                                utils.addNotificacaoConsole(line.getNumeroLinha(),"Numeros de ponto flutuante na linha ");
                            }
                            palavra += caracteres[i + 1];
                            i++;
                        }

                        if(Double.parseDouble(palavra) > -32768 && Double.parseDouble(palavra) < 32768) {
                            utils.addToken(26,line.getNumeroLinha(),palavra);
                            palavra = "";
                            isNumero = false;

                        } else if(Double.parseDouble(palavra) <= -32768 || Double.parseDouble(palavra) >= 32768) {
                            utils.addNotificacaoConsole(line.getNumeroLinha(),"Valor não aceito na linha ");

                        }
                    } catch(Exception ignored) {}

                }

                if(!isNumero) {
//                     verificacao de simbolos terminais
                    if((codigo = utils.getSimboloCombinado(charAtual, charProx)) != 0) { //verifica se é simbolo composto
                        utils.addToken(codigo, line.getNumeroLinha(),"" + charAtual + charProx);
                        palavra = "";
                        i++;
                    } else if((codigo = utils.getSimboloPrimario(charAtual)) != 0) { //verifica se é simbolo primario
                        utils.addToken(codigo,line.getNumeroLinha() ,"" + charAtual);
                        palavra = "";
                    }
                }

                if(!isNumero) {
                    // montagem de palavra
                    if(!isPalavra && utils.isLetter(charAtual)) {
                        isPalavra = true;
                        palavra += charAtual;
                    }
                    if(isPalavra) {
                        try {
                            while(utils.isLetter(caracteres[i + 1]) || utils.isNumber(caracteres[i + 1])) {
                                palavra += caracteres[i + 1];
                                i++;
                                if(palavra.length() > 30) {
                                    utils.addNotificacaoConsole(line.getNumeroLinha(),"String fora do limite de caracteres na linha ");
                                }
                            }
                        } catch(Exception ignored) {}

                        if((codigo = utils.getPalavraReservada(palavra)) != 0) {
                            utils.addToken(codigo,line.getNumeroLinha() ,palavra);
                            palavra = "";
                            isPalavra = false;
                        } else {
                            utils.addToken(25, line.getNumeroLinha(),palavra);
                            palavra = "";
                            isPalavra = false;
                        }
                    }
                }
            }
        }
    }
}
