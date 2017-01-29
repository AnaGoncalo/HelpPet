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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Ana Gonçalo
 */
@Path("logar")
public class LogarService {
    
    // "http://localhost:8080/TesteWS/rest/logar"
   @POST
   public String logarUsuario(String json){
       Gson gson = new Gson();
       Usuario user = gson.fromJson(json, Usuario.class);
       
       Usuario usuarioLogado = new Usuario();
       UsuarioDAO dao = new UsuarioDAO();
       
       String email = user.getEmail();
       String senha = user.getSenha();
       System.out.println("Deu certo " + email + " " + senha);
       
       try {
           usuarioLogado = dao.logar(email, senha);
       } catch (SQLException ex) {
           Logger.getLogger(LogarService.class.getName()).log(Level.SEVERE, null, ex);
       }
       System.out.println("Service: Logou? " + usuarioLogado.getNomeUsuario());
       String jsonSaida = gson.toJson(usuarioLogado);
       return jsonSaida;
       
   }
    
}
