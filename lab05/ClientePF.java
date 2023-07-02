package lab05;
import java.util.*;

public class ClientePF extends Cliente{
	private final String CPF;
    private String genero;
    private String educacao;
    private Date dataNasc;
    private List<Veiculo> listaVeiculos;
    
    // Construtora
    public ClientePF(String nome, String telefone, String endereco, String email, String CPF, String genero, String educacao, Date dataNasc) {
        super(nome, telefone, endereco, email);
        this.CPF = CPF;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNasc = dataNasc;
        listaVeiculos = new ArrayList<Veiculo>();
    }
    
    // Getters e setters
    public String getCPF() {
        return CPF;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getEducacao() {
        return educacao;
    }
    
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }
    
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public Date getDataNasc() {
        return dataNasc;
    }
    
    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    
    // Retorna uma string CPF
    public String strDocumento() {
        return "CPF " + CPF;
    }
    
    // Retorna bool se o objeto for igual
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ClientePF))
            return false;
        ClientePF otherCliente = (ClientePF) obj;
        return CPF.equals(otherCliente.getCPF());
    }

    // Lista os veiculos cadastrados 
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

    // Cadastra o veiculo na listaVeiculos
    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo))
            return false;
        return listaVeiculos.add(veiculo);
    }
    
    // remove o veiculo da listaVeiculos
    public boolean removerVeiculo(Veiculo veiculo) {
    	return listaVeiculos.remove(veiculo);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString().replace("Cliente", "ClientePF"));
        sb.append(String.format("\n- CPF: %s\n- Data de nascimento: %s\n- Gênero: %s\n- Educação: %s\n- Veículos: ",
                CPF, Data.dateToString(dataNasc), genero, educacao));
        
        if (listaVeiculos.isEmpty()) {
            sb.append("Nenhum veículo cadastrado");
        } else {
            for (Veiculo veiculo : listaVeiculos) {
                sb.append(veiculo.getPlaca()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove a última vírgula e espaço
        }
        
        return sb.toString();
    }
}
