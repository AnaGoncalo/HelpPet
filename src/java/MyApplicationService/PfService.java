/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import DAO.PessoaFisicaDAO;
import DAO.UsuarioDAO;
import Modelo.PessoaFisica;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Ana Gonçalo
 */
@Path("pessoaFisica")
public class PfService {
   @GET
   @Path("{idUsuario}")
   public String listarPorUsuario(@PathParam("idUsuario") int idUsuario){
       
       Gson gson = new Gson();
       String json = null;
       try {
           json = gson.toJson(UsuarioDAO.buscarPF(idUsuario));
       } catch (SQLException ex) {
           Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return json;
   }
   // "http://localhost:8080/TesteWS/rest/pessoaFisica"
   @PUT
   public String editarUsuario(String json) throws SQLException{
       Gson gson = new Gson();
       PessoaFisica u = gson.fromJson(json, PessoaFisica.class);
       
       System.out.println("Deu certo " + u.getNomeUsuario());
       PessoaFisicaDAO.editarPF(u);
       
       String jsonSaida = gson.toJson(u);
       return jsonSaida;
   }
    
}
