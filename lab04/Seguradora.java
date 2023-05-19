package lab04;

import java.util.*;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros = new ArrayList<Sinistro>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Integer> listaId = new ArrayList<Integer>();

	// Construtora
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}
	
	// Getters e Setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone( ) {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	
	public void setListaSinitros(List<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public List<Integer> getListaId() {
		return listaId;
	}
	
	public void setListaId(List<Integer> listaId) {
		this.listaId = listaId;
	}
	
	// Método que cadastra um cliente em uma lista de clientes
	public boolean cadastrarCliente(Cliente cliente) {
		int size = listaClientes.size();
		listaClientes.add(cliente);
		
		if (size == listaClientes.size() - 1) {
			System.out.println("Cliente inserido.");
			return true;
		}
		
		else {
			System.out.println("Cliente inválido.");
			return false;
		}
	}
	
	// Método que remove um cliente de uma lista de clientes
	public boolean removerCliente(String nomeRemovido) {
		for (int i = 0; i < listaClientes.size(); i++) {
			if (nomeRemovido == null ? listaClientes.get(i).getNome() == null : nomeRemovido.equals(listaClientes.get(i).getNome())) {
				listaClientes.remove(i);
				System.out.println("Cliente " + nomeRemovido + "removido com sucesso.");
			}
		}
		return true;
	}
	
	// Método que lista todos os clientes de uma lista de clientes
	public void listarClientes(String tipoCliente) {
		for (Cliente cliente : listaClientes) {
			if (cliente instanceof ClientePJ && tipoCliente.equals("PJ")) {
				System.out.println(cliente.toString());
			}
			else if (cliente instanceof ClientePF && tipoCliente.equals("PF")) {
				System.out.println(cliente.toString());
			}
		}
	}
	
	// Método que gera um sinistro aleatório
	public boolean gerarSinistro(Cliente cliente, Veículo veiculo, Seguradora seguradora) {
		Sinistro sinistro;
		
		if (!listaClientes.contains(cliente) || cliente.getListaVeiculos().contains(veiculo)) {
			System.out.println("Não foi possível gerar o sinistro.");
			return false;
		}
		
		System.out.println("Sinistro gerado com sucesso.");
		sinistro = new Sinistro("25/04/2023", "unicamp", seguradora, veiculo, cliente, Sinistro.getRandomNumber(1, 1000));
		listaSinistros.add(sinistro);
		return true;
	}
	
	// Método que visualiza o sinistro
	public boolean visualizarSinistro(String nome) {
		for (Sinistro sinistro : listaSinistros) {
			if (nome.equals(sinistro.cliente.getNome())) {
				System.out.println(sinistro.toString());
			}
		}
		return true;
	}
	
	// Método que lista todos os sinistros de uma lista de sinistros
	public void listarSinistros() {
		for (Sinistro sinistro : listaSinistros) {
			System.out.println(sinistro.toString());
		}
	}
	
	// Método que remove o sinistro sin de listaSinistros
	public boolean removeSinistro(Sinistro sin) {
		boolean removeu = listaSinistros.remove(sin);
		atualizaValoresSeguro();
		return removeu;
	}
	
	// Método que calcula preço de seguro
	public double calcularPrecoSeguroCliente(Cliente cliente) {
		cliente.setValorSeguro(cliente.calculaScore() * (1 + listaSinistros.size()));
	}
	
	
	// Método que calcula receita total da seguradora
	public double calcularReceita() {
		double s = 0;
		for (Cliente clinete: listaClientes) {
			s += cliente.getValorSeguro();
		}
		return s;
	}
	
	public void atualizaValoresSeguro() {
		for (Cliente cliente: listaClientes) {
			calcularPrecoSeguroCliente(cliente);
		}
	}
	
	public boolean transferirSeguro(Cliente clienteT, Cliente clienteR) {
		if (clienteT.equals(clienteR)) {
			return false;
		}
		for (Veiculo veiculo : clienteT.getListaVeiculos()) {
			clienteR.getListaVeiculos().add(veiculo);
		}
		clienteT.getListaVeiculos().clear();
		calcularPrecoSeguroCliente(clienteT);
		calcularPrecoSeguroCliente(clienteR);
		return true;
	}
	
	public void addlistaId(int n) {
		this.listaId.add(n);
	}
	
	public void removelistaId(int n) {
		this.listaId.remove(n);
	}
	
	
	public String toString() {
		return "Seguradora [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco + "]"; 
	}
}