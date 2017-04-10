/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.ModeloTeste;
import com.google.gson.Gson;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class testeBean {
    private String teste;
    private String testePost;
    
    /**
     * Creates a new instance of testeBean
     */
    public testeBean() {
    }
    public void testar()
    {
        Client cliente = ClientBuilder.newClient();
        
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/myapp");
        String json = caminho.request().get(String.class);
        System.out.println(json);
        teste = json;
    }
    public void testar2()
    {
        Client cliente = ClientBuilder.newClient();
        
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/myapp");
        ModeloTeste m = new ModeloTeste(testePost);
        Gson gson = new Gson();
        String json = gson.toJson(m);
        caminho.request().post(Entity.json(json));
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
    public String getTestePost() {
        return testePost;
    }

    public void setTestePost(String testePost) {
        this.testePost = testePost;
    }
}
