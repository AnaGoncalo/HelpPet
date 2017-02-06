
package Beans;

import Modelo.PessoaFisica;
import Modelo.PessoaJuridica;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class LoginBean {

    private Usuario user = new Usuario();
    private Usuario usuarioLogado = null;
    private PessoaFisica pf = null;
    private PessoaJuridica pj = null;
    
    private String mensagem;
    private Part imagem;

    public LoginBean() {
    }

    public String VerPerfil() {
        if (usuarioLogado.getIdPermissao() == 1) {
            Client cliente = ClientBuilder.newClient();
            WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/pessoaFisica/" + usuarioLogado.getIdUsuario());
            String json = caminho.request().get(String.class);
            Gson gson = new Gson();
            pf = gson.fromJson(json, PessoaFisica.class);
            return "perfilHelper.jsf";
        } else {
            Client cliente = ClientBuilder.newClient();
            WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/pessoaJuridica/" + usuarioLogado.getIdUsuario());
            String json = caminho.request().get(String.class);
            Gson gson = new Gson();
            pj = gson.fromJson(json, PessoaJuridica.class);

            if (usuarioLogado.getIdPermissao() == 2) {
                return "perfilOng.jsf";
            } else {
                return "perfilClinica.jsf";
            }
        }
    }

    public String Logar() throws SQLException {

        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/logar");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        json = caminho.request().post(Entity.json(json), String.class);

        if (gson.fromJson(json, Usuario.class).getIdUsuario() != 0) {
            usuarioLogado = gson.fromJson(json, Usuario.class);
            System.out.println("Logou? " + usuarioLogado.getNomeUsuario());
            getSession().setAttribute("usuarioLogado", usuarioLogado);

            if (usuarioLogado.getIdPermissao() == 1) {
                caminho = cliente.target("http://localhost:8080/TesteWS/rest/pessoaFisica/" + usuarioLogado.getIdUsuario());
                json = caminho.request().get(String.class);
                pf = gson.fromJson(json, PessoaFisica.class);
            } else {
                caminho = cliente.target("http://localhost:8080/TesteWS/rest/pessoaJuridica/" + usuarioLogado.getIdUsuario());
                json = caminho.request().get(String.class);
                pj = gson.fromJson(json, PessoaJuridica.class);
            }
            mensagem = "";
            return "index.jsf";
        }
        mensagem = "Email e/ou senha incorreto(s)";
        return "loginSignin.jsf";
    }

    public String SalvarEditar() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/usuario");
        Gson gson = new Gson();

        //upload
        if(imagem != null){
            usuarioLogado.setFoto(upload());
        }
        
        String json = gson.toJson(usuarioLogado);
        json = caminho.request().put(Entity.json(json), String.class);
        getSession().setAttribute("usuarioLogado", usuarioLogado);

        System.out.println("qual a permissao " + usuarioLogado.getIdPermissao());
        if (usuarioLogado.getIdPermissao() == 1) {
            System.out.println("bean editar pj");
            pf.setIdHelper(usuarioLogado.getIdUsuario());
            caminho = cliente.target("http://localhost:8080/TesteWS/rest/pessoaFisica");
            json = gson.toJson(pf);
            json = caminho.request().put(Entity.json(json), String.class);
            return "perfilHelper.jsf";
        } else {
            caminho = cliente.target("http://localhost:8080/TesteWS/rest/pessoaJuridica");
            json = gson.toJson(pj);
            json = caminho.request().put(Entity.json(json), String.class);
            if (usuarioLogado.getIdPermissao() == 2) {
                return "perfilOng.jsf";
            } else {
                return "perfilClinica.jsf";
            }
        }
    }

    public String Deslogar() {
        usuarioLogado = null;
        getSession().setAttribute("usuarioLogado", usuarioLogado);

        return "index.jsf";
    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public PessoaFisica getPf() {
        return pf;
    }

    public void setPf(PessoaFisica pf) {
        this.pf = pf;
    }

    public PessoaJuridica getPj() {
        return pj;
    }

    public void setPj(PessoaJuridica pj) {
        this.pj = pj;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }

    public String upload() {

        String nomeArquivoSaida = "D:\\Netbeans\\TesteWS\\web\\imagens\\users\\" + usuarioLogado.getIdUsuario() + ".jpg";// + imagem.getSubmittedFileName();
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
        return "imagens\\users\\" + usuarioLogado.getIdUsuario() + ".jpg";
    }

}
