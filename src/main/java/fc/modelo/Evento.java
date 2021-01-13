/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.modelo;

import java.util.Date;

/**
 *
 * @author Nécia
 */
public class Evento {

    private int pk_evento;
    private String titulo, descricao, nome_imagem, caminho_imagem;
    private Date data_evento;
    private Usuario usuario;
    
    

    public Evento() {
        
    }

    public Evento(int pk_evento, String titulo, String descricao, String nome_imagem, String caminho_imagem, Date data_evento, Usuario usuario) {
        this.pk_evento = pk_evento;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nome_imagem = nome_imagem;
        this.caminho_imagem = caminho_imagem;
        this.data_evento = data_evento;
        this.usuario = usuario;
    }

    public int getPk_evento() {
        return pk_evento;
    }

    public void setPk_evento(int pk_evento) {
        this.pk_evento = pk_evento;
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

    public Date getData_evento() {
        return data_evento;
    }

    public void setData_evento(Date data_evento) {
        this.data_evento = data_evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

    

    

    
}
