/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.UsuarioDAO;
import fc.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

@Named(value = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    List<Usuario> listaUsuarios = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
      //  listaUsuarios = usuarioDao.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    
    
    
    
    
    
    public String insert() {
        usuarioDao.insert(usuario);
        usuario = new Usuario();
        return "usuario-lista?faces-redirect=true";
    }

    public String eliminar() {
        usuarioDao.delete(usuario);
        usuario = new Usuario();
        return null;
    }

    public String prepararEditar() {
        return "usuario-editar";
    }

    public String editar() {
        usuarioDao.update(usuario);
        usuario = new Usuario();
        return "usuario-lista?faces-redirect=true";
    }
    
//     public List<SelectItem> getOpSexos() {
//        List<SelectItem> list = new ArrayList<>();
//        for (Sexo sexo : Sexo.values()) {
//            list.add(new SelectItem(sexo, sexo.getExtensao()));
//        }
//        return list;
//    }
}
