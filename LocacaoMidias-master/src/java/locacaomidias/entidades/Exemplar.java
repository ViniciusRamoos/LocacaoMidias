package locacaomidias.entidades;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Exemplar {
    
    @NotNull
    private Long codigoInterno;
    
    @NotNull
    private Boolean disponivel;
    
    @NotNull
    private Midia midia;

    public Long getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(Long codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }
    
    
    
}
