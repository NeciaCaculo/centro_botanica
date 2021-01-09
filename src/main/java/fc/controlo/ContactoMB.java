/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.ContactoDAO;
import fc.modelo.Contacto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

@Named(value = "contactoMB")
@SessionScoped
public class ContactoMB implements Serializable {

    Contacto contacto = new Contacto();
    ContactoDAO contactoDao = new ContactoDAO();
    List<Contacto> listaContactos = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
      //  listaContactos = contactoDao.findAll();
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    
    
    
    
    
    
    public String insert() {
        contactoDao.insert(contacto);
        contacto = new Contacto();
        return "contacto-lista?faces-redirect=true";
    }

    public String eliminar() {
        contactoDao.delete(contacto);
        contacto = new Contacto();
        return null;
    }

    public String prepararEditar() {
        return "contacto-editar";
    }

    public String editar() {
        contactoDao.update(contacto);
        contacto = new Contacto();
        return "contacto-lista?faces-redirect=true";
    }
    
//     public List<SelectItem> getOpSexos() {
//        List<SelectItem> list = new ArrayList<>();
//        for (Sexo sexo : Sexo.values()) {
//            list.add(new SelectItem(sexo, sexo.getExtensao()));
//        }
//        return list;
//    }
}
