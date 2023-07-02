package lab05;
import java.util.*;

public class SeguroPJ extends Seguro {
	Frota frota;
	ClientePJ cliente;
	
	// Construtora
	public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora,  Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
        calcularValor();
    }
	
	// Getters e setters
	public void setFrota(Frota frota) {
        this.frota = frota;
    }
	
    public Frota getFrota() {
        return frota;
    }
    
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
    
    public ClientePJ getCliente() {
        return cliente;
    }
    
	// Adiciona o condutor à listaCondutores
    public boolean autorizarCondutor(Condutor condutor) {
        boolean autorizou = super.autorizarCondutor(condutor);

        if (autorizou) {
            calcularValor();
        }

        return autorizou;
    }
    
    // Remove o condutor à listaCondutores
    public boolean desautorizarCondutor(Condutor condutor) {
        boolean desautorizou = super.desautorizarCondutor(condutor);

        if (desautorizou) {
            calcularValor();
        }

        return desautorizou;
    }
	
    // Gera um sinistro e adiciona à listaSinistros
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        boolean gerou = super.gerarSinistro(data, condutor, endereco);

        if (gerou) {
            calcularValor();
        }

        return gerou;
    }
    
    // Atribui o valor do seguro
    public void calcularValor() {
        if (frota == null || getSeguradora() == null || cliente == null) {
            return;
        }

        double valor = CalcSeguro.VALOR_BASE.getValor() *
                (10 + getListaCondutores().size() / 10.0) *
                (1 + 1.0 / (frota.getListaVeiculos().size() + 2)) *
                (1 + 1.0 / (Data.calcularIdade(cliente.getDataFundacao()) + 2)) *
                (2 + getSeguradora().getSinistrosPorCliente(cliente).size() / 10.0) *
                (5 + quantidadeSinistrosPorCondutor() / 10.0);

        setValorMensal(valor);
    }
    
    // Remove o sinistro da listaSinistros
    public boolean removerSinistro(Sinistro sinistro) {
        boolean removeu = super.removerSinistro(sinistro);
        if (removeu) {
            calcularValor();
        }
        return removeu;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString().replaceFirst("Seguro", "SeguroPJ"));
        sb.append("\n- Frota: ").append(frota.getCode()).append("\n- Cliente: ").append(cliente.strDocumento());
        return sb.toString();
    }
}
