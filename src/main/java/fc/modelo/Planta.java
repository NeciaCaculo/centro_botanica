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
public class Planta {

    private int pk_planta;
    private  String nome, descricao, nome_imagem, caminho_imagem, localizacao, utilidade;
    private GrupoPlanta grupoPlanta;
    private EstadoConservacao estadoConservacao;
    

    public Planta() {
        
    }

    public Planta( int pk_planta, String nome, String descricao, String nome_imagem, String caminho_imagem, String localizacao, String utilidade, GrupoPlanta grupoPlanta, EstadoConservacao estadoConservacao )
    {
        this.pk_planta = pk_planta;
        this.nome = nome;
        this.descricao = descricao;
        this.nome_imagem = nome_imagem;
        this.caminho_imagem = caminho_imagem;
        this.localizacao = localizacao;
        this.utilidade = utilidade;
        this.grupoPlanta = grupoPlanta;
        this.estadoConservacao = estadoConservacao;
    }

    public int getPk_planta()
    {
        return pk_planta;
    }

    public void setPk_planta( int pk_planta )
    {
        this.pk_planta = pk_planta;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao( String descricao )
    {
        this.descricao = descricao;
    }

    public String getNome_imagem()
    {
        return nome_imagem;
    }

    public void setNome_imagem( String nome_imagem )
    {
        this.nome_imagem = nome_imagem;
    }

    public String getCaminho_imagem()
    {
        return caminho_imagem;
    }

    public void setCaminho_imagem( String caminho_imagem )
    {
        this.caminho_imagem = caminho_imagem;
    }

    public String getLocalizacao()
    {
        return localizacao;
    }

    public void setLocalizacao( String localizacao )
    {
        this.localizacao = localizacao;
    }

    public String getUtilidade()
    {
        return utilidade;
    }

    public void setUtilidade( String utilidade )
    {
        this.utilidade = utilidade;
    }

    public GrupoPlanta getGrupoPlanta()
    {
        return grupoPlanta;
    }

    public void setGrupoPlanta( GrupoPlanta grupoPlanta )
    {
        this.grupoPlanta = grupoPlanta;
    }

    public EstadoConservacao getEstadoConservacao()
    {
        return estadoConservacao;
    }

    public void setEstadoConservacao( EstadoConservacao estadoConservacao )
    {
        this.estadoConservacao = estadoConservacao;
    }
    
    

    

    
}
