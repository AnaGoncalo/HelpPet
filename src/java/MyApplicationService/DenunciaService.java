/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;


import DAO.DenunciaDAO;
import Modelo.Denuncia;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Ana Gonçalo
 */
@Path("denuncia")
public class DenunciaService {
   // "http://localhost:8080/TesteWS/rest/denuncia"
   @GET
   public String listarDenuncias() throws SQLException
   {
       List<Denuncia> denuncias = DenunciaDAO.ListarDenuncias();
       
       Gson gson = new Gson();
       String json = gson.toJson(denuncias);
       
       return json;
   } 
   // "http://localhost:8080/TesteWS/rest/denuncia"
   @POST
   public String cadastrarDenuncia(String json) throws SQLException{
       Gson gson = new Gson();
       Denuncia a = gson.fromJson(json, Denuncia.class);
       System.out.println("Deu certo " + a.getTituloDenuncia());
       DenunciaDAO.CadastrarDenuncia(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
       
   }
}
