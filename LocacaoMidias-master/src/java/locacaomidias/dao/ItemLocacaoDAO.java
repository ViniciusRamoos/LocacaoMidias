package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Midia;

/**
 *
 * @author User
 */
public class ItemLocacaoDAO extends DAO<ItemLocacao> {
    
    public ItemLocacaoDAO() throws SQLException{
    }

    @Override
    public void salvar(ItemLocacao obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO
                item_locacao(
                    locacao_id, 
                    exemplar_codigo_interno, 
                    valor )
                VALUES ( ?, ?, ? )
                """);
        
        stmt.setLong(1, obj.getLocacao().getId());
        stmt.setLong(2, obj.getExemplar().getCodigoInterno());
        stmt.setBigDecimal(3, obj.getValor());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(ItemLocacao obj) throws SQLException {
    }

    @Override
    public void excluir(ItemLocacao obj) throws SQLException {
    }

    @Override
    public List<ItemLocacao> listarTodos() throws SQLException {
        return null;
    }

    @Override
    public ItemLocacao obterPorId(Long id) throws SQLException {
        return null;
    }
    
    public List<ItemLocacao> obterPorIdLocacao(Long idLocacao) throws SQLException{
        
        List<ItemLocacao> itensLocacao = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
                    il.valor valorItemLocacao,
                    e.codigo_interno idExemplar,
                    e.disponivel disponivelExemplar,
                    m.id idMidia
                FROM
                    item_locacao il,
                    exemplar e,
                    midia m
                WHERE
                    il.exemplar_codigo_interno = e.codigo_interno AND
                    e.midia_id = m.id AND 
                    il.locacao_id = ?;
                """);
        
        stmt.setLong(1, idLocacao);
        
        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {
            
            ItemLocacao il = new ItemLocacao();
            Exemplar e = new Exemplar();
            Midia m = new Midia();
            
            il.setValor(rs.getBigDecimal("valorItemLocacao"));
            il.setExemplar(e);
            
            m.setId(rs.getLong("idMidia"));
            
            e.setCodigoInterno(rs.getLong("idExemplar"));
            e.setDisponivel(rs.getBoolean("disponivelExemplar"));
            e.setMidia(m);
            
            itensLocacao.add(il);
        }
        
        rs.close();
        stmt.close();
        
        return itensLocacao;
    }
}
