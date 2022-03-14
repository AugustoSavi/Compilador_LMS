package view;

import javax.swing.*;

public class Codigo {
    public JScrollPane scrollPaneAreaCodigo;
    public JTextArea textAreaCodigo;

    public Codigo(){
        scrollPaneAreaCodigo = new JScrollPane();
        scrollPaneAreaCodigo.setEnabled(false);
        scrollPaneAreaCodigo.setBounds(10, 30, 736, 341);

        textAreaCodigo = new JTextArea();
        textAreaCodigo.setEditable(true);
        NumeredBorder numeredBorder = new NumeredBorder();
        scrollPaneAreaCodigo.setViewportView(numeredBorder.getBorder(textAreaCodigo));
    }
}
