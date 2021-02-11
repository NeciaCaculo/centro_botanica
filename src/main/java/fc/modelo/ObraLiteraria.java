/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.modelo;

import java.util.Date;

/**
 *
 * @author Domingos Dala Vunge
 */
public class ObraLiteraria {

    private int pk_obra_literaria;
    private  String titulo, descricao, nome_imagem, caminho_imagem, status, edicao;
    private Usuario usuario;
    private Date data_lancamento;
    

    public ObraLiteraria() {
        
    }

    public ObraLiteraria(int pk_obra_literaria, String titulo, String descricao, String nome_imagem, String caminho_imagem, String status, String edicao, Usuario usuario, Date data_lancamento) {
        this.pk_obra_literaria = pk_obra_literaria;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nome_imagem = nome_imagem;
        this.caminho_imagem = caminho_imagem;
        this.status = status;
        this.edicao = edicao;
        this.usuario = usuario;
        this.data_lancamento = data_lancamento;
    }

    public int getPk_obra_literaria() {
        return pk_obra_literaria;
    }

    public void setPk_obra_literaria(int pk_obra_literaria) {
        this.pk_obra_literaria = pk_obra_literaria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome_imagem() {
        return nome_imagem;
    }

    public void setNome_imagem(String nome_imagem) {
        this.nome_imagem = nome_imagem;
    }

    public String getCaminho_imagem() {
        return caminho_imagem;
    }

    public void setCaminho_imagem(String caminho_imagem) {
        this.caminho_imagem = caminho_imagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    
    

    

    
}
