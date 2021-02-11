/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.UsuarioDAO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named( value = "loginMB" )
@SessionScoped
public class LoginMB implements Serializable
{

    private UsuarioDAO usuarioDAO;

    private String usuario, senha;

    private final String PAGINA_LOGIN = "login_admin?faces-redirect=true";
    private final String PAGINA_ADMINITRACAO = "principal?faces-redirect=true";
    private final String PAGINA_VISTANTE = "../index?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        usuarioDAO = new UsuarioDAO();
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario( String usuario )
    {
        this.usuario = usuario;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha( String senha )
    {
        this.senha = senha;
    }

    public String login()
    {
        if ( usuarioDAO.isValido( usuario, senha ) )
        {
            usuario = "";
            senha = "";
            return PAGINA_ADMINITRACAO;
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage( "Verifique o usuario e senha" );
        facesContext.addMessage( null, facesMessage );

        return PAGINA_LOGIN;

    }

    public String paginaVisitante()
    {
        return PAGINA_VISTANTE;
    }
    
}
