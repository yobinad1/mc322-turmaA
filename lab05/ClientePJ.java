package lab05;
import java.util.*;

public class ClientePJ extends Cliente{
	private final String CNPJ;
    private Date dataFundacao;
    private List<Frota> listaFrota;
    
    // Construtora
    public ClientePJ(String nome, String telefone, String endereco, String email, String CNPJ, Date dataFundacao) {
        super(nome, telefone, endereco, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        listaFrota = new ArrayList<Frota>();
    }
    
    // Getters e setters
    public String getCNPJ() {
        return CNPJ;
    }
    
    public Date getDataFundacao() {
        return dataFundacao;
    }
    
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
    public List<Frota> getListaFrota() {
        return listaFrota;
    }
    
    public void setListaFrota(List<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }
    
    // Retorna a string CNPJ
    public String strDocumento() {
        return "CNPJ " + CNPJ;
    }
    
    // Retorna bool se o objeto é igual
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientePJ)) {
            return false;
        }
        ClientePJ otherCliente = (ClientePJ) obj;
        return CNPJ.equals(otherCliente.getCNPJ());
    }
    
    // Cadastra a frota na lista de frotas
    public boolean cadastrarFrota(Frota frota) {
        if (listaFrota.contains(frota))
            return false;
        return listaFrota.add(frota);
    }
    
    // Adiciona o veiculo a frota
    public boolean atualizarFrota(Frota frota, Veiculo veiculo) {
        if (listaFrota.contains(frota)) {
            return frota.addVeiculo(veiculo);
        }
        return false;
    }

    // Retorna o indice em listaFrota
    private int indiceFrota(String code) {
        for (int i = 0; i < listaFrota.size(); i++) {
            if (listaFrota.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }
    
    // Remove a frota e retorna se removeu
    public boolean atualizarFrota(String code) {
        int index = indiceFrota(code);
        if (index >= 0) {
            listaFrota.remove(index);
            return true;
        }
        return false;
    }
    
    // Remove o veiculo e retorna se removeu
    public boolean atualizarFrota(Frota frota, String placa) {
        if (listaFrota.contains(frota)) {
            return frota.removeVeiculo(placa);
        }
        return false;
    }
    
    // Lista as frotas
    public boolean listarFrotas() {
        if (listaFrota.isEmpty()) {
            return false;
        }
        for (int i = 0; i < listaFrota.size(); i++) {
            System.out.println(i + " - " + listaFrota.get(i).getCode());
        }
        return true;
    }

    // Retorna uma lista com os veiculos contidos na frota
    public List<Veiculo> getVeiculosPorFrota(String code) {
        int index = indiceFrota(code);
        if (index < 0) {
            return null;
        }
        List<Veiculo> copia = new ArrayList<>(listaFrota.get(index).getListaVeiculos());
        return copia;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString().replace("Cliente", "ClientePJ"));
        sb.append(String.format("\n- CNPJ: %s\n- Data de fundação: %s\n- Frotas: ",
                CNPJ, Data.dateToString(dataFundacao)));

        if (listaFrota.isEmpty()) {
            sb.append("Nenhuma frota cadastrada");
        } else {
            for (Frota frota : listaFrota) {
                sb.append(frota.getCode()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove a última vírgula e espaço
        }

        return sb.toString();
    }
}
