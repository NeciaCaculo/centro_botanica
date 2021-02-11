/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 *
 * @author Domingos Dala Vunge
 */
public class DefsUtil
{

    //public static String payaraFolder = "payara5";
//     public static String CAMINHO_EVENTOS = "/glassfish/domains/domain1/docroot/uploader/eventos";
//    public static String CAMINHO_PLANTAS = "/glassfish/domains/domain1/docroot/uploader/plantas";
    
    public static String payaraFolder = "C:/payara5/glassfish/domains/domain1/docroot/uploader";
    public static String CAMINHO_EVENTOS = "/eventos";
    public static String CAMINHO_PLANTAS = "/plantas";

    public static String getPathGlassfish( String caminho )
    {
//        return System.getProperty( "user.home" ) + "/" + payaraFolder + caminho;
        return  payaraFolder + caminho;
    }

    public static void main( String[] args )
    {
       
        System.out.println( "Caminho: " + getPathGlassfish( CAMINHO_EVENTOS ) );
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

}
