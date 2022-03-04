
import java.util.Stack<String>;

public class AnalisadorLexico {

    public static Stack<String> gerarTokens(String program) {
        Stack<String> clean = new Stack<String>();//pilha que recebera as string
        Stack<String> result = new Stack<String>();//Aux que retornara a pilha ordenada corretamente
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
