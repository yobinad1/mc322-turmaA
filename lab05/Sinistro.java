package lab05;
import java.util.*;

public class Sinistro {
	private final int id;
	private Date data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	// Construtora
	public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
		id = gerarId();
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}
	
	// Cria um ID único
	private int gerarId() {
	    // Gera um ID único baseado no UUID (Universally Unique Identifier).
	    String uuid = UUID.randomUUID().toString();
	    int id = uuid.hashCode();
	    return id;
	}
	
	// Getters e setters
	public int getId() {
		return id;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Condutor getCondutor() {
		return condutor;
	}
	
	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	
	public Seguro getSeguro() {
		return seguro;
	}
	
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("Sinistro - id %d:\n- Data: %s\n- Endereco: %s\n",
	            id, Data.dateToString(data), endereco));
	    sb.append("- Condutor: ").append(condutor.getNome()).append("\n");
	    sb.append("- Seguro: ").append(seguro.getId());
	    return sb.toString();
	}
}
