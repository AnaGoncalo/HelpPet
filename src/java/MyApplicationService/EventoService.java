/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import DAO.EventoDAO;
import Modelo.Evento;
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
@Path("evento")
public class EventoService {
    // "http://localhost:8080/TesteWS/rest/evento"
   @GET
   public String listarEvento() throws SQLException
   {
       List<Evento> eventos = EventoDAO.ListarEventos();
       
       Gson gson = new Gson();
       String json = gson.toJson(eventos);
       
       return json;
   } 
   
   // "http://localhost:8080/TesteWS/rest/evento"
   @POST
   public String cadastrarEvento(String json) throws SQLException{
       Gson gson = new Gson();
       Evento a = gson.fromJson(json, Evento.class);
       System.out.println("Deu certo " + a.getNomeEvento());
       EventoDAO.CadastrarEvento(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/evento"
   @PUT
   public String editarEvento(String json) throws SQLException{
       Gson gson = new Gson();
       Evento a = gson.fromJson(json, Evento.class);
       System.out.println("Deu certo " + a.getNomeEvento());
       EventoDAO.EditarEvento(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/evento/{idEvento}"
   @DELETE
   @Path("{idEvento}")
   public String excluirEvento(@PathParam("idEvento") int idEvento) throws SQLException{
       Gson gson = new Gson();
       //Evento a = gson.fromJson(json, Evento.class);
       
       System.out.println("Deu certo " + idEvento);
       EventoDAO.ExcluirEvento(idEvento);
       
       String jsonSaida = gson.toJson(idEvento);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/evento/{idUsuario}"
   @GET
   @Path("{idUsuario}")
   public String listarPorUsuario(@PathParam("idUsuario") int idUsuario){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(EventoDAO.ListarPorUsuario(idUsuario));
       } catch (SQLException ex) {
           Logger.getLogger(EventoService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
}
