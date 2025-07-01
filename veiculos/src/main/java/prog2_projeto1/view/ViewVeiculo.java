package prog2_projeto1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import prog2_projeto1.controller.CategoriaController;
import prog2_projeto1.controller.VeiculoController;
import prog2_projeto1.model.Categoria;
import prog2_projeto1.model.Veiculo;

public class ViewVeiculo {
    public static void main(String[] args) {
        JFrame tela = new JFrame("Cadastro de Veículos");
        tela.setSize(800, 500);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        JComboBox<Categoria> comboCategoria = new JComboBox<>();
        preencherComboCategorias(comboCategoria);

        // Tab principal para adicionar abas
        JTabbedPane tabbedPane = new JTabbedPane();
        tela.add(tabbedPane);

        // Aba 1 - Cadastro
        JPanel panelCadastro = new JPanel();

        panelCadastro.setLayout(null);

        int y = 10;
        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, y, 150, 25);
        panelCadastro.add(lblId);

        y += 20;
        JTextField txtId = new JTextField();
        txtId.setBounds(10, y, 100, 25);
        txtId.setEditable(false);
        panelCadastro.add(txtId);

        y += 30;
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(10, y, 150, 25);
        panelCadastro.add(lblNome);

        y += 20;
        JTextField txtNome = new JTextField();
        txtNome.setBounds(10, y, 150, 25);
        panelCadastro.add(txtNome);

        y += 30;
        JLabel lblCategoria = new JLabel("Categoria");
        lblCategoria.setBounds(10, y, 150, 25);
        panelCadastro.add(lblCategoria);

        y += 20;
        comboCategoria.setBounds(10, y, 250, 25);
        panelCadastro.add(comboCategoria);

        y += 30;
        JLabel lblAno = new JLabel("Ano");
        lblAno.setBounds(10, y, 150, 25);
        panelCadastro.add(lblAno);

        y += 20;
        JTextField txtAno = new JTextField();
        txtAno.setBounds(10, y, 100, 25);
        panelCadastro.add(txtAno);

        y += 30;
        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(10, y, 250, 25);
        panelCadastro.add(lblModelo);

        y += 20;
        JTextField txtModelo = new JTextField();
        txtModelo.setBounds(10, y, 250, 25);
        panelCadastro.add(txtModelo);

        y += 30;
        JLabel lblCor = new JLabel("Cor");
        lblCor.setBounds(10, y, 250, 25);
        panelCadastro.add(lblCor);

        y += 20;
        JTextField txtCor = new JTextField();
        txtCor.setBounds(10, y, 250, 25);
        panelCadastro.add(txtCor);

        y += 30;
        JLabel lblPlaca = new JLabel("Placa");
        lblPlaca.setBounds(10, y, 250, 25);
        panelCadastro.add(lblPlaca);

        y += 20;
        JTextField txtPlaca = new JTextField();
        txtPlaca.setBounds(10, y, 250, 25);
        panelCadastro.add(txtPlaca);

        y += 40; // mais espaço para os botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, y, 150, 25);
        panelCadastro.add(btnSalvar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(170, y, 150, 25);
        panelCadastro.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(330, y, 150, 25);
        panelCadastro.add(btnExcluir);

        JButton btnLimparCampos = new JButton("Limpar Campos");
        btnLimparCampos.setBounds(500, y, 150, 25);
        panelCadastro.add(btnLimparCampos);

        tabbedPane.addTab("Cadastro", panelCadastro);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculo veiculo = new Veiculo();
                veiculo.setNome(txtNome.getText());
                veiculo.setAno(Integer.parseInt(txtAno.getText()));
                veiculo.setModelo(txtModelo.getText());
                veiculo.setCor(txtCor.getText());
                veiculo.setPlaca(txtPlaca.getText());
                veiculo.setUnicoDono(true);
                veiculo.setCategoria((Categoria) comboCategoria.getSelectedItem());

                VeiculoController veiculoController = new VeiculoController();
                boolean resultado = veiculoController.salvar(veiculo);
                if (resultado) {
                    JOptionPane.showMessageDialog(tela, "Veículo salvo com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(tela, "Erro ao salvar veículo!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnAlterar.addActionListener(e -> {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(Integer.parseInt(txtId.getText()));
            veiculo.setNome(txtNome.getText());
            veiculo.setAno(Integer.parseInt(txtAno.getText()));
            veiculo.setModelo(txtModelo.getText());
            veiculo.setCor(txtCor.getText());
            veiculo.setPlaca(txtPlaca.getText());
            veiculo.setUnicoDono(true);
            veiculo.setCategoria((Categoria) comboCategoria.getSelectedItem());

            VeiculoController veiculoController = new VeiculoController();
            boolean resultado = veiculoController.alterar(veiculo);
            if (resultado) {
                JOptionPane.showMessageDialog(tela, "Veículo alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao alterar veículo!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir o veículo?",
                    "Confirmação", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            Veiculo veiculo = new Veiculo();
            veiculo.setId(Integer.parseInt(txtId.getText()));

            VeiculoController veiculoController = new VeiculoController();
            boolean resultado = veiculoController.excluir(veiculo);
            if (resultado) {
                JOptionPane.showMessageDialog(tela, "Veículo excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao excluido veículo!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimparCampos.addActionListener(e -> {
            txtId.setText("");
            txtNome.setText("");
            txtAno.setText("");
            txtModelo.setText("");
            txtCor.setText("");
            txtPlaca.setText("");
            comboCategoria.setSelectedIndex(0);
        });

        // Aba 2 - Listagem de Veículos
        JPanel panelTabela = new JPanel();

        panelTabela.setLayout(new BoxLayout(panelTabela, BoxLayout.Y_AXIS));

        String[] colunas = { "ID", "Nome", "Categoria", "Ano", "Modelo", 
        "Cor", "Placa" };
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modeloTabela);
        tabela.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panelTabela.add(scrollPane);

        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            String selectedTitle = tabbedPane.getTitleAt(selectedIndex);

            if ("Lista de Veículos".equals(selectedTitle)) {
                modeloTabela.setRowCount(0); // Limpa os dados antigos
                VeiculoController veiculoController = new VeiculoController();
                List<Veiculo> veiculos = veiculoController.buscarTodos();
                for (Veiculo v : veiculos) {
                    modeloTabela.addRow(new Object[] {
                            v.getId(), v.getNome(), v.getCategoria(), v.getAno(), v.getModelo(), v.getCor(),
                            v.getPlaca()
                    });
                }
            }
        });

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int linha = tabela.getSelectedRow();

                    txtId.setText(tabela.getValueAt(linha, 0).toString());
                    txtNome.setText(tabela.getValueAt(linha, 1).toString());
                    comboCategoria.setSelectedItem((Categoria) tabela.getValueAt(linha, 2));
                    txtAno.setText(tabela.getValueAt(linha, 3).toString());
                    txtModelo.setText(tabela.getValueAt(linha, 4).toString());
                    txtCor.setText(tabela.getValueAt(linha, 5).toString());
                    txtPlaca.setText(tabela.getValueAt(linha, 6).toString());
                    tabbedPane.setSelectedIndex(0);
                }
            }
        });

        // Adiciona o panelTabela ao tabbedPane
        tabbedPane.addTab("Lista de Veículos", panelTabela);

        tela.setVisible(true);
    }

    private static void preencherComboCategorias(JComboBox combo) {
        CategoriaController categoriaController = new CategoriaController();

        List<Categoria> categorias = categoriaController.buscarTodos();

        for (Categoria categoria : categorias) {
            combo.addItem(categoria);
        }
    }

}
