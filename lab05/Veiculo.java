package lab05;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	
	// Construtora
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}
	
	// Getters e setters
	public String getPlaca() {
		return placa;
	} 	
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj instanceof Veiculo) {
	        Veiculo otherVeiculo = (Veiculo) obj;
	        if (placa != null) {
	            return placa.equals(otherVeiculo.getPlaca());
	        } else {
	            return otherVeiculo.getPlaca() == null;
	        }
	    }
	    return false;
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("Veiculo - Placa %s:\n- Marca: %s\n- Modelo: %s\n- Ano de fabricacao: %d",
	            placa, marca, modelo, anoFabricacao));
	    return sb.toString();
	}
}
