/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.Autor;
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
 * @author Domingos Dala Vunge
 */
public class AutorDAO
{

    private String SELECT_ALL = "SELECT *  FROM autor";
    private String INSERT = "INSERT INTO autor (nome , sobrenome ,  data_nascimento , nacionalidade , grau_formacao ) VALUES(?, ?, ?, ?, ?)";
    private final String EXISTE_AUTOR = "SELECT p.* FROM autor p WHERE p.nome = ? and p.sobrenome = ?";
    private String EDITE = "UPDATE autor SET nome = ?  , sobrenome = ?  ,  data_nascimento = ? , nacionalidade = ? , grau_formacao = ? WHERE pk_autor = ?";
    private String DELETE = "DELETE FROM autor WHERE pk_autor = ?;";
    private final String FIND_BY_ID = "SELECT * FROM autor WHERE pk_autor = ?";

    ConexaoDB conexao = new ConexaoDB();

    public void update( Autor pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );

            ps.setString( 1, pac.getNome() );
            ps.setString( 2, pac.getSobrenome() );
            ps.setDate( 3, new Date( pac.getDataNacimento().getTime() ) );
            ps.setString( 4, pac.getNacionalidade() );
            ps.setInt( 5, pac.getPkAutor() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( Autor pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( INSERT );
            ps.setString( 1, pac.getNome() );
            ps.setString( 2, pac.getSobrenome() );
            ps.setDate( 3, new Date( pac.getDataNacimento().getTime() ) );
            ps.setString( 4, pac.getNacionalidade() );
            ps.setString( 5, pac.getGrauFormacao() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public void delete( Autor pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, pac.getPkAutor() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<Autor> findAll()
    {
        List<Autor> lista = new ArrayList<>();
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
                Autor pac = new Autor();
                pac.setPkAutor( rs.getInt( "pk_autor" ) );
                pac.setNome( rs.getString( "nome" ) );
                pac.setSobrenome( rs.getString( "sobrenome" ) );
                pac.setDataNacimento( rs.getDate( "data_nascimento" ) );
                pac.setNacionalidade( rs.getString( "nacionalidade" ) );
                pac.setGrauFormacao( rs.getString( "grau_formacao" ) );

                lista.add( pac );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public Autor findById( Integer id )
    {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Integer idTipoAutor = 0;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( FIND_BY_ID );
            ps.setInt( 1, id );
            rs = ps.executeQuery();
            if ( rs.next() )
            {
                Autor pac = new Autor();

                pac.setPkAutor( rs.getInt( "pk_autor" ) );
                pac.setNome( rs.getString( "nome_autor" ) );
                pac.setSobrenome( rs.getString( "descricao" ) );
                pac.setDataNacimento( rs.getDate( "data_nascimento" ) );
                pac.setNacionalidade( rs.getString( "nacionalidade" ) );
                pac.setGrauFormacao( rs.getString( "grau_formacao" ) );

                return pac;

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao buscar o objecto: " + e.getLocalizedMessage() );
        }
        return null;
    }

    public boolean existe_autor( String nome_autor, String sobre_nome )
    {
        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EXISTE_AUTOR );
            ps.setString( 1, nome_autor );
            ps.setString( 1, sobre_nome );
            ResultSet rs = ps.executeQuery();

            return rs.next();

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao verificar a exitência do autor" );
        }
        return false;
    }

}
