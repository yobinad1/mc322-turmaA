package lab04;

import java.util.*;

public class Cliente {
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	private double valorSeguro;

	// Construtora
	public Cliente(String nome, String endereco, double valorSeguro) {
		this.nome = nome;
		this.endereco = endereco;
		this.valorSeguro = valorSeguro;
	}
	
	// Getters e Setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public double getValorSeguro() {
		return valorSeguro;
	}
	
	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}
	
	public String imprimeVeiculos() {
		StringBuilder listadeveiculos = new StringBuilder();
		for (Veiculo veiculo : getListaVeiculos()) {
			listadeveiculos.append(veiculo.toString()).append("\n");
		}
		return listadeveiculos.toString();
	}
	
	public void adicionalistaVeiculos(Veiculo veiculo) {
		return listaVeiculos.add(veiculo);
	}
	
	public void removelistaVeiculos(Veiculo veiculo) {
		return listaVeiculos.remove(veiculo);
	}
	
	public double calculaScore() {
		return CalcSeguro.VALOR_BASE.getFator();
	}
	public String toString() {
		return "Cliente [nome=" + nome + ", endereco=" + endereco + ", Veiculos=" + getListaVeiculos() + ", Valor do Seguro=" + valorSeguro + "]"; 
	}
}