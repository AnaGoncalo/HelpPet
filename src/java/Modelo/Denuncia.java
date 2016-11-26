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
public class Denuncia {
    private int idDenuncia;
    private String tituloDenuncia;
    private String descricaoDenuncia;
    private String fotoDenuncia;
    private String tipoDenuncia;
    private Date dataDenuncia;
    private int idLocalizacao;

    public Denuncia() {
    }

    public Denuncia(int idDenuncia, String tituloDenuncia, String descricaoDenuncia, String fotoDenuncia, String tipoDenuncia, Date dataDenuncia, int idLocalizacao) {
        this.idDenuncia = idDenuncia;
        this.tituloDenuncia = tituloDenuncia;
        this.descricaoDenuncia = descricaoDenuncia;
        this.fotoDenuncia = fotoDenuncia;
        this.tipoDenuncia = tipoDenuncia;
        this.dataDenuncia = dataDenuncia;
        this.idLocalizacao = idLocalizacao;
    }

    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getTituloDenuncia() {
        return tituloDenuncia;
    }

    public void setTituloDenuncia(String tituloDenuncia) {
        this.tituloDenuncia = tituloDenuncia;
    }

    public String getDescricaoDenuncia() {
        return descricaoDenuncia;
    }

    public void setDescricaoDenuncia(String descricaoDenuncia) {
        this.descricaoDenuncia = descricaoDenuncia;
    }

    public String getFotoDenuncia() {
        return fotoDenuncia;
    }

    public void setFotoDenuncia(String fotoDenuncia) {
        this.fotoDenuncia = fotoDenuncia;
    }

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public Date getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(Date dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }

    public int getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(int idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }
    
    
}
