package com.excilys.librarymanager.test;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Membre.Abonnement;

import java.time.LocalDate;

import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Livre;

public class ModeleTest {
    public static void main(String[] args) {

        Membre membre = new Membre(1, "BIHARE", "Quentin", "828 BD des Maréchaux", "bihare@ensta.fr", "06.06.06.06.06",
                Abonnement.VIP);
        Livre livre = new Livre(1, "L'amour est dans le pré", "Enrico Iglesias", "LA_EI");
        Emprunt emprunt = new Emprunt(1, membre, livre, LocalDate.now());
        System.out.println(emprunt);
    }
}