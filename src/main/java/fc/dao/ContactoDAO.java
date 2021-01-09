/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.util.ConexaoDB;
import fc.modelo.Contacto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author informatica
 */
public class ContactoDAO {

    private static final String INSERIR = "INSERT INTO contacto(nome, telefone, email, endereco, mensagem)VALUES(?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE contacto SET nome = ?, telefone = ?, email = ?, endereco = ?, mensagem = ? WHERE pk_contacto = ?";
    private static final String ELIMINAR = "DELETE FROM contacto WHERE pk_contacto = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_contacto, nome_contacto, sobrenom_contacto, data_nascimento_contacto, sexo_contacto, email_contacto, telefone_contacto, rua_contacto, casa_contacto, bairro_contacto,  distritito_contacto,  nome_municipio FROM contacto f INNER JOIN municipio m ON f.id_municipio = f.id_municipio WHERE id_contacto = ?";
    private static final String BUSCAR_POR_NOME = "SELECT id_contacto, nome_contacto, sobrenom_contacto, data_nascimento_contacto, sexo_contacto, email_contacto, telefone_contacto, rua_contacto, casa_contacto, bairro_contacto,  distritito_contacto,  nome_municipio FROM contacto f INNER JOIN municipio m ON f.id_municipio = f.id_municipio WHERE nome_contacto LIKE ? OR sobrenom_contacto LIKE ?";
    private static final String LISTAR_TUDO = "SELECT pk_contacto, nome, telefone, email, endereco, mensagem FROM  contacto";;
   
    
    ConexaoDB conexaoDB = new ConexaoDB();

    public void insert(Contacto f) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = conexaoDB.ligarBB();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getTelefone());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getEndereco());
            ps.setString(5, f.getMensagem());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        } finally {

            ConexaoDB.fecharConexao(conn, ps);
        }

    }

    public void update(Contacto f) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = conexaoDB.ligarBB();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getTelefone());
            ps.setString(3, f.getEmail());
            ps.setString(2, f.getEndereco());
            ps.setString(2, f.getMensagem());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        } finally {

            ConexaoDB.fecharConexao(conn, ps);
        }

    }

    public void delete(Contacto f) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = conexaoDB.ligarBB();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, f.getPk_contacto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        } finally {

            ConexaoDB.fecharConexao(conn, ps);
        }

    }

    public List<Contacto> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Contacto> contactos = new ArrayList<>();
        try {
            conn = conexaoDB.ligarBB();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contacto contacto = new Contacto();
                popularComDados(contacto, rs);
                contactos.add(contacto);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return contactos;
    }

    public List<Contacto> findByNomeSobrenome(String valor) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Contacto> contactos = new ArrayList<>();
        try {
            conn = conexaoDB.ligarBB();

            ps = conn.prepareStatement(BUSCAR_POR_NOME);
            ps.setString(1, "%" + valor + "%");
            ps.setString(2, "%" + valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Contacto contacto = new Contacto();
                popularComDados(contacto, rs);
                contactos.add(contacto);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return contactos;
    }

    private void popularComDados(Contacto contacto, ResultSet rs) {
        try {
            contacto.setPk_contacto(rs.getInt("pk_contacto"));
            contacto.setNome(rs.getString("nome"));
            contacto.setTelefone(rs.getString("telefone"));
            contacto.setEmail(rs.getString("email"));
            contacto.setEndereco(rs.getString("endereco"));
            contacto.setMensagem(rs.getString("mensagem"));
            

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
