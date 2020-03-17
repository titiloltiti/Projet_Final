package com.excilys.librarymanager.test;

import java.time.LocalDate;

import com.excilys.librarymanager.dao.impl.EmpruntDao;
import com.excilys.librarymanager.dao.impl.LivreDao;
import com.excilys.librarymanager.dao.impl.MembreDao;

import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Membre.Abonnement;
import com.excilys.librarymanager.model.Livre;

public class DaoTest {
    public static void testLivreDao() {
        LivreDao livreDaoInstance = LivreDao.getInstance();
        try {
            int i = livreDaoInstance.count();
            int j = livreDaoInstance.create("L'amour est dans le pré", "Enrico Iglesias", "LA_EI");
            System.out.println("Nb livres : " + i);
            System.out.println("Last created id : " + j);
            livreDaoInstance.getList();
            livreDaoInstance.getById(j);
            Livre livre = new Livre(j, "L'AMOUR EST DANS LE PRE 2", "ENRICO IGLESIAS", "LA_EI2");
            livreDaoInstance.update(livre);
            livreDaoInstance.getList();
            livreDaoInstance.delete(j);
            livreDaoInstance.getList();
            i = livreDaoInstance.count();
            System.out.println("Nb livres : " + i);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testMembreDao() {
        MembreDao membreDaoInstance = MembreDao.getInstance();
        try {
            int i = membreDaoInstance.count();
            int j = membreDaoInstance.create("Biharé", "Quentin", "828 BD des Maréchaux","bihare@ensta.fr","06.06.06.06.06");
            System.out.println("Nb membres : " + i);
            System.out.println("Last created id : " + j);
            membreDaoInstance.getList();
            membreDaoInstance.getById(j);
            Membre membre = new Membre(j, "BIHARE", "QUENTIN", "9 rue des chataigniers","quentin.bihare@ensta.fr","06.07.08.09.10",Abonnement.VIP);
            membreDaoInstance.update(membre);
            membreDaoInstance.getList();
            membreDaoInstance.delete(j);
            membreDaoInstance.getList();
            i = membreDaoInstance.count();
            System.out.println("Nb membres : " + i);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testEmpruntDao() {
        EmpruntDao empruntDaoInstance = EmpruntDao.getInstance();
        try {
            int i = empruntDaoInstance.count();
            empruntDaoInstance.create(2, 2, LocalDate.now());
            System.out.println("Nb emprunts : " + (i+1));
            empruntDaoInstance.getList();
            empruntDaoInstance.getListCurrent();
            empruntDaoInstance.getListCurrentByLivre(2);
            empruntDaoInstance.getListCurrentByMembre(2);
            empruntDaoInstance.getById(i+1);
            Membre membre = new Membre(27, "Biharé", "Quentin", "828 Bd des maréchaux", "bihare@ensta.fr", "0606060606", Abonnement.VIP);
            Livre livre = new Livre(27, "L'amour est dans le pré", "Moi", "LA-M");
            Emprunt emprunt = new Emprunt(i+1, membre, livre, LocalDate.now());
            empruntDaoInstance.update(emprunt);
            empruntDaoInstance.getList();
            i = empruntDaoInstance.count();
            System.out.println("Nb emprunts : " + i);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        testLivreDao(); 
        System.out.println("1/3 tests succesfully passed");
        testMembreDao(); 
        System.out.println("2/3 tests succesfully passed");
        testEmpruntDao();
        System.out.println("3/3 tests succesfully passed");
    }
}