package lab04;

import java.text.*;
import java.util.*;

public class AppMain {
	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();
	
	public static ClientePF coletaInfoPF(Scanner entry) {
	    String nome;
	    String endereco;
	    String CPF;
	    String genero;
	    String dataN;
	    String dataL;
	    String educacao;
	    String classeEconomica;
	    Date dataLicenca;
	    Date dataNascimento;
	    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

	    do {
	        System.out.println("Nome: ");
	        nome = entry.nextLine();
	    } while (!Validacao.validarString(nome));

	    System.out.println("Endereço: ");
	    endereco = entry.nextLine();

	    do {
	        System.out.println("CPF: ");
	        CPF = entry.nextLine();
	    } while (!Validacao.validarCPF(CPF));

	    do {
	        System.out.println("Gênero: ");
	        genero = entry.nextLine();
	    } while (!Validacao.validarString(genero));

	    System.out.println("Data Licença: ");
	    dataL = entry.nextLine();

	    try {
	        dataLicenca = formatoData.parse(dataL);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null; 
	    }

	    do {
	        System.out.println("Educação: ");
	        educacao = entry.nextLine();
	    } while (!Validacao.validarString(educacao));

	    System.out.println("Data Nascimento: ");
	    dataN = entry.nextLine();

	    try {
	        dataNascimento = formatoData.parse(dataN);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }

	    do {
	        System.out.println("Classe econômica: ");
	        classeEconomica = entry.nextLine();
	    } while (!Validacao.validarString(classeEconomica));

	    ClientePF clientePF = new ClientePF(nome, endereco, genero, CPF, educacao, classeEconomica, dataLicenca, dataNascimento, 0);
	    return clientePF;
	}

	
	public static ClientePJ coletaInfoPJ(Scanner entry) {
	    String nome;
	    String endereco;
	    String CNPJ;
	    String data;
	    Date dataFundacao = null;
	    int qtdeFuncionarios;
	    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

	    do {
	        System.out.println("Nome: ");
	        nome = entry.nextLine();
	    } while (!Validacao.validarString(nome));

	    System.out.println("Endereço: ");
	    endereco = entry.nextLine();

	    do {
	        System.out.println("CNPJ: ");
	        CNPJ = entry.nextLine();
	    } while (!Validacao.validarCNPJ(CNPJ));

	    System.out.println("Data fundação: ");
	    data = entry.nextLine();

	    try {
	        formatoData.setLenient(false);
	        dataFundacao = formatoData.parse(data);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    do {
	        System.out.println("Quantidade de funcionários: ");
	        qtdeFuncionarios = entry.nextInt();
	    } while (qtdeFuncionarios < 0);

	    ClientePJ clientePJ = new ClientePJ(nome, endereco, CNPJ, dataFundacao, qtdeFuncionarios, 0);
	    return clientePJ;
	}

	public static Veiculo coletainfoVeiculo(Scanner entry) {
		String placa;
		String marca;
		String modelo;
		int anoFabricacao;
		
		System.out.println("Placa: ");
		placa = entry.nextLine();
		
		System.out.println("Marca: ");
		marca = entry.nextLine();
		
		System.out.println("Modelo: ");
		modelo = entry.nextLine();
		
		do {
			System.out.println("Ano Fabricação: ");
			anoFabricacao = entry.nextInt();
		} while (anoFabricacao > 0);
		
		Veiculo carro = new Veiculo(placa, marca, modelo, anoFabricacao);
		return carro;
	}
	
	public static Seguradora coletainfoSeguradora(Scanner entry) {
		String nome;
		String telefone;
		String email;
		String endereco;
		
		do {
			System.out.println("Nome: ");
			nome = entry.nextLine();
		} while (!Validacao.validarString(nome));
		
		System.out.println("Telefone: ");
		telefone = entry.nextLine();
		
		System.out.println("Email: ");
		email = entry.nextLine();
		
		System.out.println("Endereço: ");
		endereco = entry.nextLine();
		
		Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
		return seguradora;
	}
	
	public static int encontraSeguradora(Scanner entry) {
	    System.out.println("Nome seguradora: ");
	    String nome = entry.nextLine();
	    
	    int indice = listaSeguradoras.stream()
	        .map(Seguradora::getNome)
	        .toList()
	        .indexOf(nome);
	    
	    return indice != -1 ? indice : -1;
	}
	
	public static void Cliente encontraCliente(String nome) {
		for (Seguradora seguradora: listaSeguradoras) {
			for (Cliente cliente: seguradora.getlistaClientes()) {
				if (cliente.getNome().equals(nome)) {
					return cliente;
				}
			}
		}
		return null;
	}
	
	public static void menuCadastroCliente(Scanner entry) {
	    int k;
	    do {
	        k = encontraSeguradora(entry);
	    } while (k == -1);
	    
	    System.out.println("Pessoa Física (1) ou Jurídica (2) ?");
	    int tipopessoa = entry.nextInt();
	    entry.nextLine();
	    
	    System.out.println("Insira as informações abaixo: ");
	    if (tipopessoa == 1) {
	        ClientePF clientePF = coletaInfoPF(entry);
	        listaSeguradoras.get(k).cadastrarCliente(clientePF);
	    } else if (tipopessoa == 2) {
	        ClientePJ clientePJ = coletaInfoPJ(entry);
	        listaSeguradoras.get(k).cadastrarCliente(clientePJ);
	    } else {
	        System.out.println("Opção inválida!");
	    }
	}

	public static void listarSinistrosCliente(Scanner entry) {
	    System.out.println("Nome cliente: ");
	    String nome = entry.nextLine();
	    
	    for (Seguradora seguradora : listaSeguradoras) {
	        for (Sinistro sinistro : seguradora.getlistaSinistros()) {
	            if (sinistro.getCliente().getNome().equals(nome)) {
	                System.out.println(sinistro.toString());
	            }
	        }
	    }
	}

	public static void listarVeiculosSeguradora() {
	    int p = encontraSeguradora();
	    if (p == -1) {
	        System.out.println("Seguradora não encontrada.");
	        return;
	    }
	    
	    List<Cliente> clientes = listaSeguradoras.get(pos).getlistaClientes();
	    for (Cliente cliente : clientes) {
	        List<Veiculo> veiculos = cliente.getListaVeiculos();
	        for (Veiculo veiculo : veiculos) {
	            System.out.println(veiculo.toString());
	        }
	    }
	}

	public static void listarVeiculosCliente(Scanner entry) {
	    System.out.println("Nome do cliente: ");
	    String nome = entry.nextLine();
	    Cliente cliente = encontraCliente(nome);
	    
	    if (cliente == null) {
	        System.out.println("Cliente não encontrado.");
	        return;
	    }
	    
	    for (Veiculo veiculo : cliente.getListaVeiculos()) {
	        System.out.println(veiculo.toString());
	    }
	}

	public static void excluirCliente(Scanner entry) {
	    System.out.println("Digite o nome do cliente: ");
	    String nome = entry.nextLine();
	    
	    int seguradoraIndex = encontraSeguradora();
	    if (seguradoraIndex == -1) {
	        System.out.println("Seguradora não encontrada.");
	        return;
	    }
	    
	    List<Cliente> clientes = listaSeguradoras.get(seguradoraIndex).getlistaClientes();
	    int clienteIndex = -1;
	    
	    for (int i = 0; i < clientes.size(); i++) {
	        if (clientes.get(i).getNome().equals(nome)) {
	            clienteIndex = i;
	            break;
	        }
	    }
	    
	    if (clienteIndex == -1) {
	        System.out.println("Cliente não encontrado.");
	        return;
	    }
	    
	    clientes.remove(clienteIndex);
	}

	private static void exibirMenuExterno() {
	    System.out.println("Menu principal");
	    for (MenuOpcoes op : MenuOpcoes.values()) {
	        System.out.printf("%d - %s%n", op.ordinal(), op.getDescricao());
	    }
	}
	
	private static void exibirSubmenu(MenuOpcoes op) {
	    SubmenuOpcoes[] submenu = op.getSubmenu();
	    System.out.println(op.getDescricao());
	    for (int i = 0; i < submenu.length; i++) {
	        System.out.printf("%d - %s%n", i, submenu[i].getDescricao());
	    }
	}

	private static MenuOpcoes lerOpcaoMenuExterno(Scanner entry) {
	    int opu;
	    MenuOpcoes opUsuarioConst;
	    
	    do {
	        System.out.println("Digite uma opção: ");
	        opu = entry.nextInt();
	    } while (opu < 0 || opu >= MenuOpcoes.values().length);
	    
	    opUsuarioConst = MenuOpcoes.values()[opu];
	    return opUsuarioConst;
	}

	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op, Scanner entry) {
	    int opu;
	    SubmenuOpcoes opUsuarioConst;
	    
	    do {
	        System.out.println("Digite uma opção: ");
	        opu = entry.nextInt();
	    } while (opu < 0 || opu >= op.getSubmenu().length);
	    
	    opUsuarioConst = op.getSubmenu()[opu];
	    return opUsuarioConst;
	}

