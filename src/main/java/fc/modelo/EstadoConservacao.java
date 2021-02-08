/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.modelo;


/**
 *
 * @author Domingos Dala Vunge
 */
public class EstadoConservacao {

    private int pk_estado_conservacao;
    private String designacao;
    
    public EstadoConservacao() {
    }

    public EstadoConservacao(int pk_estado_conservacao, String designacao) {
        this.pk_estado_conservacao = pk_estado_conservacao;
        this.designacao = designacao;
        
    }

    public int getPk_estado_conservacao()
    {
        return pk_estado_conservacao;
    }

    public void setPk_estado_conservacao( int pk_estado_conservacao )
    {
        this.pk_estado_conservacao = pk_estado_conservacao;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao( String designacao )
    {
        this.designacao = designacao;
    }
    
   
}
