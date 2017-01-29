/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Ana Gonçalo
 */
public class Experiencia {
    private int idExperiencia;
    private String tituloExperiencia;
    private String tipoExperiencia;
    private String texto;
    private String foto;
    private String dataCadastro;
    private boolean statusExperiencia;
    private int idUsuario;

    public Experiencia() {
    }

    public Experiencia(int idExperiencia, String tituloExperiencia, String tipoExperiencia, String texto, String foto, String dataCadastro, boolean statusExperiencia, int idUsuario) {
        this.idExperiencia = idExperiencia;
        this.tituloExperiencia = tituloExperiencia;
        this.tipoExperiencia = tipoExperiencia;
        this.texto = texto;
        this.foto = foto;
        this.dataCadastro = dataCadastro;
        this.statusExperiencia = statusExperiencia;
        this.idUsuario = idUsuario;
    }

    public int getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getTituloExperiencia() {
        return tituloExperiencia;
    }

    public void setTituloExperiencia(String tituloExperiencia) {
        this.tituloExperiencia = tituloExperiencia;
    }

    public String getTipoExperiencia() {
        return tipoExperiencia;
    }

    public void setTipoExperiencia(String tipoExperiencia) {
        this.tipoExperiencia = tipoExperiencia;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isStatusExperiencia() {
        return statusExperiencia;
    }

    public void setStatusExperiencia(boolean statusExperiencia) {
        this.statusExperiencia = statusExperiencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
