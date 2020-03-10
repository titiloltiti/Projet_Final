package com.excilys.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.IMembreDao;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class MembreDao implements IMembreDao {
    private static MembreDao instance;

    private MembreDao() {
    }

    public static IMembreDao getInstance() {
        if (instance == null) {
            instance = new MembreDao();
        }
        return instance;
    }

    private static final String LIST_ALL_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
    private static final String GET_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM membre WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM membre;";

    public List<Membre> getList() throws DaoException {
        List<Membre> membres = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_ALL_QUERY);
                ResultSet res = preparedStatement.executeQuery();) {
            while (res.next()) {
                Membre m = new Membre(res.getInt("id"),res.getString("nom"),res.getString("prenom"),res.getString("adresse"),res.getString("email"),res.getString("telephone"),res.getString("abonnement")); // TODO use proper constructor with res
                membres.add(m);
            }
            System.out.println("GET: " + membres);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la recuperation de la liste des Membres", e);
        }   
        return membres;
    };

    public Membre getById(int id) throws DaoException {
        Membre membre = new Membre();
        ResultSet res = null;
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {

            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                membre.setId(res.getInt("id"));
                membre.setNom(res.getString("nom"));
                membre.setPrenom(res.getString("prenom"));
                membre.setAdresse(res.getString("adresse")); // WARNING: TYPE TEXT
                membre.setEmail(res.getString("email"));
                membre.setAbonnement(res.getString("abonnement")); // WARNING: TYPE ENUM
                membre.setTelephone(res.getString("telephone"));
            }
            System.out.println("GET: " + membre);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la récupération du membre", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return membre;
    };

    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        int id = -1;
        ResultSet res = null;
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);) {

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);//
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, telephone);
            preparedStatement.setString(6, "BASIC"); // Toujours incertitude sur le type
            preparedStatement.executeUpdate();
            res = preparedStatement.getGeneratedKeys();
            if (res.next()) {
                id = res.getInt(1);
            }
            res.close();
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la création du membre", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return id;
    };

    public void update(Membre membre) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {

            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setString(3, membre.getAdresse()); //
            preparedStatement.setString(4, membre.getEmail());
            preparedStatement.setString(5, membre.getTelephone());
            preparedStatement.setString(6, membre.getAbonnement()); //
            preparedStatement.setInt(7, membre.getId());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE : " + membre);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la mise à jour du membre", e);
        }
    };

    public void delete(int id) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("DELETE : " + id);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la suppression du membre", e);
        }
    };

    public int count() throws DaoException {
        int count = -1;
        ResultSet res = null;
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);) {

            preparedStatement.executeUpdate();
            res = preparedStatement.getGeneratedKeys();
            if (res.next()) {
                count = res.getInt(1);
            }
            res.close();
        } catch (SQLException e) {
            throw new DaoException("Probleme lors du comptage des membres", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    };
}