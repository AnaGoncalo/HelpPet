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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
       a.setIdUsuario(1);
       System.out.println("Deu certo " + a.getNomeEstoque());
       EstoqueDAO.CadastrarEstoque(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
}
