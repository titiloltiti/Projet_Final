package com.excilys.librarymanager.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import com.excilys.librarymanager.dao.IEmpruntDao;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class EmpruntDao implements IEmpruntDao {
    private static EmpruntDao instance;

    private EmpruntDao() {
    }

    public static EmpruntDao getInstance() {
        if (instance == null) {
            instance = new EmpruntDao();
        }
        return instance;
    }

    MembreDao membreDaoInst = MembreDao.getInstance();
    LivreDao livreDaoInst = LivreDao.getInstance();

    private static final String LIST_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
    private static final String LIST_CURRENT_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
    private static final String LIST_CURRENT_MEMBER_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
    private static final String LIST_CURRENT_BOOK_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
    private static final String GET_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";

    @Override
    public List<Emprunt> getList() throws DaoException {
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_ALL_QUERY);
                ResultSet res = preparedStatement.executeQuery();) {
            while (res.next()) {
                Emprunt e;
                if (res.getDate("dateRetour") == null) {
                    e = new Emprunt(res.getInt("id"), membreDaoInst.getById(res.getInt("idMembre")),
                            livreDaoInst.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate());
                } else {
                    e = new Emprunt(res.getInt("id"), membreDaoInst.getById(res.getInt("idMembre")),
                            livreDaoInst.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate(),
                            res.getDate("dateRetour").toLocalDate());
                }
                emprunts.add(e);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", e);
        }
        return emprunts;
    };

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_CURRENT_QUERY);
                ResultSet res = preparedStatement.executeQuery();) {
            while (res.next()) {
                Emprunt e = new Emprunt(res.getInt("id"), membreDaoInst.getById(res.getInt("idMembre")),
                        livreDaoInst.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate());
                emprunts.add(e);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la recuperation de la liste des emprunts actuels", e);
        }
        return emprunts;
    };

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        List<Emprunt> emprunts = new ArrayList<>();
        ResultSet res = null;

        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_CURRENT_MEMBER_QUERY);) {
            preparedStatement.setInt(1, idMembre);
            res = preparedStatement.executeQuery();
            while (res.next()) {
                Emprunt e = new Emprunt(res.getInt("id"), membreDaoInst.getById(res.getInt("idMembre")),
                        livreDaoInst.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate());
                emprunts.add(e);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la recuperation de la liste des emprunts actuels par membre", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return emprunts;
    };

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        List<Emprunt> emprunts = new ArrayList<>();
        ResultSet res = null;

        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_CURRENT_BOOK_QUERY);) {
            preparedStatement.setInt(1, idLivre);
            res = preparedStatement.executeQuery();
            while (res.next()) {
                Emprunt e = new Emprunt(res.getInt("id"), membreDaoInst.getById(res.getInt("idMembre")),
                        livreDaoInst.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate());
                emprunts.add(e);
            }
            System.out.println("GET: " + emprunts);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la recuperation de la liste des emprunts actuels par livre", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return emprunts;
    };

    @Override
    public Emprunt getById(int id) throws DaoException {
        Emprunt emprunt = new Emprunt();
        ResultSet res = null;
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {

            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            if (res.next()) {

                emprunt.setId(res.getInt("id"));
                emprunt.setIdMembre(membreDaoInst.getById(res.getInt("idMembre")));
                emprunt.setIdLivre(livreDaoInst.getById(res.getInt("idLivre")));
                emprunt.setDateEmprunt(res.getDate("dateEmprunt").toLocalDate());
                if (res.getDate("dateRetour") == null)
                    emprunt.setDateRetour(null);
                else
                    emprunt.setDateRetour(res.getDate("dateRetour").toLocalDate());
            }
            System.out.println("GET: " + emprunt);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la récupération du emprunt", e);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return emprunt;

    };

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);) {

            preparedStatement.setInt(1, idMembre);
            preparedStatement.setInt(2, idLivre);
            preparedStatement.setDate(3, Date.valueOf(dateEmprunt));
            preparedStatement.setDate(4, null);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la création du emprunt", e);
        }
    }; // Check signature

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {

            preparedStatement.setInt(1, emprunt.getIdMembre().getId());
            preparedStatement.setInt(2, emprunt.getIdLivre().getId());
            preparedStatement.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            if (emprunt.getDateRetour() == null)
                preparedStatement.setDate(4, null);
            else
                preparedStatement.setDate(4, Date.valueOf(emprunt.getDateRetour()));
            preparedStatement.setInt(5, emprunt.getId());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE : " + emprunt);
        } catch (SQLException e) {
            throw new DaoException("Probleme lors de la mise à jour du emprunt", e);
        }
    };

    @Override
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
            throw new DaoException("Probleme lors du comptage des emprunts", e);
        }
        return count;
    };

}
