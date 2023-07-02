package lab05;

public enum SubmenuOperacoes {
	VOLTAR("Voltar"),

	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_FROTA("Cadastrar frota"),
	CADASTRAR_CONDUTOR("Cadastrar condutor"),

	ADICIONAR_VEICULO_CLIENTE("Adicionar veiculo a uma pessoa fisica"),
	ADICIONAR_FROTA_CLIENTE("Adicionar frota a uma pessoa juridica"),
	ADICIONAR_VEICULO_FROTA("Adicionar veiculo a uma frota"),
	ADICIONAR_CONDUTOR_SEGURO("Adicionar condutor a um seguro"),

	GERAR_SINISTRO("Gerar sinistro"),
	GERAR_SEGURO("Gerar seguro"),

	EXCLUIR_SINISTRO("Excluir sinistro"),
	EXCLUIR_SEGURO("Excluir seguro"),

	IMPRIMIR_SEGURADORA("Visualizar seguradora"),
	IMPRIMIR_CLIENTE("Visualizar cliente"),
	IMPRIMIR_CONDUTOR("Visualizar condutor"),
	IMPRIMIR_SINISTRO("Visualizar sinistro"),
	IMPRIMIR_VEICULO("Visualizar veiculo"),
	IMPRIMIR_SEGURO("Visualizar seguro"),
	IMPRIMIR_FROTA("Visualizar frota");
	
	private final String descricao;
	
	// Construtora
	SubmenuOperacoes(String descricao){
		this.descricao = descricao;
	}
	
	// Getter
	public String getDescricao() {
		return descricao;
	}
}
