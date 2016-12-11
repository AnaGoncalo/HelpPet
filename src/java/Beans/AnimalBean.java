/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.AnimalDAO;
import Modelo.Animal;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class AnimalBean {
    private Animal animal = new Animal();
    private List<Animal> animais = new ArrayList();
    /**
     * Creates a new instance of AnimalBean
     */
    public AnimalBean() throws SQLException {
        Listar();
    }
    
    public void Salvar(){
        Client cliente = ClientBuilder.newClient();
        
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/animal");
        
        Gson gson = new Gson();
        
        String json = gson.toJson(animal);
        
        caminho.request().post(Entity.json(json));
        
        Listar();
        
    }
    
    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/animal");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Animal[] vetor = gson.fromJson(json, Animal[].class);
        animais = Arrays.asList(vetor);
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
    
    
}
