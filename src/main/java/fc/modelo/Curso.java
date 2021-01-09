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
public class Curso {

    private int idcurso;
    private String nome_curso, descricao;
    private Date data_inicio, data_fim;
    private Tipo_Curso tipo_Curso;
    private Double preco=0.0;
    

    public Curso() {
        
    }

    public Curso(int idcurso, String nome_curso, String descricao, Date data_inicio, Date data_fim, Tipo_Curso tipo_Curso, Double preco) {
        this.idcurso = idcurso;
        this.nome_curso = nome_curso;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.tipo_Curso = tipo_Curso;
        this.preco = preco;
    }

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public Tipo_Curso getTipo_Curso() {
        return tipo_Curso;
    }

    public void setTipo_Curso(Tipo_Curso tipo_Curso) {
        this.tipo_Curso = tipo_Curso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    
}
