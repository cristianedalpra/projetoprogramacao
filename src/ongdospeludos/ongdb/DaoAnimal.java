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
public class DaoAnimal implements Dao<Animal> {

    @Override
    public boolean adiciona(Animal animal) {
        String sql = SQLConstantesAnimal.INSERT;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, animal.getNome());
                stmt.setString(3, animal.getPorte());
                stmt.setInt(4, animal.getIdade());
                stmt.setString(5, animal.getSexo());
                stmt.setBoolean(6, animal.isCastrado());
                stmt.setString(7, animal.getCaminhoFoto());
                stmt.setInt(8, animal.getRaçaId());
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir dados na tabela Animal!");
            return false;
        }
        return true;
    }

    @Override
    public boolean altera(Animal animal) {
           String sql = SQLConstantesAnimal.UPDATE;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, animal.getNome());
                stmt.setString(2, animal.getPorte());
                stmt.setInt(3, animal.getIdade());
                stmt.setString(4, animal.getSexo());
                stmt.setBoolean(5, animal.isCastrado());
                stmt.setString(6, animal.getCaminhoFoto());
                stmt.setInt(7, animal.getRaçaId());
                stmt.setInt(8, animal.getId());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar dados do animal " + animal.getNome());
            return false;
        }
        System.out.println("a");
        return true;  
    }

    @Override
    public boolean remove(Animal m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean pesquisa(Animal m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Animal> pesquisaTodos() {
        ObservableList<Animal> animais = FXCollections.observableArrayList();
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQLConstantesAnimal.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setPorte(rs.getString("porte"));
                    animal.setIdade(rs.getInt("idade"));
                    animal.setSexo(rs.getString("sexo"));
                    animal.setCastrado(rs.getBoolean("castrado"));
                    animal.setCaminhoFoto(rs.getString("caminhoFoto"));
                    animal.setRaçaId(rs.getInt("raçaId"));
                    animais.add(animal);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return animais;
    }

    public Animal buscaAnimalPorId(int id) {
        Animal animal = new Animal();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesAnimal.SEARCHWITHID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setPorte(rs.getString("porte"));
                    animal.setIdade(rs.getInt("idade"));
                    animal.setSexo(rs.getString("sexo"));
                    animal.setCastrado(rs.getBoolean("castrado"));
                    animal.setCaminhoFoto(rs.getString("caminhoFoto"));
                    animal.setRaçaId(rs.getInt("raçaId"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return animal;
    }
    
    public int getHighestId(){
        int id = 0;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesAnimal.SEARCHHIGHESTID);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return id;
    }
}
