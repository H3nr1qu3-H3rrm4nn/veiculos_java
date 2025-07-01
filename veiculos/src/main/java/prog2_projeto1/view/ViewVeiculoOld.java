package prog2_projeto1.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prog2_projeto1.controller.VeiculoController;
import prog2_projeto1.model.Veiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewVeiculoOld {
    public static void main(String[] args) {
        JFrame tela = new JFrame("Cadastro de Veículos");
        tela.setSize(800, 400);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        tela.add(panel);
        panel.setLayout(null);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 10, 150, 25);
        panel.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(10, 30, 100, 25);
        panel.add(txtNome);

        JLabel lblAno = new JLabel("Ano");
        lblAno.setBounds(10, 60, 150, 25);
        panel.add(lblAno);

        JTextField txtAno = new JTextField();
        txtAno.setBounds(10, 80, 150, 25);
        panel.add(txtAno);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(10, 100, 250, 25);
        panel.add(lblModelo);

        JTextField txtModelo = new JTextField();
        txtModelo.setBounds(10, 120, 250, 25);
        panel.add(txtModelo);

        JLabel lblCor = new JLabel("Cor");
        lblCor.setBounds(10, 140, 250, 25);
        panel.add(lblCor);

        JTextField txtCor = new JTextField();
        txtCor.setBounds(10, 160, 250, 25);
        panel.add(txtCor);

        JLabel lblPlaca = new JLabel("Placa");
        lblPlaca.setBounds(10, 180, 250, 25);
        panel.add(lblPlaca);

        JTextField txtPlaca = new JTextField();
        txtPlaca.setBounds(10, 200, 250, 25);
        panel.add(txtPlaca);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 280, 150, 25);
        panel.add(btnSalvar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(170, 280, 150, 25);
        panel.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(340, 280, 150, 25);
        panel.add(btnExcluir);

        JButton btnLimparCampos = new JButton("Limpar Campos");
        btnLimparCampos.setBounds(510, 280, 150, 25);
        panel.add(btnLimparCampos);

        btnSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculo veiculo = new Veiculo();
                VeiculoController veiculoController = new VeiculoController();
                veiculo.setNome(txtNome.getText());
                veiculo.setAno(Integer.parseInt(txtAno.getText()));
                veiculo.setModelo(txtModelo.getText());
                veiculo.setCor(txtCor.getText());
                veiculo.setPlaca(txtPlaca.getText());
                veiculo.setUnicoDono(true);
                boolean resultado = veiculoController.salvar(veiculo);
                if (resultado) {
                    JOptionPane.showMessageDialog(btnSalvar, e, "Salvo", 0);
                } else {
                    JOptionPane.showMessageDialog(btnSalvar, e, "Erro ao salvar!", 1);
                }
            }
        });

        tela.setVisible(true);
    }
}
