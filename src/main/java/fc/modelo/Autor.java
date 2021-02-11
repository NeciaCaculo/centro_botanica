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
public class Autor {

    private int pkAutor;
    private String nome, sobrenome, nacionalidade, grauFormacao;
    private Date dataNacimento;
    

    public Autor() {
        
    }

    public Autor( int pkAutor, String nome, String sobrenome, String nacionalidade, String grauFormacao, Date dataNacimento )
    {
        this.pkAutor = pkAutor;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nacionalidade = nacionalidade;
        this.grauFormacao = grauFormacao;
        this.dataNacimento = dataNacimento;
    }

    public int getPkAutor()
    {
        return pkAutor;
    }

    public void setPkAutor( int pkAutor )
    {
        this.pkAutor = pkAutor;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public String getSobrenome()
    {
        return sobrenome;
    }

    public void setSobrenome( String sobrenome )
    {
        this.sobrenome = sobrenome;
    }

    public String getNacionalidade()
    {
        return nacionalidade;
    }

    public void setNacionalidade( String nacionalidade )
    {
        this.nacionalidade = nacionalidade;
    }

    public String getGrauFormacao()
    {
        return grauFormacao;
    }

    public void setGrauFormacao( String grauFormacao )
    {
        this.grauFormacao = grauFormacao;
    }

    public Date getDataNacimento()
    {
        return dataNacimento;
    }

    public void setDataNacimento( Date dataNacimento )
    {
        this.dataNacimento = dataNacimento;
    }

    
}
