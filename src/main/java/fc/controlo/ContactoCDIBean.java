/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.ContactoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import fc.modelo.Contacto;


/**
 *
 * @author DGTALE
 */
    @Named(value = "contactoCDIBean")
@SessionScoped
public class ContactoCDIBean implements Serializable {

    Contacto contacto;
    ContactoDAO contactoDAO = new ContactoDAO();
    List<Contacto> contactos;

    @PostConstruct
    public void inicizalizar() {
        contactos = new ArrayList<>();
        contactos = contactoDAO.listaTodosContactos();
        contacto = new Contacto();
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    

    // CRUD - Create , Read , Update , Delete
    
    public String guardar() {
        contactoDAO.save(contacto);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String operacao = "Guardar";

        FacesMessage facesMessage
                = new FacesMessage(null, "Contacto Guardado com sucesso" + " " + operacao);

        facesContext.addMessage(null, facesMessage);
        
        contacto = new Contacto();
        
        return "inserirContacto";
    }
    
//     public String editar() {
//        contactoDAO.edit(contacto);
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        String operacao = "Editar";
//
//        FacesMessage facesMessage
//                = new FacesMessage(null, " Dados do contacto editados com sucesso" + " " + operacao);
//
//        facesContext.addMessage(null, facesMessage);
//        contacto = new Contacto();
//        return "contacto";
//    }
//
//     public String prepararEditar(){
//     
//         return "editar-contacto";
//     }
//     
//      public String eliminar() {
//        contactoDAO.delete(contacto);
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        String operacao = "Eliminar";
//
//        FacesMessage facesMessage
//                = new FacesMessage(null, " Dados do contacto eliminados com sucesso" + " " + operacao);
//
//        facesContext.addMessage(null, facesMessage);
//       
//        return "lista-contactos";
//    }
}
