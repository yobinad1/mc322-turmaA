package lab05;

public abstract class Cliente {
	private String nome;
	private String telefone;
	private String endereco;
	private String email;

	// Construtora
	public Cliente(String nome, String telefone, String endereco, String email ) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}
	
	// Getters e setters	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Retorna uma string CPF/CNPJ
	public abstract String strDocumento();
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("Cliente - %s:\n- Telefone: %s\n- Endereco: %s\n- E-mail: %s",
	            nome, telefone, endereco, email));
	    return sb.toString();
	}
}
