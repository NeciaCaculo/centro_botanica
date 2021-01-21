/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.TipoCurso;
import fc.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nécia Caculo & Domingos Dala Vunge
 */
public class TipoCursoDAO
{

    private String SELECT_ALL = "SELECT p.pk_tipo_curso, p.designacao FROM tipo_curso p";
    private String INSERT = "INSERT INTO tipo_curso (`designacao`) VALUES(?)";
    private String EDITE = "UPDATE tipo_curso SET designacao = ? WHERE pk_tipo_curso = ?";
    private String DELETE = "DELETE FROM tipo_curso WHERE pk_tipo_curso = ?";
    private String LIST_BY_NAME = "SELECT p.pk_tipo_curso, p.designacao FROM tipo_curso WHERE p.designacao LIKE ?";
    private String INSERTE = "INSERT INTO tipo_curso (`designacao`) VALUES (?);";
    ConexaoDB conexao = new ConexaoDB();

    public void update( TipoCurso pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );
            ps.setString( 1, pac.getDesignacao() );
            ps.setInt( 2, pac.getPktipo_curso() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( TipoCurso pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( INSERTE );
            ps.setString( 1, pac.getDesignacao() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public void delete( TipoCurso pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, pac.getPktipo_curso() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<TipoCurso> findAll()
    {
        List<TipoCurso> lista = new ArrayList<>();
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
                TipoCurso pac = new TipoCurso();
                pac.setPktipo_curso( rs.getInt( "p.pk_tipo_curso" ) );
                pac.setDesignacao( rs.getString( "p.designacao" ) );
                lista.add( pac );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public List<TipoCurso> findByName( String nome )
    {
        List<TipoCurso> lista = new ArrayList<>();
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
                TipoCurso pac = new TipoCurso();
                pac.setPktipo_curso( rs.getInt( "p.pk_tipo_curso" ) );
                pac.setDesignacao( rs.getString( "p.designacao" ) );
                lista.add( pac );

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

}
