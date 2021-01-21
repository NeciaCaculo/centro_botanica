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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

@Named( value = "tipoCursoMB" )
@RequestScoped
public class TipoCursoMB implements Serializable
{

    TipoCurso tipoCurso = new TipoCurso();
    TipoCursoDAO tipo_Cursodao = new TipoCursoDAO();
    List<TipoCurso> listaTipo_Curso = new ArrayList<>();

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
        tipo_Cursodao.insert( tipoCurso );
        tipoCurso = new TipoCurso();
        return "tipo_curso?faces-redirect=true";
    }

    public String startEdit()
    {
        return "tipo_curso-edit";
    }

    public String finishEdit()
    {
        System.out.println( "Designacao: " + tipoCurso.getDesignacao() );
        tipo_Cursodao.update( tipoCurso );
        return "tipo_turso";
    }

    public String delete()
    {
        tipo_Cursodao.delete( tipoCurso );
        return "tipo_Curso?faces-redirect=true";
    }

    public List<SelectItem> getSelectTipo_Cursos()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( TipoCurso m : tipo_Cursodao.findAll() )
        {
            lista.add( new SelectItem( m, m.getDesignacao() ) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        tipo_Cursodao.update( ( ( TipoCurso ) event.getObject() ) );
        System.out.println( "Novo Tipo de Curso: " + ( ( TipoCurso ) event.getObject() ).getDesignacao() );
        FacesMessage msg = new FacesMessage( "Actualizado",
                ( ( TipoCurso ) event.getObject() ).getPktipo_curso() + "" );
        FacesContext.getCurrentInstance().addMessage( null, msg );

    }

    public void onRowCancel( RowEditEvent event )
    {
        FacesMessage msg = new FacesMessage( "Cancelado por sua ordem.", ( ( TipoCurso ) event.getObject() ).getDesignacao() );
        FacesContext.getCurrentInstance().addMessage( null, msg );

    }

}
