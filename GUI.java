/*
 * Disciplina Programação Orientada a Objetos
 * Autores Luís Tendai RA 204585
 * João Victor Tiodoro RA
 * Mateus Alves RA 201980
 * Atividade Final 3EC
 * Data 13/06/2024
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {
    private ControleDePilha controleDePilha;
    private JPanel mainPanel;
    private JComboBox<TipoLista> tipoListaComboBox;
    private JButton chamarButton;
    private JButton listarButton;
    private JTextPane displayArea;
    private JButton atenderButton;
    private JButton buttonInserir;
    private JLabel consultorioText;

    public GUI() {
        controleDePilha = new ControleDePilha();

        setContentPane(mainPanel);
        setTitle("Senhas do Consultório");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String[] opcoes = {"Sim","Não"};
                int confirm = JOptionPane.showOptionDialog(
                        null,
                        "Tem certeza que deseja fechar a aplicação? As senhas serão perdidas!",
                        "Confirmação de Saída",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        UIManager.getIcon("OptionPane.warningIcon"),
                        opcoes, opcoes[1]
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png"));
        setIconImage(image);

        displayArea.setBackground(Color.BLACK);
        DefaultComboBoxModel<TipoLista> model = new DefaultComboBoxModel<>(TipoLista.values());
        tipoListaComboBox.setModel(model);

        buttonInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarSenha();
            }
        });

        chamarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chamarSenha();
            }
        });
        atenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atenderSenha();
            }
        });
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarSenhas();
            }
        });
    }

    private void adicionarSenha() {
        TipoLista tipoLista = (TipoLista) tipoListaComboBox.getSelectedItem();
        String resultado = controleDePilha.inserir(tipoLista);
        JOptionPane.showMessageDialog(mainPanel, resultado, "Consultório Dr. No Problem | CRM 01.892", JOptionPane.INFORMATION_MESSAGE);
    }

    private void chamarSenha() {
        String resultado = controleDePilha.chamar();
        if (resultado.equals("Já existe uma senha chamada que precisa ser atendida.")) {
            JOptionPane.showMessageDialog(null, resultado);
        } else if (resultado.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Não há senhas em espera", "Consultório Dr. No Problem | CRM 01.892", JOptionPane.INFORMATION_MESSAGE);
        } else {
            displayArea.setText(resultado);
        }
    }

    private void atenderSenha() {
        String resultado = controleDePilha.atender();
        if (resultado != null && !resultado.equals("Nenhuma senha chamada para ser atendida.")) {
            JOptionPane.showMessageDialog(null, resultado, "Consultório Dr. No Problem | CRM 01.892", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Não existe senhas chamadas para atender.",
                    "Consultório Dr. No Problem | CRM 01.892", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void listarSenhas() {
        JOptionPane.showMessageDialog(null, controleDePilha.listar(), "Consultório Dr. No Problem | CRM 01.892", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
