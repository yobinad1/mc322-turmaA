package lab01;

public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;

	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}
	
	// Getters e Setters
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String setEndereco() {
		return endereco;
	}
	
	public void getEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ",idade=" + idade + ", endereco=" + endereco + "]"; 
	}
	
	// Método que valida se um cpf existe
	public static boolean validarCPF(String cpf) {
		
		// Se cpf = nulo, retorna falso. (cpf inválido)
		if (cpf == null) {           
	        return false;
	    }
		
		// Remove tudo que não for número
	    cpf = cpf.replaceAll("[^0-9]", "");
	    
	    // Se o tamanho for diferente de 11, retorna falso. (cpf inválido)
	    if (cpf.length() != 11) {
	        return false;
	    }
	    
	    // Verifica o primeiro dígito verificador
	    int soma = 0;
	    
	    for (int i = 0; i < 9; i++) {                           // Pega os 9 primeiros digitos, multiplica o primeiro por 10, segundo por 9, ..., 9 por 2, soma e tira o resto por 11
	        soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
	    }
	    
	    int resto = soma % 11;
	    
	    int digito1 = resto < 2 ? 0 : 11 - resto;               // Se o resto for 0 ou 1, retorna 0. Se não, retorna (11 - resto)
	    
	    if (Character.getNumericValue(cpf.charAt(9)) != digito1) {
	        return false;
	    }

	    // Verifica o segundo dígito verificador
	    soma = 0;
	    
	    for (int i = 0; i < 10; i++) {                          // Pega 9 dos 10 dígitos, começando pelo segundo, com a mesma regra de cima, até chegar em 2x o dígito verificador 1
	        soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
	    }
	    
	    resto = soma % 11;
	    
	    int digito2 = resto < 2 ? 0 : 11 - resto;               // Se o resto for 0 ou 1, retorna 0. Se não, retorna (11 - resto)
	    
	    // Se o último dígito de fato for diferente do digito verificador 2, então retorna falso. (cpf inválido)
	    if (Character.getNumericValue(cpf.charAt(10)) != digito2) { 
	        return false;
	    }

	    return true;
	}
}	