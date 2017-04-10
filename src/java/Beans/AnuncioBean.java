/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Anuncio;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
    
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");
    
    private List<Anuncio> anuncios = new ArrayList();
    private List<Anuncio> anunciosTop = new ArrayList();
    private List<Anuncio> meusAnuncios = new ArrayList();
    
    private Part imagem;
    
    public AnuncioBean() {
        Listar();
        
        if(anuncios.size() > 4){
            anunciosTop = anuncios.subList(1, 5);
        }
        else{
            anunciosTop = anuncios;
        }
        
        if(user != null){
            ListarMeusAnuncios();
        }
    }
    
    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/anuncio");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Anuncio[] vetor = gson.fromJson(json, Anuncio[].class);
        anuncios = Arrays.asList(vetor);
    }
    
    public void ListarMeusAnuncios(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/anuncio/" + user.getIdUsuario());
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Anuncio[] vetor = gson.fromJson(json, Anuncio[].class);
        meusAnuncios = Arrays.asList(vetor);
    }
    
    public String Salvar(){
        if(user == null){
            return "loginSignin.jsf";
        }
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/anuncio");
        Gson gson = new Gson();
        
        if(imagem != null){
            anuncio.setFotoAnuncio(upload());
        }
        
        if(anuncio.getIdAnuncio() == 0)
        {
            anuncio.setIdUsuario(user.getIdUsuario());
            if(imagem == null){
                anuncio.setFotoAnuncio("imagens\\anuncio.jpg");
            }
            String json = gson.toJson(anuncio);
            caminho.request().post(Entity.json(json));
        }
        else
        {
            anuncio.setIdUsuario(user.getIdUsuario());
            String json = gson.toJson(anuncio);
            caminho.request().put(Entity.json(json));
        }
        
        Listar();
        ListarMeusAnuncios();
        return "meusAnuncios.jsf";
    }
    
    public String VerAnuncio(Anuncio a){
        anuncio = a;
        return "anuncio.jsf";
    }
    
    public String EditarAnuncio(Anuncio a){
        anuncio = a;
        return "editarAnuncio.jsf";
    }
    
    public String ExcluirAnuncio(Anuncio a){
        System.out.println("Bean Anuncio Excluir " + a.getIdAnuncio());
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/anuncio/" + a.getIdAnuncio());
        caminho.request().delete();
        
        return "meusAnuncios.jsf";
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

    public List<Anuncio> getAnunciosTop() {
        return anunciosTop;
    }

    public void setAnunciosTop(List<Anuncio> anunciosTop) {
        this.anunciosTop = anunciosTop;
    }

    public List<Anuncio> getMeusAnuncios() {
        return meusAnuncios;
    }

    public void setMeusAnuncios(List<Anuncio> meusAnuncios) {
        this.meusAnuncios = meusAnuncios;
    }

    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }
    
    public String upload() {

        String nomeArquivoSaida = "D:\\Netbeans\\HelpPet\\web\\imagens\\" + anuncio.getTituloAnuncio() + ".jpg";// + imagem.getSubmittedFileName();
        //produto.setDescricao(imagem.getSubmittedFileName());
        try (InputStream is = imagem.getInputStream();
                OutputStream out = new FileOutputStream(nomeArquivoSaida)) {

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (IOException e) {
            //FacesUtil.addErrorMessage("Erro ao enviar arquivo.");
        }
        return "imagens\\" + anuncio.getTituloAnuncio() + ".jpg";
    }
}
