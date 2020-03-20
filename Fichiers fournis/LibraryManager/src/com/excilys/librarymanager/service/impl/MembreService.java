package com.excilys.librarymanager.service.impl;

import com.excilys.librarymanager.service.IMembreService;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.dao.impl.MembreDao;
import com.excilys.librarymanager.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class MembreService implements IMembreService {

    private static MembreService instance;

    private MembreService() {
    }

    public static MembreService getInstance() {
        if (instance == null) {
            instance = new MembreService();
        }
        return instance;
    }

    public List<Membre> getList() throws ServiceException {
        MembreDao membreDao = MembreDao.getInstance();
        List<Membre> membres = new ArrayList<>();
        try {
            membres = membreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return membres;
    };

    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        EmpruntService empruntService = EmpruntService.getInstance();
        MembreDao membreDao = MembreDao.getInstance();
        List<Membre> membres = new ArrayList<>();
        List<Membre> membres_possible = new ArrayList<>();
        try {
            membres = membreDao.getList();
            for (Membre membre : membres) {
                if (empruntService.isEmpruntPossible(membre))
                    membres_possible.add(membre);
            }
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        } catch (ServiceException e1) {
            System.out.println(e1.getMessage());
        }
        return membres_possible;

    };

    public Membre getById(int id) throws ServiceException {
        MembreDao membreDao = MembreDao.getInstance();
        Membre membre = new Membre();
        try {
            membre = membreDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return membre;
    };

    public int create(String nom, String prenom, String adresse, String email, String telephone)
            throws ServiceException {
        MembreDao membreDao = MembreDao.getInstance();
        int i = -1;
        try {
            if (nom == "")
                throw new ServiceException("Nom vide lors de la création du membre");

            if (prenom == "")
                throw new ServiceException("Prénom vide lors de la création du membre");

            i = membreDao.create(nom.toUpperCase(), prenom, adresse, email, telephone);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    };

    public void update(Membre membre) throws ServiceException {
        MembreDao membreDao = MembreDao.getInstance();
        try {
            if (membre.getNom() == "")
                throw new ServiceException("Nom vide lors de la mise à jour du membre : " + membre);
            if (membre.getPrenom() == "")
                throw new ServiceException("Prénom vide lors de la mise à jour du membre : " + membre);
            membre.setNom(membre.getNom().toUpperCase());
            membreDao.update(membre);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    };

    public void delete(int id) throws ServiceException {
        MembreDao membreDao = MembreDao.getInstance();
        try {
            membreDao.delete(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    };

    public int count() throws ServiceException {
        MembreDao membreDao = MembreDao.getInstance();
        int i = -1;
        try {
            i = membreDao.count();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    };
}
