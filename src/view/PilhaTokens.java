package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PilhaTokens {
    public JLabel labelPilhaTokens;
    public JTable tableTokens;
    public DefaultTableModel modelToken;
    public JScrollPane scrollPanePilhaTokens;

    public PilhaTokens(){
        scrollPanePilhaTokens = new JScrollPane();
        scrollPanePilhaTokens.setBounds(750, 30, 293, 500);
        scrollPanePilhaTokens.setBackground(Color.BLACK);

        String colunas[] = { "Código", "Palavra" , "Linha" };

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
}
