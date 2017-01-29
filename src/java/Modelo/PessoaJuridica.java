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
public class PessoaJuridica extends Usuario{
    private int idClinicaPetshop;
    private String cnpj;
    private String funcionamento;
    private String descricao;
    private String site;

    public PessoaJuridica() {
    }

    public PessoaJuridica(int idClinicaPetshop, String cnpj, String funcionamento, String descricao, String site) {
        this.idClinicaPetshop = idClinicaPetshop;
        this.cnpj = cnpj;
        this.funcionamento = funcionamento;
        this.descricao = descricao;
        this.site = site;
    }

    public PessoaJuridica(int idClinicaPetshop, String cnpj, String funcionamento, String descricao, String site, int idUsuario, 
            String nomeUsuario, String email, String senha, String dataNascimento, String foto, String localizacao, String telefone, int idPermissao) {
        super(idUsuario, nomeUsuario, email, senha, dataNascimento, foto, localizacao, telefone, idPermissao);
        this.idClinicaPetshop = idClinicaPetshop;
        this.cnpj = cnpj;
        this.funcionamento = funcionamento;
        this.descricao = descricao;
        this.site = site;
    }

    public int getIdClinicaPetshop() {
        return idClinicaPetshop;
    }

    public void setIdClinicaPetshop(int idClinicaPetshop) {
        this.idClinicaPetshop = idClinicaPetshop;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFuncionamento() {
        return funcionamento;
    }

    public void setFuncionamento(String funcionamento) {
        this.funcionamento = funcionamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    
    
}
