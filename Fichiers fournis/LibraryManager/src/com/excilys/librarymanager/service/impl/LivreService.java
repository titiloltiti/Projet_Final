package com.excilys.librarymanager.service.impl;

import com.excilys.librarymanager.service.ILivreService;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.dao.impl.LivreDao;
import com.excilys.librarymanager.exception.DaoException;


import java.util.ArrayList;
import java.util.List;

public class LivreService implements ILivreService {

    private static LivreService instance;

    private LivreService() {
    }

    public static LivreService getInstance() {
        if (instance == null) {
            instance = new LivreService();
        }
        return instance;
    }

    public List<Livre> getList() throws ServiceException {
        LivreDao livreDao = LivreDao.getInstance();
        List<Livre> livres = new ArrayList<>();
        try {
            livres = livreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return livres;
    };

    public List<Livre> getListDispo() throws ServiceException {
        EmpruntService empruntService = EmpruntService.getInstance();
        LivreDao livreDao = LivreDao.getInstance();
        List<Livre> livres = new ArrayList<>();
        List<Livre> livres_dispo = new ArrayList<>();
        try {
            livres = livreDao.getList();
            for (Livre livre : livres) {
                if (empruntService.isLivreDispo(livre.getId()))
                    livres_dispo.add(livre);
            }
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        } catch (ServiceException e1) {
            System.out.println(e1.getMessage());
        }
        return livres_dispo;
    };

    public Livre getById(int id) throws ServiceException {
        LivreDao livreDao = LivreDao.getInstance();
        Livre livre = new Livre();
        try {
            livreDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return livre;
    };

    public int create(String titre, String auteur, String isbn) throws ServiceException {
        LivreDao livreDao = LivreDao.getInstance();
        int i = -1;
        try {
            if (titre == "") 
                throw new ServiceException("Titre vide lors de la création du livre");
            i = livreDao.create(titre, auteur, isbn);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    };

    public void update(Livre livre) throws ServiceException {
        LivreDao livreDao = LivreDao.getInstance();
        try {
            if (livre.getTitre() == "") 
                throw new ServiceException("Titre vide lors de la mise à jour du livre : " + livre);
            livreDao.update(livre);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    };

    public void delete(int id) throws ServiceException {
        LivreDao livreDao = LivreDao.getInstance();
        try {
            livreDao.delete(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
    };

    public int count() throws ServiceException {
        LivreDao livreDao = LivreDao.getInstance();
        int i = -1;
        try {
            i = livreDao.count();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
        }
        return i;
    };
}
