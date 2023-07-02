package lab05;
import java.util.*;

public class Validacao {
	// Valida CPF
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
			
			// Valida CNPJ
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
			
			// Valida data
			
			public static boolean validarData(Date data) {
			    int dia = data.getDate();
			    int mes = data.getMonth() + 1;
			    int ano = data.getYear() + 1900;
			    boolean dataValida = true;
			    
			    if (dia < 1 || dia >= 31) {
			        dataValida = false;
			    }
			    
			    if (mes < 1 || mes > 12) {
			        dataValida = false;
			    }
			    
			    if (ano < 0) {
			        dataValida = false;
			    }
			    return dataValida;
			}

			
			// Valida nome
			
			public static boolean validarString(String nome) {
				return nome.matches("[a-zA-Z]+");
			}
}