	private static void executarOpcaoMenuExterno(MenuOpcoes op) {
	    Map<MenuOpcoes, Runnable> acoes = new HashMap<>();
	    acoes.put(MenuOpcoes.CADASTROS, () -> executarSubmenu(op));
	    acoes.put(MenuOpcoes.LISTAR, () -> executarSubmenu(op));
	    acoes.put(MenuOpcoes.EXCLUIR, () -> executarSubmenu(op));
	    acoes.put(MenuOpcoes.GERAR_SINISTRO, () -> System.out.println("Executar método gerar Sinistro"));
	    acoes.put(MenuOpcoes.TRANSFERIR_SEGURO, () -> System.out.println("Executar método transferir seguro"));
	    acoes.put(MenuOpcoes.CALCULAR_RECEITA, () -> System.out.println("Executar método calcular receita"));

	    if (acoes.containsKey(op)) {
	        acoes.get(op).run();
	    }
	}

	private static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) {
	    Map<SubmenuOpcoes, Runnable> acoes = new HashMap<>();
	    
	    // Mapeamento das opções para as ações correspondentes
	    acoes.put(SubmenuOpcoes.CADASTRAR_CLIENTE, () -> menuCadastroCliente());
	    acoes.put(SubmenuOpcoes.CADASTRAR_VEICULO, () -> {
	        System.out.println("Insira as informações abaixo: ");
	        Veiculo carro = coletaInfoVeiculo();
	    });
	    acoes.put(SubmenuOpcoes.CADASTRAR_SEGURADORA, () -> {
	        System.out.println("Insira as informações abaixo: ");
	        Seguradora seguradora = coletaInfoSeguradora();
	        listaSeguradoras.add(seguradora);
	    });
	    acoes.put(SubmenuOpcoes.LISTAR_CLIENTES, () -> {
	        int posseg = encontraSeguradora();
	        listaSeguradoras.get(p).listarClientes();
	    });
	    acoes.put(SubmenuOpcoes.LISTAR_SINISTROS_SEGURADORA, () -> {
	        int posseg = encontraSeguradora();
	        listaSeguradoras.get(p).listarSinistros();
	    });
	    acoes.put(SubmenuOpcoes.LISTAR_SINISTROS_CLIENTE, () -> listarSinistrosCliente());
	    acoes.put(SubmenuOpcoes.LISTAR_VEICULOS_SEGURADORA, () -> listarVeiculosSeguradora());
	    acoes.put(SubmenuOpcoes.LISTAR_VEICULOS_CLIENTE, () -> listarVeiculosCliente());
	    acoes.put(SubmenuOpcoes.EXCLUIR_CLIENTE, () -> excluirCliente());
	    acoes.put(SubmenuOpcoes.EXCLUIR_VEICULO, () -> System.out.println("Chamar método excluir veículo"));
	    acoes.put(SubmenuOpcoes.EXCLUIR_SINISTRO, () -> System.out.println("Chamar método excluir sinistro"));

	    // Executa a ação correspondente à opção selecionada
	    Runnable acao = acoes.get(opSubmenu);
	    if (acao != null) {
	        acao.run();
	    }
	}
	
	private static void executarSubmenu(MenuOpcoes op) {
	    boolean voltar = false;
	    
	    while (!voltar) {
	        exibirSubmenu(op);
	        SubmenuOpcoes opSubmenu = lerOpcaoSubmenu(op);
	        
	        switch (opSubmenu) {
	            case VOLTAR:
	                voltar = true;
	                break;
	            default:
	                executarOpcaoSubMenu(opSubmenu);
	                break;
	        }
	    }
	}
	
	public static void main(String[] args) {
	    Scanner entry = new Scanner(System.in);
		ClientePF clientePF;
	    ClientePJ clientePJ;
	    Seguradora seguradora;
	    Veiculo veiculo1, veiculo2, veiculo3;
	    Date data;
	    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	    
	    data = formatoData.parse("19/05/2023");
	    
	    veiculo1 = new Veiculo("ABC1234", "Hyundai", "HB20", 2019);
	    veiculo2 = new Veiculo("DEF5678", "Audi", "R8", 2020);
	    
        clientePF = new ClientePF("Daniel", "Barão Geraldo", "Masculino", "133.912.107-70", "Ensino superior incompleto", "Classe média", formatoData.parse("16/08/2020"), dataN, 0);
        clientePJ = new ClientePJ("Esther", "Campinas", "18.548.678/0001-79", formatadata.parse("29/07/2010"), 30, 0);
        
        seguradora = new Seguradora("Seguridade", "40028922", "seguridade@gmail.com", "Rua da segurança");
        
        clientePF.addlistaVeiculos(veiculo1);
        clientePJ.addlistaVeiculos(veiculo2);

        seguradora.cadastrarCliente(clientePF);
        seguradora.cadastrarCliente(clientePJ);

        seguradora.gerarSinistro(clientePJ, veiculo2, seguradora);
        seguradora.gerarSinistro(clientePF, veiculo1, seguradora);

        seguradora.listarClientes();
        seguradora.visualizarSinistro(clientePF.getNome());
        seguradora.listarSinistros();
	    System.out.println("Receita total: R$ " + seguradora.calcularReceita());
	    
	    seguradora.atualizaValoresSeguro(clientePF);
	    seguradora.atualizaValoresSeguro(clientePJ);
	    System.out.println("Receita total: R$ " + seguradora.calcularReceita());
	    
	    
	    MenuOpcoes op;
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		} while(op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
		entrada.close();
	}
}
