/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos.ongdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WINDOWS10
 */
public class DaoUsuario implements Dao<Usuario> {

    @Override
    public boolean adiciona(Usuario usuario) {
        String sql = SQLConstantesUsuario.INSERT;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, usuario.getNome());
                stmt.setString(3, usuario.getSenha());
                stmt.setString(4, usuario.getCargo());
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir dados na tabela Usuario!");
            return false;
        }
        return true;
    }

    @Override
    public boolean altera(Usuario usuario) {
        String sql = SQLConstantesUsuario.UPDATE;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSenha());
                stmt.setString(3, usuario.getCargo());
                stmt.setInt(4, usuario.getId());
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao alterar dados do usuário " + usuario.getNome());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Usuario usuario) {
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQLConstantesUsuario.REMOVE)) {
                stmt.setInt(1, usuario.getId());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário " + usuario.getNome());
            return false;
        }
        return true;
    }

    @Override
    public boolean pesquisa(Usuario usuario) {
        ObservableList<Usuario> allUsers = pesquisaTodos();

        for (Usuario u : allUsers) {
            if (u.equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ObservableList<Usuario> pesquisaTodos() {
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQLConstantesUsuario.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"), rs.getString("cargo"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por usuarios no banco de dados!");
        }
        return usuarios;
    }
    
    public boolean entrar(Usuario usuario) {
        ObservableList<Usuario> usuarios = new DaoUsuario().pesquisaTodos();
        for (Usuario u : usuarios) {
            if(u.getNome().equals(usuario.getNome()) && u.getSenha().equals(usuario.getSenha()))
                return true;
        }
        return false;
    }
    
    public boolean nomeEmUso(Usuario usuario) {
        ObservableList<Usuario> usuarios = new DaoUsuario().pesquisaTodos();
        for (Usuario u : usuarios) {
            if(u.getNome().equals(usuario.getNome()))
                return true;
        }
        return false;
    }
}
