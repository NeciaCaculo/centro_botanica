/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.Curso;
import fc.util.ConexaoDB;
import fc.modelo.TipoCurso;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Necia
 */
public class PlantaDAO {
    private String SELECT_ALL = "SELECT c.idcurso, c.nome_curso, c.descricao, c.data_inicio, c.data_fim, t.designacao, c.preco  FROM curso c INNER JOIN tipo_curso t ON t.pk_tipo_curso = c.fk_tipo_curso";
    private String INSERT = "INSERT INTO curso (`nome_curso`,`descricao`,`data_inicio`,`data_fim`,`pk_tipo_curso`,`preco`) VALUES(?, ?, ?, ?, ?, ?)";
    private String EDITE= "UPDATE curso SET nome_curso = ?, descricao = ?, data_inicio = ?, data_fim = ?, fk_tipo_curso = ?, preco = ? WHERE idcurso = ?";
    private String DELETE = "DELETE FROM curso WHERE idcurso = ?;";
    private String LIST_BY_NAME="SELECT c.idcurso, c.descricao, c.nome_curso, c.data_inicio, c.data_fim, t.designacao, c.preco FROM curso c INNER JOIN tipo_curso t ON t.pk_tipo_curso = c.fk_tipo_curso WHERE c.nome_curso LIKE ?";
    private String INSERTE = "INSERT INTO `curso` (`nome_curso`,`descricao`,`data_inicio`,`data_fim`,`fk_tipo_curso`,`preco`) VALUES ( ?, ?, ?, ?, ?, ?);";
    ConexaoDB conexao = new ConexaoDB();
    
    public void update(Curso pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(EDITE);
            
            ps.setString(1, pac.getNome_curso());
            ps.setString(2, pac.getDescricao());
            ps.setDate(3, (Date) pac.getData_inicio());
            ps.setDate(4, (Date) pac.getData_fim());
            ps.setInt(5, pac.getTipo_Curso().getPktipo_curso());
            ps.setDouble(6, pac.getPreco());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Actualizar o Registro: "+e.getLocalizedMessage());
        }
    }
    
    public void insert(Curso pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(INSERTE);
            ps.setString(1, pac.getNome_curso());
            ps.setString(2, pac.getDescricao());
            ps.setDate(3, (Date) pac.getData_inicio());
            ps.setDate(4, (Date) pac.getData_fim());
            ps.setInt(5, pac.getTipo_Curso().getPktipo_curso());
            ps.setDouble(6, pac.getPreco());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Inserir os dados no Banco de Dados: "+e.getLocalizedMessage());
        }
    }
    
    public void delete(Curso pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, pac.getIdcurso());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Eliminar o Registro: "+e.getLocalizedMessage());
        }
    }
    
   public List<Curso> findAll(){
       List<Curso> lista = new ArrayList<>();
       Connection con;
       PreparedStatement ps;
       ResultSet rs;
       try {
          con = conexao.ligarBB();
          ps = con.prepareStatement(SELECT_ALL);
          rs = ps.executeQuery();
           while (rs.next()) {               
               Curso pac = new Curso();
               pac.setIdcurso(rs.getInt("c.idcurso"));
               pac.setNome_curso(rs.getString("c.nome_curso"));
               pac.setDescricao(rs.getString("c.descricao"));
               pac.setData_inicio(rs.getDate("c.data_inicio"));
               pac.setData_fim(rs.getDate("c.data_fim"));
            
               TipoCurso t = new TipoCurso();
               t.setDesignacao(rs.getString("t.designacao"));
               pac.setTipo_Curso(t);
               
               
               lista.add(pac);
           }
          
       } catch (SQLException e) {
           System.err.println("Erro ao Listar: "+e.getLocalizedMessage());
       }
       return lista;
   } 
    
   public List<Curso> findByName(String nome){
       List<Curso> lista = new ArrayList<>();
       Connection con;
       PreparedStatement ps;
       ResultSet rs;
       try {
          con = conexao.ligarBB();
          ps = con.prepareStatement(LIST_BY_NAME);
          ps.setString(1,"%" + nome + "%");
          rs = ps.executeQuery();
           while (rs.next()) {               
               Curso pac = new Curso();
               pac.setIdcurso(rs.getInt("c.idcurso"));
               pac.setNome_curso(rs.getString("c.nome_curso"));
                pac.setDescricao(rs.getString("c.descricao"));
               pac.setData_inicio(rs.getDate("c.data_inicio"));
               pac.setData_fim(rs.getDate("c.data_fim"));
            
               TipoCurso t = new TipoCurso();
               t.setDesignacao(rs.getString("t.designacao"));
               pac.setTipo_Curso(t);
               
               
               lista.add(pac);
  
           }
          
       } catch (SQLException e) {
           System.err.println("Erro ao Listar: "+e.getLocalizedMessage());
       }
       return lista;
   } 

   
   
}
