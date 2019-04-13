
import java.util.Stack;

public class AnalisadorLexico {

    public static Stack gerarTokens(String program) {

        Stack clean = new Stack();//pilha que recebera as string
        Stack result = new Stack();//Aux que retornara a pilha ordenada corretamente
        String palavras[] = program.split(" ");//retira somente as palavras da String

        for (String s : palavras) {//loop

            if (!s.trim().isEmpty()) {//verifica se esta fazia

                clean.push(s); //adiciona ao topo da pilha
            }

        }

        while (!clean.isEmpty()) {//loop

            String aux = (String) clean.pop();
            result.push(aux);//inverte a pilha

        }

        return result;

    }
}
