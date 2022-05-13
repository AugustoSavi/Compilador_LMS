package analisadorSintatico;

import analisadorLexico.SimbolosTerminais;
import model.NotificacaoConsole;
import model.SintaticoReturn;
import model.Token;

import java.util.LinkedList;
import java.util.Queue;

public class AnalisadorSintatico {
    private final Gramatica tabelaDerivacoes = new Gramatica();
    private final SimbolosNaoTerminais naoTerminais = new SimbolosNaoTerminais();
    private final SimbolosTerminais terminais = new SimbolosTerminais();
    private final SintaticoReturn retorno = new SintaticoReturn();
    private final Queue<Token> derivadas = new LinkedList<>();
    private final Queue<Token> pilhaSintatica = new LinkedList<>();

        /* funcao responsavel pela analise sintatica */
        public SintaticoReturn analiseSintatica(Queue<Token> tokenStack) {

            this.pilhaSintatica.add(new Token(0, 52, "PROGRAMA"));

            //processa enquanto a pilha sintatica n estiver vazia
            while (!pilhaSintatica.isEmpty()) {

                Token valorEntrada = new Token();
                Token valorSintatico = new Token();

                //atribui valores a serem analisados
                try {
                    valorEntrada = tokenStack.poll();
                    valorSintatico = pilhaSintatica.poll();

                } catch (Exception e) {

//                    pilhaErros.add((new NotificacaoConsole(valorEntrada.getNumeroLinha(), "esperado(a)" + valorSintatico.getPalavra())));
                    break;
                }

			System.out.println(valorEntrada.getCodigo() + "-----" + valorSintatico.getPalavra());

                //se valor inicial atribui primeira derivação sintatica
                if ((valorSintatico.getCodigo() == 52) && (valorEntrada.getCodigo() == 1)) {

                    // retira token inicial e coleta sua derivacao
                    this.pilhaSintatica.poll();
                    String[] derivacoes = tabelaDerivacoes.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                    // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                    for (String derivacao : derivacoes) {
                        if (!derivacao.equals("NULL")) {
                            derivadas.add(formataDerivacao(derivacao));
                        }
                    }

                    addPilhaSintatica(derivadas);
                    derivadas.clear();

                } else /* se nao é posicao inicial */ {

                    //se x(topo da pilha sintatica) é terminal
                    if (valorSintatico.getCodigo() < 52) {

                    }
                }
            }

            return retorno;
        }

        /*  add pilha temporaria na pilha-sintatica  */
        private void addPilhaSintatica(Queue<Token> stack) {
            while (!stack.isEmpty()) {
                pilhaSintatica.add(stack.poll());
            }
        }

        /*  forma token da derivacao  */
        private Token formataDerivacao(String deriv) {
            int codigo;

            if (naoTerminais.containsKey(deriv)) {
                codigo = naoTerminais.getNaoTerminal(deriv);
                return new Token(0, codigo, deriv);

            } else {
                codigo = terminais.getPalavraReservada(deriv);
                return new Token(0, codigo, deriv);
            }
        }
}
