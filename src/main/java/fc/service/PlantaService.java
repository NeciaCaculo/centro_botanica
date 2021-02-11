/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.service;

import fc.dao.PlantaDAO;
import fc.modelo.Planta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Domingos Dala Vunge
 */
public class PlantaService
{

    private PlantaDAO plantaDAO;
    private List<Planta> plantas;

    @PostConstruct
    public void init()
    {
        plantaDAO = new PlantaDAO();
        plantas = new ArrayList<>();

        plantas = plantaDAO.findAll();

        Collections.sort( plantas, ( Planta c1, Planta c2 ) -> c1.getNome().compareTo( c2.getNome() ) );
    }

    public List<Planta> getCountries()
    {
        return new ArrayList<>( plantas );
    }

    public List<String> getNomes()
    {
        List<String> nomes = new ArrayList<>();

        for ( Planta planta : plantas )
        {
            nomes.add( planta.getNome() );
        }
        
        return nomes;
    }

}
