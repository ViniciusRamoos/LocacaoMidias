package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.entidades.Exemplar;

/**
 *
 * @author User
 */
public class ExemplarServices {
    
    public List<Exemplar> getTodos() {
        
        List<Exemplar> lista = new ArrayList<>();
        
        try ( ExemplarDAO dao = new ExemplarDAO() ) {
            
            lista = dao.listarTodos();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        
        return lista;
    }
    
}
