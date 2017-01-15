/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.PessoaJuridica;
import DAO.PessoaJuridicaDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class PjBean {
    private List<PessoaJuridica> pjs = new ArrayList();
    private PessoaJuridica pessoa;
    
    public PjBean() {
        try {
            pjs = PessoaJuridicaDAO.listarOngs();
        } catch (SQLException ex) {
            Logger.getLogger(PjBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String VerOng(PessoaJuridica pj){
        pessoa = pj;
        return "ong.jsf";
    }
    
    public String VerClinica(PessoaJuridica pj){
        pessoa = pj;
        return "clinicaPetshop.jsf";
    }

    public List<PessoaJuridica> getPjs() {
        return pjs;
    }

    public void setPjs(List<PessoaJuridica> pjs) {
        this.pjs = pjs;
    }    

    public PessoaJuridica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaJuridica pessoa) {
        this.pessoa = pessoa;
    }
    
}
