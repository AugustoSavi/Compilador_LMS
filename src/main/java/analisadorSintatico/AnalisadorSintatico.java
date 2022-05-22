package analisadorSintatico;

import model.NotificacaoConsole;
import model.SintaticoReturn;
import model.Token;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AnalisadorSintatico {
    private final ParsingTable parsingTable = new ParsingTable();
    private final SimbolosNaoTerminais simbolosNaoTerminais = new SimbolosNaoTerminais();
    private final SimbolosTerminais simbolosTerminais = new SimbolosTerminais();
    private final Stack<Token> pilhaDerivadas = new Stack<>();
    private final Stack<Token> pilhaSintatica = new Stack<>();
    Queue<NotificacaoConsole> notificacaoConsoles = new LinkedList<>();

    public SintaticoReturn analiseSintatica(Queue<Token> tokenStack) {

        this.pilhaSintatica.add(new Token(52, 0, "PROGRAMA"));

        //processa enquanto a pilha sintatica não estiver vazia
        while (!pilhaSintatica.isEmpty()) {

            Token valorEntrada = new Token();
            Token valorSintatico = new Token();

            //atribui valores a serem analisados
            try {
                valorEntrada = tokenStack.element();
                valorSintatico = pilhaSintatica.peek();

            } catch (Exception e) {
                notificacaoConsoles.add((new NotificacaoConsole(valorSintatico.getNumeroLinha(), "Valor entrada: " + valorEntrada.getPalavra() + "Esperado(a): " + valorSintatico.getPalavra())));
                break;
            }

            System.out.println("Valores em analise: " + valorEntrada.getPalavra() + "===" + valorSintatico.getPalavra());

            //se valor inicial atribui primeira derivação sintatica
            if ((valorSintatico.getCodigo() == 52) && (valorEntrada.getCodigo() == 1)) {

                // retira token inicial e coleta sua derivacao
                this.pilhaSintatica.pop();
                String[] derivacoes = parsingTable.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                for (String derivacao : derivacoes) {
                    if (!derivacao.equals("NULL")) {
                        pilhaDerivadas.add(formatDerivacao(valorEntrada.getNumeroLinha(), derivacao));
                    }
                }

                addPilhaSintatica(pilhaDerivadas);
                pilhaDerivadas.clear();

            } else /* se nao é posicao inicial */ {

                //se x(topo da pilha sintatica) é terminal
                if (valorSintatico.getCodigo() < 52) {

                    // se derivacao igual a token lexico remove ambos
                    if (valorSintatico.getCodigo() == valorEntrada.getCodigo()) {
                        tokenStack.poll();
                        pilhaSintatica.pop();

                    } else /* se não erro */ {
                        notificacaoConsoles.add((new NotificacaoConsole(valorEntrada.getNumeroLinha(), "Valor entrada: " + valorEntrada.getPalavra() + " Esperado(a): " + valorSintatico.getPalavra())));
                        System.out.println(notificacaoConsoles.peek().getMensagem());
                        break;
                    }

                } else /* se x(topo da pilha sintaica) é nao-terminal */ {

                    // se existir derivacao com os codigos do topo das pilhas sintatica e lexica
                    if (parsingTable.containsKey(valorSintatico.getCodigo(), valorEntrada.getCodigo())) {

                        // retira token inicial e coleta sua derivacao
                        this.pilhaSintatica.pop();
                        String[] derivacoes = parsingTable.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                        // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                        for (String derivacao : derivacoes) {
                            if (!derivacao.equals("NULL")) {
                                pilhaDerivadas.add(formatDerivacao(valorEntrada.getNumeroLinha(), derivacao));
                            }
                        }

                        addPilhaSintatica(pilhaDerivadas);
                        pilhaDerivadas.clear();

                    } else /* caso nao exista derivacao erro */ {
                        notificacaoConsoles.add((new NotificacaoConsole(valorEntrada.getNumeroLinha(), "Não e permitido: " + valorEntrada.getPalavra())));
                        System.out.println(notificacaoConsoles.peek().getMensagem());
                        break;
                    }
                }

            }
        }

        return new SintaticoReturn(notificacaoConsoles);
    }

    /*  add pilha temporaria na pilha-sintatica  */
    private void addPilhaSintatica(Stack<Token> stack) {
        while (!stack.isEmpty()) {
            pilhaSintatica.add(stack.pop());
        }
    }

    private Token formatDerivacao(int numeroLinha, String derivacao) {
        int codigo;

        if (simbolosNaoTerminais.containsKey(derivacao)) {
            codigo = simbolosNaoTerminais.getNaoTerminal(derivacao);
            return new Token(codigo, numeroLinha, derivacao);

        } else {
            codigo = simbolosTerminais.getTerminal(derivacao);
            return new Token(codigo, numeroLinha, derivacao);
        }
    }
}
