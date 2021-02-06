/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileEvento, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.EventoDAO;
import fc.dao.UsuarioDAO;
import fc.modelo.Evento;
import fc.util.DefsUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import org.primefaces.model.UploadedFile;

@Named( value = "eventoMB" )
@ApplicationScoped
public class EventoMB implements Serializable
{
    
    private UploadedFile fileEvento;
    Evento evento = new Evento(), eventoEdit = new Evento();
    EventoDAO eventoDao = new EventoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Evento> listaEventos = new ArrayList<>();
    
    @PostConstruct
    public void inicializar()
    {
        listaEventos = eventoDao.findAll();
    }
    
    public Evento getEventoEdit()
    {
        return eventoEdit;
    }
    
    public void setEventoEdit( Evento eventoEdit )
    {
        this.eventoEdit = eventoEdit;
    }
    
    public Evento getEvento()
    {
        return evento;
    }
    
    public void setEvento( Evento evento )
    {
        this.evento = evento;
    }
    
    public UploadedFile getFileEvento()
    {
        return fileEvento;
    }
    
    public void setFileEvento( UploadedFile fileEvento )
    {
        this.fileEvento = fileEvento;
    }
    
    public List<Evento> getListaEventos()
    {
        return listaEventos;
    }
    
    public void setListaEventos( List<Evento> listaEventos )
    {
        this.listaEventos = listaEventos;
    }
    
    public void insert()
    {
        evento.setNome_imagem( fileEvento.getFileName() );
        evento.setUsuario( usuarioDAO.findById( 1 ) );
        try
        {
            eventoDao.insert( evento );
            uploadImagem();
            evento = new Evento();
            listaEventos = eventoDao.findAll();
        }
        catch ( Exception e )
        {
        }
        
    }
    
    public String eliminar()
    {
        eventoDao.delete( evento );
        evento = new Evento();
        return null;
    }
    
    public String prepararEditar()
    {
        return "evento-editar";
    }
    
    public void editar()
    {
        if ( fileEvento != null )
        {
            eventoEdit.setNome_imagem( fileEvento.getFileName() );
        }
        eventoDao.update( eventoEdit );
        uploadImagem();
        listaEventos = eventoDao.findAll();
    }
    
    public void refreshPagina()
    {
        listaEventos = eventoDao.findAll();
    }
    
    public void actualizar( Evento evento )
    {
        System.out.println( " IDEVENTOPARM: " + evento.getPk_evento() );
        eventoEdit = evento;
        
    }
    
    private void uploadImagem()
    {
        if ( fileEvento != null )
        {
            String realPath = DefsUtil.getPathGlassfish( DefsUtil.CAMINHO_EVENTOS ) + "/" + fileEvento.getFileName();
            System.err.println( "Real caminho: " + realPath );
            try
            {
                InputStream in = fileEvento.getInputstream();
                OutputStream out = new FileOutputStream( realPath );
                byte[] buffer = new byte[ ( int ) fileEvento.getSize() ];
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
    
    public void eliminar( Evento evento )
    {
        System.out.println( "Eliminar" );
        if ( evento != null )
        {
            eventoDao.delete( evento );
            listaEventos = eventoDao.findAll();
        }
        
    }

//     public List<SelectItem> getOpSexos() {
//        List<SelectItem> list = new ArrayList<>();
//        for (Sexo sexo : Sexo.values()) {
//            list.add(new SelectItem(sexo, sexo.getExtensao()));
//        }
//        return list;
//    }
}
