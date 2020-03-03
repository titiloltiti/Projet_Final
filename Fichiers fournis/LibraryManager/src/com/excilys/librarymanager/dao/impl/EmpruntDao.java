package com.excilys.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.IEmpruntDao;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class EmpruntDao implements IEmpruntDao {
    private static EmpruntDao instance;

    private EmpruntDao() {
    }

    public static IEmpruntDao getInstance() {
        if (instance == null) {
            instance = new EmpruntDao();
        }
        return instance;
    }

    private static final String LIST_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
    private static final String LIST_UNRETURNED_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
    private static final String LIST_UNRETURNED_MEMBER_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
    private static final String LIST_UNRETURNED_BOOK_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
    private static final String GET_BORROW_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHEREid = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";

    @Override
    public List<Emprunt> getList() throws DaoException {
    };

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
    };

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
    };

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
    };

    @Override
    public Emprunt getById(int id) throws DaoException {
    };

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
    };

    @Override
    public void update(Emprunt emprunt) throws DaoException {
    };

    @Override
    public int count() throws DaoException {
    };

}
