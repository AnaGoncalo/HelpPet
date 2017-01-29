/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Denuncia;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
public class DenunciaBean {
    private Denuncia denuncia = new Denuncia();
    
    private List<Denuncia> denuncias = new ArrayList();
    private List<Denuncia> denunciasTop = new ArrayList();

    /**
     * Creates a new instance of DenunciaBean
     */
    public DenunciaBean() {
        Listar();
        
        if(denuncias.size() > 4){
            denunciasTop = denuncias.subList(1, 5);
        }
        else{
            denunciasTop = denuncias;
        }
    }
    
    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/denuncia");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Denuncia[] vetor = gson.fromJson(json, Denuncia[].class);
        denuncias = Arrays.asList(vetor);
    }
    
    public String Salvar(){
        
        Client cliente = ClientBuilder.newClient();
        
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/denuncia");
        
        Gson gson = new Gson();
        
        String json = gson.toJson(denuncia);
        
        caminho.request().post(Entity.json(json));
        
        Listar();
        
        return "buscarDenuncia.jsf";
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(List<Denuncia> denuncias) {
        this.denuncias = denuncias;
    }

    public List<Denuncia> getDenunciasTop() {
        return denunciasTop;
    }

    public void setDenunciasTop(List<Denuncia> denunciasTop) {
        this.denunciasTop = denunciasTop;
    }
    
}
