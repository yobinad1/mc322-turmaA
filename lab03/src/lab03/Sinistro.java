package lab03;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	Seguradora seguradora;
	Veículo veiculo;
	Cliente cliente;
	
	// Gerador de ID aleatório
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	// Construtora
	public Sinistro(String data, String endereco, Seguradora seguradora, Veículo veiculo, Cliente cliente, int id) {
		this.id = id;
		this.data = data;
		this.endereco = endereco;
		this.cliente = cliente;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
	}
	
	// Getters e Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Seguradora getSeguradora() {
		return seguradora;
	}
	
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
	public Veículo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veículo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String toString() {
		return "Sinistro [id=" + id + ", data=" + data + ", endereco=" + endereco + "]"; 
	}
}