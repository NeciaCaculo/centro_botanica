/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.EventoDAO;
import fc.modelo.Evento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

@Named(value = "eventoMB")
@SessionScoped
public class EventoMB implements Serializable {

    Evento evento = new Evento();
    EventoDAO eventoDao = new EventoDAO();
    List<Evento> listaEventos = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
        listaEventos = eventoDao.findAll();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    
    
    
    
    
    
    public String insert() {
        eventoDao.insert(evento);
        evento = new Evento();
        return "evento-lista?faces-redirect=true";
    }

    public String eliminar() {
        eventoDao.delete(evento);
        evento = new Evento();
        return null;
    }

    public String prepararEditar() {
        return "evento-editar";
    }

    public String editar() {
        eventoDao.update(evento);
        evento = new Evento();
        return "evento-lista?faces-redirect=true";
    }
    
//     public List<SelectItem> getOpSexos() {
//        List<SelectItem> list = new ArrayList<>();
//        for (Sexo sexo : Sexo.values()) {
//            list.add(new SelectItem(sexo, sexo.getExtensao()));
//        }
//        return list;
//    }
}
