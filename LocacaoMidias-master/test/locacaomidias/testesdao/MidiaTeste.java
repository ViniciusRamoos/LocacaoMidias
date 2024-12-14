package locacaomidias.testesdao;

import java.sql.SQLException;
import java.util.List;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
public class MidiaTeste {
    
    public static void main(String[] args) {
        
        Midia m = new Midia();
        
        MidiaDAO dao = null;
        
        Ator ap = new Ator();
        Ator ac = new Ator();
        Genero g = new Genero();
        ClassificacaoEtaria ce = new ClassificacaoEtaria();
        Tipo t = new Tipo();
        ClassificacaoInterna ci = new ClassificacaoInterna();
        
        ap.setId(Utils.getLong("1"));
        ac.setId(Utils.getLong("2"));
        g.setId(Utils.getLong("3"));
        ce.setId(Utils.getLong("2"));
        t.setId(Utils.getLong("2"));
        ci.setId(Utils.getLong("3"));
        
        m.setId(Utils.getLong("3"));
        m.setTitulo("testea");
        m.setAnoLancamento("2004");
        m.setCodigoBarras("1231231231233");
        m.setDuracaoMinutos(Utils.getLong("101"));
        m.setAtorPrincipal(ap);
        m.setAtorCoadjuvante(ac);
        m.setGenero(g);
        m.setClassificacaoEtaria(ce);
        m.setTipo(t);
        m.setClassificacaoInterna(ci);
        
        try {
            dao = new MidiaDAO();
            
            List<Midia> lista = dao.listarTodos();
            
            m = dao.obterPorId(Utils.getLong("1"));
            
            for (Midia midia : lista) {
                System.out.println(midia.getId() + midia.getTitulo() + midia.getAnoLancamento()
                        + " " + midia.getCodigoBarras() + " " + midia.getDuracaoMinutos() + " " + midia.getAtorPrincipal().getId() 
                        + " " + midia.getAtorCoadjuvante().getId() + " " + midia.getGenero().getId() + " " +
                        midia.getClassificacaoEtaria().getId() + " " + midia.getTipo().getId() + " " +
                        midia.getClassificacaoInterna().getId());
            }
            
            System.out.println(m.getId() + m.getTitulo() + m.getAnoLancamento()
                        + m.getCodigoBarras() + m.getDuracaoMinutos() + m.getAtorPrincipal().getId() 
                        + m.getAtorCoadjuvante().getId() + m.getGenero().getId() +
                        m.getClassificacaoEtaria().getId() + m.getTipo().getId() +
                        m.getClassificacaoInterna().getId());

            
        } catch (SQLException e ){
            e.printStackTrace();
        } finally {
            if ( dao != null ) {
                try{
                    dao.close();
                } catch (SQLException e ) {
                    e.printStackTrace();
                }
            }  
        }
    }
}
