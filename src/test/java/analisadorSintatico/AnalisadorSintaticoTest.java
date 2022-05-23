package analisadorSintatico;

import analisadorLexico.AnalisadorLexico;
import model.LexicoReturn;
import model.Linha;
import model.SintaticoReturn;
import org.junit.Test;
import utils.ManipuladorArquivos;

import java.io.File;
import java.util.Queue;

public class AnalisadorSintaticoTest {

    @Test
    public void AnalisadorLexicoTest() {
        File[] files = new File("./Exemplos").listFiles();

        for (File file : files) {
            Queue<Linha> linhas = new ManipuladorArquivos().readerLineByLine(file.getPath());
            LexicoReturn lexicoReturn = new AnalisadorLexico().analisadorLexico(linhas);
            SintaticoReturn sintaticoReturn = new AnalisadorSintatico().analiseSintatica(lexicoReturn.tokens);
            System.out.println("----------------------------" + file.getName() + "----------------------------");
            sintaticoReturn.getNotificacaoConsoles().forEach(not -> System.out.println(not.getMensagem()));
        }
    }
}
