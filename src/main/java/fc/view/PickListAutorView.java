/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.view;

import fc.service.AutorService;
import fc.modelo.Autor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Domingos Dala Vunge
 */
@Named
@RequestScoped
public class PickListAutorView
{

    @Inject
    private AutorService service;

    private DualListModel<String> autor;
    private DualListModel<Autor> autores;

    @PostConstruct
    public void init()
    {
        //Cities
        List<String> autorSource = new ArrayList<>();
        List<String> autorTarget = new ArrayList<>();

        autorSource = service.getNomeCompleto();
        autor = new DualListModel<>( autorSource, autorTarget );

        List<Autor> autoresSource = service.getAutores();
        List<Autor> autoresTarget = new ArrayList<>();

        autores = new DualListModel<>( autoresSource, autoresTarget );

    }

    public DualListModel<String> getAutor()
    {
        return autor;
    }

    public void setAutors( DualListModel<String> autor )
    {
        this.autor = autor;
    }

    public AutorService getService()
    {
        return service;
    }

    public void setService( AutorService service )
    {
        this.service = service;
    }

    public DualListModel<Autor> getAutores()
    {
        return autores;
    }

    public void setAutores( DualListModel<Autor> autores )
    {
        this.autores = autores;
    }

    public void onTransfer( TransferEvent event )
    {
        StringBuilder builder = new StringBuilder();
        for ( Object item : event.getItems() )
        {
            builder.append( ( ( Autor ) item ).getNome() ).append( "<br />" );
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity( FacesMessage.SEVERITY_INFO );
        msg.setSummary( "Items Transferred" );
        msg.setDetail( builder.toString() );

        FacesContext.getCurrentInstance().addMessage( null, msg );
    }

//    public void onSelect( SelectEvent<Country> event )
//    {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().getName() ) );
//    }
//
//   
//    public void onUnselect( UnselectEvent<Country> event )
//    {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().getName() ) );
//    }
    public void onReorder()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, "List Reordered", null ) );
    }
}
