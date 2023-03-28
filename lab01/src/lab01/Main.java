package lab01;

public class Main {
	public static void main(String[] args) {
		Cliente cliente = new Cliente("nome", "cpf", "dataNascimento", 10, "endereco");
		Seguradora seguradora = new Seguradora("nome", "telefone", "email", "endereco");
		Veículo veiculo = new Veículo("placa", "marca", "modelo");
		Sinistro sinistro = new Sinistro("data", "endereco");
	}
}
