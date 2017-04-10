/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Experiencia;
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
public class ExperienciaBean {
    private Experiencia experiencia = new Experiencia();
    private Usuario autor = null;
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");
    
    private List<Experiencia> experiencias = new ArrayList();
    private List<Experiencia> minhasExperiencias = new ArrayList();
    
    private Part imagem;
    
    public ExperienciaBean() {
        Listar();
        
        if(user != null){
            ListarMinhasExperiencias();
        }
    }
    
    public void Listar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/experiencia");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Experiencia[] vetor = gson.fromJson(json, Experiencia[].class);
        experiencias = Arrays.asList(vetor);
    }
    
    public void ListarMinhasExperiencias(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/experiencia/" + user.getIdUsuario());
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Experiencia[] vetor = gson.fromJson(json, Experiencia[].class);
        minhasExperiencias = Arrays.asList(vetor);
    }
    
    public String Salvar(){
        if(user == null)
        {
            return "loginSignin.jsf";
        }
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/experiencia");
        Gson gson = new Gson();
        
        if(imagem != null){
            experiencia.setFoto(upload());
        }
        
        if(experiencia.getIdExperiencia() == 0)
        {
            experiencia.setIdUsuario(user.getIdUsuario());
            if(imagem == null){
                experiencia.setFoto("imagens\\experiencia.jpg");
            }
            String json = gson.toJson(experiencia);
            caminho.request().post(Entity.json(json));
        }
        else
        {
            experiencia.setIdUsuario(user.getIdUsuario());
            String json = gson.toJson(experiencia);
            caminho.request().put(Entity.json(json));
        }
        
        Listar();
        ListarMinhasExperiencias();
        return "minhasExperiencias.jsf";
    }
    
    public String VerExperiencia(Experiencia e){
        experiencia = e;
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/usuario/" + experiencia.getIdUsuario());
        String json = caminho.request().get(String.class);
        Gson gson = new Gson();
        autor = gson.fromJson(json, Usuario.class);
        return "experiencia.jsf";
    }
    
    public String EditarExperiencia(Experiencia e){
        experiencia = e;
        return "editarExperiencia.jsf";
    }
    
    public String ExcluirExperiencia(Experiencia e){
        System.out.println("Bean Experiencia Excluir " + e.getIdExperiencia());
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/experiencia/" + e.getIdExperiencia());
        caminho.request().delete();
        
        ListarMinhasExperiencias();
        return "minhasExperiencias.jsf";
    }
    
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
  
    public HttpSession getSession(){
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public List<Experiencia> getMinhasExperiencias() {
        return minhasExperiencias;
    }

    public void setMinhasExperiencias(List<Experiencia> minhasExperiencias) {
        this.minhasExperiencias = minhasExperiencias;
    }

    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }
    
    public String upload() {

        String nomeArquivoSaida = "D:\\Netbeans\\HelpPet\\web\\imagens\\" + experiencia.getTituloExperiencia() + ".jpg";// + imagem.getSubmittedFileName();
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
        return "imagens\\" + experiencia.getTituloExperiencia() + ".jpg";
    }
    
}
