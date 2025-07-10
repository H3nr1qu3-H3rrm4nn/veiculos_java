package prog2_projeto1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

import prog2_projeto1.controller.ClienteController;
import prog2_projeto1.controller.VeiculoController;
import prog2_projeto1.controller.VendaVeiculoController;
import prog2_projeto1.controller.VendedorController;
import prog2_projeto1.model.Cliente;
import prog2_projeto1.model.Veiculo;
import prog2_projeto1.model.VendaVeiculo;
import prog2_projeto1.model.Vendedor;

public class ViewVendaVeiculo {
    public static void main(String[] args) {
        JFrame tela = new JFrame("Cadastro de Vendas de Veículos");
        tela.setSize(900, 650);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        // Combos para relacionamentos
        JComboBox<Vendedor> comboVendedor = new JComboBox<>();
        JComboBox<Cliente> comboCliente = new JComboBox<>();
        JComboBox<Veiculo> comboVeiculo = new JComboBox<>();
        
        preencherCombos(comboVendedor, comboCliente, comboVeiculo);

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
        JLabel lblVendedor = new JLabel("Vendedor");
        lblVendedor.setBounds(10, y, 150, 25);
        panelCadastro.add(lblVendedor);

        y += 20;
        comboVendedor.setBounds(10, y, 300, 25);
        panelCadastro.add(comboVendedor);

        y += 30;
        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, y, 150, 25);
        panelCadastro.add(lblCliente);

        y += 20;
        comboCliente.setBounds(10, y, 300, 25);
        panelCadastro.add(comboCliente);

        y += 30;
        JLabel lblVeiculo = new JLabel("Veículo");
        lblVeiculo.setBounds(10, y, 150, 25);
        panelCadastro.add(lblVeiculo);

        y += 20;
        comboVeiculo.setBounds(10, y, 300, 25);
        panelCadastro.add(comboVeiculo);

        y += 30;
        JLabel lblDataVenda = new JLabel("Data da Venda (AAAA-MM-DD)");
        lblDataVenda.setBounds(10, y, 200, 25);
        panelCadastro.add(lblDataVenda);

        y += 20;
        JTextField txtDataVenda = new JTextField();
        txtDataVenda.setBounds(10, y, 150, 25);
        panelCadastro.add(txtDataVenda);

        y += 30;
        JLabel lblValorDesconto = new JLabel("Valor do Desconto");
        lblValorDesconto.setBounds(10, y, 150, 25);
        panelCadastro.add(lblValorDesconto);

        y += 20;
        JTextField txtValorDesconto = new JTextField();
        txtValorDesconto.setBounds(10, y, 150, 25);
        panelCadastro.add(txtValorDesconto);

        y += 30;
        JLabel lblValorFinal = new JLabel("Valor Final");
        lblValorFinal.setBounds(10, y, 150, 25);
        panelCadastro.add(lblValorFinal);

        y += 20;
        JTextField txtValorFinal = new JTextField();
        txtValorFinal.setBounds(10, y, 150, 25);
        panelCadastro.add(txtValorFinal);

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

        JButton btnAtualizarCombos = new JButton("Atualizar Combos");
        btnAtualizarCombos.setBounds(670, y, 150, 25);
        panelCadastro.add(btnAtualizarCombos);

        tabbedPane.addTab("Cadastro", panelCadastro);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VendaVeiculo venda = new VendaVeiculo();
                    venda.setVendedor((Vendedor) comboVendedor.getSelectedItem());
                    venda.setCliente((Cliente) comboCliente.getSelectedItem());
                    venda.setVeiculo((Veiculo) comboVeiculo.getSelectedItem());
                    
                    if (!txtDataVenda.getText().isEmpty()) {
                        venda.setDataVenda(LocalDate.parse(txtDataVenda.getText()));
                    }
                    
                    venda.setValorDesconto(Double.parseDouble(txtValorDesconto.getText()));
                    venda.setValorFinal(Double.parseDouble(txtValorFinal.getText()));

