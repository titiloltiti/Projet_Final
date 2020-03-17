package com.excilys.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.ILivreDao;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class LivreDao implements ILivreDao {
    private static LivreDao instance;

    private LivreDao() {
    }

    public static LivreDao getInstance() { // TODO : Change all to ILivreDao
        if (instance == null) {
            instance = new LivreDao();
        }
        return instance;
    }

    private static final String LIST_ALL_QUERY = "SELECT id, titre, auteur, isbn FROM livre;";
    private static final String GET_QUERY = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM livre WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM livre;";

    public List<Livre> getList() throws DaoException {
        List<Livre> books = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_ALL_QUERY);
                ResultSet res = preparedStatement.executeQuery();) {
            while (res.next()) {
                Livre b = new Livre(res.getInt("id"), res.getString("titre"), res.getString("auteur"),
                        res.getString("isbn"));
                books.add(b);
            }
            System.out.println("GET: " + books);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la recuperation de la liste des livres", e);
        }
        return books;
    };

    public Livre getById(int id) throws DaoException {
        Livre book = new Livre();
        ResultSet res = null;
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {

            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                book.setId(res.getInt("id"));
                book.setTitre(res.getString("titre"));
                book.setAuteur(res.getString("auteur"));
                book.setIsbn(res.getString("isbn"));
            }
            System.out.println("GET: " + book);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la récupération du livre", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return book;
    };

    public int create(String titre, String auteur, String isbn) throws DaoException {
        int id = -1;
        ResultSet res = null;
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY,
                        Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, isbn);
            preparedStatement.executeUpdate();
            res = preparedStatement.getGeneratedKeys();
            if (res.next()) {
                id = res.getInt(1);
            }
            res.close();
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la création du livre", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return id;
    };

    public void update(Livre livre) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {

            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getId());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE : " + livre);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la mise à jour du livre", e);
        }
    };

    public void delete(int id) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("DELETE : " + id);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la suppression du livre", e);
        }
    };

    public int count() throws DaoException {
        int count = -1;
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY,
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet res = preparedStatement.executeQuery();) {

            if (res.next()) {
                count = res.getInt(1);
            }
            res.close();
        } catch (SQLException e) {
            throw new DaoException("Probleme lors du comptage des livres", e);
        } 
        return count;
    };
}