package prog2_projeto1.view;

import javax.swing.*;
import java.awt.event.*;

public class ViewPrincipal extends JFrame {

    public ViewPrincipal() {
        setTitle("Emprestimo de Veículos");
        setSize(600, 400);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastros");
        JMenu menuRelatorios = new JMenu("Relatorios");

        JMenuItem itemVeiculo = new JMenuItem("Veículos");
        JMenuItem itemCategoria = new JMenuItem("Categorias");
        JMenuItem itemRel1 = new JMenuItem("Relatório 1");
        JMenuItem itemRel2 = new JMenuItem("Relatório 2");

        JSeparator separator = new JSeparator();

        menuCadastro.add(itemVeiculo);
        menuCadastro.add(separator);
        menuCadastro.add(itemCategoria);

        menuRelatorios.add(itemRel1);
        menuRelatorios.add(itemRel2);

        menuBar.add(menuCadastro);
        menuBar.add(menuRelatorios);

        setJMenuBar(menuBar);

        itemVeiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewVeiculo.main(null);
            }
        });

        itemCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewCategoria.main(null);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewPrincipal().setVisible(true);
        });
    }
}
