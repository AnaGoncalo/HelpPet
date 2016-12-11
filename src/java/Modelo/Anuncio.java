/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Ana Gonçalo
 */
public class Anuncio {
    private int idAnuncio;
    private String tituloAnuncio;
    private String descricaoAnuncio;
    private String fotoAnuncio;
    private String tipoAnuncio;
    private int idUsuario;

    public Anuncio() {
    }

    public Anuncio(int idAnuncio, String tituloAnuncio, String descricaoAnuncio, String fotoAnuncio, String tipoAnuncio, int idUsuario) {
        this.idAnuncio = idAnuncio;
        this.tituloAnuncio = tituloAnuncio;
        this.descricaoAnuncio = descricaoAnuncio;
        this.fotoAnuncio = fotoAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        this.idUsuario = idUsuario;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTituloAnuncio() {
        return tituloAnuncio;
    }

    public void setTituloAnuncio(String tituloAnuncio) {
        this.tituloAnuncio = tituloAnuncio;
    }

    public String getDescricaoAnuncio() {
        return descricaoAnuncio;
    }

    public void setDescricaoAnuncio(String descricaoAnuncio) {
        this.descricaoAnuncio = descricaoAnuncio;
    }

    public String getFotoAnuncio() {
        return fotoAnuncio;
    }

    public void setFotoAnuncio(String fotoAnuncio) {
        this.fotoAnuncio = fotoAnuncio;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
