package locacaomidias.testesdao;

import java.sql.SQLException;
import java.util.List;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
public class ItemLocacaoTeste {
    
    
    public static void main(String[] args) {

        ItemLocacao item1 = new ItemLocacao();
        ItemLocacao item2 = new ItemLocacao();
        
        Exemplar e1 = new Exemplar();
        Exemplar e2 = new Exemplar();
        
        Locacao l = new Locacao();
        
        ItemLocacaoDAO dao = null;
        
        l.setId(Utils.getLong("2"));
        
        e1.setCodigo_interno(Utils.getLong("1"));
        e2.setCodigo_interno(Utils.getLong("10"));
        
        item1.setLocacao(l);
        item1.setExemplar(e1);
        item1.setValor(Utils.getBigDecimal("10"));
        
        item2.setLocacao(l);
        item2.setExemplar(e2);
        item2.setValor(Utils.getBigDecimal("20"));
        
        try {
            dao = new ItemLocacaoDAO();
            
            List<ItemLocacao> lista = dao.obterPorIdLocacao(Utils.getLong("2"));
            
            for (ItemLocacao item : lista ){
                System.out.println(item.getExemplar().getCodigo_interno() + " " + item.getValor());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ( dao != null ) {
                try {
                    dao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
