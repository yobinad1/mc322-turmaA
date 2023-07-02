package lab05;
import java.util.*;

public class Seguradora {
	private final String CNPJ;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private List<Cliente> listaClientes;
	private List<Seguro> listaSeguros;
	
	// Construtora
	public Seguradora(String CNPJ, String nome, String telefone, String endereco, String email) {
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		listaSeguros = new ArrayList<Seguro>();
		listaClientes = new ArrayList<Cliente>();
	}
	
	// Getters e setters
	public String getCNPJ() {
		return CNPJ;
	}
	
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
	
	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}
	
	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	// Retorna bool na comparação de objetos
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    
	    Seguradora otherSeguradora = (Seguradora) obj;
	    return CNPJ.equals(otherSeguradora.getCNPJ());
	}
	
	// Lista clientes que contraram a seguradora
	public boolean listarClientes(String tipoCliente) {
	    boolean imprimiu = false;
	    
	    for (int i = 0; i < listaClientes.size(); i++) {
	        Cliente cliente = listaClientes.get(i);
	        
	        if ((tipoCliente.equals("PF") && cliente instanceof ClientePF) ||
	            (tipoCliente.equals("PJ") && cliente instanceof ClientePJ) ||
	            tipoCliente.isEmpty()) {
	            System.out.println(i + " - " + cliente.getNome() + " - " + cliente.strDocumento());
	            imprimiu = true;
	        }
	    }
	    
	    return imprimiu;
	}
	
	// Gera um novo seguroPJ
	public boolean gerarSeguro(Date dataInicio, Date dataFim, Frota frota, ClientePJ cliente) {
	    cadastrarCliente(cliente);
	    
	    SeguroPJ seguroPJ = new SeguroPJ(dataInicio, dataFim, this, frota, cliente);
	    boolean gerou = listaSeguros.add(seguroPJ);
	    
	    atualizarValoresSeguro(); 
	    
	    return gerou;
	}

	// Gera um novo seguroPF
	public boolean gerarSeguro(Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente) {
	    cadastrarCliente(cliente); 
	    
	    SeguroPF seguroPF = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
	    boolean gerou = listaSeguros.add(seguroPF);
	    
	    atualizarValoresSeguro(); 
	    
	    return gerou;
	}
	
	// Remove um seguro da listaSeguros
	public boolean cancelarSeguro(Seguro seguro) {
	    boolean cancelou = listaSeguros.remove(seguro);
	    
	    for (Sinistro sinistro : seguro.getListaSinistros()) {
	        sinistro.getCondutor().removerSinistro(sinistro);
	    }
	    
	    if (getSegurosPorCliente(seguro.getCliente()).isEmpty()) {
	        listaClientes.remove(seguro.getCliente());
	    }
	    
	    atualizarValoresSeguro();
	    
	    return cancelou; 
	}
	
	// Adiciona cliente na listaClientes
	public boolean cadastrarCliente(Cliente cliente) {
	    if (listaClientes.contains(cliente)) {
	        return false;
	    }
	    
	    return listaClientes.add(cliente);
	}
	
	// Remove cliente da listaClientes
	public boolean removerCliente(Cliente cliente) {
	    boolean removeu = listaClientes.remove(cliente);
	    
	    List<Seguro> segurosCliente = getSegurosPorCliente(cliente);
	    for (Seguro seguro : segurosCliente) {
	        cancelarSeguro(seguro);
	    }
	    
	    return removeu;
	}
	
	// Retorna lista com os seguros do cliente
	public List<Seguro> getSegurosPorCliente(Cliente cliente) {
	    List<Seguro> listaSegurosCliente = new ArrayList<>();
	    
	    for (Seguro seguro : listaSeguros) {
	        if (seguro instanceof SeguroPF) {
	            SeguroPF seguroPF = (SeguroPF) seguro;
	            if (seguroPF.getCliente().equals(cliente)) {
	                listaSegurosCliente.add(seguro);
	            }
	        } else if (seguro instanceof SeguroPJ) {
	            SeguroPJ seguroPJ = (SeguroPJ) seguro;
	            if (seguroPJ.getCliente().equals(cliente)) {
	                listaSegurosCliente.add(seguro);
	            }
	        }
	    }
	    
	    return listaSegurosCliente;
	}
	
	// Retorna lista com os sinistros do cliente
	public List<Sinistro> getSinistrosPorCliente(Cliente cliente) {
	    List<Sinistro> listaSinistros = new ArrayList<>();

	    List<Seguro> segurosCliente = getSegurosPorCliente(cliente);
	    for (Seguro seguro : segurosCliente) {
	        listaSinistros.addAll(seguro.getListaSinistros());
	    }
	    
	    return listaSinistros;
	}
	
	// Retorna lista com os seguros do condutor
	public List<Seguro> getSegurosPorCondutor(Condutor condutor) {
	    List<Seguro> segurosCondutor = new ArrayList<>();
	    
	    for (Seguro seguro : listaSeguros) {
	        if (seguro.getListaCondutores().contains(condutor)) {
	            segurosCondutor.add(seguro);
	        }
	    }
	    
	    return segurosCondutor;
	}
	
	// Retorna lista de veículos de um cliente na seguradora
	public List<Veiculo> getVeiculosPorCliente(ClientePF cliente) {
	    List<Veiculo> listaVeiculos = new ArrayList<>();

	    for (Seguro seguro : listaSeguros) {
	        if (seguro instanceof SeguroPF) {
	            SeguroPF seguroPF = (SeguroPF) seguro;
	            if (seguroPF.getCliente().equals(cliente)) {
	                listaVeiculos.add(seguroPF.getVeiculo());
	            }
	        }
	    }

	    return listaVeiculos;
	}
	
	// Retorna lista com os segurosPJ que contém a frota
	public List<Seguro> getSegurosPorFrota(Frota frota) {
	    List<Seguro> segurosFrota = new ArrayList<>();

	    for (Seguro seguro : listaSeguros) {
	        if (seguro instanceof SeguroPJ) {
	            SeguroPJ seguroPJ = (SeguroPJ) seguro;
	            if (seguroPJ.getFrota().equals(frota)) {
	                segurosFrota.add(seguro);
	            }
	        }
	    }

	    return segurosFrota;
	}
	
	// Retorna lista com os segurosPF que contém o veiculo
	public List<Seguro> getSegurosPorVeiculo(Veiculo veiculo) {
	    List<Seguro> segurosVeiculo = new ArrayList<>();

	    for (Seguro seguro : listaSeguros) {
	        if (seguro instanceof SeguroPF) {
	            SeguroPF seguroPF = (SeguroPF) seguro;
	            if (seguroPF.getVeiculo().equals(veiculo)) {
	                segurosVeiculo.add(seguro);
	            }
	        }
	    }

	    return segurosVeiculo;
	}
	
	// Lista os seguros
	public boolean listarSeguros() {
	    if (listaSeguros.isEmpty()) {
	        return false;
	    }
	    
	    for (int i = 0; i < listaSeguros.size(); i++) {
	        Seguro seguro = listaSeguros.get(i);
	        System.out.println(i + " - " + seguro.getId());
	    }
	    
	    return true;
	}
	
	// Atualiza o valor do seguro
	public void atualizarValoresSeguro() {
	    listaSeguros.forEach(Seguro::calcularValor);
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("Seguradora - %s:\n- Telefone: %s\n- Endereco: %s\n- E-mail: %s\n- Clientes: ",
	            nome, telefone, endereco, email));

	    if (!listaClientes.isEmpty()) {
	        for (int i = 0; i < listaClientes.size() - 1; i++) {
	            sb.append(listaClientes.get(i).strDocumento()).append(", ");
	        }
	        sb.append(listaClientes.get(listaClientes.size() - 1).strDocumento());
	    } else {
	        sb.append("Nenhum cliente cadastrado");
	    }

	    sb.append("\n- Seguros: ");
	    if (!listaSeguros.isEmpty()) {
	        for (int i = 0; i < listaSeguros.size() - 1; i++) {
	            sb.append(listaSeguros.get(i).getId()).append(", ");
	        }
	        sb.append(listaSeguros.get(listaSeguros.size() - 1).getId());
	    } else {
	        sb.append("Nenhum seguro cadastrado");
	    }

	    return sb.toString();
	}
}