/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.GrupoPlantaDAO;
import fc.modelo.GrupoPlanta;
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

@Named( value = "grupoPlantaMB" )
@RequestScoped
public class GrupoPlantaMB implements Serializable
{

    @Inject
    GrupoPlanta grupoPlanta;
    @Inject
    GrupoPlantaDAO grupoPlantaDAO;
    List<GrupoPlanta> listaGrupoPlanta = new ArrayList<>();

    private final String MESMA_PAGINA = "grupo_plantas?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        listaGrupoPlanta = grupoPlantaDAO.findAll();
    }

    public GrupoPlanta getGrupoPlanta()
    {
        return grupoPlanta;
    }

    public void setGrupoPlanta( GrupoPlanta grupoPlanta )
    {
        this.grupoPlanta = grupoPlanta;
    }

    public List<GrupoPlanta> getListaGrupoPlanta()
    {

        return listaGrupoPlanta;
    }

    public void setListaTipo_Curso( List<GrupoPlanta> listaTipo_Curso )
    {
        this.listaGrupoPlanta = listaTipo_Curso;
    }

    public String insert()
    {

        if ( !grupoPlantaDAO.existeDesignacao( grupoPlanta.getDesignacao() ) )
        {
            grupoPlantaDAO.insert( grupoPlanta );
            grupoPlanta = new GrupoPlanta();
            return MESMA_PAGINA;
        }
        else
        {
            grupoPlanta = new GrupoPlanta();
            mostrarMenssagem( FacesMessage.SEVERITY_WARN, "AVISO", "Caro usuário já existe um grupo de plantas com esta designação" );
            return "";
        }

    }

    public List<SelectItem> getSelectGrupoPlantas()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( GrupoPlanta m : grupoPlantaDAO.findAll() )
        {
            lista.add( new SelectItem( m.getPkgrupo_planta(), m.getDesignacao() ) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        grupoPlantaDAO.update( ( ( GrupoPlanta ) event.getObject() ) );
        System.out.println( "Novo grupo de plantas: " + ( ( GrupoPlanta ) event.getObject() ).getDesignacao() );
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
