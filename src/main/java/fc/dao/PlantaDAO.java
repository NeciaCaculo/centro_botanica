/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.Planta;
import fc.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Necia e Domingos Dala Vunge
 */
public class PlantaDAO
{

    private String SELECT_ALL = "SELECT *  FROM planta;";
    private String INSERTE = "INSERT INTO planta ( nome , descricao , nome_imagem, caminho_imagem, localizacao, utilidade, fk_grupo_planta, fk_estado_conservacao) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String EXISTE_PLANTA = "SELECT p.* FROM planta p WHERE p.nome = ?";
    private String EDITE = "UPDATE planta SET nome = ? , descricao = ? , nome_imagem = ? , caminho_imagem = ? , localizacao = ?, utilidade = ? , fk_grupo_planta = ?, fk_estado_conservacao = ? WHERE pk_planta = ?";
    private String DELETE = "DELETE FROM planta WHERE pk_planta = ?;";
    private final String FIND_BY_ID = "SELECT * FROM planta WHERE pk_planta = ?";

    ConexaoDB conexao = new ConexaoDB();

    public void update( Planta planta )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );

            ps.setString( 1, planta.getNome() );
            ps.setString( 2, planta.getDescricao() );
            ps.setString( 3, planta.getNome_imagem() );
            ps.setString( 4, planta.getCaminho_imagem() );
            ps.setString( 5, planta.getLocalizacao() );
            ps.setString( 6, planta.getUtilidade() );
            ps.setInt( 7, planta.getGrupoPlanta().getPkgrupo_planta() );
            ps.setInt( 8, planta.getEstadoConservacao().getPk_estado_conservacao() );
            ps.setInt( 9, planta.getPk_planta() );

            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( Planta planta )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( INSERTE );
            ps.setString( 1, planta.getNome() );
            ps.setString( 2, planta.getDescricao() );
            ps.setString( 3, planta.getNome_imagem() );
            ps.setString( 4, planta.getCaminho_imagem() );
            ps.setString( 5, planta.getLocalizacao() );
            ps.setString( 6, planta.getUtilidade() );
            ps.setInt( 7, planta.getGrupoPlanta().getPkgrupo_planta() );
            ps.setInt( 8, planta.getEstadoConservacao().getPk_estado_conservacao() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public void delete( Planta planta )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, planta.getPk_planta() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<Planta> findAll()
    {
        GrupoPlantaDAO grupoPlantaDAO = new GrupoPlantaDAO();
        EstadoConservacaoDAO estadoConservacaoDAO = new EstadoConservacaoDAO();
        List<Planta> lista = new ArrayList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Integer idGrupoPlanta = 0, idEstadoConservacao = 0;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( SELECT_ALL );
            rs = ps.executeQuery();
            while ( rs.next() )
            {
                Planta planta = new Planta();
                planta.setPk_planta( rs.getInt( "pk_planta" ) );
                planta.setNome( rs.getString( "nome" ) );
                planta.setDescricao( rs.getString( "descricao" ) );
                planta.setNome_imagem( rs.getString( "nome_imagem" ) );
                planta.setCaminho_imagem( rs.getString( "caminho_imagem" ) );
                planta.setLocalizacao( rs.getString( "localizacao" ) );
                planta.setUtilidade( rs.getString( "utilidade" ) );

                idGrupoPlanta = rs.getInt( "fk_grupo_planta" );
                idEstadoConservacao = rs.getInt( "fk_estado_conservacao" );

                planta.setGrupoPlanta( grupoPlantaDAO.findById( idGrupoPlanta ) );
                planta.setEstadoConservacao( estadoConservacaoDAO.findById( idEstadoConservacao ) );

                lista.add( planta );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public Planta findById( Integer id )
    {
        GrupoPlantaDAO grupoPlantaDAO = new GrupoPlantaDAO();
        EstadoConservacaoDAO estadoConservacaoDAO = new EstadoConservacaoDAO();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Integer idGrupoPlanta = 0, idEstadoConservacao = 0;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( FIND_BY_ID );
            ps.setInt( 1, id );
            rs = ps.executeQuery();
            if ( rs.next() )
            {
                Planta planta = new Planta();

                planta.setPk_planta( rs.getInt( "pk_planta" ) );
                planta.setNome( rs.getString( "nome" ) );
                planta.setDescricao( rs.getString( "descricao" ) );
                planta.setNome_imagem( rs.getString( "nome_imagem" ) );
                planta.setCaminho_imagem( rs.getString( "caminho_imagem" ) );
                planta.setLocalizacao( rs.getString( "localizacao" ) );
                planta.setUtilidade( rs.getString( "utilidade" ) );

                idGrupoPlanta = rs.getInt( "fk_grupo_planta" );
                idEstadoConservacao = rs.getInt( "fk_estado_conservacao" );

                planta.setGrupoPlanta( grupoPlantaDAO.findById( idGrupoPlanta ) );
                planta.setEstadoConservacao( estadoConservacaoDAO.findById( idEstadoConservacao ) );

                return planta;

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao buscar o objecto: " + e.getLocalizedMessage() );
        }
        return null;
    }

   

    public boolean existeNome( String nome_planta )
    {
        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EXISTE_PLANTA );
            ps.setString( 1, nome_planta );
            ResultSet rs = ps.executeQuery();

            return rs.next();

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao verificar a exitência do tipo de planta" );
        }
        return false;
    }

}
