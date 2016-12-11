/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Evento;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.util.Arrays;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class EventoBean {
    private Evento evento = new Evento();
    private List<Evento> eventos = new ArrayList();

    public EventoBean() {
        Listar();
    }
    
    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/evento");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Evento[] vetor = gson.fromJson(json, Evento[].class);
        eventos = Arrays.asList(vetor);
        
    }
    
    public void Salvar(){
        Client cliente = ClientBuilder.newClient();
        
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/evento");
        
        Gson gson = new Gson();
        
        String json = gson.toJson(evento);
        
        caminho.request().post(Entity.json(json));
        
        Listar();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    
    
}
