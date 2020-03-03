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
    };

    public Membre getById(int id) throws DaoException {
    };

    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
    };

    public void update(Membre membre) throws DaoException {
    };

    public void delete(int id) throws DaoException {
    };

    public int count() throws DaoException {
    };
}