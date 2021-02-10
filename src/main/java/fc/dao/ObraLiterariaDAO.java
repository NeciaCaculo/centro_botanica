/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.ObraLiteraria;
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
 * @author Necia e Domingos Dala Vunge
 */
public class ObraLiterariaDAO
{

    private String SELECT_ALL = "SELECT *  FROM obra_literaria;";
    private String INSERTE = "INSERT INTO obra_literaria ( titulo , data_lancamento, nome_imagem, caminho_imagem, descricao , status, fk_usuario, edicao) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String EXISTE_PLANTA = "SELECT p.* FROM obra_literaria p WHERE p.titulo = ?";
    private String EDITE = "UPDATE obra_literaria SET titulo = ? , data_lancamento = ? , nome_imagem = ? , caminho_imagem = ? , descricao = ?, status = ? , fk_usuario = ?, edicao = ? WHERE pk_obra_literaria = ?";
    private String DELETE = "DELETE FROM obra_literaria WHERE pk_obra_literaria = ?;";
    private final String FIND_BY_ID = "SELECT * FROM obra_literaria WHERE pk_obra_literaria = ?";

    ConexaoDB conexao = new ConexaoDB();

    public void update( ObraLiteraria obra_literaria )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );

            ps.setString( 1, obra_literaria.getTitulo());
            ps.setDate( 2, new Date( obra_literaria.getData_lancamento().getTime() ) );
            ps.setString( 3, obra_literaria.getNome_imagem() );
            ps.setString( 4, obra_literaria.getCaminho_imagem() );
            ps.setString( 5, obra_literaria.getDescricao() );
            ps.setString( 6, obra_literaria.getStatus() );
            ps.setInt( 7, obra_literaria.getUsuario().getPkusuario() );
            ps.setString( 8, obra_literaria.getEdicao());
            ps.setInt( 9, obra_literaria.getPk_obra_literaria() );

            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( ObraLiteraria obra_literaria )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( INSERTE );
            ps.setString( 1, obra_literaria.getTitulo());
            ps.setDate( 2, new Date( obra_literaria.getData_lancamento().getTime() ) );
            ps.setString( 3, obra_literaria.getNome_imagem() );
            ps.setString( 4, obra_literaria.getCaminho_imagem() );
            ps.setString( 5, obra_literaria.getDescricao() );
            ps.setString( 6, obra_literaria.getStatus() );
            ps.setInt( 7, obra_literaria.getUsuario().getPkusuario() );
            ps.setString( 8, obra_literaria.getEdicao());
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public void delete( ObraLiteraria obra_literaria )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, obra_literaria.getPk_obra_literaria() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<ObraLiteraria> findAll()
    {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<ObraLiteraria> lista = new ArrayList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Integer idUsuario = 0;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( SELECT_ALL );
            rs = ps.executeQuery();
            while ( rs.next() )
            {
                ObraLiteraria obra_literaria = new ObraLiteraria();
                obra_literaria.setPk_obra_literaria( rs.getInt( "pk_obra_literaria" ) );
                obra_literaria.setTitulo(rs.getString( "titulo" ) );
                obra_literaria.setData_lancamento(rs.getDate( "data_lancamento" ) );
                obra_literaria.setNome_imagem( rs.getString( "nome_imagem" ) );
                obra_literaria.setCaminho_imagem( rs.getString( "caminho_imagem" ) );
                obra_literaria.setDescricao( rs.getString( "descricao" ) );
                obra_literaria.setStatus( rs.getString( "status" ) );
                obra_literaria.setEdicao(rs.getString( "edicao" ) );

                idUsuario = rs.getInt( "fk_usuario" );

                obra_literaria.setUsuario( usuarioDAO.findById( idUsuario ) );

                lista.add( obra_literaria );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public ObraLiteraria findById( Integer id )
    {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EstadoConservacaoDAO estadoConservacaoDAO = new EstadoConservacaoDAO();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Integer idUsuario = 0, idEstadoConservacao = 0;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( FIND_BY_ID );
            ps.setInt( 1, id );
            rs = ps.executeQuery();
            if ( rs.next() )
            {
                ObraLiteraria obra_literaria = new ObraLiteraria();

                obra_literaria.setPk_obra_literaria( rs.getInt( "pk_obra_literaria" ) );
                obra_literaria.setTitulo(rs.getString( "titulo" ) );
                obra_literaria.setData_lancamento(rs.getDate( "data_lancamento" ) );
                obra_literaria.setNome_imagem( rs.getString( "nome_imagem" ) );
                obra_literaria.setCaminho_imagem( rs.getString( "caminho_imagem" ) );
                obra_literaria.setDescricao( rs.getString( "descricao" ) );
                obra_literaria.setStatus( rs.getString( "status" ) );
                obra_literaria.setEdicao(rs.getString( "edicao" ) );

                obra_literaria.setUsuario( usuarioDAO.findById( idUsuario ) );

                return obra_literaria;

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao buscar o objecto: " + e.getLocalizedMessage() );
        }
        return null;
    }

   

    public boolean existeNome( String nome_obra_literaria )
    {
        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EXISTE_PLANTA );
            ps.setString( 1, nome_obra_literaria );
            ResultSet rs = ps.executeQuery();

            return rs.next();

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao verificar a exitência do tipo de obra_literaria" );
        }
        return false;
    }

}
