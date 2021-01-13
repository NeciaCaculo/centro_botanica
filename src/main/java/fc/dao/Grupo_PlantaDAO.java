/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;


import fc.modelo.GrupoPlanta;
import fc.util.ConexaoDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nécia Caculo
 */
public class Grupo_PlantaDAO {
    private String SELECT_ALL = "SELECT p.pk_grupo_planta, p.designacao FROM grupo_planta p";
    private String INSERT = "INSERT INTO grupo_planta (`designacao`) VALUES(?)";
    private String EDITE= "UPDATE grupo_planta SET designacao = ? WHERE pk_grupo_planta = ?";
    private String DELETE = "DELETE FROM grupo_planta WHERE pk_grupo_planta = ?;";
    private String LIST_BY_NAME="SELECT p.pk_grupo_planta, p.designacao FROM grupo_planta WHERE p.designacao LIKE ?";
    private String INSERTE = "INSERT INTO `grupo_planta` (`designacao`) VALUES (?);";
    ConexaoDB conexao = new ConexaoDB();
    
    public void update(GrupoPlanta pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(EDITE);
            ps.setString(1, pac.getDesignacao());
            ps.setInt(2, pac.getPkgrupo_planta());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Actualizar o Registro: "+e.getLocalizedMessage());
        }
    }
    
    public void insert(GrupoPlanta pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(INSERTE);
            ps.setString(1, pac.getDesignacao());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Inserir os dados no Banco de Dados: "+e.getLocalizedMessage());
        }
    }
    
    public void delete(GrupoPlanta pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, pac.getPkgrupo_planta());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Eliminar o Registro: "+e.getLocalizedMessage());
        }
    }
    
   public List<GrupoPlanta> findAll(){
       List<GrupoPlanta> lista = new ArrayList<>();
       Connection con;
       PreparedStatement ps;
       ResultSet rs;
       try {
          con = conexao.ligarBB();
          ps = con.prepareStatement(SELECT_ALL);
          rs = ps.executeQuery();
           while (rs.next()) {               
               GrupoPlanta pac = new GrupoPlanta();
               pac.setPkgrupo_planta(rs.getInt("p.pk_grupo_planta"));
               pac.setDesignacao(rs.getString("p.designacao"));           
               lista.add(pac);
           }
          
       } catch (SQLException e) {
           System.err.println("Erro ao Listar: "+e.getLocalizedMessage());
       }
       return lista;
   } 
    
   public List<GrupoPlanta> findByName(String nome){
       List<GrupoPlanta> lista = new ArrayList<>();
       Connection con;
       PreparedStatement ps;
       ResultSet rs;
       try {
          con = conexao.ligarBB();
          ps = con.prepareStatement(LIST_BY_NAME);
          ps.setString(1,"%" + nome + "%");
          rs = ps.executeQuery();
           while (rs.next()) {               
               GrupoPlanta pac = new GrupoPlanta();
               pac.setPkgrupo_planta(rs.getInt("p.pk_grupo_planta"));
               pac.setDesignacao(rs.getString("p.designacao"));               
               lista.add(pac);
  
           }
          
       } catch (SQLException e) {
           System.err.println("Erro ao Listar: "+e.getLocalizedMessage());
       }
       return lista;
   } 


}
