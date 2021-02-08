/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.EstadoConservacaoDAO;
import fc.dao.GrupoPlantaDAO;
import fc.dao.PlantaDAO;
import fc.modelo.EstadoConservacao;
import fc.modelo.GrupoPlanta;
import fc.modelo.Planta;
import fc.util.DefsUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.primefaces.model.UploadedFile;

@Named( value = "plantaMB" )
@SessionScoped
public class PlantaMB implements Serializable
{

    private UploadedFile filePlanta;
    @Inject
    transient Planta planta, plantaEdit, plantaEliminar;
    @Inject
    transient PlantaDAO plantadao;
    @Inject
    transient GrupoPlantaDAO grupoPlantaDAO;
    @Inject
    transient EstadoConservacaoDAO estadoConservacaoDAO;

    List<Planta> listaPlanta = new ArrayList<>();

    String idGrupoPlanta, idEstadoConservacao;

    private final String MESMA_PAGINA = "planta?faces-redirect=true";

    @PostConstruct
    public void inicializar()
    {
        listaPlanta = plantadao.findAll();
    }

    public String getIdGrupoPlanta()
    {
        return idGrupoPlanta;
    }

    public void setIdGrupoPlanta( String idGrupoPlanta )
    {
        this.idGrupoPlanta = idGrupoPlanta;
    }

    public String getIdEstadoConservacao()
    {
        return idEstadoConservacao;
    }

    public UploadedFile getFilePlanta()
    {
        return filePlanta;
    }

    public void setFilePlanta( UploadedFile filePlanta )
    {
        this.filePlanta = filePlanta;
    }

    public void setIdEstadoConservacao( String idEstadoConservacao )
    {
        this.idEstadoConservacao = idEstadoConservacao;
    }

    public Planta getPlantaEdit()
    {
        return plantaEdit;
    }

    public void setPlantaEdit( Planta plantaEdit )
    {
        this.plantaEdit = plantaEdit;
    }

    public Planta getPlanta()
    {
        return planta;
    }

    public void setPlanta( Planta planta )
    {
        this.planta = planta;
    }

    public void setListaPlanta( List<Planta> listaPlanta )
    {
        this.listaPlanta = listaPlanta;
    }

    public void insert()
    {

        GrupoPlanta grupoPlanta = grupoPlantaDAO.findById( Integer.parseInt( idGrupoPlanta ) );
        EstadoConservacao estadoConservacao = estadoConservacaoDAO.findById( Integer.parseInt( idEstadoConservacao ) );
        planta.setGrupoPlanta( grupoPlanta );
        planta.setEstadoConservacao( estadoConservacao );
        planta.setNome_imagem( filePlanta.getFileName() );

        System.out.println( "**** MOSTAR DADOS ****" );
        System.out.println( "Nome: " + planta.getNome() );
        System.out.println( "Descrição: " + planta.getDescricao() );
        System.out.println( "Nome da Imagem: " + planta.getNome_imagem() );
        System.out.println( "Caminho da Imagem: " + planta.getCaminho_imagem() );
        System.out.println( "Localição: " + planta.getLocalizacao() );
        System.out.println( "Utilidade: " + planta.getUtilidade() );
        System.out.println( "Grupo Planta: " + planta.getGrupoPlanta().getDesignacao() );
        System.out.println( "Estado de conservação: " + planta.getEstadoConservacao().getDesignacao() );

        if ( !plantadao.existeNome( planta.getNome() ) )
        {
            plantadao.insert( planta );
            uploadImagem();
            planta = new Planta();
            listaPlanta = plantadao.findAll();
        }
        else
        {
            planta = new Planta();
            mostrarMenssagem( FacesMessage.SEVERITY_WARN, "AVISO", "Caro usuário já existe um tipo de planta com esta designação" );
        }

    }

    public void editar()
    {

        GrupoPlanta grupoPlanta = grupoPlantaDAO.findById( Integer.parseInt( idGrupoPlanta ) );
        EstadoConservacao estadoConservacao = estadoConservacaoDAO.findById( Integer.parseInt( idEstadoConservacao ) );
        plantaEdit.setGrupoPlanta( grupoPlanta );
        plantaEdit.setEstadoConservacao( estadoConservacao );

        System.out.println( "**** MOSTAR DADOS EDIT ****" );
        System.out.println( "Nome: " + plantaEdit.getNome() );
        System.out.println( "Descrição: " + plantaEdit.getDescricao() );
        System.out.println( "Nome da Imagem: " + plantaEdit.getNome_imagem() );
        System.out.println( "Caminho da Imagem: " + plantaEdit.getCaminho_imagem() );
        System.out.println( "Localição: " + plantaEdit.getLocalizacao() );
        System.out.println( "Utilidade: " + plantaEdit.getUtilidade() );
        System.out.println( "Grupo Planta: " + plantaEdit.getGrupoPlanta().getDesignacao() );
        System.out.println( "Estado de conservação: " + plantaEdit.getEstadoConservacao().getDesignacao() );

        plantadao.update( plantaEdit );
        listaPlanta = plantadao.findAll();
        //return MESMA_PAGINA;
    }

    public void refreshPagina()
    {
        listaPlanta = plantadao.findAll();
    }

    public void actualizar( Planta planta )
    {
        System.out.println( " IDCURSOPARM: " + planta.getPk_planta() );
        plantaEdit = planta;

    }

    public void preparar_eliminar( Planta planta )
    {
        System.err.println( "CURSO ELIMINAR: " + planta.getPk_planta() );
        plantaEliminar = planta;
    }

    public void eliminar( Planta planta )
    {
        System.out.println( "Eliminar" );
        if ( planta != null )
        {
            plantadao.delete( planta );
            listaPlanta = plantadao.findAll();
        }

    }

    private void uploadImagem()
    {
        if ( filePlanta != null )
        {
            String realPath = DefsUtil.getPathGlassfish( DefsUtil.CAMINHO_PLANTAS ) + "/" + filePlanta.getFileName();
            System.err.println( "Real caminho: " + realPath );
            try
            {
                InputStream in = filePlanta.getInputstream();
                OutputStream out = new FileOutputStream( realPath );
                byte[] buffer = new byte[ ( int ) filePlanta.getSize() ];
                int contador = 0;
                while ( ( contador = in.read( buffer ) ) != -1 )
                {
                    out.write( buffer, 0, contador );
                }
                in.close();
                out.close();
                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println( "Ficheiro Nulo" );
        }
        
    }
    public List<Planta> getListaPlanta()
    {
        return listaPlanta;
    }

    public List<SelectItem> getSelectTipo_Plantas()
    {
        List<SelectItem> lista = new ArrayList<>();
        for ( Planta m : plantadao.findAll() )
        {
            lista.add( new SelectItem( m, m.getDescricao() ) );
        }
        return lista;
    }

    public void onRowEdit( RowEditEvent event )
    {

        plantadao.update( ( ( Planta ) event.getObject() ) );
        System.out.println( "Novo Tipo de Planta: " + ( ( Planta ) event.getObject() ).getDescricao() );
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
