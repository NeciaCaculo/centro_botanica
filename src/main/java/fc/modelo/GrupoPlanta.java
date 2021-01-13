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
public class GrupoPlanta {

    private int pk_grupo_planta;
    private String designacao;
    
    public GrupoPlanta() {
    }

    public GrupoPlanta(int pk_grupo_planta, String designacao) {
        this.pk_grupo_planta = pk_grupo_planta;
        this.designacao = designacao;
        
    }

    public int getPkgrupo_planta() {
        return pk_grupo_planta;
    }

    public void setPkgrupo_planta(int pk_grupo_planta) {
        this.pk_grupo_planta = pk_grupo_planta;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    
    
}
