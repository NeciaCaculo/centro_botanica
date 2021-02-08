/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.EstadoConservacao;
import fc.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Domingos Dala Vunge
 */


public class EstadoConservacaoDAO
{

    private final String SELECT_ALL = "SELECT * FROM estado_conservacao";
    private final String EXISTE_DESIGNACAO = "SELECT p.* FROM estado_conservacao p WHERE p.designacao = ?";
    private final String INSERT = "INSERT INTO estado_conservacao (`designacao`) VALUES(?)";
    private final String EDITE = "UPDATE estado_conservacao SET designacao = ? WHERE pk_estado_conservacao = ?";
    private final String DELETE = "DELETE FROM estado_conservacao WHERE pk_estado_conservacao = ?";
    private final String LIST_BY_NAME = "SELECT p.pk_estado_conservacao, p.designacao FROM estado_conservacao WHERE p.designacao LIKE ?";
    private final String FIND_BY_ID = "SELECT * FROM estado_conservacao WHERE pk_estado_conservacao = ?";
    private final String INSERTE = "INSERT INTO estado_conservacao (`designacao`) VALUES (?);";

    ConexaoDB conexao = new ConexaoDB();

    public void update( EstadoConservacao ec )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );
            ps.setString( 1, ec.getDesignacao() );
            ps.setInt( 2, ec.getPk_estado_conservacao());
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( EstadoConservacao ec )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( INSERTE );
            ps.setString( 1, ec.getDesignacao() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public boolean existeDesignacao( String desgnacao )
    {
        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EXISTE_DESIGNACAO );
            ps.setString( 1, desgnacao );
            ResultSet rs = ps.executeQuery();

            return rs.next();

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao verificar a exitência do tipo de curso" );
        }
        return false;
    }

    public void delete( EstadoConservacao pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, pac.getPk_estado_conservacao() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<EstadoConservacao> findAll()
    {
        List<EstadoConservacao> lista = new ArrayList<>();
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
                EstadoConservacao pac = new EstadoConservacao();
                pac.setPk_estado_conservacao(rs.getInt( "pk_estado_conservacao" ) );
                pac.setDesignacao( rs.getString( "designacao" ) );
                lista.add( pac );
            }

        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public List<EstadoConservacao> findByName( String nome )
    {
        List<EstadoConservacao> lista = new ArrayList<>();
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
                EstadoConservacao pac = new EstadoConservacao();
                pac.setPk_estado_conservacao(rs.getInt( "p.pk_estado_conservacao" ) );
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

    public EstadoConservacao findById( Integer id )
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
                EstadoConservacao pac = new EstadoConservacao();
                pac.setPk_estado_conservacao(rs.getInt( "pk_estado_conservacao" ) );
                pac.setDesignacao( rs.getString( "designacao" ) );
                return pac;

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao buscar o objecto: " + e.getLocalizedMessage() );
        }
        return null;
    }

    public static void main( String[] args )
    {
//        ConexaoDB  conexaoDB = new ConexaoDB();
        List<EstadoConservacao> findAll = new EstadoConservacaoDAO().findAll();
        for ( EstadoConservacao estadoConservacao : findAll )
        {
            System.out.println( "Designação: " + estadoConservacao.getDesignacao() );
        }
    }
}
