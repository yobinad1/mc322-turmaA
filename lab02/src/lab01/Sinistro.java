package lab01;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public Sinistro(String data, String endereco) {
		this.id = getRandomNumber(1, 1000);
		this.data = data;
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		return "Sinistro [id=" + id + ", data=" + data + ", endereco=" + endereco + "]"; 
	}
}
