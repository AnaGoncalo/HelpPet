/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Encontro;
import Modelo.Animal;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class EncontroBean {
    private Encontro encontro;
    private Animal animal;
    private List<Encontro> encontros = new ArrayList();
    
    public EncontroBean() {
    }
    
    public String Adotar(Animal a){
        animal = a;
        encontro.setIdUsuario(1); //pega o usuario da sessao
        
        return "cadastrarEncontro.jsf";
    }
    
    public void Salvar(){
        Client cliente = ClientBuilder.newClient();
        
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/encontro");
        
        Gson gson = new Gson();
        
        String json = gson.toJson(encontro);
        
        caminho.request().post(Entity.json(json));
        
    }
    
    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/encontro");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Encontro[] vetor = gson.fromJson(json, Encontro[].class);
        encontros = Arrays.asList(vetor);
    }

    public Encontro getEncontro() {
        return encontro;
    }

    public void setEncontro(Encontro encontro) {
        this.encontro = encontro;
    }

    public List<Encontro> getEncontros() {
        return encontros;
    }

    public void setEncontros(List<Encontro> encontros) {
        this.encontros = encontros;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    
    
}
