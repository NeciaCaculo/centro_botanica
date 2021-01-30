/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.TipoCursoDAO;
import fc.modelo.TipoCurso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

@Named(value = "tipoCursoMB")
@SessionScoped
public class TipoCursoMB implements Serializable {

    TipoCurso tipoCurso = new TipoCurso();
    TipoCursoDAO tipoCursoDao = new TipoCursoDAO();
    List<TipoCurso> listaTipoCursos = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
      //  listaTipoCursos = tipoCursoDao.findAll();
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public List<TipoCurso> getListaTipoCursos() {
        return listaTipoCursos;
    }

    public void setListaTipoCursos(List<TipoCurso> listaTipoCursos) {
        this.listaTipoCursos = listaTipoCursos;
    }

    
    
    
    
    
    
    public String insert() {
        tipoCursoDao.insert(tipoCurso);
        tipoCurso = new TipoCurso();
        return "tipoCurso-lista?faces-redirect=true";
    }

    public String eliminar() {
        tipoCursoDao.delete(tipoCurso);
        tipoCurso = new TipoCurso();
        return null;
    }

    public String prepararEditar() {
        return "tipoCurso-editar";
    }

    public String editar() {
        tipoCursoDao.update(tipoCurso);
        tipoCurso = new TipoCurso();
        return "tipoCurso-lista?faces-redirect=true";
    }
    
//     public List<SelectItem> getOpSexos() {
//        List<SelectItem> list = new ArrayList<>();
//        for (Sexo sexo : Sexo.values()) {
//            list.add(new SelectItem(sexo, sexo.getExtensao()));
//        }
//        return list;
//    }
}
