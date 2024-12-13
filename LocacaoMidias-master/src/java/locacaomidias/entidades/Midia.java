package locacaomidias.entidades;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 *
 * @author User
 */
public class Midia {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size ( min = 1, max = 100 )
    private String titulo;
    
    @NotNull
    @Size ( min = 1, max = 100 )
    private String anoLancamento;
    
    @NotNull
    @Size ( min = 1, max = 13 )
    private String codigoBarras;
    
    @NotNull
    @PositiveOrZero
    private Long duracaoMinutos;
    
    @NotNull
    private Ator atorPrincipal;
    
    @NotNull
    private Ator atorCoadjuvante;
    
    @NotNull
    private Genero genero;
    
    @NotNull
    private ClassificacaoEtaria classificacaoEtaria;
    
    @NotNull
    private Tipo tipo;
    
    @NotNull
    private ClassificacaoInterna classificacaoInterna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Long duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Ator getAtorPrincipal() {
        return atorPrincipal;
    }

    public void setAtorPrincipal(Ator atorPrincipal) {
        this.atorPrincipal = atorPrincipal;
    }

    public Ator getAtorCoadjuvante() {
        return atorCoadjuvante;
    }

    public void setAtorCoadjuvante(Ator atorCoadjuvante) {
        this.atorCoadjuvante = atorCoadjuvante;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public ClassificacaoEtaria getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(ClassificacaoEtaria classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public ClassificacaoInterna getClassificacaoInterna() {
        return classificacaoInterna;
    }

    public void setClassificacaoInterna(ClassificacaoInterna classificacaoInterna) {
        this.classificacaoInterna = classificacaoInterna;
    }
    
    
}
