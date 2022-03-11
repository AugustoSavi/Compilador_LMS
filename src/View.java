import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {

    private JPanel contentPane;
    private JLabel lblConsole;
    private JLabel lblAreaDeCdigo;
    private JScrollPane scrollPaneAreaDeCodigo;
    private JTextArea areaDeCodigo;
    private JScrollPane scrollPaneConsole;
    private JTextArea consoleTextArea;
    private DefaultListModel defaultListModel = new DefaultListModel();
    private String fontCode;
    private JButton btnSalvar;
    private JButton btnCompilar;
    private JButton btnAbrirArquivo;

    private JTable tableTokens;
    private JTable tableDeriv;
    private JTable tableVariaveis;
    private DefaultTableModel modelToken, modelDeriv, modelVariaveis;
    private JScrollPane scrollPane;
    private JLabel lblPilhaDeDerivaes;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View frame = new View();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View() {
        setTitle("Compilador");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1065, 583);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        lblConsole = new JLabel("Console:");
        lblConsole.setBounds(10, 370, 146, 29);
        contentPane.add(lblConsole);

        scrollPaneAreaDeCodigo = new JScrollPane();
        scrollPaneAreaDeCodigo.setBounds(10, 30, 736, 341);
        contentPane.add(scrollPaneAreaDeCodigo);

        areaDeCodigo = new JTextArea();
        NumeredBorder numeredBorder = new NumeredBorder();
        scrollPaneAreaDeCodigo.setViewportView(numeredBorder.getBorder(areaDeCodigo));

        scrollPaneConsole = new JScrollPane();
        scrollPaneConsole.setEnabled(false);
        scrollPaneConsole.setBounds(10, 400, 736, 132);
        contentPane.add(scrollPaneConsole);

        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        scrollPaneConsole.setViewportView(consoleTextArea);


        btnCompilar = new JButton("Compilar");
        btnCompilar.setBackground(SystemColor.menu);
        btnCompilar.setBounds(219, 5, 131, 23);
        btnCompilar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {}
        });
        contentPane.setLayout(null);

        btnAbrirArquivo = new JButton("Abrir Arquivo");
        btnAbrirArquivo.setBackground(SystemColor.menu);
        btnAbrirArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        });
        btnAbrirArquivo.setBounds(100, 5, 118, 23);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        });
        btnSalvar.setBackground(SystemColor.menu);
        btnSalvar.setBounds(10, 5, 89, 23);

        contentPane.add(btnSalvar);
        contentPane.add(btnAbrirArquivo);
        contentPane.add(btnCompilar);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(750, 30, 293, 500);
        scrollPane_1.setBackground(Color.BLACK);
        contentPane.add(scrollPane_1);

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
        scrollPane_1.setViewportView(tableTokens);

        JLabel lblPilhaDeTokens = new JLabel("Pilha de Tokens");
        lblPilhaDeTokens.setBounds(861, 344, 188, 11);
        contentPane.add(lblPilhaDeTokens);

    }
}
