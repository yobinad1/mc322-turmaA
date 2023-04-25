package lab03;

import java.text.*;
import java.util.*;

public class ClientePF extends Cliente {
	private String genero;
	private String CPF;
	private String educacao;
	private String classeEconomica;
	private SimpleDateFormat dataLicenca;
	private SimpleDateFormat dataNascimento;
	
	// Construtora
	public ClientePF(String nome, String endereco, List<Veículo> listaVeiculos, String genero, String CPF, String educacao, String classeEconomica, SimpleDateFormat dataLicenca, SimpleDateFormat dataNascimento) {
		// Herdeira da classe mãe Cliente
		super(nome, endereco, listaVeiculos);
		this.genero = genero;
		this.CPF = CPF;
		this.educacao = educacao;
		this.classeEconomica = classeEconomica;	
		this.dataLicenca = dataLicenca;
		this.dataNascimento = dataNascimento;
	}
	
	// Getters e Setters
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public String getEducacao() {
		return educacao;
	}
	
	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}
	
	public String getClasseEconomica() {
		return classeEconomica;
	}
	
	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	public SimpleDateFormat getDataLicenca() {
		return dataLicenca;
	}
	
	public void setDataLicenca(SimpleDateFormat dataLicenca) {
		this.dataLicenca = dataLicenca;
	}
	
	public SimpleDateFormat getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(SimpleDateFormat dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String toString() {
		return "ClientePF [genero=" + genero + ", CPF=" + CPF + ", educacao=" + educacao + ", classeEconomica=" + classeEconomica + ", dataLicenca=" + dataLicenca + ", dataNascimento=" + dataNascimento + "]"; 
	}
	
	// Validador de CPF
	public boolean validarCPF(String CPF) {
		
		// Se cpf = nulo, retorna falso. (cpf inválido)
		if (CPF == null) {           
	        return false;
	    }
		
		// Remove tudo que não for número
		CPF = CPF.replaceAll("[^0-9]", "");
	    
	    // Se o tamanho for diferente de 11, retorna falso. (cpf inválido)
	    if (CPF.length() != 11) {
	        return false;
	    }
	    
	    // Verifica o primeiro dígito verificador
	    int soma = 0;
	    
	    for (int i = 0; i < 9; i++) {                           // Pega os 9 primeiros digitos, multiplica o primeiro por 10, segundo por 9, ..., 9 por 2, soma e tira o resto por 11
	        soma += Character.getNumericValue(CPF.charAt(i)) * (10 - i);
	    }
	    
	    int resto = soma % 11;
	    
	    int digito1 = resto < 2 ? 0 : 11 - resto;               // Se o resto for 0 ou 1, retorna 0. Se não, retorna (11 - resto)
	    
	    if (Character.getNumericValue(CPF.charAt(9)) != digito1) {
	        return false;
	    }

	    // Verifica o segundo dígito verificador
	    soma = 0;
	    
	    for (int i = 0; i < 10; i++) {                          // Pega 9 dos 10 dígitos, começando pelo segundo, com a mesma regra de cima, até chegar em 2x o dígito verificador 1
	        soma += Character.getNumericValue(CPF.charAt(i)) * (11 - i);
	    }
	    
	    resto = soma % 11;
	    
	    int digito2 = resto < 2 ? 0 : 11 - resto;               // Se o resto for 0 ou 1, retorna 0. Se não, retorna (11 - resto)
	    
	    // Se o último dígito de fato for diferente do digito verificador 2, então retorna falso. (cpf inválido)
	    if (Character.getNumericValue(CPF.charAt(10)) != digito2) { 
	        return false;
	    }

	    return true;
	}
}
