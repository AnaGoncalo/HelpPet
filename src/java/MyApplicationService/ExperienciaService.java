/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import DAO.ExperienciaDAO;
import Modelo.Experiencia;
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
@Path("experiencia")
public class ExperienciaService {
    // "http://localhost:8080/TesteWS/rest/experiencia"
   @GET
   public String listarExperiencias() throws SQLException
   {
       List<Experiencia> experiencias = ExperienciaDAO.ListarExperiencias();
       
       Gson gson = new Gson();
       String json = gson.toJson(experiencias);
       
       return json;
   } 
   // "http://localhost:8080/TesteWS/rest/experiencia"
   @POST
   public String cadastrarExperiencia(String json) throws SQLException{
       Gson gson = new Gson();
       Experiencia a = gson.fromJson(json, Experiencia.class);
       a.setIdUsuario(1);
       System.out.println("Deu certo " + a.getTituloExperiencia());
       ExperienciaDAO.CadastrarExperiencia(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
       
   }
}
