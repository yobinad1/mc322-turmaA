package lab04;

public enum SubmenuOpcoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_SEGURADORA("Cadastrar Seguradora"),
	CADASTRAR_VEICULO("Cadastrar veículo"),
	LISTAR_CLIENTES("Clientes"),
	LISTAR_SINISTROS_SEGURADORA("Sinistros por seguradora"),
	LISTAR_SINISTROS_CLIENTE("Sinistros por cliente"),
	LISTAR_VEICULOS_CLIENTE("Veículos por cliente"),
	LISTAR_VEICULOS_SEGURADORA("Veículos por seguradora"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veículo"),
	EXCLUIR_SINISTRO("Excluir sinistro"),
	VOLTAR("Voltar");
	
	// Atributo
	private final String descricao;
	
	// Construtora
	SubmenuOpcoes(String descricao) {
		this.descricao = descricao;
	}
	
	// Getter
	public String getDescricao() {
		return descricao;
	}
}
