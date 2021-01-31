/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.TipoCursoDAO;
import fc.modelo.TipoCurso;
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

@Named( value = "tipoCursoMB" )
@RequestScoped
public class TipoCursoMB implements Serializable
{

    @Inject
    TipoCurso tipoCurso;
    @Inject
    TipoCursoDAO tipo_Cursodao;
    List<TipoCurso> listaTipo_Curso = new ArrayList<>();

    private final String MESMA_PAGINA = "tipo_curso?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        listaTipo_Curso = tipo_Cursodao.findAll();
    }

    public TipoCurso getTipoCurso()
    {
        return tipoCurso;
    }

    public void setTipoCurso( TipoCurso tipoCurso )
    {
        this.tipoCurso = tipoCurso;
    }

    public List<TipoCurso> getListaTipoCurso()
    {

        return listaTipo_Curso;
    }

    public void setListaTipo_Curso( List<TipoCurso> listaTipo_Curso )
    {
        this.listaTipo_Curso = listaTipo_Curso;
    }

    public String insert()
    {

        if ( !tipo_Cursodao.existeDesignacao( tipoCurso.getDesignacao() ) )
        {
            tipo_Cursodao.insert( tipoCurso );
            tipoCurso = new TipoCurso();
            return MESMA_PAGINA;
        }
        else
        {
            tipoCurso = new TipoCurso();
            mostrarMenssagem( FacesMessage.SEVERITY_WARN, "AVISO", "Caro usuário já existe um tipo de curso com esta designação" );
            return "";
        }

    }

    public List<SelectItem> getSelectTipo_Cursos()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( TipoCurso m : tipo_Cursodao.findAll() )
        {
            lista.add( new SelectItem( m.getPktipo_curso(), m.getDesignacao() ) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        tipo_Cursodao.update( ( ( TipoCurso ) event.getObject() ) );
        System.out.println( "Novo Tipo de Curso: " + ( ( TipoCurso ) event.getObject() ).getDesignacao() );
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
