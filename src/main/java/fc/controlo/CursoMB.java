/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.CursoDAO;
import fc.dao.TipoCursoDAO;
import fc.modelo.Curso;
import fc.modelo.TipoCurso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

@Named( value = "cursoMB" )
@SessionScoped
public class CursoMB implements Serializable
{

    @Inject
    transient Curso curso, cursoEdit, cursoEliminar;
    @Inject
    transient CursoDAO cursodao;
    @Inject
    transient TipoCursoDAO tipoCursoDAO;

    List<Curso> listaCurso = new ArrayList<>();
    List<Curso> cursosComLetras;
    String letrasNomeCurso;

    String idTipoCurso;

    private final String MESMA_PAGINA = "curso?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        listaCurso = cursodao.findAll();
    }

    public CursoDAO getCursodao()
    {
        return cursodao;
    }

    public void setCursodao( CursoDAO cursodao )
    {
        this.cursodao = cursodao;
    }

    public String getIdTipoCurso()
    {
        return idTipoCurso;
    }

    public void setIdTipoCurso( String idTipoCurso )
    {
        this.idTipoCurso = idTipoCurso;
    }

    public Curso getCursoEdit()
    {
        return cursoEdit;
    }

    public void setCursoEdit( Curso cursoEdit )
    {
        this.cursoEdit = cursoEdit;
    }

    public Curso getCurso()
    {
        return curso;
    }

    public void setCurso( Curso curso )
    {
        this.curso = curso;
    }

    public void setListaCurso( List<Curso> listaCurso )
    {
        this.listaCurso = listaCurso;
    }

    public List<Curso> getCursosComLetras() {
        return cursosComLetras;
    }

    public void setCursosComLetras(List<Curso> cursosComLetras) {
        this.cursosComLetras = cursosComLetras;
    }

    public String getLetrasNomeCurso() {
        return letrasNomeCurso;
    }

    public void setLetrasNomeCurso(String letrasNomeCurso) {
        this.letrasNomeCurso = letrasNomeCurso;
    }
    
    
    

    public void insert()
    {

        TipoCurso tipoCurso = tipoCursoDAO.findById( Integer.parseInt( idTipoCurso ) );
        curso.setTipo_Curso( tipoCurso );
        String dataFim = ( curso.getData_fim() == null ) ? "null" : "";

        System.out.println( "**** MOSTAR DADOS ****" );
        System.out.println( "Nome: " + curso.getNome_curso() );
        System.out.println( "Data Inicio: " + curso.getData_inicio() );
        System.out.println( "Preço: " + curso.getPreco() );
        System.out.println( "Data Fim: " + dataFim );
        System.out.println( "Descrição: " + curso.getDescricao() );
        System.out.println( "Tipo Curso: " + curso.getTipo_Curso().getDesignacao() );
        System.out.println( "Modalidade Pagamento: " + curso.getModalidade_pagto() );
        System.out.println( "Documentos: " + curso.getDocumentos() );
        System.out.println( "Tópicos: " + curso.getTopicos() );

        if ( !cursodao.existeDescricao( curso.getDescricao() ) )
        {
            cursodao.insert( curso );
            curso = new Curso();
            listaCurso = cursodao.findAll();
        }
        else
        {
            curso = new Curso();
            mostrarMenssagem( FacesMessage.SEVERITY_WARN, "AVISO", "Caro usuário já existe um tipo de curso com esta designação" );
        }

    }

    public void editar()
    {

        TipoCurso tipoCurso = tipoCursoDAO.findById( Integer.parseInt( idTipoCurso ) );
        cursoEdit.setTipo_Curso( tipoCurso );

        System.out.println( "Curso Editar: " + cursoEdit.getPk_curso() );
        System.out.println( "Curso Editar: " + cursoEdit.getDescricao() );
        System.out.println( "Tipo Curso: " + cursoEdit.getTipo_Curso().getDesignacao() );
        cursodao.update( cursoEdit );
        listaCurso = cursodao.findAll();
        //return MESMA_PAGINA;
    }

    public void refreshPagina()
    {
        listaCurso = cursodao.findAll();
    }

    public void actualizar( Curso curso )
    {
        System.out.println( " IDCURSOPARM: " + curso.getPk_curso() );
        cursoEdit = curso;

    }

    public void preparar_eliminar( Curso curso )
    {
        System.err.println( "CURSO ELIMINAR: " +curso.getPk_curso() );
        cursoEliminar = curso;
    }

    public void eliminar(Curso curso)
    {
        System.out.println( "Eliminar" );
        if ( curso != null )
        {
            cursodao.delete( curso );
            listaCurso = cursodao.findAll();
        }

    }

    public List<Curso> getListaCurso()
    {
        return listaCurso;
    }

    public List<SelectItem> getSelectTipo_Cursos()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( Curso m : cursodao.findAll() )
        {
            lista.add( new SelectItem( m, m.getDescricao() ) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        cursodao.update( ( ( Curso ) event.getObject() ) );
        System.out.println( "Novo Tipo de Curso: " + ( ( Curso ) event.getObject() ).getDescricao() );
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
