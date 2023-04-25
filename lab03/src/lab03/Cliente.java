package lab03;

import java.util.*;

public class Cliente {
	private String nome;
	private String endereco;
	private List<Veículo> listaVeiculos;

	// Construtora
	public Cliente(String nome, String endereco, List<Veículo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = listaVeiculos;
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
	
	public List<Veículo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public void setListaVeiculos(List<Veículo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public String toString() {
		return "Cliente [nome=" + nome + ", endereco=" + endereco + "]"; 
	}
}	