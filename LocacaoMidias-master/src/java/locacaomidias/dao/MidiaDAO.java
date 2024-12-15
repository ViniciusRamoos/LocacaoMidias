package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;


public class MidiaDAO extends DAO<Midia>{
    
    public MidiaDAO() throws SQLException{
    }

    @Override
    public void salvar(Midia obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO
                midia(
                    titulo,
                    ano_lancamento,
                    codigo_barras,
                    duracao_em_minutos,
                    ator_principal,
                    ator_coadjuvante,
                    genero_id,
                    classificacao_etaria_id,
                    tipo_id,
                    classificacao_interna_id)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """,
                new String[]{ "insert_id" });
        
        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setString(3, obj.getCodigoBarras());
        stmt.setLong(4, obj.getDuracaoMinutos());
        stmt.setLong(5, obj.getAtorPrincipal().getId());
        stmt.setLong(6, obj.getAtorCoadjuvante().getId());
        stmt.setLong(7, obj.getGenero().getId());
        stmt.setLong(8, obj.getClassificacaoEtaria().getId());
        stmt.setLong(9, obj.getTipo().getId());
        stmt.setLong(10, obj.getClassificacaoInterna().getId());
        
        stmt.executeUpdate();
        obj.setId(Utils.getChavePrimariaAposInsercao(stmt, "insert_id"));
        stmt.close();
    }

    @Override
    public void atualizar(Midia obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                UPDATE midia
                SET
                    titulo = ?,
                    ano_lancamento = ?,
                    codigo_barras = ?,
                    duracao_em_minutos = ?,
                    ator_principal = ?,
                    ator_coadjuvante = ?,
                    genero_id = ?,
                    classificacao_etaria_id = ?,
                    tipo_id = ?,
                    classificacao_interna_id = ?
                WHERE
                    id = ?;
                """);
        
        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setString(3, obj.getCodigoBarras());
        stmt.setLong(4, obj.getDuracaoMinutos());
        stmt.setLong(5, obj.getAtorPrincipal().getId());
        stmt.setLong(6, obj.getAtorCoadjuvante().getId());
        stmt.setLong(7, obj.getGenero().getId());
        stmt.setLong(8, obj.getClassificacaoEtaria().getId());
        stmt.setLong(9, obj.getTipo().getId());
        stmt.setLong(10, obj.getClassificacaoInterna().getId());
        stmt.setLong(11, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Midia obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM midia
                WHERE
                    id = ?;
                """);
        
        stmt.setLong(1, obj.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Midia> listarTodos() throws SQLException {
        
        List<Midia> lista = new ArrayList<>();
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
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
                    midia m,
                    ator ap,
                    ator ac,
                    genero g,
                    classificacao_etaria ce,
                    tipo t,
                    classificacao_interna ci
                WHERE
                    m.ator_principal = ap.id AND
                    m.ator_coadjuvante = ac.id AND
                    m.genero_id = g.id AND
                    m.classificacao_etaria_id = ce.id AND
                    m.tipo_id = t.id AND
                    m.classificacao_interna_id = ci.id
                ORDER BY
                    m.titulo, m.ano_lancamento;
                """);
        
        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {
            
            Midia m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
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
            t.setDescricao(rs.getString("descricaoTipo"));
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));
            
            lista.add(m);
            
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }

    @Override
    public Midia obterPorId(Long id) throws SQLException {
        
        Midia m = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
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
                    midia m,
                    ator ap,
                    ator ac,
                    genero g,
                    classificacao_etaria ce,
                    tipo t,
                    classificacao_interna ci
                WHERE
                    m.ator_principal = ap.id AND
                    m.ator_coadjuvante = ac.id AND
                    m.genero_id = g.id AND
                    m.classificacao_etaria_id = ce.id AND
                    m.tipo_id = t.id AND
                    m.classificacao_interna_id = ci.id AND
                    m.id = ?
                ORDER BY
                    m.titulo, m.ano_lancamento;
                """);
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if ( rs.next() ) {
            
            m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
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
            t.setDescricao(rs.getString("descricaoTipo"));
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));
                        
        }
        
        rs.close();
        stmt.close();
        
        return m;
    }
    
}
