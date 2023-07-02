package lab05;
import java.util.*;

public class AppMain {
	private static List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	private static List<Cliente> listaClientes = new ArrayList<Cliente>();
	private static List<Veiculo> listaVeiculosCadastrados = new ArrayList<Veiculo>();
	private static List<Veiculo> listaVeiculosDisponiveis = new ArrayList<Veiculo>();
	private static List<Frota> listaFrotasCadastradas = new ArrayList<Frota>();
	private static List<Frota> listaFrotasDisponiveis = new ArrayList<Frota>();
	private static List<Condutor> listaCondutores = new ArrayList<Condutor>();
	
	private static class Leitura {
	    private static Scanner scanner = new Scanner(System.in);

	    public static int lerInteiro() {
	        String input;
	        do {
	            input = scanner.nextLine();
	            try {
	                return Integer.parseInt(input);
	            } catch (NumberFormatException e) {
	                System.out.println("Erro. Digite um número inteiro válido.");
	            }
	        } while (true);
	    }

	    public static String lerString() {
	        return scanner.nextLine();
	    }

	    public static String lerData() {
	        String input;
	        do {
	            input = Leitura.lerString();
	            if (Validacao.validarData(input)) {
	                return input;
	            }
	            System.out.print("Data inválida ou fora do formato especificado (dd/mm/aaaa).");
	        } while (true);
	    }

	    private static boolean indiceValido(int i, int tam) {
	        return i >= 0 && i < tam;
	    }

	    public static int lerIndice(int tam) {
	        int pos;
	        do {
	            System.out.print("Digite uma opção: ");
	            pos = lerInteiro();
	            if (indiceValido(pos, tam)) {
	                return pos;
	            }
	            System.out.println("Digite um valor válido.");
	        } while (true);
	    }

	    public static String lerNome() {
	        String nome;
	        do {
	            nome = lerString();
	            if (Validacao.validaNome(nome)) {
	                break;
	            }
	            System.out.println("Digite um nome válido.");
	        } while (true);
	        return nome;
	    }

	    public static String lerCNPJ() {
	        String cnpj;
	        do {
	            cnpj = lerString();
	            if (!Validacao.validarCNPJ(CNPJ)) {
	                System.out.println("CNPJ inválido. Tente digitar novamente.");
	            } else {
	                break;
	            }
	        } while (true);
	        return cnpj;
	    }

	    public static String lerCPF() {
	        String cpf;
	        do {
	            cpf = Leitura.lerString();
	            if (!Validacao.validarCPF(CPF)) {
	                System.out.println("CPF inválido. Tente digitar novamente.");
	            } else {
	                break;
	            }
	        } while (true);
	        return cpf;
	    }

	    public static void fechar() {
	        scanner.close();
	    }
	}

