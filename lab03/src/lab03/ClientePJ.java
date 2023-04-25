package lab03;

import java.text.*;
import java.util.*;

public class ClientePJ extends Cliente{
	private String CNPJ;
	private SimpleDateFormat dataFundacao;
	
	// Construtora
	public ClientePJ(String nome, String endereco, List<Veículo> listaVeiculos, String CNPJ, SimpleDateFormat dataFundacao) {
		// Herdeira da classe mãe Cliente
		super(nome, endereco, listaVeiculos);
		this.CNPJ = CNPJ;
		this.dataFundacao = dataFundacao;
	}
	
	// Getters e Setters
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public SimpleDateFormat getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(SimpleDateFormat dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	// Validador de CNPJ
	public boolean isCNPJValido(String CNPJ) {
		CNPJ = CNPJ.replaceAll("\\D", "");

		if (CNPJ.length() != 14) {
		    return false;
		}
		
		int[] digitos = new int[CNPJ.length()];
		
		for (int i = 0; i < CNPJ.length(); i++) {
		    digitos[i] = Character.getNumericValue(CNPJ.charAt(i));
		}
		
		// Cálculo do primeiro dígito verificador
		
		int soma = 0;
		
		soma += digitos[0] * 5;
		soma += digitos[1] * 4;
		soma += digitos[2] * 3;
		soma += digitos[3] * 2;
		soma += digitos[4] * 9;
		soma += digitos[5] * 8;
		soma += digitos[6] * 7;
		soma += digitos[7] * 6;
		soma += digitos[8] * 5;
		soma += digitos[9] * 4;
		soma += digitos[10] * 3;
		soma += digitos[11] * 2;
		
		int resto = soma % 11;
		
		int digitoVerificador1 = resto < 2 ? 0 : 11 - resto; // Se o resto for menor que 2, o digito verificador é 0. Caso contrário, ele é 11 - resto
		
		// Cálculo do segundo dígito verificador
		
		soma = 0;
		soma += digitos[0] * 6;
		soma += digitos[1] * 5;
		soma += digitos[2] * 4;
		soma += digitos[3] * 3;
		soma += digitos[4] * 2;
		soma += digitos[5] * 9;
		soma += digitos[6] * 8;
		soma += digitos[7] * 7;
		soma += digitos[8] * 6;
		soma += digitos[9] * 5;
		soma += digitos[10] * 4;
		soma += digitos[11] * 3;
		soma += digitoVerificador1 * 2;
		
		resto = soma % 11;
		
		int digitoVerificador2 = resto < 2 ? 0 : 11 - resto; // Se o resto for menor que 2, o digito verificador é 0. Caso contrário, ele é 11 - resto
		
		if (digitos[12] != digitoVerificador1 || digitos[13] != digitoVerificador2) {  // Confere se o décimo segundo dígito é o DV1 e se o décimo terceiro digito é o DV2. Caso não seja, retorna false.
		    return false;
		}

		return true;		
	}
	
	public String toString() {
		return "ClientePJ [CNPJ=" + CNPJ + ", dataFundacao=" + dataFundacao + "]"; 
	}
}
