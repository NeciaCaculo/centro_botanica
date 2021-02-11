/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.AutorDAO;
import fc.modelo.Autor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

@Named( value = "autorMB" )
@RequestScoped
public class AutorMB implements Serializable
{

    @Inject
    Autor autor;
    @Inject
    AutorDAO autoDAO;
    List<Autor> listAutor = new ArrayList<>();

    private final String MESMA_PAGINA = "autor?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        listAutor = autoDAO.findAll();
    }

    public Autor getAutor()
    {
        return autor;
    }

    public void setAutor( Autor autor )
    {
        this.autor = autor;
    }

    public List<Autor> getListaAutor()
    {

        return listAutor;
    }

    public void setListAutor( List<Autor> listAutor )
    {
        this.listAutor = listAutor;
    }

    public String insert()
    {

        if ( !autoDAO.existe_autor( autor.getNome(), autor.getSobrenome() ) )
        {
            autoDAO.insert( autor );
            autor = new Autor();
            return MESMA_PAGINA;
        }
        else
        {
            autor = new Autor();
            mostrarMenssagem( FacesMessage.SEVERITY_WARN, "AVISO", "Caro usuário já existe um autor com este nome e sobrenome" );
            return "";
        }

    }

    public List<SelectItem> getSelectTipo_Cursos()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( Autor m : autoDAO.findAll() )
        {
            lista.add( new SelectItem( m.getPkAutor(), m.getNome()) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        autoDAO.update( ( ( Autor ) event.getObject() ) );
        System.out.println( "Novo Tipo de Curso: " + ( ( Autor ) event.getObject() ).getNome());
        mostrarMenssagem( FacesMessage.SEVERITY_INFO, "INFO: ", "Registro actualizado com sucesso" );

    }

    public String onRowCancel( RowEditEvent event )
    {
        try
        {
            mostrarMenssagem( FacesMessage.SEVERITY_INFO, "INFO: ", "Cancelado por sua ordem." );
            return "";
        }
        catch ( Exception e )
        {
            mostrarMenssagem( FacesMessage.SEVERITY_ERROR, "ERRO: ", "Falha ao eliminar o registro." );
        }
        return "";

    }

    public void mostrarMenssagem( Severity severity, String resumo, String detalhe )
    {
        FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( severity, resumo, detalhe ) );
    }

}
