package view;

import model.Linha;
import model.Token;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Stack;

public class PilhaTokens {
    public JLabel labelPilhaTokens;
    public JTable tableTokens;
    public DefaultTableModel modelToken;
    public JScrollPane scrollPanePilhaTokens;

    public PilhaTokens() {
        scrollPanePilhaTokens = new JScrollPane();
        scrollPanePilhaTokens.setBounds(750, 30, 293, 500);
        scrollPanePilhaTokens.setBackground(Color.BLACK);

        String colunas[] = {"Linha", "Palavra", "Código"};

        modelToken = new DefaultTableModel(null, colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTokens = new JTable();
        tableTokens.setModel(modelToken);
        tableTokens.getColumnModel().getColumn(0).setResizable(false);
        tableTokens.getColumnModel().getColumn(1).setMinWidth(23);
        scrollPanePilhaTokens.setViewportView(tableTokens);

        labelPilhaTokens = new JLabel("Pilha de Tokens");
        labelPilhaTokens.setBounds(861, 344, 188, 11);
    }

    public void updateLexicTable(Stack tokens) {
        Stack tempStack = (Stack) tokens.clone();

        modelToken.setRowCount(0);

        while (!tempStack.isEmpty()) {
            Linha linha = (Linha) tempStack.pop();
            modelToken.addRow(new Object[]{1, linha.getLinha(), linha.getNumeroLinha()});
        }
    }

    public void addRow(Token token) {
        modelToken.addRow(new Object[]{token.getNumeroLinha(), token.getPalavra(), token.getCodigo()});
    }

    public void removeRow(Integer row) {
        modelToken.removeRow(row);
    }
}
