package app.model.niveau;

import app.model.formes.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class NiveauxFactory {

    public static Map<String, Map<String, FormePlace>> creerTousLesNiveaux() {
        Map<String, Map<String, FormePlace>> niveaux = new LinkedHashMap<>();

        // Niveau 1 : Visage Souriant
        Map<String, FormePlace> visage = new LinkedHashMap<>();
        visage.put("tete", new FormePlace(new FormeCercle(11, 11), 5, 0));
        visage.put("oeilGauche", new FormePlace(new FormeCercle(3, 3), 7, 2));
        visage.put("oeilDroit", new FormePlace(new FormeCercle(3, 3), 11, 2));
        visage.put("nez", new FormePlace(new FormeCercle(3, 3), 9, 5));
        visage.put("bouche", new FormePlace(new FormeCercle(3, 7), 7, 8));
        niveaux.put("Visage Souriant", visage);

        // Niveau 2 : Maison
        Map<String, FormePlace> maison = new LinkedHashMap<>();
        maison.put("corps", new FormePlace(new FormeCarre(10, 10), 5, 10));
        maison.put("toit", new FormePlace(new FormeTriangle(5, 10), 5, 5));
        maison.put("porte", new FormePlace(new FormeCarre(4, 2), 9, 16));
        maison.put("fenetreGauche", new FormePlace(new FormeCarre(2, 2), 6, 12));
        maison.put("fenetreDroite", new FormePlace(new FormeCarre(2, 2), 12, 12));
        niveaux.put("Maison", maison);

        // Niveau 3 : Arbre
        Map<String, FormePlace> arbre = new LinkedHashMap<>();
        arbre.put("couronne", new FormePlace(new FormeCercle(7, 7), 6, 2));
        arbre.put("tronc", new FormePlace(new FormeCarre(6, 2), 9, 9));
        niveaux.put("Arbre", arbre);

        // Niveau 4 : Voiture
        Map<String, FormePlace> voiture = new LinkedHashMap<>();
        voiture.put("corps", new FormePlace(new FormeCarre(4, 10), 5, 10));
        voiture.put("roueAvant", new FormePlace(new FormeCercle(3, 3), 6, 14));
        voiture.put("roueArriere", new FormePlace(new FormeCercle(3, 3), 12, 14));
        voiture.put("fenetre", new FormePlace(new FormeCarre(2, 2), 9, 11));
        niveaux.put("Voiture", voiture);

        // Niveau 5 : Soleil
        Map<String, FormePlace> soleil = new LinkedHashMap<>();
        soleil.put("centre", new FormePlace(new FormeCercle(7, 7), 6, 6));
        soleil.put("rayon1", new FormePlace(new FormeTriangle(3, 3), 3, 6));
        soleil.put("rayon2", new FormePlace(new FormeTriangle(3, 3), 12, 3));
        soleil.put("rayon3", new FormePlace(new FormeTriangle(3, 3), 12, 12));
        niveaux.put("Soleil", soleil);

        // Niveau 6 : Fleur
        Map<String, FormePlace> fleur = new LinkedHashMap<>();
        fleur.put("coeur", new FormePlace(new FormeCercle(3, 3), 9, 9));
        fleur.put("petale1", new FormePlace(new FormeCercle(3, 3), 6, 9));
        fleur.put("petale2", new FormePlace(new FormeCercle(3, 3), 12, 9));
        fleur.put("petale3", new FormePlace(new FormeCercle(3, 3), 9, 6));
        fleur.put("petale4", new FormePlace(new FormeCercle(3, 3), 9, 12));
        niveaux.put("Fleur", fleur);

        // Niveau 7 : Bateau
        Map<String, FormePlace> bateau = new LinkedHashMap<>();
        bateau.put("corps", new FormePlace(new FormeCarre(3, 10), 5, 14));
        bateau.put("voile", new FormePlace(new FormeTriangle(5, 5), 8, 9));
        bateau.put("cabine", new FormePlace(new FormeCarre(2, 2), 7, 13));
        niveaux.put("Bateau", bateau);

        // Niveau 8 : Poisson
        Map<String, FormePlace> poisson = new LinkedHashMap<>();
        poisson.put("corps", new FormePlace(new FormeCercle(5, 5), 8, 8));
        poisson.put("nageoire", new FormePlace(new FormeTriangle(3, 3), 6, 10));
        poisson.put("queue", new FormePlace(new FormeTriangle(3, 3), 13, 9));
        poisson.put("oeil", new FormePlace(new FormeCercle(1, 1), 9, 9));
        niveaux.put("Poisson", poisson);

        // Niveau 9 : Bonhomme de Neige
        Map<String, FormePlace> bonhomme = new LinkedHashMap<>();
        bonhomme.put("corpsBas", new FormePlace(new FormeCercle(9, 9), 6, 12));
        bonhomme.put("corpsMilieu", new FormePlace(new FormeCercle(7, 7), 7, 8));
        bonhomme.put("tete", new FormePlace(new FormeCercle(5, 5), 8, 4));
        bonhomme.put("oeil1", new FormePlace(new FormeCercle(1, 1), 9, 5));
        bonhomme.put("oeil2", new FormePlace(new FormeCercle(1, 1), 11, 5));
        bonhomme.put("nez", new FormePlace(new FormeCercle(1, 1), 10, 6));
        bonhomme.put("chapeau", new FormePlace(new FormeTriangle(3, 5), 8, 2));
        niveaux.put("Bonhomme de Neige", bonhomme);

        // Niveau 10 : Avion
        Map<String, FormePlace> avion = new LinkedHashMap<>();
        avion.put("corps", new FormePlace(new FormeCarre(3, 10), 5, 8));
        avion.put("aileGauche", new FormePlace(new FormeTriangle(3, 4), 2, 9));
        avion.put("aileDroite", new FormePlace(new FormeTriangle(3, 4), 13, 9));
        avion.put("hublot", new FormePlace(new FormeCercle(1, 1), 8, 9));
        avion.put("queue", new FormePlace(new FormeTriangle(2, 3), 15, 7));
        niveaux.put("Avion", avion);

        return niveaux;
    }

    public static void sauvegarder(String fichier) throws IOException {
        Map<String, Map<String, FormePlace>> niveaux = creerTousLesNiveaux();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(niveaux);
        }
    }

    public static void main(String[] args) {
        try {
            sauvegarder("dist/niveaux.ser");
            System.out.println("Niveaux enregistrés avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
