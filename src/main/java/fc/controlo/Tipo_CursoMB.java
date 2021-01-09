/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.Tipo_CursoDAO;
import fc.modelo.Tipo_Curso;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

@Named(value = "tipo_CursoMB")
@RequestScoped
public class Tipo_CursoMB {

    Tipo_Curso tipo_Curso = new Tipo_Curso();
    Tipo_CursoDAO tipo_Cursodao = new Tipo_CursoDAO();
    List<Tipo_Curso> listaTipo_Curso = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
        listaTipo_Curso = tipo_Cursodao.findAll();
    }

    public Tipo_Curso getTipo_Curso() {
        return tipo_Curso;
    }

    public void setTipo_Curso(Tipo_Curso tipo_Curso) {
        this.tipo_Curso = tipo_Curso;
    }

    public List<Tipo_Curso> getListaTipo_Curso() {
        
        return listaTipo_Curso;
    }

    public void setListaTipo_Curso(List<Tipo_Curso> listaTipo_Curso) {
        this.listaTipo_Curso = listaTipo_Curso;
    }

    public String insert() {
        tipo_Cursodao.insert(tipo_Curso);
        tipo_Curso = new Tipo_Curso();
        return "tipo_Curso?faces-redirect=true";
    }

    public String startEdit() {
        return "tipo_Curso-edit";
    }

    public String finishEdit() {
        tipo_Cursodao.update(tipo_Curso);
        return "tipo_Curso";
    }
    
    public String delete() {
        tipo_Cursodao.delete(tipo_Curso);
        return "tipo_Curso?faces-redirect=true";
    }
    
    public List<SelectItem> getSelectTipo_Cursos() {
        List<SelectItem> lista = new ArrayList<>();
        for (Tipo_Curso m : tipo_Cursodao.findAll()) {
            lista.add(new SelectItem(m, m.getDesignacao()));
        }
        return lista;
    }


}
