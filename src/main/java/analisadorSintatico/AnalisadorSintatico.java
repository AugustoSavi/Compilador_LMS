package analisadorSintatico;

import model.NotificacaoConsole;
import model.SintaticoReturn;
import model.Token;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AnalisadorSintatico {
    //------------	tabelas
    private final Parsing tabelaDerivacoes = new Parsing();
    private final SimbolosNaoTerminais naoTerminais = new SimbolosNaoTerminais();
    private final SimbolosTerminais terminais = new SimbolosTerminais();

    //----------	pilhas
    private final Stack<Token> derivadas = new Stack<>();

    Queue<NotificacaoConsole> notificacaoConsoles = new LinkedList<>();
    private final Queue<Token> pilhaSintatica = new LinkedList<>();

    /*------------------------------------Metodos--------------------------------------*/

    /* funcao responsavel pela analise sintatica */
    public SintaticoReturn analiseSintatica(Queue<Token> tokenStack) {

        this.pilhaSintatica.add(new Token(52,0,"PROGRAMA"));

        //processa enquanto a pilha sintatica n estiver vazia
        while(!pilhaSintatica.isEmpty()) {

            Token valorEntrada;
            Token valorSintatico = new Token();

            //atribui valores a serem analisados
            try {
                valorEntrada = tokenStack.peek();
                valorSintatico = pilhaSintatica.peek();

            } catch (Exception e) {

                notificacaoConsoles.add((new NotificacaoConsole(valorSintatico.getNumeroLinha(),"ERRO----> Esperado(a) ' " + valorSintatico.getPalavra())));
                break;
            }

            System.out.println(valorEntrada.getPalavra() + "-----" + valorSintatico.getPalavra());

            //se valor inicial atribui primeiira derivação sintatica
            if((valorSintatico.getCodigo() == 52) && (valorEntrada.getCodigo() == 1)) {

                // retira token inicial e coleta sua derivacao
                this.pilhaSintatica.remove();
                String[] derivacoes = tabelaDerivacoes.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                for(String derivacao : derivacoes) {
                    if(!derivacao.equals("NULL")) {
                        derivadas.add(formataDerivacao(valorSintatico.getNumeroLinha(),derivacao));
                    }
                }

                addPilhaSintatica(derivadas);
                derivadas.clear();

            } else /* se nao é posicao inicial */{

                //se x(topo da pilha sintatica) é terminal
                if(valorSintatico.getCodigo() < 52) {

                    // se derivacao igual a token lexico remove ambos
                    if(valorSintatico.getCodigo() == valorEntrada.getCodigo()) {
                        tokenStack.remove();
                        pilhaSintatica.remove();

                    } else /* se nao erro */{
                        notificacaoConsoles.add((new NotificacaoConsole(valorSintatico.getNumeroLinha(),"ERRO----> Esperado(a) ' " + valorSintatico.getPalavra())));
                        System.out.println(notificacaoConsoles.peek().getMensagem());
                        break;
                    }


                } else /* se x(topo da pilha sintaica) � nao-terminal */{

                    // se existir derivacao com os codigos do topo das pilhas sintatica e lexica
                    if(tabelaDerivacoes.containsKey(valorSintatico.getCodigo(), valorEntrada.getCodigo())) {

                        // retira token inicial e coleta sua derivacao
                        this.pilhaSintatica.remove();
                        String[] derivacoes = tabelaDerivacoes.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                        // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                        for(String derivacao : derivacoes) {
                            if(!derivacao.equals("NULL")) {
                                derivadas.add(formataDerivacao(valorSintatico.getNumeroLinha(),derivacao));
                            }
                        }

                        addPilhaSintatica(derivadas);
                        derivadas.clear();

                    } else /* caso nao exista derivacao erro */{
                        notificacaoConsoles.add((new NotificacaoConsole(valorEntrada.getNumeroLinha(),"ERRO-------> Não e permitido ' " + valorEntrada.getPalavra())));
                        System.out.println(notificacaoConsoles.peek().getMensagem());
                        break;
                    }
                }

            }
        }

        return new SintaticoReturn(notificacaoConsoles);
    }

    /*------------------------ funcoes auxiliares --------------------------------*/

    /*  add pilha temporaria na pilha-sintatica  */
    private void addPilhaSintatica(Stack<Token> stack) {
        while(!stack.isEmpty()) {
            pilhaSintatica.add(stack.pop());
        }
    }

    /*  forma token da derivacao  */
    private Token formataDerivacao(int numeroLinha,String deriv) {
        int codigo;

        if(naoTerminais.containsKey(deriv)) {
            codigo = naoTerminais.getNaoTerminal(deriv);
            return new Token(codigo, numeroLinha, deriv);

        } else {
            codigo = terminais.getSimboloTerminal(deriv);
            return new Token(codigo, numeroLinha,deriv);
        }
    }
}
