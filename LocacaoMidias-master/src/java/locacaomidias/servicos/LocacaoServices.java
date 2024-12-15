package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Locacao;

/**
 *
 * @author User
 */
public class LocacaoServices {
    
    public List<Locacao> getTodos(){
        
        List<Locacao> lista = new ArrayList<>();
        
        try ( LocacaoDAO dao = new LocacaoDAO() ) {
            
            lista = dao.listarTodos();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        
        return lista;
    }
}
