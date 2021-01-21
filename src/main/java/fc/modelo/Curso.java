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
    private String nome_curso, descricao, modalidade_pagto, documentos, topicos;
    private Date data_inicio, data_fim;
    private TipoCurso tipo_Curso;
    private Double preco=0.0;
    

    public Curso() {
        
    }

    public Curso(int idcurso, String nome_curso, String descricao, String modalidade_pagto, String documentos, String topicos, Date data_inicio, Date data_fim, TipoCurso tipo_Curso) {
        this.idcurso = idcurso;
        this.nome_curso = nome_curso;
        this.descricao = descricao;
        this.modalidade_pagto = modalidade_pagto;
        this.documentos = documentos;
        this.topicos = topicos;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.tipo_Curso = tipo_Curso;
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

    public String getModalidade_pagto() {
        return modalidade_pagto;
    }

    public void setModalidade_pagto(String modalidade_pagto) {
        this.modalidade_pagto = modalidade_pagto;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getTopicos() {
        return topicos;
    }

    public void setTopicos(String topicos) {
        this.topicos = topicos;
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

    public TipoCurso getTipo_Curso() {
        return tipo_Curso;
    }

    public void setTipo_Curso(TipoCurso tipo_Curso) {
        this.tipo_Curso = tipo_Curso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    

    

    
}
