/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyApplicationService;

import Modelo.ModeloTeste;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Ana Gonçalo
 */
// "http://localhost:8080/TesteWS/rest/myapp"
@Path("myapp")
public class MyAppService {
    
    @GET
    public String Testar() {
        return "Oii sou um servico";
    }
    
    //http://localhost:8080/TesteWS/rest/myapp"
    @POST
    public String Testar2(String json) {
        Gson gson = new Gson();
        ModeloTeste mt = gson.fromJson(json, ModeloTeste.class);
        System.out.println("Deu certo " + mt.getNomeTeste());
        //ModeloTesteDAO.salvar(mt);
        
        String jsonSaida = gson.toJson(mt);
        return "ok";
        //return "Oi post";
    }
}
