package view;

import model.Linha;
import utils.ManipuladorArquivos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Queue;

public class Buttons {
    public JButton buttonNovoArquivo;
    public JButton buttonSalvar;
    public JButton buttonCompilar;
    public JButton buttonAbrirArquivo;
    public JButton buttonAddRow;
    public JButton buttonRemoveRow;
    public String PATH = "";

    public Buttons (JTextArea textAreaCodigo, PilhaTokens pilhaTokens){

        buttonAbrirArquivo = new JButton();
        buttonAbrirArquivo.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        buttonAbrirArquivo.setToolTipText("Abrir Arquivo");
        buttonAbrirArquivo.setBounds(10, 5, 70, 23);
        buttonAbrirArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jFileChooser.setCurrentDirectory(new File("."));

                try {
                    if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        StringBuilder stringBuilder = new ManipuladorArquivos().readFile(jFileChooser.getSelectedFile().getPath());
                        String texto = stringBuilder.toString();
                        textAreaCodigo.setText(texto);
                        PATH = jFileChooser.getSelectedFile().getPath();
                    }

                } catch (IOException ioException) {
                    // TODO Auto-generated catch block
                    ioException.printStackTrace();
                }
            }
        });

        buttonNovoArquivo = new JButton();
        buttonNovoArquivo.setIcon(UIManager.getIcon("FileView.fileIcon"));
        buttonNovoArquivo.setToolTipText("Novo Arquivo");
        buttonNovoArquivo.setBounds(85, 5, 70, 23);
        buttonNovoArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String codigoFonte = textAreaCodigo.getText();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));

                if(codigoFonte.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"código fonte vazio");

                }else {
                    try {
                         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                            new ManipuladorArquivos().writeFile(codigoFonte,fileChooser.getSelectedFile().getPath());
                            PATH = fileChooser.getSelectedFile().getPath();
                        }
                    } catch (IOException ioException) {
                        // TODO Auto-generated catch block
                        ioException.printStackTrace();
                    }
                }
            }
        });

        buttonSalvar = new JButton();
        buttonSalvar.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        buttonSalvar.setToolTipText("Salvar");
        buttonSalvar.setBounds(160, 5, 70, 23);
        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String codigoFonte = textAreaCodigo.getText();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));

                if(codigoFonte.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Código Fonte vazio");

                }else {
                    try {
                        if (!PATH.isEmpty()){
                            new ManipuladorArquivos().writeFile(codigoFonte,PATH);
                        }
                        else if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                            new ManipuladorArquivos().writeFile(codigoFonte,fileChooser.getSelectedFile().getPath());
                            PATH = fileChooser.getSelectedFile().getPath();
                        }
                    } catch (IOException ioException) {
                        // TODO Auto-generated catch block
                        ioException.printStackTrace();
                    }
                }
            }
        });

        buttonCompilar = new JButton();
        buttonCompilar.setIcon(UIManager.getIcon("FileView.computerIcon"));
        buttonCompilar.setToolTipText("Compilar");
        buttonCompilar.setBounds(235, 5, 70, 23);
        buttonCompilar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(textAreaCodigo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Código Fonte vazio");

                }
                else if (PATH.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Salve o arquivo antes de compilar");
                }
                else {
                    Queue<Linha> linhas = new ManipuladorArquivos().readerLineByLine(PATH);

                    if(!linhas.isEmpty()) {
                        for (Linha linha : linhas) {
                            pilhaTokens.addRow(linha);
                        }
                    }
                }
            }
        });


        buttonAddRow = new JButton();
        buttonAddRow.setText("Adiciona Linha");
        buttonAddRow.setBounds(310, 5, 170, 23);
        buttonAddRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                pilhaTokens.addRow(new Linha("teste",(int)Math.floor(Math.random()*(1000-1+1)+1)));
            }
        });

        buttonRemoveRow = new JButton();
        buttonRemoveRow.setText("Remover Linha");
        buttonRemoveRow.setBounds(485, 5, 170, 23);
        buttonRemoveRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (pilhaTokens.modelToken.getRowCount() == 0){
                    return;
                }
                pilhaTokens.removeRow(0);
            }
        });
    }
}
