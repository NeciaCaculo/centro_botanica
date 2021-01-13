/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.Evento;
import fc.util.ConexaoDB;
import fc.modelo.Usuario;
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
public class EventoDAO {
    private String SELECT_ALL = "SELECT e.pk_eventos, e.titulo, e.data_evento, e.descricao, e.nome_imagem, e.caminho_imagem, u.nomeCompleto  FROM evento e INNER JOIN usuario u ON u.pkusuario = e.fk_usuario";
    private String INSERT = "INSERT INTO evento (`titulo`,`data_evento`, `descricao`, `nome_imagem`, `caminho_imagem`, `nomeCompleto`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private String EDITE= "UPDATE evento SET titulo = ?, data_evento = ?, descricao= ?, nome_imagem= ?, caminho_imagem= ?, nomeCompleto = ?";
    private String DELETE = "DELETE FROM evento WHERE pk_eventos = ?;";
    private String LIST_BY_NAME="SELECT e.pk_eventos, e.titulo, e.data_evento, e.descricao, e.nome_imagem, e.caminho_imagem, u.nomeCompleto FROM evento e INNER JOIN usuario u ON u.pkusuario = e.fk_usuario WHERE e.titulo LIKE ?";
    private String INSERTE = "INSERT INTO `evento` (`titulo`,`data_evento`, `descricao`, `nome_imagem`, `caminho_imagem`, `nomeCompleto`) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
    ConexaoDB conexao = new ConexaoDB();
    
    public void update(Evento pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(EDITE);
            
            ps.setString(1, pac.getTitulo());
            ps.setDate(2, (Date) pac.getData_evento());
            ps.setString(3, pac.getDescricao());
            ps.setString(4, pac.getNome_imagem());
            ps.setString(5, pac.getCaminho_imagem());
            ps.setString(6, pac.getUsuario().getNomeCompleto());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Actualizar o Registro: "+e.getLocalizedMessage());
        }
    }
    
    public void insert(Evento pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(INSERTE);
            ps.setString(1, pac.getTitulo());
            ps.setDate(2, (Date) pac.getData_evento());
            ps.setString(3, pac.getDescricao());
            ps.setString(4, pac.getNome_imagem());
            ps.setString(5, pac.getCaminho_imagem());
            ps.setString(6, pac.getUsuario().getNomeCompleto());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Inserir os dados no Banco de Dados: "+e.getLocalizedMessage());
        }
    }
    
    public void delete(Evento pac){
        
        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, pac.getPk_evento());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Eliminar o Registro: "+e.getLocalizedMessage());
        }
    }
    
   public List<Evento> findAll(){
       List<Evento> lista = new ArrayList<>();
       Connection con;
       PreparedStatement ps;
       ResultSet rs;
       try {
          con = conexao.ligarBB();
          ps = con.prepareStatement(SELECT_ALL);
          rs = ps.executeQuery();
           while (rs.next()) {               
               Evento pac = new Evento();
               pac.setPk_evento(rs.getInt("e.pk_eventos"));
               pac.setTitulo(rs.getString("e.titulo"));
               pac.setData_evento(rs.getDate("e.data_evento"));
               pac.setDescricao(rs.getString("e.descricao"));
               pac.setNome_imagem(rs.getString("e.nome_imagem"));
               pac.setCaminho_imagem(rs.getString("e.caminho_imagem"));
                           
               Usuario u = new Usuario();
               u.setNomeCompleto(rs.getString("u.nomeCompleto"));
               pac.setUsuario(u);
               
               
               lista.add(pac);
           }
          
       } catch (SQLException e) {
           System.err.println("Erro ao Listar: "+e.getLocalizedMessage());
       }
       return lista;
   } 
    
   public List<Evento> findByName(String nome){
       List<Evento> lista = new ArrayList<>();
       Connection con;
       PreparedStatement ps;
       ResultSet rs;
       try {
          con = conexao.ligarBB();
          ps = con.prepareStatement(LIST_BY_NAME);
          ps.setString(1,"%" + nome + "%");
          rs = ps.executeQuery();
           while (rs.next()) {               
               Evento pac = new Evento();
               pac.setPk_evento(rs.getInt("e.pk_eventos"));
               pac.setTitulo(rs.getString("e.titulo"));
               pac.setData_evento(rs.getDate("e.data_evento"));
               pac.setDescricao(rs.getString("e.descricao"));
               pac.setNome_imagem(rs.getString("e.nome_imagem"));
               pac.setCaminho_imagem(rs.getString("e.caminho_imagem"));
                           
               Usuario u = new Usuario();
               u.setNomeCompleto(rs.getString("u.nomeCompleto"));
               pac.setUsuario(u);
               
               
               lista.add(pac);
  
           }
          
       } catch (SQLException e) {
           System.err.println("Erro ao Listar: "+e.getLocalizedMessage());
       }
       return lista;
   } 

   
   
}
