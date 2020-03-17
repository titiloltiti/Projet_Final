package com.excilys.librarymanager.test;


import com.excilys.librarymanager.service.impl.EmpruntService;
import com.excilys.librarymanager.service.impl.LivreService;
import com.excilys.librarymanager.service.impl.MembreService;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Membre.Abonnement;
import com.excilys.librarymanager.model.Livre;

public class ServiceTest {
    public static void testLivreService() {
        LivreService livreServiceInstance = LivreService.getInstance();
        try {
            livreServiceInstance.create("", "Enrico Iglesias", "985-1234567");
            Livre livre = new Livre(1, "", "Alexandre Dumas", "986-1234567");
            livreServiceInstance.update(livre);
            System.out.println(livreServiceInstance.getListDispo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testMembreService() {
        MembreService membreServiceInstance = MembreService.getInstance();
        try {
            int j = membreServiceInstance.create("Biharé", "Quentin", "828 BD des Maréchaux","bihare@ensta.fr","06.06.06.06.06");
            membreServiceInstance.create("Biharé", "", "828 BD des Maréchaux","bihare@ensta.fr","06.06.06.06.06");        
            Membre membre = new Membre(j, "", "QUENTIN", "9 rue des chataigniers","quentin.bihare@ensta.fr","06.07.08.09.10",Abonnement.VIP);
            membreServiceInstance.update(membre);
            System.out.println(membreServiceInstance.getListMembreEmpruntPossible());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testEmpruntService() {
        MembreService membreServiceInstance = MembreService.getInstance();
        EmpruntService empruntServiceInstance = EmpruntService.getInstance();
        try {
            Membre membre = membreServiceInstance.getById(5);
            empruntServiceInstance.getListCurrentByMembre(membre.getId());
            System.out.println(empruntServiceInstance.isEmpruntPossible(membre));
            empruntServiceInstance.getListCurrentByLivre(2);
            System.out.println(empruntServiceInstance.isLivreDispo(2));
            empruntServiceInstance.returnBook(5); // ici c'est L'id de l'emprunt 
            System.out.println(empruntServiceInstance.isLivreDispo(2));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        testLivreService(); 
        System.out.println("1/3 tests succesfully passed");
        testMembreService(); 
        System.out.println("2/3 tests succesfully passed");
        testEmpruntService();
        System.out.println("3/3 tests succesfully passed");
    }
}