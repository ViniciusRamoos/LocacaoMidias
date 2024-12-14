package locacaomidias.testesdao;

import java.sql.SQLException;
import java.util.List;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
public class LocacaoTeste {
    
    public static void main(String[] args) {
        
        Locacao locacao = new Locacao();
        Cliente c = new Cliente();
        
        LocacaoDAO dao = null;
        
        c.setId(Utils.getLong("2"));
        
        locacao.setDataInicio(Utils.getDate("2002-01-02"));
        locacao.setDataFim(Utils.getDate("2002-01-03"));
        locacao.setCancelada(true);
        locacao.setCliente(c);
        
        try {
            dao = new LocacaoDAO();
            
            dao.salvar(locacao);
            
            List<Locacao> lista = dao.listarTodos();
            
            locacao = dao.obterPorId(Utils.getLong("1"));
            
            for (Locacao l : lista ) {
                System.out.println(l.getId() + " " + l.getDataInicio() + " " + l.getDataFim()+ " " + l.getCancelada() + " " + l.getCliente().getId());
            }
            
            System.out.println(locacao.getId() + " " + locacao.getDataInicio() + " " + locacao.getDataFim()+ " " + locacao.getCancelada() + " " + locacao.getCliente().getId());
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( dao != null ) {
                try {
                    dao.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
