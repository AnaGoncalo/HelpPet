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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
       //a.setIdUsuario(1);
       System.out.println("Deu certo " + a.getTituloExperiencia());
       ExperienciaDAO.CadastrarExperiencia(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
       
   }
   
   // "http://localhost:8080/TesteWS/rest/experiencia"
   @PUT
   public String editarExperiencia(String json) throws SQLException{
       Gson gson = new Gson();
       Experiencia a = gson.fromJson(json, Experiencia.class);
       
       ExperienciaDAO.EditarExperiencia(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/experiencia/{idExperiencia}"
   @DELETE
   @Path("{idExperiencia}")
   public String excluirExperiencia(@PathParam("idExperiencia") int idExperiencia) throws SQLException{
       Gson gson = new Gson();
       //Experiencia a = gson.fromJson(json, Experiencia.class);
       
       ExperienciaDAO.ExcluirExperiencia(idExperiencia);
       
       String jsonSaida = gson.toJson(idExperiencia);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/experiencia/{idUsuario}"
   @GET
   @Path("{idUsuario}")
   public String listarPorUsuario(@PathParam("idUsuario") int idUsuario){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(ExperienciaDAO.ListarPorUsuario(idUsuario));
       } catch (SQLException ex) {
           Logger.getLogger(ExperienciaService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
}
