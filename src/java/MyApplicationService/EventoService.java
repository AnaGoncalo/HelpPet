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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
       a.setIdUsuario(1);
       a.setIdLocalizacao(1);
       System.out.println("Deu certo " + a.getNomeEvento());
       EventoDAO.CadastrarEvento(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
}
