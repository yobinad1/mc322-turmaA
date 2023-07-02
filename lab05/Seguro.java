package lab05;
import java.util.*;

public abstract class Seguro {
	private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private List<Sinistro> listaSinistros;
    private List<Condutor> listaCondutores;
    private double valorMensal;	
    
    // Construtora
    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        id = gerarId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
    }
    
    // Getters e setters
    public int getId() {
        return id;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    public Seguradora getSeguradora() {
        return seguradora;
    }
    
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    
    public List<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    
    public void setListaCondutores(List<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
    
    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    
    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
    
    public double getValorMensal() {
        return valorMensal;
    }
    
    private int gerarId() {
        // Gera um ID único baseado no UUID (Universally Unique Identifier).
        String uuid = UUID.randomUUID().toString();
        int id = uuid.hashCode();
        return id;
    }

    // Remove o condutor da listaCondutores
    public boolean desautorizarCondutor(Condutor condutor) {
        return listaCondutores.remove(condutor);
    }
    
    // Adiciona o condutor na listaCondutores
    public boolean autorizarCondutor(Condutor condutor) {
        if (listaCondutores.contains(condutor))
            return false;
        return listaCondutores.add(condutor);
    }
    
    // Atribui o valor do seguro
    public abstract void calcularValor();
    
    // Gera um sinistro
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        if (!listaCondutores.contains(condutor)) {
            return false; // Se o condutor não está incluso no seguro, retorna false
        }

        Sinistro sinistro = new Sinistro(data, endereco, condutor, this); // Cria o sinistro
        condutor.adicionarSinistro(sinistro); // Adiciona o sinistro à lista do condutor
        boolean gerou = listaSinistros.add(sinistro); // Adiciona o sinistro à lista de sinistros

        calcularValor(); // Calcula o valor

        return gerou; // Retorna true se o sinistro foi adicionado, senão false
    }
    
    // Lista os sinistros da listaSinistros
    public boolean listarSinistros() {
        if (listaSinistros.isEmpty()) {
            return false; // Se a lista de sinistros estiver vazia, retorna false
        }

        for (int i = 0; i < listaSinistros.size(); i++) {
            Sinistro sinistro = listaSinistros.get(i);
            System.out.println(i + " - " + sinistro.getId());
        }

        return true;
    }
    
    // Lista os condutores da listaCondutores
    public boolean listarCondutores() {
        if (listaCondutores.isEmpty()) {
            return false; // Se a lista de condutores estiver vazia, retorna false
        }

        for (int i = 0; i < listaCondutores.size(); i++) {
            Condutor condutor = listaCondutores.get(i);
            System.out.println(i + " - " + condutor.getNome() + " - CPF " + condutor.getCPF());
        }

        return true;
    }
    
    // Remove um sinistro da listaSinistros
    public boolean removerSinistro(Sinistro sinistro) {
        boolean removeu = listaSinistros.remove(sinistro); // Remove o sinistro da lista de sinistros

        for (Condutor condutor : listaCondutores) {
            condutor.getListaSinistros().remove(sinistro); // Remove o sinistro da lista de sinistros do condutor
        }

        calcularValor(); // Recalcula o valor

        return removeu;
    }
    
    // Retorna a quantidade de sinistros por condutor
    protected int quantidadeSinistrosPorCondutor() {
        int qtdeSinistrosCondutor = 0;
        
        for (Condutor condutor : getListaCondutores()) {
            qtdeSinistrosCondutor += condutor.getSinistrosPorSeguradora(getSeguradora()).size();
        }
        
        return qtdeSinistrosCondutor;
    }
    
    // Retorna o cliente
    public abstract Cliente getCliente();
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Seguro - id %d:\n- Data de inicio: %s\n- Data de fim: %s\n- Seguradora: %s\n" +
                "- Valor mensal: %.2f\n- Sinistros: ", id, Data.dateToString(dataInicio),
                Data.dateToString(dataFim), seguradora.getNome(), valorMensal));

        if (!listaSinistros.isEmpty()) {
            for (int i = 0; i < listaSinistros.size() - 1; i++) {
                sb.append(listaSinistros.get(i).getId()).append(", ");
            }
            sb.append(listaSinistros.get(listaSinistros.size() - 1).getId());
        } else {
            sb.append("Nenhum sinistro cadastrado");
        }

        sb.append("\n- Condutores: ");
        if (!listaCondutores.isEmpty()) {
            for (int i = 0; i < listaCondutores.size() - 1; i++) {
                sb.append(listaCondutores.get(i).getCPF()).append(", ");
            }
            sb.append(listaCondutores.get(listaCondutores.size() - 1).getCPF());
        } else {
            sb.append("Nenhum condutor cadastrado");
        }

        return sb.toString();
    }   
}