/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.ObraLiterariaDAO;
import fc.dao.UsuarioDAO;
import fc.modelo.ObraLiteraria;
import fc.modelo.Usuario;
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

@Named( value = "obraliterariaMB" )
@SessionScoped
public class ObraLiterariaMB implements Serializable
{

    @Inject
    transient ObraLiteraria obraliteraria, obraliterariaEdit, obraliterariaEliminar;
    @Inject
    transient ObraLiterariaDAO obraliterariadao;
    @Inject
    transient UsuarioDAO usuarioDAO;

    List<ObraLiteraria> listaObraLiteraria = new ArrayList<>();

    String idUsuario;

    private final String MESMA_PAGINA = "obraliteraria?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        listaObraLiteraria = obraliterariadao.findAll();
    }

    public ObraLiterariaDAO getObraLiterariadao()
    {
        return obraliterariadao;
    }

    public void setObraLiterariadao( ObraLiterariaDAO obraliterariadao )
    {
        this.obraliterariadao = obraliterariadao;
    }

    public String getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario( String idUsuario )
    {
        this.idUsuario = idUsuario;
    }

    public ObraLiteraria getObraLiterariaEdit()
    {
        return obraliterariaEdit;
    }

    public void setObraLiterariaEdit( ObraLiteraria obraliterariaEdit )
    {
        this.obraliterariaEdit = obraliterariaEdit;
    }

    public ObraLiteraria getObraLiteraria()
    {
        return obraliteraria;
    }

    public void setObraLiteraria( ObraLiteraria obraliteraria )
    {
        this.obraliteraria = obraliteraria;
    }

    public void setListaObraLiteraria( List<ObraLiteraria> listaObraLiteraria )
    {
        this.listaObraLiteraria = listaObraLiteraria;
    }

    public void insert()
    {

        Usuario usuario = usuarioDAO.findById( Integer.parseInt( idUsuario ) );
//        obraliteraria.setTipo_ObraLiteraria( usuario );
//        String dataFim = ( obraliteraria.getData_fim() == null ) ? "null" : "";
//
//        System.out.println( "**** MOSTAR DADOS ****" );
//        System.out.println( "Nome: " + obraliteraria.getNome_obraliteraria() );
//        System.out.println( "Data Inicio: " + obraliteraria.getData_inicio() );
//        System.out.println( "Preço: " + obraliteraria.getPreco() );
//        System.out.println( "Data Fim: " + dataFim );
//        System.out.println( "Descrição: " + obraliteraria.getDescricao() );
//        System.out.println( "Tipo ObraLiteraria: " + obraliteraria.getTipo_ObraLiteraria().getDesignacao() );
//        System.out.println( "Modalidade Pagamento: " + obraliteraria.getModalidade_pagto() );
//        System.out.println( "Documentos: " + obraliteraria.getDocumentos() );
//        System.out.println( "Tópicos: " + obraliteraria.getTopicos() );

        if ( !obraliterariadao.existeNome(obraliteraria.getDescricao() ) )
        {
            obraliterariadao.insert( obraliteraria );
            obraliteraria = new ObraLiteraria();
            listaObraLiteraria = obraliterariadao.findAll();
        }
        else
        {
            obraliteraria = new ObraLiteraria();
            mostrarMenssagem( FacesMessage.SEVERITY_WARN, "AVISO", "Caro usuário já existe um tipo de obraliteraria com esta designação" );
        }

    }

    public void editar()
    {

        Usuario usuario = usuarioDAO.findById( Integer.parseInt( idUsuario ) );
//        obraliterariaEdit.setTipo_ObraLiteraria( usuario );
//
//        System.out.println( "ObraLiteraria Editar: " + obraliterariaEdit.getPk_obraliteraria() );
//        System.out.println( "ObraLiteraria Editar: " + obraliterariaEdit.getDescricao() );
//        System.out.println( "Tipo ObraLiteraria: " + obraliterariaEdit.getTipo_ObraLiteraria().getDesignacao() );
//        obraliterariadao.update( obraliterariaEdit );
//        listaObraLiteraria = obraliterariadao.findAll();
        //return MESMA_PAGINA;
    }

    public void refreshPagina()
    {
        listaObraLiteraria = obraliterariadao.findAll();
    }

    public void actualizar( ObraLiteraria obraliteraria )
    {
        //System.out.println( " IDCURSOPARM: " + obraliteraria.getPk_obraliteraria() );
        obraliterariaEdit = obraliteraria;

    }

    public void preparar_eliminar( ObraLiteraria obraliteraria )
    {
       // System.err.println( "CURSO ELIMINAR: " +obraliteraria.getPk_obraliteraria() );
        obraliterariaEliminar = obraliteraria;
    }

    public void eliminar(ObraLiteraria obraliteraria)
    {
        System.out.println( "Eliminar" );
        if ( obraliteraria != null )
        {
            obraliterariadao.delete( obraliteraria );
            listaObraLiteraria = obraliterariadao.findAll();
        }

    }

    public List<ObraLiteraria> getListaObraLiteraria()
    {
        return listaObraLiteraria;
    }

    public List<SelectItem> getSelectTipo_ObraLiterarias()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( ObraLiteraria m : obraliterariadao.findAll() )
        {
            lista.add( new SelectItem( m, m.getDescricao() ) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        obraliterariadao.update( ( ( ObraLiteraria ) event.getObject() ) );
        System.out.println( "Novo Tipo de ObraLiteraria: " + ( ( ObraLiteraria ) event.getObject() ).getDescricao() );
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
