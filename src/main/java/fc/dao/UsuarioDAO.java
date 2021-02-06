/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.Usuario;
import fc.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nécia Caculo e Domingos Dala Vunge
 */
public class UsuarioDAO
{

    private final String FIND_BY_ID = "SELECT * FROM usuario WHERE pkusuario = ?";
    private String SELECT_ALL = "SELECT p.pkusuario, p.nomeCompleto, p.username, p.senha FROM usuario p";
    private String INSERT = "INSERT INTO usuario (`nomeCompleto`,`username`,`senha`) VALUES(?,?,?)";
    private String EDITE = "UPDATE usuario SET nomeCompleto = ?, username = ?, senha = ? WHERE pkusuario = ?";
    private String DELETE = "DELETE FROM usuario WHERE pkusuario = ?;";
    private String LIST_BY_NAME = "SELECT p.pkusuario, p.nomeCompleto, p.username, p.senha FROM usuario WHERE p.nomeCompleto LIKE ?";
    private String INSERTE = "INSERT INTO `usuario` (`nomeCompleto`,`username`,`senha`) VALUES (?, ?, ?);";
    ConexaoDB conexao = new ConexaoDB();

    public void update( Usuario pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );
            ps.setString( 1, pac.getNomeCompleto() );
            ps.setString( 2, pac.getUsername() );
            ps.setString( 3, pac.getSenha() );
            ps.setInt( 4, pac.getPkusuario() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( Usuario pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( INSERTE );
            ps.setString( 1, pac.getNomeCompleto() );
            ps.setString( 2, pac.getUsername() );
            ps.setString( 3, pac.getSenha() );
            ps.setInt( 4, pac.getPkusuario() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public void delete( Usuario pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, pac.getPkusuario() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<Usuario> findAll()
    {
        List<Usuario> lista = new ArrayList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( SELECT_ALL );
            rs = ps.executeQuery();
            while ( rs.next() )
            {
                Usuario pac = new Usuario();
                pac.setPkusuario( rs.getInt( "p.pkusuario" ) );
                pac.setNomeCompleto( rs.getString( "p.nomeCompleto" ) );
                pac.setUsername( rs.getString( "p.username" ) );
                pac.setSenha( rs.getString( "p.senha" ) );
                lista.add( pac );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public List<Usuario> findByName( String nome )
    {
        List<Usuario> lista = new ArrayList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( LIST_BY_NAME );
            ps.setString( 1, "%" + nome + "%" );
            rs = ps.executeQuery();
            while ( rs.next() )
            {
                Usuario pac = new Usuario();
                pac.setPkusuario( rs.getInt( "p.pkusuario" ) );
                pac.setNomeCompleto( rs.getString( "p.nomeCompleto" ) );
                pac.setUsername( rs.getString( "p.username" ) );
                pac.setSenha( rs.getString( "p.senha" ) );
                lista.add( pac );

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public Usuario findById( Integer id )
    {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( FIND_BY_ID );
            ps.setInt( 1, id );
            rs = ps.executeQuery();
            if ( rs.next() )
            {
                Usuario pac = new Usuario();
                pac.setPkusuario( rs.getInt( "pkusuario" ) );
                pac.setNomeCompleto( rs.getString( "nomeCompleto" ) );
                pac.setUsername( rs.getString( "username" ) );
                return pac;

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao buscar o objecto: " + e.getLocalizedMessage() );
        }
        return null;
    }

}
