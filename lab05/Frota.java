package lab05;
import java.util.*;

public class Frota {
	private final String code;
	private List<Veiculo> listaVeiculos;
	
	// Construtora
	public Frota() {
        this.code = gerarCode();
        this.listaVeiculos = new ArrayList<Veiculo>();
    }
	
	// Getters e setters
	public String getCode() {
		return code;
	}
	
	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	// Gera um ID para um objeto
	private String gerarCode() {
	    // Gera um ID único baseado no UUID (Universally Unique Identifier).
	    String id = UUID.randomUUID().toString();
	    return id;
	}
	
	// Lista os veículos cadastrados para a frota
	public boolean listarVeiculos() {
	    if (listaVeiculos.isEmpty()) {
	        return false;
	    }
	    
	    for (int i = 0; i < listaVeiculos.size(); i++) {
	        Veiculo veiculo = listaVeiculos.get(i);
	        System.out.println(i + " - " + veiculo.getPlaca());
	    }
	    return true;
	}

	// Retorna bool se o objeto é igual 
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Frota otherFrota = (Frota) obj;
	    return code.equals(otherFrota.getCode());
	}
	
	// Remove veiculo da listaVeiculos
	public boolean removeVeiculo(Veiculo veiculo) {
        return listaVeiculos.remove(veiculo);
    }
	
	// Adiciona veiculo da listaVeiculos
	public boolean addVeiculo(Veiculo veiculo) {
	    if (listaVeiculos.contains(veiculo)) {
	        return false;
	    }
	    return listaVeiculos.add(veiculo);
	}
	
	// Remove o veiculo da frota, indicando se removeu
	public boolean removeVeiculo(String placa) {
	    for (int i = 0; i < listaVeiculos.size(); i++) {
	        if (listaVeiculos.get(i).getPlaca().equals(placa)) {
	            listaVeiculos.remove(i);
	            return true;
	        }
	    }
	    return false;
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("Frota - code %s:\n- Veiculos: ", code));

	    if (listaVeiculos.isEmpty()) {
	        sb.append("Nenhum veiculo cadastrado");
	    } else {
	        for (Veiculo veiculo : listaVeiculos) {
	            sb.append(veiculo.getPlaca()).append(", ");
	        }
	        sb.setLength(sb.length() - 2); // Remove a última vírgula e espaço
	    }

	    return sb.toString();
	}
}
