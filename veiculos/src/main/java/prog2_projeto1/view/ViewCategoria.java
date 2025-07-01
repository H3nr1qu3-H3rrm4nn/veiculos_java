package prog2_projeto1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import prog2_projeto1.model.Categoria;

public class ViewCategoria {
    public static void main(String[] args) {
        JFrame tela = new JFrame("Cadastro de Categorias");
        tela.setSize(800, 450);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        // Tab principal para adicionar abas
        JTabbedPane tabbedPane = new JTabbedPane();
        tela.add(tabbedPane);

        // Aba 1 - Cadastro
        JPanel panelCadastro = new JPanel();

        panelCadastro.setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, 10, 150, 25);
        panelCadastro.add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(10, 30, 100, 25);
        txtId.setEditable(false);
        panelCadastro.add(txtId);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 60, 150, 25);
        panelCadastro.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(10, 80, 200, 25);
        panelCadastro.add(txtNome);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 350, 150, 25);
        panelCadastro.add(btnSalvar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(170, 350, 150, 25);
        panelCadastro.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(330, 350, 150, 25);
        panelCadastro.add(btnExcluir);

        JButton btnLimparCampos = new JButton("Limpar Campos");
        btnLimparCampos.setBounds(500, 350, 150, 25);
        panelCadastro.add(btnLimparCampos);

        // Adiciona o panelCadastro ao tabbedPane
        tabbedPane.addTab("Cadastro", panelCadastro);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Categoria categoria = new Categoria();
                categoria.setNome(txtNome.getText());
                CategoriaController categoriaController = new CategoriaController();
                boolean resultado = categoriaController.salvar(categoria);
                if (resultado) {
                    JOptionPane.showMessageDialog(tela, "Categoria salvo com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(tela, "Erro ao salvar categoria!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnAlterar.addActionListener(e -> {
            Categoria categoria = new Categoria();
            categoria.setId(Integer.parseInt(txtId.getText()));
            categoria.setNome(txtNome.getText());

            CategoriaController categoriaController = new CategoriaController();
            boolean resultado = categoriaController.alterar(categoria);
            if (resultado) {
                JOptionPane.showMessageDialog(tela, "Categoria alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao alterar categoria!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir o categoria?",
                    "Confirmação", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            Categoria categoria = new Categoria();
            categoria.setId(Integer.parseInt(txtId.getText()));

            CategoriaController categoriaController = new CategoriaController();
            boolean resultado = categoriaController.excluir(categoria);
            if (resultado) {
                JOptionPane.showMessageDialog(tela, "Categoria excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao excluido categoria!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimparCampos.addActionListener(e -> {
            txtId.setText("");
            txtNome.setText("");
        });

        // Aba 2 - Listagem de Categorias
        JPanel panelTabela = new JPanel();

        panelTabela.setLayout(new BoxLayout(panelTabela, BoxLayout.Y_AXIS));

        String[] colunas = { "ID", "Nome" };
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modeloTabela);
        tabela.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panelTabela.add(scrollPane);

        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            String selectedTitle = tabbedPane.getTitleAt(selectedIndex);

            if ("Lista de Categorias".equals(selectedTitle)) {
                modeloTabela.setRowCount(0);
                CategoriaController categoriaController = new CategoriaController();
                List<Categoria> categorias = categoriaController.buscarTodos();
                for (Categoria v : categorias) {
                    modeloTabela.addRow(new Object[] {
                            v.getId(), v.getNome()
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
                    tabbedPane.setSelectedIndex(0);
                }
            }
        });

        // Adiciona o panelTabela ao tabbedPane
        tabbedPane.addTab("Lista de Categorias", panelTabela);

        tela.setVisible(true);
    }
}
