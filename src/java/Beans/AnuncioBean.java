/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.AnuncioDAO;
import Modelo.Anuncio;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
public class AnuncioBean {
    private Anuncio anuncio = new Anuncio();
    private List<Anuncio> anuncios = new ArrayList();
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");
    
    public AnuncioBean() {
        Listar();
    }
    
    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/anuncio");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Anuncio[] vetor = gson.fromJson(json, Anuncio[].class);
        anuncios = Arrays.asList(vetor);
        
    }
    
    public void MeusAnuncios(){
        try {
            anuncios = AnuncioDAO.ListarPorUsuario(user.getIdUsuario());
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Salvar(){
        anuncio.setIdUsuario(user.getIdUsuario());
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/anuncio");
        Gson gson = new Gson();
        String json = gson.toJson(anuncio);
        caminho.request().post(Entity.json(json));
        
        Listar();
    }

    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
  
    public HttpSession getSession(){
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }
    
    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
    
    
    
}
