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
        JMenuItem itemCliente = new JMenuItem("Clientes");
        JMenuItem itemVendedor = new JMenuItem("Vendedores");
        JMenuItem itemVendaVeiculo = new JMenuItem("Vendas de Veículos");
        JMenuItem itemRel1 = new JMenuItem("Relatório 1");
        JMenuItem itemRel2 = new JMenuItem("Relatório 2");

        JSeparator separator1 = new JSeparator();
        JSeparator separator2 = new JSeparator();

        menuCadastro.add(itemVeiculo);
        menuCadastro.add(itemCategoria);
        menuCadastro.add(separator1);
        menuCadastro.add(itemCliente);
        menuCadastro.add(itemVendedor);
        menuCadastro.add(separator2);
        menuCadastro.add(itemVendaVeiculo);

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

        itemCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewCliente.main(null);
            }
        });

        itemVendedor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewVendedor.main(null);
            }
        });

        itemVendaVeiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewVendaVeiculo.main(null);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewPrincipal().setVisible(true);
        });
    }
}
