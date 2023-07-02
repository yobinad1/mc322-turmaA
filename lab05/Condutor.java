package lab05;
import java.util.*;

public class Condutor {
	private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNasc;
    private List<Sinistro> listaSinistros;
    
    // Construtora
    public Condutor(String CPF, String nome, String telefone, String endereco, String email, Date dataNasc) {
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        listaSinistros = new ArrayList<Sinistro>();
    }
    
    // Getters e setters
    public String getCPF() {
        return CPF;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Date getDataNasc() {
        return dataNasc;
    }
    
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    
    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    
    // Construtora
    public Condutor(ClientePF cliente) {
        this(cliente.getCPF(), cliente.getNome(), cliente.getTelefone(), cliente.getEndereco(), cliente.getEmail(), cliente.getDataNasc());
    }
    
    // Retorna bool se o objeto é igual
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Condutor otherCondutor = (Condutor) obj;
        return CPF.equals(otherCondutor.getCPF());
    }
    
    // Adicionar sinistro na listaSinistros
    public boolean adicionarSinistro(Sinistro sinistro) {
        return listaSinistros.add(sinistro);
    }
    
    // Remover sinistro da listaSinistros
    public boolean removerSinistro(Sinistro sinistro) {
        return listaSinistros.remove(sinistro);
    }
    
    // Retorna lista contendo todos os sinistros do condutor
    public List<Sinistro> getSinistrosPorSeguradora(Seguradora seguradora) {
        List<Sinistro> listaRetorno = new ArrayList<>();
        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getSeguro().getSeguradora().equals(seguradora)) {
                listaRetorno.add(sinistro);
            }
        }
        return listaRetorno;
    }
    
    // Lista os sinistros
    public boolean listarSinistros() {
        if (listaSinistros.isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < listaSinistros.size(); i++) {
            Sinistro sinistro = listaSinistros.get(i);
            System.out.println(i + " - " + sinistro.getId());
        }
        
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Condutor - %s:\n- CPF: %s\n- Telefone: %s\n- Endereço: %s\n" +
                "- E-mail: %s\n- Data de nascimento: %s\n- Sinistros: ", nome, CPF, telefone,
                endereco, email, Data.dateToString(dataNasc)));

        if (listaSinistros.isEmpty()) {
            sb.append("Nenhum sinistro cadastrado");
        } else {
            for (Sinistro sinistro : listaSinistros) {
                sb.append(sinistro.getId()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove a última vírgula e espaço
        }

        return sb.toString();
    }
}
