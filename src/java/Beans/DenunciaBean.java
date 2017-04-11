/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Denuncia;
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
import javax.faces.event.ValueChangeEvent;
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
public class DenunciaBean {
    private Denuncia denuncia = new Denuncia();
    
    private List<Denuncia> denuncias = new ArrayList();
    private List<Denuncia> denunciasTop = new ArrayList();
    
    private Part imagem;
    
    private String tipo = "Todos";
    
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
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/denuncia");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Denuncia[] vetor = gson.fromJson(json, Denuncia[].class);
        denuncias = Arrays.asList(vetor);
    }
    
    public void FiltrarTipo(ValueChangeEvent e){
        tipo = e.getNewValue().toString();
        Filtrar();
    }
    
    public void Filtrar(){
        Listar();
        
        List<Denuncia> filtro = new ArrayList();
        for(Denuncia d : denuncias){
            if(d.getTipoDenuncia().equals(tipo)){
                filtro.add(d);
            }
            else if(tipo.equals("Todos")){
                filtro.add(d);
            }
        }
        
        denuncias = filtro;
    }
    
    public String Salvar(){
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/denuncia");
        Gson gson = new Gson();
        
        if(imagem != null){
            denuncia.setFotoDenuncia(upload());
        }
        else{
            denuncia.setFotoDenuncia("imagens\\denuncie.jpg");
        }
        
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
    
    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String upload() {

        String nomeArquivoSaida = "D:\\Netbeans\\HelpPet\\web\\imagens\\" + denuncia.getTituloDenuncia() + ".jpg";// + imagem.getSubmittedFileName();
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
        return "imagens\\" + denuncia.getTituloDenuncia() + ".jpg";
    }
    
}
