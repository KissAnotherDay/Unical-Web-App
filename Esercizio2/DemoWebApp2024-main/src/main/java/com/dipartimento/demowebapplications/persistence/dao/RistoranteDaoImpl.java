package com.dipartimento.demowebapplications.persistence.dao;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RistoranteDaoImpl implements RistoranteDao {

    private static final String URL = "jdbc:mysql://localhost:3306/db1";
    private static final String USER = "";
    private static final String PASSWORD = "";

    @Override
    public List<Ristorante> findAll() {
        List<Ristorante> ristoranti = new ArrayList<>();
        String query = "SELECT * FROM ristoranti";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Ristorante ristorante = new Ristorante();
                ristorante.setId(rs.getInt("id"));
                ristorante.setNome(rs.getString("nome"));
                ristoranti.add(ristorante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ristoranti;
    }

    @Override
    public Ristorante findByPrimaryKey(String nome) {
        Ristorante ristorante = null;
        String query = "SELECT * FROM ristoranti WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ristorante = new Ristorante();
                ristorante.setId(rs.getInt("id"));
                ristorante.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ristorante;
    }

    @Override
    public void save(Ristorante ristorante) {
        String query = "INSERT INTO ristoranti (nome) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ristorante.getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ristorante ristorante) {
        String query = "DELETE FROM ristoranti WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ristorante.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPiattoToRistorante(int ristoranteId, int piattoId) {
        String query = "INSERT INTO piatto_ristorante (ristorante_id, piatto_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ristoranteId);
            stmt.setInt(2, piattoId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePiattoFromRistorante(int ristoranteId, int piattoId) {
        String query = "DELETE FROM piatto_ristorante WHERE ristorante_id = ? AND piatto_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ristoranteId);
            stmt.setInt(2, piattoId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Piatto> findPiattiByRistorante(int ristoranteId) {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT p.* FROM piatti p INNER JOIN piatto_ristorante pr ON p.id = pr.piatto_id WHERE pr.ristorante_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ristoranteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setId(rs.getInt("id"));
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
