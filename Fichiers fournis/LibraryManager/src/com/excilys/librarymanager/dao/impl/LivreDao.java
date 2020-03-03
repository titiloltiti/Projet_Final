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
// import com.app.utils.EstablishConnection;

public class LivreDao implements ILivreDao {
    private static LivreDao instance;

    private LivreDao() {
    }

    public static ILivreDao getInstance() {
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
    };

    public Livre getById(int id) throws DaoException {
    };

    public int create(String titre, String auteur, String isbn) throws DaoException {
    };

    public void update(Livre livre) throws DaoException {
    };

    public void delete(int id) throws DaoException {
    };

    public int count() throws DaoException {
    };
}