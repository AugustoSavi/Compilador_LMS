package utils;

import model.Linha;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class ManipuladorArquivos {

    public void writeFile(String texto, String path) throws IOException {

        FileOutputStream arq = null;
        PrintStream ps = null;

        try {
            File f = new File(path);
            arq = new FileOutputStream(f);
            try {
                ps = new PrintStream(arq);
                ps.println(texto);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } finally {
            if (arq != null) {
                arq.close();
            }
        }
    }

    public StringBuilder readFile(String path) throws IOException {
        StringBuilder result = new StringBuilder();
        FileInputStream arq = null;
        try {
            File f = new File(path);
            arq = new FileInputStream(f);
            int caracterLido = arq.read();

            while (caracterLido != -1) {
                result.append((char) caracterLido);
                caracterLido = arq.read();
            }
        } finally {
            if (arq != null) {
                arq.close();
            }
        }
        return result;
    }

    public Queue<Linha> readerLineByLine(String path) {
        Queue<Linha> linhas = new LinkedList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            int numberLine = 1;

            while (line != null) {
                linhas.add(new Linha(line, numberLine));
                line = bufferedReader.readLine();
                numberLine++;
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return linhas;
    }
}