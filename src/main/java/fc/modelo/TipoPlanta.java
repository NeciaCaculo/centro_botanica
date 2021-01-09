/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.modelo;


/**
 *
 * @author Nécia Caculo
 */
public class TipoPlanta {

    private int pktipo_planta;
    private String designacao;
    
    public TipoPlanta() {
    }

    public TipoPlanta(int pktipo_planta, String designacao) {
        this.pktipo_planta = pktipo_planta;
        this.designacao = designacao;
        
    }

    public int getPktipo_planta() {
        return pktipo_planta;
    }

    public void setPktipo_planta(int pktipo_planta) {
        this.pktipo_planta = pktipo_planta;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    
    
}
