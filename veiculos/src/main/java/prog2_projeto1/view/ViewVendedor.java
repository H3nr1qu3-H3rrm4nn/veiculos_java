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

import prog2_projeto1.controller.VendedorController;
import prog2_projeto1.model.Vendedor;

public class ViewVendedor {
    public static void main(String[] args) {
        JFrame tela = new JFrame("Cadastro de Vendedores");
        tela.setSize(800, 600);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);

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
        txtNome.setBounds(10, y, 250, 25);
        panelCadastro.add(txtNome);

        y += 30;
        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(10, y, 150, 25);
        panelCadastro.add(lblCpf);

        y += 20;
        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(10, y, 150, 25);
        panelCadastro.add(txtCpf);

        y += 30;
        JLabel lblRg = new JLabel("RG");
        lblRg.setBounds(10, y, 150, 25);
        panelCadastro.add(lblRg);

        y += 20;
        JTextField txtRg = new JTextField();
        txtRg.setBounds(10, y, 150, 25);
        panelCadastro.add(txtRg);

        y += 30;
        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setBounds(10, y, 150, 25);
        panelCadastro.add(lblEndereco);

        y += 20;
        JTextField txtEndereco = new JTextField();
        txtEndereco.setBounds(10, y, 300, 25);
        panelCadastro.add(txtEndereco);

        y += 30;
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(10, y, 150, 25);
        panelCadastro.add(lblTelefone);

        y += 20;
        JTextField txtTelefone = new JTextField();
        txtTelefone.setBounds(10, y, 150, 25);
        panelCadastro.add(txtTelefone);

        y += 30;
        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(10, y, 150, 25);
        panelCadastro.add(lblEmail);

        y += 20;
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(10, y, 250, 25);
        panelCadastro.add(txtEmail);

        y += 30;
        JLabel lblSalario = new JLabel("Salário");
        lblSalario.setBounds(10, y, 150, 25);
        panelCadastro.add(lblSalario);

        y += 20;
        JTextField txtSalario = new JTextField();
        txtSalario.setBounds(10, y, 150, 25);
        panelCadastro.add(txtSalario);

        y += 30;
        JLabel lblComissao = new JLabel("Comissão (%)");
        lblComissao.setBounds(10, y, 150, 25);
        panelCadastro.add(lblComissao);

        y += 20;
        JTextField txtComissao = new JTextField();
        txtComissao.setBounds(10, y, 150, 25);
        panelCadastro.add(txtComissao);

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
                Vendedor vendedor = new Vendedor();
                vendedor.setNome(txtNome.getText());
                vendedor.setCpf(txtCpf.getText());
                vendedor.setRg(txtRg.getText());
                vendedor.setEndereco(txtEndereco.getText());
                vendedor.setTelefone(txtTelefone.getText());
                vendedor.setEmail(txtEmail.getText());
                vendedor.setSalario(Double.parseDouble(txtSalario.getText()));
                vendedor.setComissao(Double.parseDouble(txtComissao.getText()));

                VendedorController vendedorController = new VendedorController();
                boolean resultado = vendedorController.salvar(vendedor);
                if (resultado) {
                    JOptionPane.showMessageDialog(tela, "Vendedor salvo com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(tela, "Erro ao salvar vendedor!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAlterar.addActionListener(e -> {
            Vendedor vendedor = new Vendedor();
            vendedor.setId(Integer.parseInt(txtId.getText()));
            vendedor.setNome(txtNome.getText());
            vendedor.setCpf(txtCpf.getText());
            vendedor.setRg(txtRg.getText());
            vendedor.setEndereco(txtEndereco.getText());
            vendedor.setTelefone(txtTelefone.getText());
            vendedor.setEmail(txtEmail.getText());
            vendedor.setSalario(Double.parseDouble(txtSalario.getText()));
            vendedor.setComissao(Double.parseDouble(txtComissao.getText()));

            VendedorController vendedorController = new VendedorController();
            boolean resultado = vendedorController.alterar(vendedor);
            if (resultado) {
                JOptionPane.showMessageDialog(tela, "Vendedor alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao alterar vendedor!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir o vendedor?",
                    "Confirmação", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            Vendedor vendedor = new Vendedor();
            vendedor.setId(Integer.parseInt(txtId.getText()));

            VendedorController vendedorController = new VendedorController();
            boolean resultado = vendedorController.excluir(vendedor);
            if (resultado) {
                JOptionPane.showMessageDialog(tela, "Vendedor excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao excluir vendedor!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimparCampos.addActionListener(e -> {
            txtId.setText("");
            txtNome.setText("");
            txtCpf.setText("");
            txtRg.setText("");
            txtEndereco.setText("");
            txtTelefone.setText("");
            txtEmail.setText("");
            txtSalario.setText("");
            txtComissao.setText("");
        });

        // Aba 2 - Listagem de Vendedores
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(new BoxLayout(panelTabela, BoxLayout.Y_AXIS));

        String[] colunas = { "ID", "Nome", "CPF", "Telefone", "E-mail", "Salário", "Comissão" };
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modeloTabela);
        tabela.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panelTabela.add(scrollPane);

        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            String selectedTitle = tabbedPane.getTitleAt(selectedIndex);

            if ("Lista de Vendedores".equals(selectedTitle)) {
                modeloTabela.setRowCount(0); // Limpa os dados antigos
                VendedorController vendedorController = new VendedorController();
                List<Vendedor> vendedores = vendedorController.buscarTodos();
                for (Vendedor v : vendedores) {
                    modeloTabela.addRow(new Object[] {
                            v.getId(), v.getNome(), v.getCpf(), v.getTelefone(), 
                            v.getEmail(), v.getSalario(), v.getComissao()
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
                    txtCpf.setText(tabela.getValueAt(linha, 2).toString());
                    txtTelefone.setText(tabela.getValueAt(linha, 3).toString());
                    txtEmail.setText(tabela.getValueAt(linha, 4).toString());
                    txtSalario.setText(tabela.getValueAt(linha, 5).toString());
                    txtComissao.setText(tabela.getValueAt(linha, 6).toString());

                    // Mudar para a aba de cadastro
                    tabbedPane.setSelectedIndex(0);
                }
            }
        });

        // Adiciona o panelTabela ao tabbedPane
        tabbedPane.addTab("Lista de Vendedores", panelTabela);

        tela.setVisible(true);
    }
}
