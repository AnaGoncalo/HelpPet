/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Ana Gonçalo
 */
@Path("usuario")
public class UsuarioService {
    
    // "http://localhost:8080/TesteWS/rest/usuario"
   @POST
   public String cadastrarUsuario(String json) throws SQLException{
       Gson gson = new Gson();
       Usuario u = gson.fromJson(json, Usuario.class);
       
       System.out.println("Deu certo " + u.getNomeUsuario());
       UsuarioDAO.inserir(u);
       
       String jsonSaida = gson.toJson(u);
       return jsonSaida;
       
   }
    
}
