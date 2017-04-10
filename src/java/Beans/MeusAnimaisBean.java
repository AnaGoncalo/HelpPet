/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Animal;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class MeusAnimaisBean {

    private List<Animal> meusAnimais = new ArrayList();                                 //Lista de animais do Usuario
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");        // Usuario que está logado na sessão

    /**
     * Creates a new instance of MeusAnimaisBean
     */
    public MeusAnimaisBean() {
        if (user != null) {
            Listar();
        }
    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public void Listar() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/animal/" + user.getIdUsuario());
        String json = caminho.request().get(String.class);
        Gson gson = new Gson();
        Animal[] vetor = gson.fromJson(json, Animal[].class);
        meusAnimais = Arrays.asList(vetor);
    }

    public List<Animal> getMeusAnimais() {
        return meusAnimais;
    }

    public void setMeusAnimais(List<Animal> meusAnimais) {
        this.meusAnimais = meusAnimais;
    }

}
