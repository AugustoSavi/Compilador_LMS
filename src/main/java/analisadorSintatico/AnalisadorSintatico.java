package analisadorSintatico;

import analisadorSemantico.AnalisadorSemantico;
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
    private AnalisadorSemantico analisadorSemantico = new AnalisadorSemantico();
    Queue<NotificacaoConsole> notificacaoConsoles = new LinkedList<>();

    private static final String PROGRAMA = "PROGRAMA";
    private static final String PARAMETER = "PARAMETER";
    private static final String NULL = "NULL";
    private static final String PROCEDURE = "PROCEDURE";

    private boolean hasErrorSemantico = false;

    public SintaticoReturn analiseSintatica(Queue<Token> tokenStack) {

        this.pilhaSintatica.add(new Token(52, 0, PROGRAMA));

        //processa enquanto a pilha sintatica não estiver vazia
        while (!pilhaSintatica.isEmpty()) {

            Token valorEntrada = new Token();
            Token valorSintatico = new Token();

            //atribui valores a serem analisados
            try {
                valorEntrada = tokenStack.element();
                valorSintatico = pilhaSintatica.peek();

            } catch (Exception e) {
                notificacaoConsoles.add((new NotificacaoConsole(valorSintatico.getNumeroLinha(), "Valor entrada: " + valorEntrada.getPalavra() + "Esperado: " + valorSintatico.getPalavra())));
                if (null != notificacaoConsoles.peek()) {
                    System.out.println(notificacaoConsoles.peek().getMensagem());
                }
                break;
            }

//            System.out.println("Valores em analise: " + valorEntrada.getPalavra() + "===" + valorSintatico.getPalavra());

            //se valor inicial atribui primeira derivação sintatica
            if ((valorSintatico.getCodigo() == 52) && (valorEntrada.getCodigo() == 1)) {

                // retira token inicial e coleta sua derivacao
                this.pilhaSintatica.pop();
                String[] derivacoes = parsingTable.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                for (String derivacao : derivacoes) {
                    if (!derivacao.equals(NULL)) {
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
                        Token lexItem = tokenStack.poll();
                        Token sintaticItem = pilhaSintatica.pop();
                        String response = "";

                        // casos da analise semantica
                        switch (sintaticItem.getCodigo()) {

                            // caso encontre uma categoria
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                analisadorSemantico.setCategory(sintaticItem.getPalavra());
                                analisadorSemantico.setIsStating(true);
                                analisadorSemantico.setIsDefaultParameter(false);

                                break;

                            // caso encontre uma procedure
                            case 5:
                                // salva a categoria e adiciona a table
                                analisadorSemantico.setCategory(sintaticItem.getPalavra());
                                analisadorSemantico.setType(PROCEDURE);
                                analisadorSemantico.setIsStating(true);
                                analisadorSemantico.pushOnIdentifierStack(pilhaDerivadas.peek());

                                // salva key para manipular os parametros
                                analisadorSemantico.setLastProcedure((pilhaDerivadas.pop()).getPalavra());
                                response = analisadorSemantico.analyze();
                                analisadorSemantico.clearParameters();
                                analisadorSemantico.setLevel(1);
                                analisadorSemantico.setCategory(PARAMETER);
                                pilhaSintatica.pop();
                                analisadorSemantico.setIsDefaultParameter(true);
                                verifyError(response, valorEntrada);
                                break;

                            //caso encontre um 'end'
                            case 7:
                                analisadorSemantico.setLevel(0);
                                analisadorSemantico.deleteAllByLevel(1);
                                analisadorSemantico.clearParameters();

                                break;

                            //caso encontre o tipo integer/array
                            case 8:
                            case 9:
                                //adiciona os identificadores a tabela com o tipo encontrado
                                analisadorSemantico.setType(valorEntrada.getPalavra());
                                response = analisadorSemantico.analyze();
                                verifyError(response, valorEntrada);
                                break;

                            // caso encontre um call
                            case 11:
//								System.out.println((pilhaDerivadas.peek()).getValor());

                                response = analisadorSemantico.searchOnIdentifierTable(pilhaDerivadas.peek().getPalavra());
                                verifyError(response, pilhaDerivadas.peek());

                                if (!hasErrorSemantico) {
                                    analisadorSemantico.setIsCallingProcedure(true);
                                    analisadorSemantico.setLastProcedure((pilhaDerivadas.pop()).getPalavra());
                                    pilhaSintatica.pop();
                                }
                                break;

                            //caso encontre um identificador
                            case 25:
                                // se estiver declarando adiciona a tabela
                                if (analisadorSemantico.getIsStating()) {
                                    analisadorSemantico.pushOnIdentifierStack(lexItem);

                                } else {
                                    // se não verifica se existe na tabela
                                    response = analisadorSemantico.searchOnIdentifierTable(valorEntrada.getPalavra());
                                    verifyError(response, valorEntrada);
                                }
                                break;

                            //caso encontre um ';'
                            case 47:
                                // se estiver declarando adicioca os identificadores ja encontrados a tabela
                                if (analisadorSemantico.getIsStating()) {
                                    response = analisadorSemantico.analyze();
                                    verifyError(response, valorEntrada);
                                }
                                if (analisadorSemantico.getIsExpression()) {
                                    response = analisadorSemantico.checkExpressionTypes();
                                    verifyError(response, valorEntrada);
                                }

                                analisadorSemantico.setIsCallingProcedure(false);
                                analisadorSemantico.setIsExpression(false);
                                break;

                            default:
                                continue;
                        }

                        if (hasErrorSemantico) {
                            break;
                        }

                    } else /* se não erro */ {
                        notificacaoConsoles.add((new NotificacaoConsole(valorEntrada.getNumeroLinha(), "Valor entrada: " + valorEntrada.getPalavra() + " Esperado: " + valorSintatico.getPalavra())));
                        if (null != notificacaoConsoles.peek()) {
                            System.out.println(notificacaoConsoles.peek().getMensagem());
                        }
                        break;
                    }

                } else /* se x(topo da pilha sintaica) é nao-terminal */ {

                    // se existir derivacao com os codigos do topo das pilhas sintatica e lexica
                    if (parsingTable.containsKey(valorSintatico.getCodigo(), valorEntrada.getCodigo())) {

                        // se entrar em um bloco ou corpo
                        if (valorSintatico.getCodigo() == 64 || valorSintatico.getCodigo() == 53) {
                            analisadorSemantico.setIsStating(false);
                            analisadorSemantico.clearParameters();
                        }

                        // caso encontre um comando ou expressao
                        if (valorSintatico.getCodigo() == 77) {
                            analisadorSemantico.setIsExpression(true);
                        }

                        // retira token inicial e coleta sua derivacao
                        this.pilhaSintatica.pop();
                        String[] derivacoes = parsingTable.getCombinado(valorSintatico.getCodigo(), valorEntrada.getCodigo());

                        // atribui as derivacoes a uma pilha temporaria e depois a pilha principal
                        for (String derivacao : derivacoes) {
                            if (!derivacao.equals(NULL)) {
                                pilhaDerivadas.add(formatDerivacao(valorEntrada.getNumeroLinha(), derivacao));
                            }
                        }

                        addPilhaSintatica(pilhaDerivadas);
                        pilhaDerivadas.clear();

                    } else /* caso nao exista derivacao erro */ {
                        notificacaoConsoles.add((new NotificacaoConsole(valorEntrada.getNumeroLinha(), "Não e permitido: " + valorEntrada.getPalavra())));
                        if (null != notificacaoConsoles.peek()) {
                            System.out.println(notificacaoConsoles.peek().getMensagem());
                        }
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

        } else {
            codigo = simbolosTerminais.getTerminal(derivacao);
        }
        return new Token(codigo, numeroLinha, derivacao);
    }

    private void verifyError(String response, Token valorEntrada) {
        if (!response.isEmpty()) {
            hasErrorSemantico = true;
            notificacaoConsoles.add(new NotificacaoConsole(valorEntrada.getNumeroLinha(), "Analisador Semantico: " + response + " Valor entrada: " + valorEntrada));
        }
    }
}