	private static void exibirMenuExterno() {
	    MenuOperacoes[] menuOpcoes = MenuOperacoes.values();
	    System.out.println("\n** Menu principal **");
	    for (MenuOperacoes opcao : menuOpcoes) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(opcao.ordinal()).append(" - ").append(opcao.getDescricao());
	        System.out.println(sb.toString());
	    }
	}

	private static void exibirSubmenu(MenuOperacoes op) {
	    SubmenuOperacoes[] submenu = op.getSubmenu();
	    System.out.println("\n** " + op.getDescricao() + " **");
	    for (int i = 0; i < submenu.length; i++) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(i).append(" - ").append(submenu[i].getDescricao());
	        System.out.println(sb.toString());
	    }
	}
	
	private static MenuOperacoes lerOpcaoMenuExterno() {
	    int opUsuario = Leitura.lerIndice(MenuOperacoes.values().length);
	    return MenuOperacoes.values()[opUsuario];
	}
	
	private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes op) {
	    int opUsuario = Leitura.lerIndice(op.getSubmenu().length);
	    return op.getSubmenu()[opUsuario];
	}

	private static void executarOpcaoMenuExterno(MenuOperacoes op) {
	    switch (op) {
	        case CADASTROS:
	        case ADICIONAR:
	        case GERAR:
	        case EXCLUIR:
	        case IMPRIMIR:
	            executarSubmenu(op);
	            break;
	        case CALCULAR_RECEITA:
	            operacaoCalcularReceita();
	            break;
	        case SAIR:
	            break;
	        default:
	        	// Lida com casos inesperados ou realiza ações padrão
	            break;
	    }
	}

	private static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) {
	    System.out.println("\n** " + opSubmenu.getDescricao() + " **");
	    switch (opSubmenu) {
	        case CADASTRAR_SEGURADORA:
	            operacaoCadastrarSeguradora();
	            break;
	        case CADASTRAR_CLIENTE:
	            operacaoCadastrarCliente();
	            break;
	        case CADASTRAR_VEICULO:
	            operacaoCadastrarVeiculo();
	            break;
	        case CADASTRAR_FROTA:
	            operacaoCadastrarFrota();
	            break;
	        case CADASTRAR_CONDUTOR:
	            operacaoCadastrarCondutor();
	            break;
	        case ADICIONAR_VEICULO_CLIENTE:
	            operacaoAddVeiculoCliente();
	            break;
	        case ADICIONAR_FROTA_CLIENTE:
	            operacaoAddFrotaCliente();
	            break;
	        case ADICIONAR_VEICULO_FROTA:
	            operacaoAddVeiculoFrota();
	            break;
	        case ADICIONAR_CONDUTOR_SEGURO:
	            operacaoAddCondutorSeguro();
	            break;
	        case GERAR_SINISTRO:
	            operacaoGerarSinistro();
	            break;
	        case GERAR_SEGURO:
	            operacaoGerarSeguro();
	            break;
	        case EXCLUIR_SEGURO:
	            operacaoExcluirSeguro();
	            break;
	        case EXCLUIR_SINISTRO:
	            operacaoExcluirSinistro();
	            break;
	        case IMPRIMIR_SEGURADORA:
	            operacaoImprimirSeguradora();
	            break;
	        case IMPRIMIR_CLIENTE:
	            operacaoImprimirCliente();
	            break;
	        case IMPRIMIR_VEICULO:
	            operacaoImprimirVeiculo();
	            break;
	        case IMPRIMIR_FROTA:
	            operacaoImprimirFrota();
	            break;
	        case IMPRIMIR_CONDUTOR:
	            operacaoImprimirCondutor();
	            break;
	        case IMPRIMIR_SEGURO:
	            operacaoImprimirSeguro();
	            break;
	        case IMPRIMIR_SINISTRO:
	            operacaoImprimirSinistro();
	            break;
	        case VOLTAR:
	            break;
	        default:
	            // Lida com casos inesperados ou realiza ações padrão
	            break;
	    }
	}
	
	private static void executarSubmenu(MenuOperacoes op) {
	    do {
	        SubmenuOperacoes opSubmenu = lerOpcaoSubmenu(op);
	        if (opSubmenu == SubmenuOperacoes.VOLTAR) {
	            System.out.println("Voltando ao menu principal...");
	            break;
	        }
	        exibirSubmenu(op);
	        executarOpcaoSubMenu(opSubmenu);
	    } while (true);
	}
	
	private static void inicializacao() {
		Seguradora uniSeg = new Seguradora("57.123.654/0001-81", "Uni Seguros",
			"+55(19)1234-5678", "PB", "contato@uniseguros.com");
		
		Seguradora uspSeg = new Seguradora("12.345.678/0001-95", "USP Seguros", "+55(19)8765-4321",
			"SP", "contato@uspseguros.com");
		
		Veiculo v1 = new Veiculo("ABC1D23", "R8", "Audi", 2019);
		Veiculo v2 = new Veiculo("EFG4H56", "Volkswagen", "Gol", 2010);
		Veiculo v3 = new Veiculo("IJK7L89", "Chevrolet", "Corsa", 2013);
		Veiculo v4 = new Veiculo("MNO1P23", "Volkswagen", "Cross", 2020);
		Veiculo v5 = new Veiculo("RST4U56", "Toyota", "Corolla", 2015);
		Veiculo v6 = new Veiculo("VWX7Y89", "Hyundai", "HB20", 2011);
		
		ClientePF ana = new ClientePF("Ana", "+55(21)91234-5678", "Instituto de Artes",
			"ana@gmail.com", "123.456.789-09", "Feminino", "Superior incompleto", Data.stringToDate("04/03/1998"));
		ClientePF beto = new ClientePF("Beto", "+55(19)98279-1583", "Instituto de Musica",
			 "beto@gmail.com", "088.836.147-55", "Masculino", "Cursando superior", Data.stringToDate("21/12/2000"));
		ClientePJ facebook = new ClientePJ("Facebook", "+55(19)96548-7090", "Instituto de Geografia",
			"facebook@gmail.com", "12.345.678/0001-91", Data.stringToDate("16/08/1994"));
		
		Condutor anaC = new Condutor(ana);
		Condutor betoC = new Condutor(beto);
		Condutor carlosC = new Condutor("123.456.789-10", "Carlos", "+55(19)99762-4525",
			"Instituto de Farmacia", "carlos@gmail.com", Data.stringToDate("12/03/2004"));
		
		ClientePJ yahoo = new ClientePJ("Yahoo", "+55(19)91234-5678", "FEQ",
			"yahoo@gmail.com", "12.345.678/0001-23", Data.stringToDate("31/07/1994"));
		
		Frota frotaseila1 = new Frota();
		Frota frotaseila2 = new Frota();
		Frota frotayahoo = new Frota();
		
		listaSeguradoras.add(uniSeg);
		listaSeguradoras.add(uspSeg);
		listaVeiculosCadastrados.add(v1);
		listaVeiculosCadastrados.add(v2);
		listaVeiculosCadastrados.add(v3);
		listaVeiculosCadastrados.add(v4);
		listaVeiculosCadastrados.add(v5);
		listaVeiculosCadastrados.add(v6);
		listaClientes.add(ana);
		listaClientes.add(beto);
		listaClientes.add(yahoo);
		listaClientes.add(facebook);
		listaCondutores.add(anaC);
		listaCondutores.add(betoC);
		listaCondutores.add(carlosC);
		listaFrotasCadastradas.add(frotaseila1);
		listaFrotasCadastradas.add(frotaseila2);
		listaFrotasCadastradas.add(frotayahoo);
		
		facebook.cadastrarFrota(frotaseila1); 	 
		facebook.atualizarFrota(frotaseila1, v1); 
		facebook.atualizarFrota(frotaseila1, v2); 
		facebook.cadastrarFrota(frotaseila2); 	 
		facebook.atualizarFrota(frotaseila2, v3); 
		yahoo.cadastrarFrota(frotayahoo);  
		yahoo.atualizarFrota(frotayahoo, v4);
		
		ana.cadastrarVeiculo(v5);
		beto.cadastrarVeiculo(v6);
		
		uniSeg.gerarSeguro(Data.stringToDate("12/03/2012"), Data.stringToDate("12/03/2015"), v5, ana);
		Seguro seguroAna = uniSeg.getListaSeguros().get(0);
		uniSeg.gerarSeguro(Data.stringToDate("13/04/2020"), Data.stringToDate("13/04/2025"), frotaseila1, facebook);
		Seguro seguroseila1 = uniSeg.getListaSeguros().get(1);
		uspSeg.gerarSeguro(Data.stringToDate("15/06/2019"), Data.stringToDate("15/06/2022"), frotayahoo, yahoo);
		Seguro seguroyahoo = uspSeg.getListaSeguros().get(0);
		uspSeg.gerarSeguro(Data.stringToDate("17/08/2022"), Data.stringToDate("17/08/2023"), v6, beto);
		Seguro seguroBeto = uspSeg.getListaSeguros().get(1);
		uspSeg.gerarSeguro(Data.stringToDate("19/10/2023"), Data.stringToDate("19/10/2024"), frotaseila2, facebook);
		Seguro seguroseila2 = uspSeg.getListaSeguros().get(2);
		
		seguroAna.autorizarCondutor(anaC);
		seguroAna.autorizarCondutor(carlosC);
		seguroseila1.autorizarCondutor(anaC);
		seguroyahoo.autorizarCondutor(betoC);
		seguroBeto.autorizarCondutor(betoC);
		seguroseila2.autorizarCondutor(anaC);
		
		seguroAna.gerarSinistro(Data.stringToDate("12/03/2045"), carlosC, "IFCH");
		Sinistro sinistroAna = seguroAna.getListaSinistros().get(0);
		seguroAna.gerarSinistro(Data.stringToDate("23/05/2056"), anaC, "IC");
		seguroseila1.gerarSinistro(Data.stringToDate("25/06/2078"), anaC, "IFGW");
		seguroBeto.gerarSinistro(Data.stringToDate("31/07/2091"), betoC, "BC");
		
		System.out.println(uniSeg + "\n\n" + v1 + "\n\n" + beto + "\n\n" + facebook + "\n\n" + frotaseila1 +
			"\n\n" + anaC + "\n\n" + seguroBeto + "\n\n" + seguroseila1 + "\n\n" + sinistroAna + "\n");
	}
	
	public static void main(String[] args) {
	    inicializacao(); // Inicializa objetos

	    System.out.println("-------------- Menu --------------");
	    MenuOperacoes opcaoMenu;
	    do {
	        exibirMenu();
	        opcaoMenu = lerOpcaoMenu();
	        executarOpcaoMenu(opcaoMenu);
	    } while (opcaoMenu != MenuOperacoes.SAIR);

	    System.out.println("Saiu do sistema.");
	    Leitura.fechar();
	}

	private static void exibirMenu() {
	    System.out.println("Opções do menu:");
	    System.out.println("1. Listar seguradoras");
	    System.out.println("2. Outra opção do menu");
	    System.out.println("3. Sair");
	}

	private static MenuOperacoes lerOpcaoMenu() {
	    int opcao = Leitura.leInteiro("Digite a opção desejada: ");
	    return MenuOperacoes.valueOf(opcao);
	}

	private static void executarOpcaoMenu(MenuOperacoes opcaoMenu) {
	    switch (opcaoMenu) {
	        case LISTAR_SEGURADORAS:
	            boolean seguradorasListadas = listarSeguradoras();
	            if (!seguradorasListadas) {
	                System.out.println("Não há seguradoras cadastradas.");
	            }
	            break;
	        case OUTRA_OPCAO:
	            // Executar a outra opção do menu
	            break;
	        case SAIR:
	            break;
	    }
	}

	private static void msgOperacaoRealizada(boolean operacaoRealizada) {
	    if (operacaoRealizada) {
	        System.out.println("Operação realizada com sucesso.");
	    } else {
	        System.out.println("Ocorreu um erro. Tente novamente.");
	    }
	}

	private static boolean listarSeguradoras() {
	    if (listaSeguradoras.isEmpty()) {
	        return false;
	    }

	    for (int i = 0; i < listaSeguradoras.size(); i++) {
	        System.out.println(i + ". " + listaSeguradoras.get(i).getNome());
	    }

	    return true;
	}

	private static Seguradora requisitarSeguradora() {
	    if (listaSeguradoras.isEmpty()) {
	        System.out.println("Não há seguradoras cadastradas. Operação abortada.");
	        return null;
	    }

	    System.out.println("Digite o número referente a seguradora:");
	    listarSeguradoras();
	    int posicao = Leitura.lerIndice(listaSeguradoras.size());
	    return listaSeguradoras.get(posicao);
	}

	private static Cliente requisitarCliente(String tipoCliente) {
	    Seguradora seguradoraTemporaria = new Seguradora(null, null, null, null, null);
	    int posicao;
	    seguradoraTemporaria.setListaClientes(listaClientes);
	    System.out.println("Digite o numero referente ao cliente:");
	    if (!seguradoraTemporaria.getListaClientes().isEmpty()) {
	        posicao = Leitura.lerIndice(seguradoraTemporaria.getListaClientes().size()); 
	        return seguradoraTemporaria.getListaClientes().get(posicao); 
	    }
	    System.out.println("Não há clientes cadastrados. Operacao abortada.");
	    return null;
	}

	private static Frota requisitarFrotaCliente(ClientePJ clientePJ) {
	    int posicao;
	    System.out.println("Digite o numero referente a frota:");
	    if (clientePJ.listarFrotas()) {
	        posicao = Leitura.lerIndice(clientePJ.getListaFrota().size());
	        return clientePJ.getListaFrota().get(posicao);
	    }
	    System.out.println("Não há frotas cadastradas/disponiveis. Operação abortada.");
	    return null;
	}

	private static Frota requisitarFrota(List<Frota> listaDeFrotas) {
	    ClientePJ clientePJTemporario = new ClientePJ(null, null, null, null, null, null);
	    clientePJTemporario.setListaFrota(listaDeFrotas);
	    return requisitarFrotaCliente(clientePJTemporario);
	}
	
	private static Veiculo requisitarVeiculo(List<Veiculo> listaVeiculos) {
	    Frota frotaTemporaria = new Frota();
	    int posicao;
	    frotaTemporaria.setListaVeiculos(listaVeiculos);
	    System.out.println("Digite o número referente ao veiculo:");
	    if (!frotaTemporaria.getListaVeiculos().isEmpty()) {
	        posicao = Leitura.lerIndice(frotaTemporaria.getListaVeiculos().size());
	        return frotaTemporaria.getListaVeiculos().get(posicao);
	    }
	    System.out.println("Não há veiculos cadastrados/disponiveis. Operação abortada.");
	    return null;
	}

	private static Condutor requisitarCondutorSeguro(Seguro seguro) {
	    int posicao;
	    System.out.println("Digite o número referente ao condutor:");
	    if (seguro.listarCondutores()) {
	        posicao = Leitura.lerIndice(seguro.getListaCondutores().size());
	        return seguro.getListaCondutores().get(posicao);
	    }
	    System.out.println("Não há condutores cadastrados. Operação abortada.");
	    return null;
	}

	private static Condutor requisitarCondutor(List<Condutor> listaCondutores) {
	    Seguro seguroTemporario = new SeguroPF(null, null, null, null, null);
	    seguroTemporario.setListaCondutores(listaCondutores);
	    return requisitarCondutorSeguro(seguroTemporario);
	}

	private static Seguro requisitarSeguro() {
	    int posicao;
	    Seguradora seguradoraSelecionada = requisitarSeguradora();
	    if (seguradoraSelecionada == null) {
	        return null;
	    }
	    System.out.println("Digite o número referente ao seguro:");
	    if (seguradoraSelecionada.listarSeguros()) {
	        posicao = Leitura.lerIndice(seguradoraSelecionada.getListaSeguros().size());
	        return seguradoraSelecionada.getListaSeguros().get(posicao);
	    }
	    System.out.println("Não há seguros cadastrados. Operacão abortada.");
	    return null;
	}
	
	private static Sinistro requisitarSinistro() {
	    int posicao;
	    Seguro seguroSelecionado = requisitarSeguro();
	    if (seguroSelecionado == null) {
	        return null;
	    }
	    System.out.println("Digite o número referente ao sinistro:");
	    if (!seguroSelecionado.getListaSinistros().isEmpty()) {
	        posicao = Leitura.lerIndice(seguroSelecionado.getListaSinistros().size());
	        return seguroSelecionado.getListaSinistros().get(posicao);
	    }
	    System.out.println("Não há sinistros cadastrados. Operação abortada.");
	    return null;
	}

	private static String[] lerCliente() {
	    String[] atributos = {"nome", "telefone", "endereco", "e-mail"};
	    String[] dadosCliente = new String[4];
	    for (int i = 0; i < atributos.length; i++) {
	        System.out.print("Digite o " + atributos[i] + ": ");
	        if (i == 0) {
	            dadosCliente[i] = Leitura.lerNome();
	        } else {
	            dadosCliente[i] = Leitura.lerString();
	        }
	    }
	    return dadosCliente;
	}

	private static ClientePF lerClientePF(String nome, String telefone, String endereco, String email) {
	    String[] atributosClientePF = {"o cpf", "o gênero", "o nível de educação", "a data de nascimento"};
	    String[] dadosClientePF = new String[4];
	    for (int i = 0; i < atributosClientePF.length; i++) {
	        System.out.print("Digite " + atributosClientePF[i] + ": ");
	        if (i == 0) {
	            dadosClientePF[i] = Leitura.lerCPF();
	        } else if (i == 3) {
	            dadosClientePF[i] = Leitura.lerData();
	        } else {
	            dadosClientePF[i] = Leitura.lerString();
	        }
	    }
	    return new ClientePF(nome, telefone, endereco, email, dadosClientePF[0], dadosClientePF[1], dadosClientePF[2], Data.stringToDate(dadosClientePF[3]));
	}

	private static ClientePJ lerClientePJ(String nome, String telefone, String endereco, String email) {
	    String cnpjSeguradora;
	    String dataFundacao;
	    System.out.print("Digite o CNPJ: ");
	    cnpjSeguradora = Leitura.lerCNPJ();
	    System.out.print("Digite a data de fundação: ");
	    dataFundacao = Leitura.lerString();
	    return new ClientePJ(nome, telefone, endereco, email, cnpjSeguradora, Data.stringToDate(dataFundacao));
	}

	private static Seguradora lerSeguradora() {
	    String[] atributosCliente;
	    String cnpjSeguradora;
	    atributosCliente = lerCliente();
	    System.out.print("Digite o CNPJ: ");
	    cnpjSeguradora = Leitura.lerCNPJ();
	    return new Seguradora(cnpjSeguradora, atributosCliente[0], atributosCliente[1], atributosCliente[2], atributosCliente[3]);
	}
	
	private static void operacaoCadastrarSeguradora() {
	    Seguradora seguradoraCadastrada = lerSeguradora();
	    if (listaSeguradoras.add(seguradoraCadastrada)) {
	        msgOperacaoRealizada(true);
	    } else {
	        System.out.println("A seguradora já está cadastrada");
	    }
	}

	private static void operacaoCadastrarCliente() {
	    String[] atributosCliente;
	    int opcaoCadastro;
	    Cliente novoCliente;
	    Condutor novoCondutor;
	    System.out.println("0 - Cadastrar pessoa fisica\n1 - Cadastrar pessoa juridica");
	    opcaoCadastro = Leitura.lerIndice(2);
	    atributosCliente = lerCliente();
	    if (opcaoCadastro == 0) {
	        novoCliente = lerClientePF(atributosCliente[0], atributosCliente[1], atributosCliente[2], atributosCliente[3]);
	        if (!listaClientes.contains(novoCliente)) {
	            listaClientes.add(novoCliente);
	            novoCondutor = new Condutor((ClientePF) novoCliente);
	            listaCondutores.addIfAbsent(novoCondutor);
	        }
	    } else {
	        novoCliente = lerClientePJ(atributosCliente[0], atributosCliente[1], atributosCliente[2], atributosCliente[3]);
	    }
	    if (listaClientes.add(novoCliente)) {
	        msgOperacaoRealizada(true);
	    } else {
	        System.out.println("O cliente já está cadastrado.");
	    }
	}

	private static Veiculo lerVeiculo() {
	    String[] atributos = {"a placa", "a marca", "o modelo", "o ano de fabricação"};
	    String[] dadosVeiculo = new String[3];
	    int anoFabricacaoVeiculo = 0;
	    for (int i = 0; i < atributos.length; i++) {
	        System.out.print("Digite " + atributos[i] + ": ");
	        if (i != 3) {
	            dadosVeiculo[i] = Leitura.lerString();
	        } else {
	            anoFabricacaoVeiculo = Leitura.lerInteiro();
	        }
	    }
	    return new Veiculo(dadosVeiculo[0], dadosVeiculo[1], dadosVeiculo[2], anoFabricacaoVeiculo);
	}

	private static void operacaoCadastrarVeiculo() {
	    Veiculo veiculo = lerVeiculo();
	    if (listaVeiculosCadastrados.add(veiculo)) {
	        msgOperacaoRealizada(listaVeiculosDisponiveis.add(veiculo));
	    } else {
	        System.out.println("O veículo já está cadastrado.");
	    }
	}
	
	private static void operacaoCadastrarFrota() {
	    Frota novaFrota = new Frota();
	    if (listaFrotasCadastradas.add(novaFrota)) {
	        msgOperacaoRealizada(listaFrotasDisponiveis.add(novaFrota));
	        System.out.println("Code da frota: " + novaFrota.getCode());
	    } else {
	        System.out.println("A frota já está cadastrada.");
	    }
	}

	private static Condutor lerCondutor() {
	    String cpfCondutor, dataNascimento;
	    String[] atributosCondutor;

	    System.out.print("Digite o cpf: ");
	    cpfCondutor = Leitura.lerCPF();
	    atributosCondutor = lerCliente();
	    System.out.print("Digite a data de nascimento: ");
	    dataNascimento = Leitura.lerData();

	    return new Condutor(cpfCondutor, atributosCondutor[0], atributosCondutor[1], atributosCondutor[2],
	            atributosCondutor[3], Data.stringToDate(dataNascimento));
	}

	private static void operacaoCadastrarCondutor() {
	    Condutor novoCondutor = lerCondutor();
	    if (listaCondutores.add(novoCondutor)) {
	        msgOperacaoRealizada(true);
	    } else {
	        System.out.println("O condutor já está cadastrado.");
	    }
	}

	private static void removerVeiculoListaDisponiveis(Veiculo veiculo) {
	    listaVeiculosDisponiveis.remove(veiculo);
	}

	private static void operacaoAddVeiculoCliente() {
	    Cliente cliente = requisitarCliente("PF");
	    if (cliente == null) return;

	    Veiculo veiculoSelecionado = requisitarVeiculo(listaVeiculosDisponiveis);
	    if (veiculoSelecionado == null) return;

	    msgOperacaoRealizada(((ClientePF) cliente).cadastrarVeiculo(veiculoSelecionado));
	    removerVeiculoListaDisponiveis(veiculoSelecionado);
	}

	private static void operacaoAddFrotaCliente() {
	    Cliente cliente = requisitarCliente("PJ");
	    if (cliente == null) return;

	    Frota frotaSelecionada = requisitarFrota(listaFrotasDisponiveis);
	    if (frotaSelecionada == null) return;

	    msgOperacaoRealizada(((ClientePJ) cliente).cadastrarFrota(frotaSelecionada));
	    listaFrotasDisponiveis.remove(frotaSelecionada);
	}

	private static void operacaoAddVeiculoFrota() {
	    Veiculo veiculoSelecionado = requisitarVeiculo(listaVeiculosDisponiveis);
	    if (veiculoSelecionado == null) return;

	    Frota frotaSelecionada = requisitarFrota(listaFrotasCadastradas);
	    if (frotaSelecionada == null) return;

	    msgOperacaoRealizada(frotaSelecionada.addVeiculo(veiculoSelecionado));
	    removerVeiculoListaDisponiveis(veiculoSelecionado);

	    for (Seguradora seguradora : listaSeguradoras) {
	        for (Seguro seguro : seguradora.getSegurosPorFrota(frotaSelecionada)) {
	            seguro.calcularValor();
	        }
	    }
	}

	private static void operacaoAddCondutorSeguro() {	
	    Seguro seguroSelecionado = requisitarSeguro();
	    if (seguroSelecionado == null) return;

	    Condutor condutorSelecionado = requisitarCondutor(listaCondutores);
	    if (condutorSelecionado == null) return;

	    msgOperacaoRealizada(seguroSelecionado.autorizarCondutor(condutorSelecionado));
	}

	private static String[] lerAtributosSeguro() {
	    String[] datasSeguro = new String[2];
	    System.out.print("Digite a data de início do seguro: ");
	    datasSeguro[0] = Leitura.lerData();
	    System.out.print("Digite a data de fim do seguro: ");
	    datasSeguro[1] = Leitura.lerData();
	    return datasSeguro;
	}

	private static int obterUltimoIdSeguro(Seguradora seguradora) {
	    List<Seguro> listaSeguros = seguradora.getListaSeguros();
	    return listaSeguros.get(listaSeguros.size() - 1).getId();
	}

	private static void operacaoGerarSeguro() {
	    Seguradora seguradoraSelecionada = requisitarSeguradora();
	    String[] datasSeguro = lerAtributosSeguro();
	    Cliente clienteSelecionado = requisitarCliente("");
	    if (clienteSelecionado == null) return;

	    Veiculo veiculoSelecionado;
	    Frota frotaSelecionada;

	    if (clienteSelecionado instanceof ClientePF) {
	        veiculoSelecionado = requisitarVeiculo(((ClientePF) clienteSelecionado).getListaVeiculos());
	        if (veiculoSelecionado == null) return;
	        msgOperacaoRealizada(seguradoraSelecionada.gerarSeguro(
	                Data.stringToDate(datasSeguro[0]),
	                Data.stringToDate(datasSeguro[1]),
	                veiculoSelecionado,
	                (ClientePF) clienteSelecionado));
	    } else {
	        frotaSelecionada = requisitarFrotaCliente((ClientePJ) clienteSelecionado);
	        if (frotaSelecionada == null) return;
	        msgOperacaoRealizada(seguradoraSelecionada.gerarSeguro(
	                Data.stringToDate(datasSeguro[0]),
	                Data.stringToDate(datasSeguro[1]),
	                frotaSelecionada,
	                (ClientePJ) clienteSelecionado));
	    }

	    int ultimoIdSeguro = obterUltimoIdSeguro(seguradoraSelecionada);
	    System.out.println("ID do seguro: " + ultimoIdSeguro);
	}

	private static String[] lerAtributosSinistro() {
	    String[] atributosSinistro = new String[2];
	    System.out.print("Digite a data do sinistro: ");
	    atributosSinistro[0] = Leitura.lerData();
	    System.out.print("Digite o endereço do sinistro: ");
	    atributosSinistro[1] = Leitura.lerString();
	    return atributosSinistro;
	}

	private static void operacaoGerarSinistro() {
	    Seguro seguroSelecionado = requisitarSeguro();
	    if (seguroSelecionado == null) return;

	    Condutor condutorSelecionado = requisitarCondutorSeguro(seguroSelecionado);
	    if (condutorSelecionado == null) return;

	    String[] atributosSinistro = lerAtributosSinistro();
	    msgOperacaoRealizada(seguroSelecionado.gerarSinistro(
	            Data.stringToDate(atributosSinistro[0]),
	            condutorSelecionado,
	            atributosSinistro[1]));

	    int ultimoIdSinistro = obterUltimoIdSinistro(seguroSelecionado);
	    System.out.println("ID do sinistro: " + ultimoIdSinistro);
	}

	private static void operacaoImprimirSeguradora() {
	    Seguradora seguradoraSelecionada = requisitarSeguradora();
	    if (seguradoraSelecionada == null) return;

	    System.out.println(seguradoraSelecionada);
	}

	private static void operacaoImprimirCliente() {
	    Cliente clienteSelecionado = requisitarCliente("");
	    if (clienteSelecionado == null) return;

	    System.out.println(clienteSelecionado);
	}

	private static void operacaoImprimirVeiculo() {
	    Veiculo veiculoSelecionado = requisitarVeiculo(listaVeiculosCadastrados);
	    if (veiculoSelecionado == null) return;

	    System.out.println(veiculoSelecionado);
	}

	private static void operacaoImprimirFrota() {
	    Frota frotaSelecionada = requisitarFrota(listaFrotasCadastradas);
	    if (frotaSelecionada == null) return;

	    System.out.println(frotaSelecionada);
	}

	private static void operacaoImprimirCondutor() {
	    Condutor condutorSelecionado = requisitarCondutor(listaCondutores);
	    if (condutorSelecionado == null) return;

	    System.out.println(condutorSelecionado);
	}

	private static void operacaoImprimirSeguro() {
	    Seguro seguroSelecionado = requisitarSeguro();
	    if (seguroSelecionado == null) return;

	    System.out.println(seguroSelecionado);
	}

	private static void operacaoImprimirSinistro() {
	    Sinistro sinistroSelecionado = requisitarSinistro();
	    if (sinistroSelecionado == null) return;

	    System.out.println(sinistroSelecionado);
	}

	private static void operacaoExcluirSeguro() {
	    Seguro seguroSelecionado = requisitarSeguro();
	    if (seguroSelecionado == null) return;

	    msgOperacaoRealizada(seguroSelecionado.getSeguradora().cancelarSeguro(seguroSelecionado));
	}

	private static void operacaoExcluirSinistro() {
	    Sinistro sinistroSelecionado = requisitarSinistro();
	    if (sinistroSelecionado == null) return;

	    msgOperacaoRealizada(sinistroSelecionado.getSeguro().removerSinistro(sinistroSelecionado));
	}

	private static void operacaoCalcularReceita() {
	    Seguradora seguradoraSelecionada = requisitarSeguradora();
	    if (seguradoraSelecionada == null) return;

	    System.out.printf("Receita de %s: R$ %.2f\n", seguradoraSelecionada.getNome(), seguradoraSelecionada.calcularReceita());
	}
}
