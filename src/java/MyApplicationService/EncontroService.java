package MyApplicationService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.EncontroDAO;
import Modelo.Encontro;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.sound.midi.Soundbank;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
       a.setIdUsuario(1);
       a.setIdLocalizacao(1);
       System.out.println("Ainda não deu certo" + a.getHorarioEncontro());
       System.out.println("Deu certo " + a.getIdAnimal());
       EncontroDAO.CadastrarEncontro(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
   }
    
}
