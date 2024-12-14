package locacaomidias.testesdao;

import java.sql.SQLException;
import java.util.List;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
public class ExemplarTeste {
    
    public static void main(String[] args) {
        
        Exemplar exemplar = new Exemplar();
        Midia m = new Midia();
        
        ExemplarDAO dao = null;
        
        m.setId(Utils.getLong("1"));
        
        exemplar.setCodigo_interno(Utils.getLong("11"));
        exemplar.setMidia(m);
        exemplar.setDisponivel(false);
        
        try {
            dao = new ExemplarDAO();
            
            List<Exemplar> lista = dao.listarTodos();
            
            exemplar = dao.obterPorId(Utils.getLong("7"));
            
            for (Exemplar e : lista) {
                System.out.println(e.getCodigo_interno() + " " + e.getDisponivel() + " " + e.getMidia().getId());
            }
            
            System.out.println(exemplar.getCodigo_interno() + " " + exemplar.getDisponivel() + " " + exemplar.getMidia().getId());
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if ( dao != null ) {
                try {
                    dao.close();
                } catch (SQLException e ){
                    e.printStackTrace();
                }
            }
        }
    }
}
