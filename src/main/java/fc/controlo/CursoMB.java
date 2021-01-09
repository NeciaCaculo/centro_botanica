/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.CursoDAO;
import fc.modelo.Curso;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "cursoMB")
@RequestScoped
public class CursoMB {

    Curso curso = new Curso();
    CursoDAO cursodao = new CursoDAO();
    List<Curso> listaCurso = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
        listaCurso = cursodao.findAll();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getListaCurso() {
        
        return listaCurso;
    }

    public void setListaCurso(List<Curso> listaCurso) {
        this.listaCurso = listaCurso;
    }

    public String insert() {
        cursodao.insert(curso);
        curso = new Curso();
        return "curso?faces-redirect=true";
    }

    public String startEdit() {
        return "curso-edit";
    }

    public String finishEdit() {
        cursodao.update(curso);
        return "curso";
    }
    
    public String delete() {
        cursodao.delete(curso);
        return "curso?faces-redirect=true";
    }

}
