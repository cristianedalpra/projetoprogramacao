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
public class DaoRaça implements Dao<Raça> {

    @Override
    public boolean adiciona(Raça m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean altera(Raça m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Raça m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean pesquisa(Raça m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Raça> pesquisaTodos() {
        ObservableList<Raça> raças = FXCollections.observableArrayList();
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQLConstantesRaça.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Raça raça = new Raça(rs.getInt("id"), rs.getString("nome"), rs.getString("especie"));
                    raças.add(raça);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por raças no banco de dados!");
        }
        return raças;
    }

    public ObservableList<Raça> pesquisaCBox(String especie) {
        ObservableList<Raça> raças = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(SQLConstantesRaça.SEARCHCBOX);
            stmt.setString(1, especie);
            ResultSet rs = stmt.executeQuery();

            {
                while (rs.next()) {
                    Raça raça = new Raça(rs.getInt("id"), rs.getString("nome"), rs.getString("especie"));
                    raças.add(raça);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por raças no banco de dados!");
        }
        return raças;
    }

    public ObservableList<String> pesquisaEspecies() {
        ObservableList<String> especies = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(SQLConstantesRaça.SEARCHESPECIESEXISTENTES);
            ResultSet rs = stmt.executeQuery();

            {
                while (rs.next()) {
                    especies.add(rs.getString("especie"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por especies no banco de dados!");
        }
        return especies;
    }

    public Raça pesquisaRaçaPorId(int id) {
        Raça raça = new Raça();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesRaça.SEARCHWITHID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    raça.setId(rs.getInt("id"));
                    raça.setNome(rs.getString("nome"));
                    raça.setEspecie(rs.getString("especie"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return raça;
    }

    public ObservableList<Raça> pesquisaRaçasExistentes(String especie) {
        ObservableList<Raça> raças = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(SQLConstantesRaça.SEARCHRAÇASEXISTENTES);
            stmt.setString(1, especie);
            ResultSet rs = stmt.executeQuery();

            {
                while (rs.next()) {
                    Raça raça = new Raça(rs.getInt("id"), rs.getString("nome"), rs.getString("especie"));
                    raças.add(raça);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por raças no banco de dados!");
        }
        return raças;
    }
}
