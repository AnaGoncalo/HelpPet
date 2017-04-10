package MyApplicationService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.AnimalDAO;
import DAO.EncontroDAO;
import Modelo.Encontro;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Soundbank;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
/**
 *
 * @author Ana Gonçalo
 */
@Path("encontro")
public class EncontroService {
    // "http://localhost:8080/TesteWS/rest/encontro"
   @GET
   public String listarEncontros() throws SQLException
   {
       List<Encontro> encontros = EncontroDAO.ListarEncontros();
       
       Gson gson = new Gson();
       String json = gson.toJson(encontros);
       
       return json;
   } 
   
   // "http://localhost:8080/TesteWS/rest/encontro"
   @POST
   public String cadastrarEncontro(String json) throws SQLException{
       Gson gson = new Gson();
       Encontro a = gson.fromJson(json, Encontro.class);
       //a.setIdUsuario(1);
       System.out.println("Ainda não deu certo" + a.getAdotante().getIdUsuario());
       //System.out.println("Deu certo " + a.getIdAnimal());
       EncontroDAO.CadastrarEncontro(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/encontro"
   @PUT
   public String editarEncontro(String json) throws SQLException{
       Gson gson = new Gson();
       Encontro a = gson.fromJson(json, Encontro.class);
       
       EncontroDAO.EditarEncontro(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/encontro/{idEncontro}"
   @DELETE
   @Path("{idEncontro}")
   public String excluirEncontro(@PathParam("idEncontro") int idEncontro) throws SQLException{
       Gson gson = new Gson();
       //Encontro a = gson.fromJson(json, Encontro.class);
       System.out.println("EncontroService excluir" + idEncontro);
       
       EncontroDAO.ExcluirEncontro(idEncontro);
       
       String jsonSaida = gson.toJson(idEncontro);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/encontro/{idUsuario}"
   @GET
   @Path("{idUsuario}")
   public String listarPorUsuario(@PathParam("idUsuario") int idUsuario){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(EncontroDAO.ListarPorUsuario(idUsuario));
       } catch (SQLException ex) {
           Logger.getLogger(EncontroService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
}
