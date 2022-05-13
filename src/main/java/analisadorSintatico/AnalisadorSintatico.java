package analisadorSintatico;

import analisadorLexico.SimbolosTerminais;
import model.NotificacaoConsole;
import model.SintaticoReturn;
import model.Token;

import java.util.LinkedList;
import java.util.Queue;

public class AnalisadorSintatico {
    //------------	tabelas
    private final Gramatica tabelaDerivacoes = new Gramatica();
    private final SimbolosNaoTerminais naoTerminais = new SimbolosNaoTerminais();
    private final SimbolosTerminais terminais = new SimbolosTerminais();
    private final SintaticoReturn retorno = new SintaticoReturn();
    //----------	pilhas
    private final Queue<Token> derivadas = new LinkedList<>();
    private final Queue<Token> pilhaSintatica = new LinkedList<>();

        /*------------------------------------Metodos--------------------------------------*/

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

                //se valor inicial atribui primeiira deriva��o sintatica
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

                        // se derivacao igual a token lexico remove ambos
                        if (valorSintatico.getCodigo() == valorEntrada.getCodigo()) {
                            Token lexItem = tokenStack.poll();
                            Token sintaticItem = pilhaSintatica.poll();
                            String response = "";

                            // casos da analise semantica
                            switch (sintaticItem.getCodigo()) {

                                //caso encontre um identificador
                                case 25:

                                    // se estiver declarando adiciona a tabela
//                                if(semanticAnalyser.getIsStating()) {
//                                    semanticAnalyser.pushOnIdentifierStack(lexItem);
//
//                                } else {
//                                    // se n�o verifica se existe na tabela
//                                    response = semanticAnalyser.searchOnIdentTable(valorEntrada.getValor());
//                                    verifyError(response, valorEntrada);
//                                }

                                    break;

                                //caso encontre o tipo integer/array
                                case 8:
                                case 9:
                                    //adiciona os identificadores a tabela com o tipo encontrado
//                                semanticAnalyser.setType(valorEntrada.getValor());
//                                response = semanticAnalyser.anylize();
//                                verifyError(response, valorEntrada);
                                    break;

                                //caso encontre um ';'
                                case 47:
                                    // se estiver declarando adicioca os identificadores ja encontrados a tabela
//                                if(semanticAnalyser.getIsStating()) {
//                                    response = semanticAnalyser.anylize();
//                                    verifyError(response, valorEntrada);
//                                }
//                                if(semanticAnalyser.getIsExpression()) {
//                                    response = semanticAnalyser.checkExpressionTypes();
//                                    verifyError(response, valorEntrada);
//                                }
//
//                                semanticAnalyser.setIsCallingProc(false);
//                                semanticAnalyser.setIsExpression(false);
                                    break;

                                // caso encontre uma categoria
                                case 1:
                                case 2:
                                case 3:
                                case 4:
//                                semanticAnalyser.setCategory(sintaticItem.getValor());
//                                semanticAnalyser.setIsStating(true);
//                                semanticAnalyser.setIsDefParam(false);

                                    break;

                                // caso encontre uma procedure
                                case 5:
                                    // salva a categoria e adiciona a table
//                                semanticAnalyser.setCategory(sintaticItem.getValor());
//                                semanticAnalyser.setType("PROCEDURE");
//                                semanticAnalyser.setIsStating(true);
//                                semanticAnalyser.pushOnIdentifierStack(pilhaLexica.peek());
//
//                                // salva key para manipular os parametros
//                                semanticAnalyser.setLastProc((pilhaLexica.pop()).getValor());
//                                response = semanticAnalyser.anylize();
//                                semanticAnalyser.clearParameters();
//                                semanticAnalyser.setLevel(1);
//                                semanticAnalyser.setCategory("PARAMETER");
//                                pilhaSintatica.pop();
//                                semanticAnalyser.setIsDefParam(true);
//                                verifyError(response, valorEntrada);
                                    break;

                                //caso encontre um 'end'
                                case 7:
//                                semanticAnalyser.setLevel(0);
//                                semanticAnalyser.deleteAllByLevel(1);
//                                semanticAnalyser.clearParameters();

                                    break;

                                // caso encontre um call
                                case 11:
//								System.out.println((pilhaLexica.peek()).getValor());

//                                response = semanticAnalyser.searchOnIdentTable(pilhaLexica.peek().getValor());
//                                verifyError(response, pilhaLexica.peek());
//
//                                if(!retorno.gethasError()) {
//                                    semanticAnalyser.setIsCallingProc(true);
//                                    semanticAnalyser.setLastProc((pilhaLexica.pop()).getValor());
//                                    pilhaSintatica.pop();
//                                }
                                    break;
                            }

                            // se ouver erro semantico
//                        if(retorno.gethasError()) {
////							pilhaErros.add((new Erros(response, valorEntrada.getNumLinha())));
//                            break;
//
//                        }

                        } else /* se nao erro */ {
//                        pilhaErros.add((new Erros("valor inesperado", valorEntrada.getNumLinha())));
//                        retorno.setErrorStack(pilhaErros);
//                        System.out.println(((Erros)pilhaErros.peek()).getMensagem());
//                        break;
                        }


                    } else /* se x(topo da pilha sintaica) � nao-terminal */ {

                        // se existir derivacao com os codigos do topo das pilhas sintatica e lexica
                        if (tabelaDerivacoes.containsKey(valorSintatico.getCodigo(), valorEntrada.getCodigo())) {

                            // se entrar em um bloco ou corpo
                            if (valorSintatico.getCodigo() == 64 || valorSintatico.getCodigo() == 53) {
                                System.out.println("toqui");
//                            semanticAnalyser.setIsStating(false);
//                            semanticAnalyser.clearParameters();
                            }

                            // caso encontre um comando ou express�o
                            if (valorSintatico.getCodigo() == 77) {
//                            semanticAnalyser.setIsExpression(true);
                            }

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

                        } else /* caso nao exista derivacao erro */ {
//                        pilhaErros.add((new Erros("deriva��o inexistente", valorEntrada.getNumLinha())));
//                        retorno.setErrorStack(pilhaErros);
//                        System.out.println(((Erros)pilhaErros.peek()).getMensagem());
                            break;
                        }
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

        private void verifyError(String response, Token valorEntrada) {
//        if(!response.equals("")) {
//            pilhaErros.add(new Erros(response, valorEntrada.getNumLinha()));
//            retorno.setErrorStack(pilhaErros);
//            retorno.setHasError(true);
//        }
        }
}
