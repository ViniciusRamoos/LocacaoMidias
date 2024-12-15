package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
public class ExemplarDAO extends DAO<Exemplar> {
    
    public ExemplarDAO() throws SQLException{
    }

    @Override
    public void salvar(Exemplar obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO 
                exemplar(
                    disponivel,
                    midia_id)
                VALUES ( ?, ? );
                """,
                new String[]{ "insert_id" });
        
        stmt.setBoolean(1, obj.getDisponivel());
        stmt.setLong(2, obj.getMidia().getId());
        
        stmt.execute();
        obj.setCodigoInterno(Utils.getChavePrimariaAposInsercao(stmt, "insert_id"));
        stmt.close();
    }

    @Override
    public void atualizar(Exemplar obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                UPDATE exemplar
                SET
                    disponivel = ?,
                    midia_id = ?
                WHERE 
                    codigo_interno = ?
                """);
        
        stmt.setBoolean(1, obj.getDisponivel());
        stmt.setLong(2, obj.getMidia().getId());
        stmt.setLong(3, obj.getCodigoInterno());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Exemplar obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM exemplar
                WHERE
                    codigo_interno = ?;
                """);
        
        stmt.setLong(1, obj.getCodigoInterno());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Exemplar> listarTodos() throws SQLException {
        
        List<Exemplar> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
                    e.codigo_interno idExemplar,
                    e.disponivel disponivelExemplar,
                    m.id idMidia,
                    m.titulo tituloMidia,
                    m.ano_lancamento anoLancamentoMidia,
                    m.codigo_barras codigoBarrasMidia,
                    m.duracao_em_minutos duracaoMinutos,
                    ap.id idAtorPrincipal,
                    ap.nome nomeAtorPrincipal,
                    ap.sobrenome sobrenomeAtorPrincipal,
                    ap.data_estreia dataEstreiaAtorPrincipal,
                    ac.id idAtorCoadjuvante,
                    ac.nome nomeAtorCoadjuvante,
                    ac.sobrenome sobrenomeAtorCoadjuvante,
                    ac.data_estreia dataEstreiaAtorCoadjuvante,
                    g.id idGenero,
                    g.descricao descricaoGenero,
                    ce.id idClassificacaoEtaria,
                    ce.descricao descricaoClassificacaoEtaria,
                    t.id idTipo,
                    t.descricao descricaoTipo,
                    ci.id idClassificacaoInterna,
                    ci.descricao descricaoClassificacaoInterna,
                    ci.valor_aluguel valorAluguelClassificacaoInterna
                FROM
                    exemplar e,
                    midia m,
                    ator ap,
                    ator ac,
                    genero g,
                    classificacao_etaria ce,
                    tipo t,
                    classificacao_interna ci
                WHERE
                    e.midia_id = m.id AND
                    m.ator_principal = ap.id AND
                    m.ator_coadjuvante = ac.id AND
                    m.genero_id = g.id AND
                    m.classificacao_etaria_id = ce.id AND
                    m.tipo_id = t.id AND
                    m.classificacao_interna_id = ci.id
                ORDER BY
                e.codigo_interno, e.disponivel;
                """);
        
        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {
            
            Exemplar e = new Exemplar();
            Midia m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            e.setCodigoInterno(rs.getLong("idExemplar"));
            e.setDisponivel(rs.getBoolean("disponivelExemplar"));
            e.setMidia(m);
            
            m.setId(rs.getLong("idMidia"));
            m.setTitulo(rs.getString("tituloMidia"));
            m.setAnoLancamento(rs.getString("anoLancamentoMidia"));
            m.setCodigoBarras(rs.getString("codigoBarrasMidia"));
            m.setDuracaoMinutos(rs.getLong("duracaoMinutos"));
            m.setAtorPrincipal(ap);
            m.setAtorCoadjuvante(ac);
            m.setGenero(g);
            m.setClassificacaoEtaria(ce);
            m.setTipo(t);
            m.setClassificacaoInterna(ci);
            
            ap.setId(rs.getLong("idAtorPrincipal"));
            ap.setNome(rs.getString("nomeAtorPrincipal"));
            ap.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            ap.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            
            ac.setId(rs.getLong("idAtorCoadjuvante"));
            ac.setNome(rs.getString("nomeAtorCoadjuvante"));
            ac.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            ac.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            
            g.setId(rs.getLong("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));
            
            ce.setId(rs.getLong("idClassificacaoEtaria"));
            ce.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            
            t.setId(rs.getLong("idTipo"));
            t.setDescricao("descricaoTipo");
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));
            
            lista.add(e);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }

    @Override
    public Exemplar obterPorId(Long id) throws SQLException {
        
        Exemplar e = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
                    e.codigo_interno idExemplar,
                    e.disponivel disponivelExemplar,
                    m.id idMidia,
                    m.titulo tituloMidia,
                    m.ano_lancamento anoLancamentoMidia,
                    m.codigo_barras codigoBarrasMidia,
                    m.duracao_em_minutos duracaoMinutos,
                    ap.id idAtorPrincipal,
                    ap.nome nomeAtorPrincipal,
                    ap.sobrenome sobrenomeAtorPrincipal,
                    ap.data_estreia dataEstreiaAtorPrincipal,
                    ac.id idAtorCoadjuvante,
                    ac.nome nomeAtorCoadjuvante,
                    ac.sobrenome sobrenomeAtorCoadjuvante,
                    ac.data_estreia dataEstreiaAtorCoadjuvante,
                    g.id idGenero,
                    g.descricao descricaoGenero,
                    ce.id idClassificacaoEtaria,
                    ce.descricao descricaoClassificacaoEtaria,
                    t.id idTipo,
                    t.descricao descricaoTipo,
                    ci.id idClassificacaoInterna,
                    ci.descricao descricaoClassificacaoInterna,
                    ci.valor_aluguel valorAluguelClassificacaoInterna
                FROM
                    exemplar e,
                    midia m,
                    ator ap,
                    ator ac,
                    genero g,
                    classificacao_etaria ce,
                    tipo t,
                    classificacao_interna ci
                WHERE
                    e.midia_id = m.id AND
                    m.ator_principal = ap.id AND
                    m.ator_coadjuvante = ac.id AND
                    m.genero_id = g.id AND
                    m.classificacao_etaria_id = ce.id AND
                    m.tipo_id = t.id AND
                    m.classificacao_interna_id = ci.id AND
                    e.codigo_interno = ?
                ORDER BY
                e.codigo_interno, e.disponivel;
                """);
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {
            
            e = new Exemplar();
            Midia m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            e.setCodigoInterno(rs.getLong("idExemplar"));
            e.setDisponivel(rs.getBoolean("disponivelExemplar"));
            e.setMidia(m);
            
            m.setId(rs.getLong("idMidia"));
            m.setTitulo(rs.getString("tituloMidia"));
            m.setAnoLancamento(rs.getString("anoLancamentoMidia"));
            m.setCodigoBarras(rs.getString("codigoBarrasMidia"));
            m.setDuracaoMinutos(rs.getLong("duracaoMinutos"));
            m.setAtorPrincipal(ap);
            m.setAtorCoadjuvante(ac);
            m.setGenero(g);
            m.setClassificacaoEtaria(ce);
            m.setTipo(t);
            m.setClassificacaoInterna(ci);
            
            ap.setId(rs.getLong("idAtorPrincipal"));
            ap.setNome(rs.getString("nomeAtorPrincipal"));
            ap.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            ap.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            
            ac.setId(rs.getLong("idAtorCoadjuvante"));
            ac.setNome(rs.getString("nomeAtorCoadjuvante"));
            ac.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            ac.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            
            g.setId(rs.getLong("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));
            
            ce.setId(rs.getLong("idClassificacaoEtaria"));
            ce.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            
            t.setId(rs.getLong("idTipo"));
            t.setDescricao("descricaoTipo");
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));
            
        }
        
        rs.close();
        stmt.close();
        
        return e;
    }
    
}