                    VendaVeiculoController vendaController = new VendaVeiculoController();
                    boolean resultado = vendaController.salvar(venda);
                    if (resultado) {
                        JOptionPane.showMessageDialog(tela, "Venda salva com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(tela, "Erro ao salvar venda!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(tela, "Erro: " + ex.getMessage(), "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAlterar.addActionListener(e -> {
            try {
                VendaVeiculo venda = new VendaVeiculo();
                venda.setId(Integer.parseInt(txtId.getText()));
                venda.setVendedor((Vendedor) comboVendedor.getSelectedItem());
                venda.setCliente((Cliente) comboCliente.getSelectedItem());
                venda.setVeiculo((Veiculo) comboVeiculo.getSelectedItem());
                
                if (!txtDataVenda.getText().isEmpty()) {
                    venda.setDataVenda(LocalDate.parse(txtDataVenda.getText()));
                }
                
                venda.setValorDesconto(Double.parseDouble(txtValorDesconto.getText()));
                venda.setValorFinal(Double.parseDouble(txtValorFinal.getText()));

                VendaVeiculoController vendaController = new VendaVeiculoController();
                boolean resultado = vendaController.alterar(venda);
                if (resultado) {
                    JOptionPane.showMessageDialog(tela, "Venda alterada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(tela, "Erro ao alterar venda!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(tela, "Erro: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir a venda?",
                    "Confirmação", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            VendaVeiculo venda = new VendaVeiculo();
            venda.setId(Integer.parseInt(txtId.getText()));

            VendaVeiculoController vendaController = new VendaVeiculoController();
            boolean resultado = vendaController.excluir(venda);
            if (resultado) {
                JOptionPane.showMessageDialog(tela, "Venda excluída com sucesso!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao excluir venda!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimparCampos.addActionListener(e -> {
            txtId.setText("");
            txtDataVenda.setText("");
            txtValorDesconto.setText("");
            txtValorFinal.setText("");
            comboVendedor.setSelectedIndex(0);
            comboCliente.setSelectedIndex(0);
            comboVeiculo.setSelectedIndex(0);
        });

        btnAtualizarCombos.addActionListener(e -> {
            preencherCombos(comboVendedor, comboCliente, comboVeiculo);
        });

        // Aba 2 - Listagem de Vendas
        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(new BoxLayout(panelTabela, BoxLayout.Y_AXIS));

        String[] colunas = { "ID", "Vendedor", "Cliente", "Veículo", "Data Venda", "Valor Desconto", "Valor Final" };
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modeloTabela);
        tabela.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panelTabela.add(scrollPane);

        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            String selectedTitle = tabbedPane.getTitleAt(selectedIndex);

            if ("Lista de Vendas".equals(selectedTitle)) {
                modeloTabela.setRowCount(0); // Limpa os dados antigos
                VendaVeiculoController vendaController = new VendaVeiculoController();
                List<VendaVeiculo> vendas = vendaController.buscarTodos();
                for (VendaVeiculo v : vendas) {
                    modeloTabela.addRow(new Object[] {
                            v.getId(), 
                            v.getVendedor() != null ? v.getVendedor().getNome() : "N/A",
                            v.getCliente() != null ? v.getCliente().getNome() : "N/A",
                            v.getVeiculo() != null ? v.getVeiculo().getNome() : "N/A",
                            v.getDataVenda(),
                            v.getValorDesconto(),
                            v.getValorFinal()
                    });
                }
            }
        });

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int linha = tabela.getSelectedRow();

                    txtId.setText(tabela.getValueAt(linha, 0).toString());
                    txtDataVenda.setText(tabela.getValueAt(linha, 4).toString());
                    txtValorDesconto.setText(tabela.getValueAt(linha, 5).toString());
                    txtValorFinal.setText(tabela.getValueAt(linha, 6).toString());

                    // Mudar para a aba de cadastro
                    tabbedPane.setSelectedIndex(0);
                }
            }
        });

        // Adiciona o panelTabela ao tabbedPane
        tabbedPane.addTab("Lista de Vendas", panelTabela);

        tela.setVisible(true);
    }

    private static void preencherCombos(JComboBox<Vendedor> comboVendedor, 
                                       JComboBox<Cliente> comboCliente, 
                                       JComboBox<Veiculo> comboVeiculo) {
        // Limpar combos
        comboVendedor.removeAllItems();
        comboCliente.removeAllItems();
        comboVeiculo.removeAllItems();

        // Preencher combo vendedores
        VendedorController vendedorController = new VendedorController();
        List<Vendedor> vendedores = vendedorController.buscarTodos();
        for (Vendedor vendedor : vendedores) {
            comboVendedor.addItem(vendedor);
        }

        // Preencher combo clientes
        ClienteController clienteController = new ClienteController();
        List<Cliente> clientes = clienteController.buscarTodos();
        for (Cliente cliente : clientes) {
            comboCliente.addItem(cliente);
        }

        // Preencher combo veículos
        VeiculoController veiculoController = new VeiculoController();
        List<Veiculo> veiculos = veiculoController.buscarTodos();
        for (Veiculo veiculo : veiculos) {
            comboVeiculo.addItem(veiculo);
        }
    }
}
