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
 * @author Necia & Domingos Dala Vunge
 */
public class EventoDAO
{

    private String SELECT_ALL = "SELECT e.pk_eventos, e.titulo, e.data_evento, e.descricao, e.nome_imagem, e.caminho_imagem, u.nomeCompleto  FROM eventos e INNER JOIN usuario u ON u.pkusuario = e.fk_usuario";
    private String SELECT_ALL_IMAGENS = "SELECT nome_imagem FROM eventos";
    private String INSERT = "INSERT INTO eventos (`titulo`,`data_evento`, `descricao`, `nome_imagem`, `caminho_imagem`, `nomeCompleto`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private String EDITE = "UPDATE eventos SET titulo = ?, data_evento = ?, descricao = ?, nome_imagem = ? WHERE pk_eventos = ?";
    private String DELETE = "DELETE FROM eventos WHERE pk_eventos = ?;";
    private String LIST_BY_NAME = "SELECT e.pk_eventos, e.titulo, e.data_evento, e.descricao, e.nome_imagem, e.caminho_imagem, u.nomeCompleto FROM eventos e INNER JOIN usuario u ON u.pkusuario = e.fk_usuario WHERE e.titulo LIKE ?";
    private String INSERTE = "INSERT INTO eventos (titulo , data_evento, descricao, nome_imagem, caminho_imagem, fk_usuario) VALUES ( ?, ?, ?, ?, ?, ?);";

    ConexaoDB conexao = new ConexaoDB();

    public void update( Evento pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );

            ps.setString( 1, pac.getTitulo() );
            ps.setDate( 2, new Date( pac.getData_evento().getTime() ) );
            ps.setString( 3, pac.getDescricao() );
            ps.setString( 4, pac.getNome_imagem() );
            ps.setInt( 5, pac.getPk_evento() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( Evento pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();

            // (titulo , data_evento, descricao, nome_imagem, caminho_imagem, fk_usuario)
            ps = con.prepareStatement( INSERTE );
            ps.setString( 1, pac.getTitulo() );
            ps.setDate( 2, new Date( pac.getData_evento().getTime() ) );
            ps.setString( 3, pac.getDescricao() );
            ps.setString( 4, pac.getNome_imagem() );
            ps.setString( 5, pac.getCaminho_imagem() );
            ps.setInt( 6, pac.getUsuario().getPkusuario() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public void delete( Evento pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, pac.getPk_evento() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<Evento> findAll()
    {
        List<Evento> lista = new ArrayList<>();
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
                Evento pac = new Evento();
                pac.setPk_evento( rs.getInt( "e.pk_eventos" ) );
                pac.setTitulo( rs.getString( "e.titulo" ) );
                pac.setData_evento( rs.getDate( "e.data_evento" ) );
                pac.setDescricao( rs.getString( "e.descricao" ) );
                pac.setNome_imagem( rs.getString( "e.nome_imagem" ) );
                pac.setCaminho_imagem( rs.getString( "e.caminho_imagem" ) );

                Usuario u = new Usuario();
                u.setNomeCompleto( rs.getString( "u.nomeCompleto" ) );
                pac.setUsuario( u );

                lista.add( pac );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public List<String> getAllImagens()
    {
        List<String> lista = new ArrayList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( SELECT_ALL_IMAGENS );
            rs = ps.executeQuery();
            while ( rs.next() )
            {
                Evento pac = new Evento();
                pac.setNome_imagem( rs.getString( "nome_imagem" ) );
                lista.add( pac.getNome_imagem() );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public List<Evento> findByName( String nome )
    {
        List<Evento> lista = new ArrayList<>();
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
                Evento pac = new Evento();
                pac.setPk_evento( rs.getInt( "e.pk_eventos" ) );
                pac.setTitulo( rs.getString( "e.titulo" ) );
                pac.setData_evento( rs.getDate( "e.data_evento" ) );
                pac.setDescricao( rs.getString( "e.descricao" ) );
                pac.setNome_imagem( rs.getString( "e.nome_imagem" ) );
                pac.setCaminho_imagem( rs.getString( "e.caminho_imagem" ) );

                Usuario u = new Usuario();
                u.setNomeCompleto( rs.getString( "u.nomeCompleto" ) );
                pac.setUsuario( u );

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
