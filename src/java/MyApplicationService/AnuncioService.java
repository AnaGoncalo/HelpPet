/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import DAO.AnuncioDAO;
import Modelo.Anuncio;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Ana Gonçalo
 */
@Path("anuncio")
public class AnuncioService {
    // "http://localhost:8080/TesteWS/rest/anuncio"
   @GET
   public String listarAnuncio() throws SQLException
   {
       List<Anuncio> anuncios = AnuncioDAO.ListarAnuncios();
       
       Gson gson = new Gson();
       String json = gson.toJson(anuncios);
       
       return json;
   } 
   
   // "http://localhost:8080/TesteWS/rest/anuncio"
   @POST
   public String cadastrarEstoque(String json) throws SQLException{
       Gson gson = new Gson();
       Anuncio a = gson.fromJson(json, Anuncio.class);
       a.setIdUsuario(1);
       System.out.println("Deu certo " + a.getTituloAnuncio());
       AnuncioDAO.CadastrarAnuncio(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
   
   // "http://localhost:8080/TesteWS/rest/anuncio/{idUsuario}"
   @GET
   @Path("{idUsuario}")
   public String listarPorUsuario(@PathParam("idUsuario") int idUsuario){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(AnuncioDAO.ListarPorUsuario(idUsuario));
       } catch (SQLException ex) {
           Logger.getLogger(AnuncioService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
}