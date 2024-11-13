package com.dipartimento.demowebapplications.persistence.dao;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDaoImpl implements PiattoDao {

    private static final String URL = "jdbc:mysql://localhost:3306/dbprova"; 
    private static final String USER = ""; 
    private static final String PASSWORD = ""; 

    @Override
    public List<Piatto> findAll() {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT * FROM piatti";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setNome(rs.getString("nome"));
                piatto.setIngredienti(rs.getString("ingredienti"));
                piatti.add(piatto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatti;
    }

    @Override
    public Piatto findByPrimaryKey(String nome) {
        Piatto piatto = null;
        String query = "SELECT * FROM piatti WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                piatto = new Piatto();
                piatto.setNome(rs.getString("nome"));
                piatto.setIngredienti(rs.getString("ingredienti"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatto;
    }

    @Override
    public void save(Piatto piatto) {
        String query = "INSERT INTO piatti (nome, ingredienti) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, piatto.getNome());
            stmt.setString(2, piatto.getIngredienti());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Piatto piatto) {
        String query = "DELETE FROM piatti WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, piatto.getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Piatto> findAllByRistoranteName(String name) {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT * FROM piatti WHERE ristorante_nome = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setNome(rs.getString("nome"));
                piatto.setIngredienti(rs.getString("ingredienti"));
                piatti.add(piatto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatti;
    }
}
