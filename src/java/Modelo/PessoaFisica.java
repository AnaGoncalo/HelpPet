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
public class PessoaFisica extends Usuario {
    private int idHelper;
    private String cpf;

    public PessoaFisica() {
    }

    public PessoaFisica(int idHelper, String cpf) {
        this.idHelper = idHelper;
        this.cpf = cpf;
    }

    public PessoaFisica(int idHelper, String cpf, int idUsuario, String nomeUsuario, String email, String senha, String dataNascimento, String foto, String localizacao, String telefone, int idPermissao) {
        super(idUsuario, nomeUsuario, email, senha, dataNascimento, foto, localizacao, telefone, idPermissao);
        this.idHelper = idHelper;
        this.cpf = cpf;
    }

    public int getIdHelper() {
        return idHelper;
    }

    public void setIdHelper(int idHelper) {
        this.idHelper = idHelper;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
}
