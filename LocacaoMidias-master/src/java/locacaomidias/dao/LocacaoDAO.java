package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
public class LocacaoDAO extends DAO<Locacao>{
    
    public LocacaoDAO() throws SQLException{
    }

    @Override
    public void salvar(Locacao obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO
                locacao(
                    data_inicio,
                    data_fim,
                    cancelada,
                    cliente_id )
                VALUES ( ?, ?, ?, ? );
                """,
                new String[] { "insert_id" });
        
        stmt.setDate(1, obj.getDataInicio());
        stmt.setDate(2, obj.getDataFim());
        stmt.setBoolean(3, obj.getCancelada());
        stmt.setLong(4, obj.getCliente().getId());
        
        stmt.executeUpdate();
        obj.setId(Utils.getChavePrimariaAposInsercao(stmt, "insert_id"));
        stmt.close();
    }

    @Override
    public void atualizar(Locacao obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                UPDATE locacao
                SET
                    data_inicio = ?,
                    data_fim = ?,
                    cancelada = ?,
                    cliente_id = ?
                WHERE
                    id = ?;
                """);
        
        stmt.setDate(1, obj.getDataInicio());
        stmt.setDate(2, obj.getDataFim());
        stmt.setBoolean(3, obj.getCancelada());
        stmt.setLong(4, obj.getCliente().getId());
        stmt.setLong(5, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Locacao obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM locacao
                WHERE 
                    id = ?;
                """);
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Locacao> listarTodos() throws SQLException {
        
        List<Locacao> lista = new ArrayList<>();
        
        PreparedStatement stmt =  getConnection().prepareStatement(
                """
                SELECT
                    l.id idLocacao,
                    l.data_inicio dataInicioLocacao,
                    l.data_fim dataFimLocacao,
                    l.cancelada canceladaLocacao,
                    c.id idCliente,
                    c.nome nomeCliente,
                    c.sobrenome sobrenomeCliente,
                    c.data_nascimento dataNascimentoCliente,
                    c.cpf CPFCliente,
                    c.email emailCliente,
                    c.logradouro logradouroCliente,
                    c.numero numeroCliente,
                    c.bairro bairroCliente,
                    c.cep CEPCliente,
                    ci.id idCidade,
                    ci.nome nomeCidade,
                    e.id idEstado,
                    e.nome nomeEstado,
                    e.sigla siglaEstado
                FROM
                    locacao l,
                    cliente c,
                    cidade ci,
                    estado e
                WHERE
                    l.cliente_id = cliente.id AND
                    c.cidade_id = ci.id AND
                    ci.estado_id = e.id
                ORDER BY
                    l.data_inicio, l.data_fim;
                """);
        
        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {
            
            Locacao l = new Locacao();
            Cliente c = new Cliente();
            Cidade ci = new Cidade();
            Estado e = new Estado();
            
            l.setId(rs.getLong("idLocacao"));
            l.setDataInicio(rs.getDate("dataInicioLocacao"));
            l.setDataFim(rs.getDate("dataFimLocacao"));
            l.setCancelada(rs.getBoolean("canceladaLocacao"));
            l.setCliente(c);
            
            c.setId(rs.getLong("idCliente"));
            c.setNome(rs.getString("nomeCliente"));
            c.setSobrenome(rs.getString("sobrenomeCliente"));
            c.setDataNascimento(rs.getDate("dataNascimentoCliente"));
            c.setCpf(rs.getString("CPFCliente"));
            c.setEmail(rs.getString("emailCliente"));
            c.setLogradouro(rs.getString("logradouroCliente"));
            c.setNumero(rs.getString("numeroCliente"));
            c.setBairro(rs.getString("bairroCliente"));
            c.setCep(rs.getString("CEPCliente"));
            c.setCidade(ci);
            
            ci.setId(rs.getLong("idCidade"));
            ci.setNome(rs.getString("nomeCidade"));
            ci.setEstado(e);
            
            e.setId(rs.getLong("idEstado"));
            e.setNome(rs.getString("nomeEstado"));
            e.setSigla(rs.getString("siglaEstado"));
            
            lista.add(l);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }

    @Override
    public Locacao obterPorId(Long id) throws SQLException {
        
        Locacao l = null;
        
        PreparedStatement stmt =  getConnection().prepareStatement(
                """
                SELECT
                    l.id idLocacao,
                    l.data_inicio dataInicioLocacao,
                    l.data_fim dataFimLocacao,
                    l.cancelada canceladaLocacao,
                    c.id idCliente,
                    c.nome nomeCliente,
                    c.sobrenome sobrenomeCliente,
                    c.data_nascimento dataNascimentoCliente,
                    c.cpf CPFCliente,
                    c.email emailCliente,
                    c.logradouro logradouroCliente,
                    c.numero numeroCliente,
                    c.bairro bairroCliente,
                    c.cep CEPCliente,
                    ci.id idCidade,
                    ci.nome nomeCidade,
                    e.id idEstado,
                    e.nome nomeEstado,
                    e.sigla siglaEstado
                FROM
                    locacao l,
                    cliente c,
                    cidade ci,
                    estado e
                WHERE
                    l.cliente_id = cliente.id AND
                    c.cidade_id = ci.id AND
                    ci.estado_id = e.id AND
                    l.id = ?
                ORDER BY
                    l.data_inicio, l.data_fim;
                """);
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {
            
            l = new Locacao();
            Cliente c = new Cliente();
            Cidade ci = new Cidade();
            Estado e = new Estado();
            
            l.setId(rs.getLong("idLocacao"));
            l.setDataInicio(rs.getDate("dataInicioLocacao"));
            l.setDataFim(rs.getDate("dataFimLocacao"));
            l.setCancelada(rs.getBoolean("canceladaLocacao"));
            l.setCliente(c);
            
            c.setId(rs.getLong("idCliente"));
            c.setNome(rs.getString("nomeCliente"));
            c.setSobrenome(rs.getString("sobrenomeCliente"));
            c.setDataNascimento(rs.getDate("dataNascimentoCliente"));
            c.setCpf(rs.getString("CPFCliente"));
            c.setEmail(rs.getString("emailCliente"));
            c.setLogradouro(rs.getString("logradouroCliente"));
            c.setNumero(rs.getString("numeroCliente"));
            c.setBairro(rs.getString("bairroCliente"));
            c.setCep(rs.getString("CEPCliente"));
            c.setCidade(ci);
            
            ci.setId(rs.getLong("idCidade"));
            ci.setNome(rs.getString("nomeCidade"));
            ci.setEstado(e);
            
            e.setId(rs.getLong("idEstado"));
            e.setNome(rs.getString("nomeEstado"));
            e.setSigla(rs.getString("siglaEstado"));
            
        }
        
        rs.close();
        stmt.close();
        
        return l;
    }
    
}
