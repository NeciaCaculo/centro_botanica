/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.util.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import fc.modelo.Contacto;


/**
 *
 * @author DGTALE
 */
public class ContactoDAO {

    /*
   
    


    
     */
    String INSERT = "INSERT INTO contacto(nome, telefone, email, endereco, mensagem) VALUES(?, ?, ?, ?, ?)";
    String UPDATE = "UPDATE contacto SET nome = ?, telefone = ?, email = ?, endereco = ?, mensagem = ? WHERE pk_contacto = ?";
    String DELETE = "DELETE FROM contacto WHERE pk_contacto = ?";
    String SELECT_ALL = "SELECT pk_contacto, nome, telefone, email, endereco, mensagem FROM  contacto";
    String SELECT_BY_NOME = "SELECT pk_contacto, nome, telefone, email, endereco, mensagem FROM  contacto f WHERE f.nome LIKE ? ";
    String SELECT_BY_NOME_SOBRENOME = "SELECT id_contacto, nome, sobrenome, data_nascimento, rua, casa, bairro, sexo, id_municipio FROM  contacto f WHERE f.nome = ? AND f.sobrenome = ?";
    String SELECT_BY_DATA_NASCIMENTO = "SELECT id_contacto, nome, sobrenome, data_nascimento, rua, casa, bairro, sexo, id_municipio FROM  contacto f WHERE f.data_nascimento BETWEEN ? AND ?";

    public void save(Contacto f) {
        PreparedStatement ps = null;
        Connection conn = null;
    
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getTelefone());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getEndereco());
            ps.setString(5, f.getMensagem());
            ps.executeUpdate();
        } catch (SQLException e) {

            System.err.println("Erro ao inserir dados:"
                    + " ContactoDAO:save" + e.getLocalizedMessage());
        }

    }
    
//     public void edit(Contacto f) {
//        PreparedStatement ps = null;
//        Connection conn = null;
//    
//        try {
//            conn = ConexaoDB.ligarBD();
//            ps = conn.prepareStatement(UPDATE);
//            ps.setString(1, f.getNome());
//            ps.setString(2, f.getSobrenome());
//            ps.setDate(3, new java.sql.Date(f.getDataNascimento().getTime()));
//            ps.setString(4, f.getRua());
//            ps.setString(5, f.getCasa());
//            ps.setString(6, f.getBairro());
//            ps.setString(7, f.getSexo().getAbreviatua());
//            ps.setInt(8, f.getMunicipio().getIdMunicipio());
//            ps.setInt(9, f.getId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//
//            System.err.println("Erro ao actualizar dados:"
//                    + " DepartamentoDAO:save" + e.getLocalizedMessage());
//        }
//
//    }

//      public void delete(Contacto f) {
//        PreparedStatement ps = null;
//        Connection conn = null;
//    
//        try {
//            conn = ConexaoDB.ligarBD();
//            ps = conn.prepareStatement(DELETE);
//            ps.setInt(1, f.getId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//
//            System.err.println("Erro ao eliminar dados:"
//                    + " DepartamentoDAO:save" + e.getLocalizedMessage());
//        }
//
//    }
//     
    public List<Contacto> listaTodosContactos() {
        List<Contacto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                /*nome, sobrenome, data_nascimento, 
                rua, casa, bairro, sexo, id_municipio*/
                Contacto f = new Contacto();
                f.setPk_contacto(rs.getInt(1));
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("sobrenome"));
                f.setEmail(rs.getString(4));
                f.setEndereco(rs.getString(5));
                f.setMensagem(rs.getString(6));
                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:"
                    + "ContactoDAO:"
                    + "listaTodosContactos" + ex.getLocalizedMessage());
        }

        return lista;
    }

//    public List<Departamento> listaContactosByNome(String nome) {
//        List<Departamento> lista = new ArrayList<>();
//        PreparedStatement ps = null;
//        Connection conn = null;
//        ResultSet rs = null;
//        try {
//            conn = ConexaoDB.ligarBD();
//            ps = conn.prepareStatement(SELECT_BY_NOME);
//            ps.setString(1, nome);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Departamento d = new Departamento();
//                d.setSigla(rs.getString(1));
//                d.setNome(rs.getString(2));
//                lista.add(d);
//            }
//
//        } catch (SQLException ex) {
//            System.err.println("Erro ao ler dados:"
//                    + "ContactoDAO:"
//                    + "listaContactosByNome" + ex.getLocalizedMessage());
//        }
//
//        return lista;
//    }
}
