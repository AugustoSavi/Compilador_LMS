package analisadorLexico;

import analisadorSintatico.AnalisadorSintatico;
import model.LexicoReturn;
import model.Linha;
import model.SintaticoReturn;
import org.junit.Assert;
import org.junit.Test;
import utils.ManipuladorArquivos;

import java.io.File;
import java.util.Queue;

public class SintaticoLexicoTest {

    @Test
    public void AnalisadorLexicoTest(){
        File[] files = new File("./Exemplos").listFiles();

        for (File file: files){
            if (!file.getName().endsWith("txt")) {
                Queue<Linha> linhas = new ManipuladorArquivos().readerLineByLine(file.getPath());
                LexicoReturn lexicoReturn =  new AnalisadorLexico().analisadorLexico(linhas);
                if (lexicoReturn.notificacaoConsoles.size() > 1 ) {
                    System.out.println("----------------------------" + file.getName() + "----------------------------");
                    lexicoReturn.getNotificacaoConsoles().forEach(not -> System.out.println(not.getMensagem()));
                    Assert.assertEquals(1,lexicoReturn.notificacaoConsoles.size());
                }
                SintaticoReturn sintaticoReturn = new AnalisadorSintatico().analiseSintatica(lexicoReturn.tokens);
                if (sintaticoReturn.notificacaoConsoles.size() > 1 ) {
                    System.out.println("----------------------------" + file.getName() + "----------------------------");
                    sintaticoReturn.getNotificacaoConsoles().forEach(not -> System.out.println(not.getMensagem()));
                    Assert.assertEquals(1,sintaticoReturn.notificacaoConsoles.size());
                }
            }
        }
    }
}
