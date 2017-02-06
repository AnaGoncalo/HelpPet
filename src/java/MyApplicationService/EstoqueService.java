/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

/**
 *
 * @author Ana Gonçalo
 */
import DAO.EstoqueDAO;
import Modelo.Estoque;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("estoque")
public class EstoqueService {
    // "http://localhost:8080/TesteWS/rest/estoque"
   @GET
   public String listarEstoque() throws SQLException
   {
       List<Estoque> estoques = EstoqueDAO.ListarEstoques();
       
       Gson gson = new Gson();
       String json = gson.toJson(estoques);
       
       return json;
   } 
   
   // "http://localhost:8080/TesteWS/rest/estoque"
   @POST
   public String cadastrarEstoque(String json) throws SQLException{
       Gson gson = new Gson();
       Estoque a = gson.fromJson(json, Estoque.class);
       //a.setIdUsuario(1);
       System.out.println("Deu certo " + a.getNomeEstoque());
       EstoqueDAO.CadastrarEstoque(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/estoque"
   @PUT
   public String editarEstoque(String json) throws SQLException{
       Gson gson = new Gson();
       Estoque a = gson.fromJson(json, Estoque.class);
       
       System.out.println("Deu certo " + a.getNomeEstoque());
       EstoqueDAO.EditarEstoque(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/estoque/{idEstoque}"
   @DELETE
   @Path("{idEstoque}")
   public String excluirEstoque(@PathParam("idEstoque") int idEstoque) throws SQLException{
       Gson gson = new Gson();
       //Estoque a = gson.fromJson(json, Estoque.class);
       
       System.out.println("Deu certo " + idEstoque);
       EstoqueDAO.ExcluirEstoque(idEstoque);
       
       String jsonSaida = gson.toJson(idEstoque);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/estoque/{idUsuario}"
   @GET
   @Path("{idUsuario}")
   public String listarPorUsuario(@PathParam("idUsuario") int idUsuario){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(EstoqueDAO.ListarPorUsuario(idUsuario));
       } catch (SQLException ex) {
           Logger.getLogger(EstoqueService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
}
