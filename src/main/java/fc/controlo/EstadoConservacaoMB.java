/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.EstadoConservacaoDAO;
import fc.modelo.EstadoConservacao;
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

@Named( value = "estadoConservacaoMB" )
@RequestScoped
public class EstadoConservacaoMB implements Serializable
{

    @Inject
    EstadoConservacao estadoConservacao;
    @Inject
    EstadoConservacaoDAO estadoConservacaoDAO;
    List<EstadoConservacao> listaEstadoConservacao = new ArrayList<>();

    private final String MESMA_PAGINA = "estado_conservacao?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        listaEstadoConservacao = estadoConservacaoDAO.findAll();
    }

    public EstadoConservacao getEstadoConservacao()
    {
        return estadoConservacao;
    }

    public void setEstadoConservacao( EstadoConservacao estadoConservacao )
    {
        this.estadoConservacao = estadoConservacao;
    }

    public List<EstadoConservacao> getListaEstadoConservacao()
    {

        return listaEstadoConservacao;
    }

    public void setListaTipo_Curso( List<EstadoConservacao> listaTipo_Curso )
    {
        this.listaEstadoConservacao = listaTipo_Curso;
    }

    public String insert()
    {

        if ( !estadoConservacaoDAO.existeDesignacao( estadoConservacao.getDesignacao() ) )
        {
            estadoConservacaoDAO.insert( estadoConservacao );
            estadoConservacao = new EstadoConservacao();
            return MESMA_PAGINA;
        }
        else
        {
            estadoConservacao = new EstadoConservacao();
            mostrarMenssagem( FacesMessage.SEVERITY_WARN, "AVISO", "Caro usuário já existe um estado de conservação esta designação" );
            return "";
        }

    }

    public List<SelectItem> getSelectEstadoConservacao()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( EstadoConservacao m : estadoConservacaoDAO.findAll() )
        {
            lista.add( new SelectItem( m.getPk_estado_conservacao(), m.getDesignacao() ) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        estadoConservacaoDAO.update( ( ( EstadoConservacao ) event.getObject() ) );
        System.out.println( "Novo estado conservação: " + ( ( EstadoConservacao ) event.getObject() ).getDesignacao() );
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
