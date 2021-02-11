/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.service;

import fc.dao.AutorDAO;
import fc.modelo.Autor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Domingos Dala Vunge
 */
public class AutorService
{

    private AutorDAO autorDAO;
    private List<Autor> autores;

    @PostConstruct
    public void init()
    {
        autorDAO = new AutorDAO();
        autores = new ArrayList<>();
        autores = autorDAO.findAll();
        Collections.sort( autores, ( Autor c1, Autor c2 ) -> c1.getNome().compareTo( c2.getNome() ) );
    }

    public List<Autor> getAutores()
    {
        return new ArrayList<>( autores );
    }

    public List<String> getNomeCompleto()
    {
        List<String> autor = new ArrayList<>();

        for ( Autor planta : autores )
        {
            autor.add( planta.getNome() + " " +planta.getSobrenome());
        }
        
        return autor;
    }

}
