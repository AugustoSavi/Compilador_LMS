
import java.util.Stack;

class AnalisadorSintatico {

    void analisar(Stack<String> tokens) {

        //Criando as pilhas.
        Stack<String> pilhaA = new Stack<String>();

        Stack<Integer> pilhaX = new Stack<Integer>();

        //Inicializa as pilhas.
        pilhaA = tokens;
        pilhaX.add(52);

        do {
            //faça x ser o topo da pilha
            Integer topox = pilhaX.peek();
            //faça "a" ser o proximo simbolo de entrada
            String topoa = pilhaA.peek();

            Integer codigo = Gramatica.TERMINAIS_E_NAO_TERMINAIS.get(topoa);//recebendo o codigo do topo da pilha a

            if (codigo == null) {
                try {
                    Integer.valueOf(topoa);
                    codigo = 26;
                } catch (Exception e) {
                    codigo = 25;
                }
            }
            //Se X é terminal ou $ então
            if (topox < 52 && topox != null) {

                //se X= a então
                if (codigo.equals(topox)) {

                    //Retire X do topo da pilha
                    topoa = pilhaA.pop();
                    //retire a da entrada
                    topox = pilhaX.pop();

                } else {//senão

                    System.out.println("[Erro]");

                    break;
                }
            } else {//se X não e terminal
                //se M(x,a)
                String juncao = Gramatica.GRAMATICA.get(topox + "," + codigo);

                //Retire X da pilha
                pilhaX.pop();

                if (juncao != null && !juncao.isEmpty()) {
                    //coloque Ykyk-1y2y1 na pilha(com y1 no topo)
                    Integer[] Y1y2 = Gramatica.geraDadosCruzamentoTabParsingToken(juncao);
                    for (int i = Y1y2.length - 1; i >= 0; i--) {
                        pilhaX.push(Y1y2[i]);
                    }
                } 
            }
            if((pilhaX.isEmpty() && !pilhaA.isEmpty()) || (!pilhaX.isEmpty() && pilhaA.isEmpty())) {
                System.out.println("[Erro]");
            }
        } while (!pilhaX.isEmpty() && !pilhaA.isEmpty());   
    }
}
