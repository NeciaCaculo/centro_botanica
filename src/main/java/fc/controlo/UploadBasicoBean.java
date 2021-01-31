/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.controlo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mac
 */
@Named( value = "uploadBasicoBean" )
@SessionScoped
public class UploadBasicoBean implements Serializable
{

    
    transient private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile()
    {
        return uploadedFile;
    }

    public void setUploadedFile( UploadedFile uploadedFile )
    {
        this.uploadedFile = uploadedFile;
    }

    public void upload()
    {
        try
        {
            ServletContext servletContext = ( ServletContext ) FacesContext.getCurrentInstance().getExternalContext().getContext();
            System.err.println( "File Name: " + uploadedFile.getFileName() );
//            String newFileName = servletContext.getRealPath( "" ) + File.separator + "uploaded" + File.separator + uploadedFile.getFileName();
            File file = new File( uploadedFile.getFileName() );

            OutputStream out = new FileOutputStream( file );
            out.write( uploadedFile.getContents() );
            out.close();

            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage( "Upload completo",
                            "O arquivo " + uploadedFile.getFileName() + " foi salvo!" ) );
        }
        catch ( IOException e )
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage( FacesMessage.SEVERITY_WARN, "Erro", e.getMessage() ) );
        }

    }

}
