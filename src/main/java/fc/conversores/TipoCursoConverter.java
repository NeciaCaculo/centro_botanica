/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.conversores;

import fc.dao.TipoCursoDAO;
import fc.modelo.TipoCurso;
import java.util.List;
import java.util.function.Predicate;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Domingos Dala Vunge
 */
@FacesConverter( value = "fc.conversores.TipoCursoConverter" )
public class TipoCursoConverter implements Converter
{

    TipoCursoDAO tipoCursoDAO = new TipoCursoDAO();

    @Override
    public Object getAsObject( FacesContext context, UIComponent component, String value )
    {
//        TipoCurso o = null;
//        Integer id = 0;
//        if ( !( value == null || value.isEmpty() ) )
//        {
////            o = this.getSelectedItemAsEntity( component, value );
//            System.err.println( "Valor: " +value );
//            id = Integer.parseInt( value );
//            System.out.println( "ID: " + id );
//            o = tipoCursoDAO.findById( id );
//        }
        return null;
    }

    @Override
    public String getAsString( FacesContext context, UIComponent component, Object value )
    {
        String s = "";
        if ( value != null )
        {
            s = String.valueOf( value );
        }
        return s;
    }

    private TipoCurso getSelectedItemAsEntity( UIComponent comp, String value )
    {
        TipoCurso item = null;

        List<TipoCurso> selectItems = null;
        for ( UIComponent uic : comp.getChildren() )
        {
            if ( uic instanceof UISelectItems )
            {
                String itemId = String.valueOf( value );
                selectItems = ( List<TipoCurso> ) ( ( UISelectItems ) uic ).getValue();

                if ( itemId != null && selectItems != null && !selectItems.isEmpty() )
                {
                    Predicate<TipoCurso> predicate = i -> i.getDesignacao().equals( itemId );
                    item = ( TipoCurso ) selectItems.stream().filter( predicate ).findFirst().orElse( null );
                }
            }
        }

        return item;
    }
}
