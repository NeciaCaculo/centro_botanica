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
public class TipoCurso {

    private int pk_tipo_curso;
    private String designacao;
    
    public TipoCurso() {
    }

    public TipoCurso(int pk_tipo_curso, String designacao) {
        this.pk_tipo_curso = pk_tipo_curso;
        this.designacao = designacao;
        
    }

    public int getPktipo_curso() {
        return pk_tipo_curso;
    }

    public void setPktipo_curso(int pk_tipo_curso) {
        this.pk_tipo_curso = pk_tipo_curso;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    
    
}
