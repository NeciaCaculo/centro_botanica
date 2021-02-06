/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Domingos Dala Vunge
 */
@Named( value = "uploadBasicoBean" )
@SessionScoped
public class UploadBasicoBean implements Serializable
{

    private EventoDAO eventoDAO;
    private UsuarioDAO usuarioDAO;
    private UploadedFile file;
    private Evento evento;

    @PostConstruct
    public void iniciar()
    {
        evento = new Evento();
        eventoDAO = new EventoDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public Evento getEvento()
    {
        return evento;
    }

    public void setEvento( Evento Evento )
    {
        this.evento = Evento;
    }

    public UploadedFile getFile()
    {
        return file;
    }

    public void setFile( UploadedFile file )
    {
        this.file = file;
    }

    public void upload()
    {
       
        if ( file != null )
        {

            System.out.println( "Entrei aqui" );
            System.out.println( "Nome ficheiro: " + file.getFileName() );
            evento.setData_evento( new Date() );
            evento.setCaminho_imagem( "XXX" );
            evento.setDescricao( "XXX" );
            evento.setUsuario( usuarioDAO.findById( 1 ) );
            evento.setNome_imagem( file.getFileName() );
            eventoDAO.insert( evento );
            uploadImagem();
            evento = new Evento();

//            FacesMessage message = new FacesMessage( "Successful", file.getFileName() + " is uploaded." );
//            FacesContext.getCurrentInstance().addMessage( null, message );
        }
        else 
        {
             System.out.println( "File Nulo" );
        }
    }

    private void uploadImagem()
    {
        String realPath = DefsUtil.getPathGlassfish( DefsUtil.CAMINHO_EVENTOS ) + "/" + file.getFileName();
        try
        {
            InputStream in = file.getInputstream();
            OutputStream out = new FileOutputStream( realPath );
            byte[] buffer = new byte[ ( int ) file.getSize() ];
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

    public void handleFileUpload( FileUploadEvent event )
    {
        UploadedFile file_local = event.getFile();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        String realPath = DefsUtil.getPathGlassfish( DefsUtil.CAMINHO_EVENTOS ) + "/" + file_local.getFileName();

        try
        {
            InputStream in = file_local.getInputstream();
            OutputStream out = new FileOutputStream( realPath );
            byte[] buffer = new byte[ ( int ) file_local.getSize() ];
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

    public String enderecoIp() throws SocketException, UnknownHostException
    {
        String meuIp = null;

        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while ( e.hasMoreElements() )
        {
            NetworkInterface i = ( NetworkInterface ) e.nextElement();
            Enumeration ds = i.getInetAddresses();
            while ( ds.hasMoreElements() )
            {
                InetAddress myself = ( InetAddress ) ds.nextElement();

                if ( !myself.isLoopbackAddress() && myself.isSiteLocalAddress() )
                {

                    meuIp = myself.getHostAddress();

                }
            }
        }
        return meuIp;

    }

    public String endereco() throws SocketException, UnknownHostException
    {
        String c;

        if ( enderecoIp() != null )
        {
            c = enderecoIp();

        }
        else //c= getEnderecoIP();
        {
            c = "127.0.0.1";
        }

        return c;
    }

    public List<String> getNomeImagensEventos()
    {
        List<String> list = new ArrayList<>();

//        list.add( "logo_skype.jpg" );
//        list.add( "HTML-Website.jpg" );
        return eventoDAO.getAllImagens();

    }

}
