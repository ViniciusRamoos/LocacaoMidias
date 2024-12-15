package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.AtorDAO;
import locacaomidias.entidades.Ator;

/**
 *
 * @author User
 */
public class AtorServices {
    
    
    public List<Ator> getTodos() {
        
        List<Ator> lista = new ArrayList<>();
        
        try ( AtorDAO dao = new AtorDAO() ) {
            
            lista = dao.listarTodos();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        
        return lista;
    }
}
