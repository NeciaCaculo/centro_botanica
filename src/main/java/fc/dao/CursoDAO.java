/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fc.dao;

import fc.modelo.Curso;
import fc.util.ConexaoDB;
import fc.modelo.TipoCurso;
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
public class CursoDAO
{

    private String SELECT_ALL = "SELECT *  FROM curso;";
    private String INSERT = "INSERT INTO curso (`nome_curso`,`descricao`, `modalidade_pagto`, `documentos`, `topicos`, `data_inicio`,`data_fim`,`pk_tipo_curso`,`preco`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String EXISTE_CURSO = "SELECT p.* FROM curso p WHERE p.nome_curso = ?";
    private String EDITE = "UPDATE curso SET nome_curso = ?, descricao = ?, modalidade_pagto= ?, documentos= ?, topicos= ?, data_inicio = ?, data_fim = ?, fk_tipo_curso = ?, preco = ? WHERE idcurso = ?";
    private String DELETE = "DELETE FROM curso WHERE idcurso = ?;";
    private String LIST_BY_NAME = "SELECT c.idcurso, c.descricao, c.nome_curso, c.modalidade_pagto, c.documentos, c.topicos, c.data_inicio, c.data_fim, t.designacao, c.preco FROM curso c INNER JOIN tipo_curso t ON t.pk_tipo_curso = c.fk_tipo_curso WHERE c.nome_curso LIKE ?";
    private String INSERTE = "INSERT INTO `curso` (`nome_curso`,`descricao`,`modalidade_pagto`, `documentos`, `topicos`, `data_inicio`,`data_fim`,`fk_tipo_curso`,`preco`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String FIND_BY_ID = "SELECT * FROM curso WHERE idcurso = ?";

    ConexaoDB conexao = new ConexaoDB();

    public void update( Curso pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EDITE );

            ps.setString( 1, pac.getNome_curso() );
            ps.setString( 2, pac.getDescricao() );
            ps.setString( 3, pac.getModalidade_pagto() );
            ps.setString( 4, pac.getDocumentos() );
            ps.setString( 5, pac.getTopicos() );
            ps.setDate( 6, new Date( pac.getData_inicio().getTime() ) );
            ps.setDate( 7, new Date( pac.getData_fim().getTime() ) );
            ps.setInt( 8, pac.getTipo_Curso().getPktipo_curso() );
            ps.setDouble( 9, pac.getPreco() );
            ps.setInt( 10, pac.getPk_curso() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Actualizar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public void insert( Curso pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( INSERTE );
            ps.setString( 1, pac.getNome_curso() );
            ps.setString( 2, pac.getDescricao() );
            ps.setString( 3, pac.getModalidade_pagto() );
            ps.setString( 4, pac.getDocumentos() );
            ps.setString( 5, pac.getTopicos() );
            ps.setDate( 6, new Date( pac.getData_inicio().getTime() ) );
            ps.setDate( 7, new Date( pac.getData_fim().getTime() ) );
            ps.setInt( 8, pac.getTipo_Curso().getPktipo_curso() );
            ps.setDouble( 9, pac.getPreco() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Inserir os dados no Banco de Dados: " + e.getLocalizedMessage() );
        }
    }

    public void delete( Curso pac )
    {

        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( DELETE );
            ps.setInt( 1, pac.getPk_curso() );
            ps.executeUpdate();
        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Eliminar o Registro: " + e.getLocalizedMessage() );
        }
    }

    public List<Curso> findAll()
    {
        TipoCursoDAO tipoCursoDAO = new TipoCursoDAO();
        List<Curso> lista = new ArrayList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Integer idTipoCurso = 0;
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( SELECT_ALL );
            rs = ps.executeQuery();
            while ( rs.next() )
            {
                Curso pac = new Curso();
                pac.setPk_curso( rs.getInt( "idcurso" ) );
                pac.setNome_curso( rs.getString( "nome_curso" ) );
                pac.setDescricao( rs.getString( "descricao" ) );
                pac.setModalidade_pagto( rs.getString( "modalidade_pagto" ) );
                pac.setDocumentos( rs.getString( "documentos" ) );
                pac.setTopicos( rs.getString( "topicos" ) );
                pac.setData_inicio( rs.getDate( "data_inicio" ) );
                pac.setData_fim( rs.getDate( "data_fim" ) );
                pac.setPreco( rs.getDouble( "preco" ) );

                idTipoCurso = rs.getInt( "fk_tipo_curso" );

                pac.setTipo_Curso( tipoCursoDAO.findById( idTipoCurso ) );

                lista.add( pac );
            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public Curso findById( Integer id )
    {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        Integer idTipoCurso = 0;
        TipoCursoDAO tipoCursoDAO = new TipoCursoDAO();
        try
        {
            con = conexao.ligarBB();
            ps = con.prepareStatement( FIND_BY_ID );
            ps.setInt( 1, id );
            rs = ps.executeQuery();
            if ( rs.next() )
            {
                Curso pac = new Curso();

                pac.setPk_curso( rs.getInt( "idcurso" ) );
                pac.setNome_curso( rs.getString( "nome_curso" ) );
                pac.setDescricao( rs.getString( "descricao" ) );
                pac.setModalidade_pagto( rs.getString( "modalidade_pagto" ) );
                pac.setDocumentos( rs.getString( "documentos" ) );
                pac.setTopicos( rs.getString( "topicos" ) );
                pac.setData_inicio( rs.getDate( "data_inicio" ) );
                pac.setData_fim( rs.getDate( "data_fim" ) );
                idTipoCurso = rs.getInt( "fk_tipo_curso" );
                pac.setTipo_Curso( tipoCursoDAO.findById( idTipoCurso ) );

                return pac;

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao buscar o objecto: " + e.getLocalizedMessage() );
        }
        return null;
    }

    public List<Curso> findByName( String nome )
    {
        List<Curso> lista = new ArrayList<>();
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
                Curso pac = new Curso();
                pac.setPk_curso( rs.getInt( "c.idcurso" ) );
                pac.setNome_curso( rs.getString( "c.nome_curso" ) );
                pac.setDescricao( rs.getString( "c.descricao" ) );
                pac.setModalidade_pagto( rs.getString( "c.modalidade_pagto" ) );
                pac.setDocumentos( rs.getString( "c.documentos" ) );
                pac.setTopicos( rs.getString( "c.topicos" ) );
                pac.setData_inicio( rs.getDate( "c.data_inicio" ) );
                pac.setData_fim( rs.getDate( "c.data_fim" ) );

                TipoCurso t = new TipoCurso();
                t.setDesignacao( rs.getString( "t.designacao" ) );
                pac.setTipo_Curso( t );

                lista.add( pac );

            }

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao Listar: " + e.getLocalizedMessage() );
        }
        return lista;
    }

    public boolean existeDescricao( String nome_curso )
    {
        PreparedStatement ps;
        try
        {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement( EXISTE_CURSO );
            ps.setString( 1, nome_curso );
            ResultSet rs = ps.executeQuery();

            return rs.next();

        }
        catch ( SQLException e )
        {
            System.err.println( "Erro ao verificar a exitência do tipo de curso" );
        }
        return false;
    }

}
