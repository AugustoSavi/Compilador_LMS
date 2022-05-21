package analisadorSintatico;

import model.NotificacaoConsole;
import model.SintaticoReturn;
import model.Token;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AnalisadorSintatico {
    private final Parsing tabelaDerivacoes = new Parsing();
    private final SimbolosNaoTerminais naoTerminais = new SimbolosNaoTerminais();
    private final SimbolosTerminais terminais = new SimbolosTerminais();
    private final Stack<Token> derivadas = new Stack<>();
    Queue<NotificacaoConsole> notificacaoConsoles = new LinkedList<>();
    private final Stack<Token> pilhaSintatica = new Stack<>();

    /* funcao responsavel pela analise sintatica */
    public SintaticoReturn analiseSintatica(Queue<Token> tokenStack) {

        this.pilhaSintatica.add(new Token(52, 0, "PROGRAMA"));

        //processa enquanto a pilha sintatica n estiver vazia
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
                String[] derivacoes = tabelaDerivacoes.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                for (String derivacao : derivacoes) {
                    if (!derivacao.equals("NULL")) {
                        derivadas.add(formatDerivacao(valorEntrada.getNumeroLinha(), derivacao));
                    }
                }

                addPilhaSintatica(derivadas);
                derivadas.clear();

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
                    if (tabelaDerivacoes.containsKey(valorSintatico.getCodigo(), valorEntrada.getCodigo())) {

                        // retira token inicial e coleta sua derivacao
                        this.pilhaSintatica.pop();
                        String[] derivacoes = tabelaDerivacoes.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                        // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                        for (String derivacao : derivacoes) {
                            if (!derivacao.equals("NULL")) {
                                derivadas.add(formatDerivacao(valorEntrada.getNumeroLinha(), derivacao));
                            }
                        }

                        addPilhaSintatica(derivadas);
                        derivadas.clear();

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

    /*  forma token da derivacao  */
    private Token formatDerivacao(int numeroLinha, String derivacao) {
        int codigo;

        if (naoTerminais.containsKey(derivacao)) {
            codigo = naoTerminais.getNaoTerminal(derivacao);
            return new Token(codigo, numeroLinha, derivacao);

        } else {
            codigo = terminais.getTerminal(derivacao);
            return new Token(codigo, numeroLinha, derivacao);
        }
    }
}
