/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import fc.dao.EventoDAO;
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
import java.util.Enumeration;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
    
    
    @PostConstruct
    public void iniciar()
    {
        eventoDAO = new EventoDAO();
    }
    
    public void handleFileUpload( FileUploadEvent event )
    {
        UploadedFile file = event.getFile();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

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
            c = "localhost";
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
