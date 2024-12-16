package locacaomidias.controladores;

import java.sql.Date;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;
import java.time.LocalDate;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Locacao;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
@WebServlet(name = "LocacoesServlet", urlPatterns = {"/processaLocacoes"})
public class LocacoesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try ( LocacaoDAO daoLocacao = new LocacaoDAO();
              ClienteDAO daoCliente = new ClienteDAO();
              ItemLocacaoDAO daoItemLocacao = new ItemLocacaoDAO();
              ExemplarDAO daoExemplar = new ExemplarDAO();
              MidiaDAO daoMidia = new MidiaDAO();
              ClassificacaoInternaDAO daoCI = new ClassificacaoInternaDAO()) {
            
            if ( acao.equals("inserir") ) {
                
                Long idCliente = Utils.getLong(request, "idCliente");
                String itensLocacao = request.getParameter("itensLocacao");
                String dataFim = request.getParameter("dataFim");
                
                JsonReader jsr = Json.createReader(
                    new StringReader (itensLocacao));
                
                JsonArray jsaItensLocacao = jsr.readArray();
                
                Cliente cliente = daoCliente.obterPorId(idCliente);
                
                Locacao locacao = new Locacao();
                locacao.setDataInicio(Date.valueOf(LocalDate.now()));
                locacao.setDataFim(Utils.getDate(dataFim));
                locacao.setCancelada(false);
                locacao.setCliente(cliente);
                
                Utils.validar(locacao, "id");
                daoLocacao.salvar(locacao);
                
                for ( JsonValue jsv : jsaItensLocacao ) {
                    
                    JsonObject jso = jsv.asJsonObject();
                    
                    Long codigoInterno = Utils.getLong(
                            jso.getString("codigoInterno"));
                    Exemplar exemplar = daoExemplar.obterPorId(codigoInterno);
                    exemplar.setDisponivel(false);
                    
                    Long idMidia = exemplar.getMidia().getId();
                    Midia midia = daoMidia.obterPorId(idMidia);
                    
                    ClassificacaoInterna ci = daoCI.obterPorId(
                            midia.getClassificacaoInterna().getId());
                    
                    ItemLocacao item = new ItemLocacao();
                    item.setLocacao(locacao);
                    item.setExemplar(exemplar);
                    item.setValor(ci.getValorAluguel());
                    
                    daoExemplar.atualizar(exemplar);
                    daoItemLocacao.salvar(item);
                    
                }
                
                disp = request.getRequestDispatcher(
                        "/formularios/locacoes/listagem.jsp" );
            } else if ( acao.equals("cancelar") ) {
                
                Long id = Utils.getLong(request, "id");
                
                System.out.println(id);
                
                Locacao locacao = daoLocacao.obterPorId(id);
                locacao.setCancelada(true);
                daoLocacao.atualizar(locacao);
                
                for (ItemLocacao il : daoItemLocacao.obterPorIdLocacao(id)) {
                    
                    Exemplar exemplar = il.getExemplar();
                    System.out.println(exemplar.getMidia().getId());
                    System.out.println(exemplar.getMidia().getTitulo());
                    exemplar.setDisponivel(true);                    
                    daoExemplar.atualizar(exemplar);
                }
                
                response.setContentType( "application/json;charset=UTF-8" );
                
                JsonObject jo = Json.createObjectBuilder()
                        .add( "status", "ok" )
                        .build();
                
                try ( PrintWriter out = response.getWriter() ) {
                    out.print( jo );
                }
            }
            
        } catch ( SQLException e ) {
            disp = Utils.prepararDespachoErro(request, e.getMessage());
        }
        
        if ( disp != null ) {
            disp.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
