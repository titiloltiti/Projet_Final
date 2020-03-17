package com.excilys.librarymanager.service.impl;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.dao.impl.EmpruntDao;
import com.excilys.librarymanager.dao.impl.LivreDao;
import com.excilys.librarymanager.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class EmpruntService {

    private static EmpruntService instance;

    private EmpruntService() {
    }

    public static EmpruntService getInstance() {
        if (instance == null) {
            instance = new EmpruntService();
        }
        return instance;
    }

    public List<Emprunt> getList() throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    };

    public List<Emprunt> getListCurrent() throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrent();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    };

    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByMembre(idMembre);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    };

    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunts;
    };

    public Emprunt getById(int id) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        Emprunt emprunt = new Emprunt();
        try {
            empruntDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return emprunt;
    };

    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        try {
            empruntDao.create(idMembre, idLivre, dateEmprunt);
            ;
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    };

    public void returnBook(int id) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        try {
            Emprunt emprunt = empruntDao.getById(id);
            if (emprunt.getDateRetour() == null) {
                emprunt.setDateRetour(LocalDate.now());
                empruntDao.update(emprunt);
            } else
                throw new ServiceException("Le livre a été déjà retourné : " + emprunt);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());

        }
    };

    public int count() throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        int i = -1;
        try {
            i = empruntDao.count();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    };

    public boolean isLivreDispo(int idLivre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return emprunts.isEmpty();
    };

    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByMembre(membre.getId());
            int nb_emprunts = emprunts.size();
            switch (membre.getAbonnement()) {
                case BASIC:
                    return nb_emprunts < 2;
                case PREMIUM:
                    return nb_emprunts < 5;
                case VIP:
                    return nb_emprunts < 20;
            }
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return false;
    };
}
