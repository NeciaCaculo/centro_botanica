/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.modelo;


/**
 *
 * @author Nécia Caculo
 */
public class Usuario {

    private int pkusuario;
    private String designacao, nomeCompleto, username, senha;
    
    public Usuario() {
    }

    public Usuario(int pkusuario, String designacao, String nomeCompleto, String username, String senha) {
        this.pkusuario = pkusuario;
        this.designacao = designacao;
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.senha = senha;
    }

    public int getPkusuario() {
        return pkusuario;
    }

    public void setPkusuario(int pkusuario) {
        this.pkusuario = pkusuario;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    
    
}
