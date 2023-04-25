package lab03;

import java.util.*;
import java.text.*;

public class Main {
	public static void main(String[] args) {
		List<Veículo> veicLista = new ArrayList<>();
		
		// Criando um veículo
		System.out.println("Insira um veículo com nome, placa, marca e modelo.");
		Scanner entrada = new Scanner(System.in);
		String placa = entrada.nextLine();
		String marca = entrada.nextLine();
		String modelo = entrada.nextLine();
		int anoFabricacao = entrada.nextInt();
		Veículo veic = new Veículo(placa, marca, modelo, anoFabricacao);
		veicLista.add(veic);
		
		// Criando um cliente PJ
		System.out.println("Insira um CNPJ, data de fundação, nome e endereço.");
		String CNPJ = entrada.nextLine();
		String endereco = entrada.nextLine();
		SimpleDateFormat dataFundacao = new SimpleDateFormat(entrada.nextLine());
		String nome = entrada.nextLine();
		ClientePJ cliente1 = new ClientePJ(nome, endereco, veicLista, CNPJ, dataFundacao);
		
		// Criando um cliente PF
		System.out.println("Insira um CPF, gênero, data de licença, educação, data de nascimento, classe econômica, nome e endereço.");
		String genero = entrada.nextLine();
		String classeEconomica = entrada.nextLine();
		SimpleDateFormat dataLicenca = new SimpleDateFormat(entrada.nextLine());
		String educacao = entrada.nextLine();
		SimpleDateFormat dataNascimento = new SimpleDateFormat(entrada.nextLine());
		String CPF = entrada.nextLine();
		nome = entrada.nextLine();
		endereco = entrada.nextLine();
		ClientePF cliente2 = new ClientePF(nome, endereco, veicLista, genero, CPF, educacao, classeEconomica, dataLicenca, dataNascimento);
		
		List<Cliente> clienteLista = new ArrayList<>();
		clienteLista.add(cliente1);
		clienteLista.add(cliente2);
		
		// Usar o método de validar CPF e CNPJ
		cliente1.isCNPJValido(CNPJ);
		cliente2.validarCPF(CPF);
		
		// Gerando uma seguradora e cadastrando 2 clientes
		System.out.println("Insira um nome, telefone, email e endereço.");
		nome = entrada.nextLine();
		String email = entrada.nextLine();
		String telefone = entrada.nextLine();
		endereco = entrada.nextLine();
		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco, null, clienteLista);
		seguradora.cadastrarCliente(cliente1);
		seguradora.cadastrarCliente(cliente2);
		
		// Gerando um sinistro
		System.out.println("Insira um ID, data e local");
		int id = entrada.nextInt();
		endereco = entrada.nextLine();
		String data = entrada.nextLine();
		Sinistro sinistro = new Sinistro(data, endereco, seguradora, veic, cliente2, id);
		List<Sinistro> sinistroLista = new ArrayList<>();
		sinistroLista.add(sinistro);
		seguradora.setListaSinitros(sinistroLista);
		
		// Chamando o método toString() de cada classe
		
		System.out.println(cliente1.toString());
		System.out.println(cliente2.toString());
		System.out.println(sinistro.toString());
		System.out.println(veic.toString());
		System.out.println(seguradora.toString());
		
		// Chamando todos os métodos da classe Seguradora
		seguradora.listarClientes("PJ");
		seguradora.listarClientes("PF");
		seguradora.listarSinistros();
		seguradora.visualizarSinistro(cliente2.getNome());
		
		// Removendo algum cliente
		seguradora.removerCliente(cliente1.getNome());
		
		entrada.close();
	}
}