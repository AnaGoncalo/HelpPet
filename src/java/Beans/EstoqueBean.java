/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.EstoqueDAO;
import Modelo.Estoque;
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
public class EstoqueBean {
    private Estoque estoque = new Estoque();
    private List<Estoque> estoques = new ArrayList();
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");

    public EstoqueBean(){
        Listar();
    }

    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/estoque");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Estoque[] vetor = gson.fromJson(json, Estoque[].class);
        estoques = Arrays.asList(vetor);
        
    }
    
    public void MeusEstoques(){
        try {
            estoques = EstoqueDAO.ListarPorUsuario(user.getIdUsuario());
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Salvar(){
        estoque.setIdUsuario(user.getIdUsuario());
        Client cliente = ClientBuilder.newClient();
        
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/estoque");
        
        Gson gson = new Gson();
        
        String json = gson.toJson(estoque);
        
        caminho.request().post(Entity.json(json));
        
        Listar();
    }
    
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
  
    public HttpSession getSession(){
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }
    
    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }
    
    
    
}
