package locacaomidias.entidades;

import java.sql.Date;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Locacao {
    
    @NotNull
    private Long id;
    
    @NotNull
    private Date dataInicio;
    
    @NotNull
    private Date dataFim;
    
    @NotNull
    private Boolean cancelada;
    
    @NotNull
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
