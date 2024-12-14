package locacaomidias.testesdao;

import java.sql.SQLException;
import java.util.List;
import locacaomidias.dao.AtorDAO;
import locacaomidias.entidades.Ator;
import locacaomidias.utils.Utils;


public class AtorTeste {
    
    public static void main(String[] args) {
        
        Ator ator = new Ator();
        
        ator.setId(Utils.getLong("5"));
        ator.setNome("testea");
        ator.setSobrenome("testea");
        ator.setDataEstreia(Utils.getDate("2005-01-01"));
        
        AtorDAO dao = null;
        
        try {
            dao = new AtorDAO();
            
            List<Ator> lista = dao.listarTodos();
            
            ator = dao.obterPorId(Utils.getLong("1"));
            
            for (Ator a : lista){
                //System.out.println(a.getId() + a.getNome() + a.getSobrenome() + a.getDataEstreia());
            }
            
            System.out.println(ator.getId() + ator.getNome() + ator.getSobrenome() + ator.getDataEstreia());
            
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
