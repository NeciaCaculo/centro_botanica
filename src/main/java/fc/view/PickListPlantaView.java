/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.view;

import fc.service.PlantaService;
import fc.modelo.Planta;
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
public class PickListPlantaView
{

    @Inject
    private PlantaService service;

    private DualListModel<String> planta;
    private DualListModel<Planta> countries;

    @PostConstruct
    public void init()
    {
        //Cities
        List<String> plantaSource = new ArrayList<>();
        List<String> plantaTarget = new ArrayList<>();

        plantaSource = service.getNomes();
        planta = new DualListModel<>( plantaSource, plantaTarget );

        List<Planta> plantasSource = service.getCountries();
        List<Planta> plantasTarget = new ArrayList<>();

        countries = new DualListModel<>( plantasSource, plantasTarget );

    }

    public DualListModel<String> getPlantas()
    {
        return planta;
    }

    public void setPlantas( DualListModel<String> cities )
    {
        this.planta = cities;
    }

    public PlantaService getService()
    {
        return service;
    }

    public void setService( PlantaService service )
    {
        this.service = service;
    }

    public DualListModel<Planta> getCountries()
    {
        return countries;
    }

    public void setCountries( DualListModel<Planta> countries )
    {
        this.countries = countries;
    }

    public void onTransfer( TransferEvent event )
    {
        StringBuilder builder = new StringBuilder();
        for ( Object item : event.getItems() )
        {
            builder.append( ( ( Planta ) item ).getNome() ).append( "<br />" );
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
