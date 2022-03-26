package view;

import javax.swing.*;

public class Console {
    public JLabel labelConsole;
    public JScrollPane scrollPaneConsole;
    public JTextArea textAreaConsole;

    public Console() {
        labelConsole = new JLabel("Console:");
        labelConsole.setBounds(10, 370, 146, 29);

        scrollPaneConsole = new JScrollPane();
        scrollPaneConsole.setEnabled(false);
        scrollPaneConsole.setBounds(10, 400, 736, 132);

        textAreaConsole = new JTextArea();
        textAreaConsole.setEditable(false);
        scrollPaneConsole.setViewportView(textAreaConsole);
    }
}
