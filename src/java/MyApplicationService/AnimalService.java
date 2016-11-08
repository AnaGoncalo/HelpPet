/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import DAO.AnimalDAO;
import Modelo.Animal;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Ana Gonçalo
 */
@Path("animal")
public class AnimalService {
   // "http://localhost:8080/TesteWS/rest/animal"
   @GET
   public String listar() throws SQLException
   {
       List<Animal> animais = AnimalDAO.ListarAnimaisNaoAdotados();
       
       Gson gson = new Gson();
       String json = gson.toJson(animais);
       
       return json;
   }
   // "http://localhost:8080/TesteWS/rest/animal/{codigo}"
   @GET
   @Path("{codigo}")
   public String listarByCod(@PathParam("codigo") String codigo){
       AnimalDAO animalDao = new AnimalDAO();
       
       Gson gson = new Gson();
       String json = gson.toJson(animalDao.listarByCod(codigo));
       
       return json;
   }
   // "http://localhost:8080/TesteWS/rest/animal"
   @POST
   public String cadastrarAnimal(String json) throws SQLException{
       Gson gson = new Gson();
       Animal a = gson.fromJson(json, Animal.class);
       a.setIdUsuario(1);
       a.setIdLocalizacao(1);
       System.out.println("Deu certo " + a.getNomeAnimal());
       AnimalDAO.CadastrarAnimal(a);
       
       String jsonSaida = gson.toJson(a);
       return jsonSaida;
       
   }
}
