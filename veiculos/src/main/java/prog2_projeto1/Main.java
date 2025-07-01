package prog2_projeto1;

import java.sql.SQLException;

import prog2_projeto1.controller.VeiculoController;
import prog2_projeto1.model.Veiculo;

public class Main {
    public static void main(String[] args) {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome("Opala");
        veiculo.setAno(1999);
        veiculo.setModelo("Não sei");
        veiculo.setCor("Preto");
        veiculo.setPlaca("BBB-5678");
        veiculo.setUnicoDono(false);
        VeiculoController veiculoController = new VeiculoController();
        veiculoController.salvar(veiculo);
        // veiculoController.buscar(1);
    }
}