package lab04;

public enum MenuOpcoes {
	CADASTROS("Cadastros", new SubmenuOpcoes[] {
			SubmenuOpcoes.CADASTRAR_CLIENTE,
			SubmenuOpcoes.CADASTRAR_SEGURADORA,
			SubmenuOpcoes.CADASTRAR_VEICULO,
			SubmenuOpcoes.VOLTAR
	}),
	
	LISTAR("Listar", new SubmenuOpcoes[] {
			SubmenuOpcoes.LISTA_CLIENTES,
			SubmenuOpcoes.LISTA_SINISTROS_SEGURADORA,
			SubmenuOpcoes.LISTA_SINISTROS_CLIENTE,
			SubmenuOpcoes.LISTA_VEICULOS_CLIENTE,
			SubmenuOpcoes.LISTA_VEICULOS_SEGURADORA,
			SubmenuOpcoes.VOLTAR
	}),
	
	EXCLUIR("Excluir", new SubmenuOpcoes[] {
			SubmenuOpcoes.EXCLUIR_CLIENTE,
			SubmenuOpcoes.EXCLUIR_VEICULO,
			SubmenuOpcoes.EXCLUIR_SINISTRO,
			SubmenuOpcoes.VOLTAR
	}),
	
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOpcoes[] {
			SubmenuOpcoes.VOLTAR}),
	
	TRANSFERIR_SEGURO("Transferir Seguro", new SubmenuOpcoes[] {
			SubmenuOpcoes.VOLTAR}),
	
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOpcoes[] {
			SubmenuOpcoes.VOLTAR}),
	
	SAIR("Sair", new SubmenuOpcoes[] {});
	
	// Atributos
	private final String descricao;
	private final SubmenuOpcoes[] Submenu;
	
	// Construtora
	MenuOpcoes(String descricao, SubmenuOpcoes[] Submenu) {
		this.descricao = descricao;
		this.Submenu = Submenu;
	}
	
	// Getters
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOpcoes[] getSubmenu() {
		return Submenu;
	}	
}
