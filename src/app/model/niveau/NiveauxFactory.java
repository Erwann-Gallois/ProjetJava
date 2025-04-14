package app.model.niveau;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import app.model.formes.FormeCarre;
import app.model.formes.FormeCercle;
import app.model.formes.FormeTriangle;

/**
 * Classe NiveauxFactory
 * Cette classe est responsable de la création et de la gestion des niveaux de jeu.
 * Elle permet de créer des niveaux prédéfinis et de les sauvegarder/charger à partir d'un fichier.
 */
public class NiveauxFactory {

    /**
     * Crée tous les niveaux disponibles dans l'application.
     *
     * @return Une map contenant tous les niveaux avec leur nom comme clé.
     */
    public static Map<String, Niveau> creerTousLesNiveaux() {
        Map<String, Niveau> niveaux = new LinkedHashMap<>();

        // Niveau 1 : Visage Souriant
        Niveau visage = new Niveau("Visage Souriant");
        visage.ajouter(new FormeCercle("tete", 5, 0, 11, 11));
        visage.ajouter(new FormeCercle("oeilGauche", 7, 2, 3, 3));
        visage.ajouter(new FormeCercle("oeilDroit", 11, 2, 3, 3));
        visage.ajouter(new FormeCercle("nez", 9, 5, 3, 3));
        visage.ajouter(new FormeCercle("bouche", 7, 8, 7, 3));
        niveaux.put(visage.getNom(), visage);

        // Niveau 2 : Maison
        Niveau maison = new Niveau("Maison");
        maison.ajouter(new FormeCarre("corps", 5, 10, 10, 10));
        maison.ajouter(new FormeTriangle("toit", 5, 5, 10, 5));
        maison.ajouter(new FormeCarre("porte", 9, 16, 2, 4));
        maison.ajouter(new FormeCarre("fenetreGauche", 6, 12, 2, 2));
        maison.ajouter(new FormeCarre("fenetreDroite", 12, 12, 2, 2));
        niveaux.put(maison.getNom(), maison);

        // Niveau 3 : Arbre
        Niveau arbre = new Niveau("Arbre");
        arbre.ajouter(new FormeCercle("couronne", 6, 2, 7, 7));
        arbre.ajouter(new FormeCarre("tronc", 9, 9, 3, 6));
        niveaux.put(arbre.getNom(), arbre);

        // Niveau 4 : Voiture
        Niveau voiture = new Niveau("Voiture");
        voiture.ajouter(new FormeCarre("corps", 5, 10, 10, 4));
        voiture.ajouter(new FormeCercle("roueAvant", 6, 14, 3, 3));
        voiture.ajouter(new FormeCercle("roueArriere", 12, 14, 3, 3));
        voiture.ajouter(new FormeCarre("fenetre", 9, 11, 2, 2));
        niveaux.put(voiture.getNom(), voiture);

        // Niveau 5 : Soleil
        Niveau soleil = new Niveau("Soleil");
        soleil.ajouter(new FormeCercle("centre", 6, 6, 7, 7));
        soleil.ajouter(new FormeTriangle("rayon1", 3, 6, 3, 3));
        soleil.ajouter(new FormeTriangle("rayon2", 12, 3, 3, 3));
        soleil.ajouter(new FormeTriangle("rayon3", 12, 12, 3, 3));
        niveaux.put(soleil.getNom(), soleil);

        // Niveau 6 : Fleur
        Niveau fleur = new Niveau("Fleur");
        fleur.ajouter(new FormeCercle("coeur", 9, 9, 3, 3));
        fleur.ajouter(new FormeCercle("petale1", 6, 9, 3, 3));
        fleur.ajouter(new FormeCercle("petale2", 12, 9, 3, 3));
        fleur.ajouter(new FormeCercle("petale3", 9, 6, 3, 3));
        fleur.ajouter(new FormeCercle("petale4", 9, 12, 3, 3));
        niveaux.put(fleur.getNom(), fleur);

        // Niveau 7 : Bateau
        Niveau bateau = new Niveau("Bateau");
        bateau.ajouter(new FormeCarre("corps", 5, 14, 10, 3));
        bateau.ajouter(new FormeTriangle("voile", 8, 9, 5, 5));
        bateau.ajouter(new FormeCarre("cabine", 7, 13, 2, 2));
        niveaux.put(bateau.getNom(), bateau);

        // Niveau 8 : Poisson
        Niveau poisson = new Niveau("Poisson");
        poisson.ajouter(new FormeCercle("corps", 8, 8, 5, 5));
        poisson.ajouter(new FormeTriangle("nageoire", 6, 10, 3, 3));
        poisson.ajouter(new FormeTriangle("queue", 13, 9, 3, 3));
        poisson.ajouter(new FormeCercle("oeil", 9, 9, 1, 1));
        niveaux.put(poisson.getNom(), poisson);

        // Niveau 9 : Bonhomme de Neige
        Niveau bonhomme = new Niveau("Bonhomme de Neige");
        bonhomme.ajouter(new FormeCercle("corpsBas", 6, 12, 9, 9));
        bonhomme.ajouter(new FormeCercle("corpsMilieu", 7, 8, 7, 7));
        bonhomme.ajouter(new FormeCercle("tete", 8, 4, 5, 5));
        bonhomme.ajouter(new FormeCercle("oeil1", 9, 5, 1, 1));
        bonhomme.ajouter(new FormeCercle("oeil2", 11, 5, 1, 1));
        bonhomme.ajouter(new FormeCercle("nez", 10, 6, 1, 1));
        bonhomme.ajouter(new FormeTriangle("chapeau", 8, 2, 3, 5));
        niveaux.put(bonhomme.getNom(), bonhomme);

        // Niveau 10 : Avion
        Niveau avion = new Niveau("Avion");
        avion.ajouter(new FormeCarre("corps", 5, 8, 10, 3));
        avion.ajouter(new FormeTriangle("aileGauche", 2, 9, 3, 4));
        avion.ajouter(new FormeTriangle("aileDroite", 13, 9, 3, 4));
        avion.ajouter(new FormeCercle("hublot", 8, 9, 1, 1));
        avion.ajouter(new FormeTriangle("queue", 15, 7, 2, 3));
        niveaux.put(avion.getNom(), avion);

        return niveaux;
    }

    /**
     * Sauvegarde tous les niveaux dans un fichier.
     * @param fichier Le nom du fichier où sauvegarder les niveaux.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    public static void sauvegarder(String fichier) throws IOException {
        Map<String, Niveau> niveaux = creerTousLesNiveaux();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(niveaux);
        }
    }

    /**
     * Charge les niveaux à partir d'un fichier.
     * @param fichier Le nom du fichier à charger.
     * @return La map contenant les niveaux chargés.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     * @throws ClassNotFoundException Si la classe Niveau n'est pas trouvée.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Niveau> charger(String fichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
            return (Map<String, Niveau>) ois.readObject();
        }
    }

    /**
     * Méthode principale pour tester la sauvegarde des niveaux.
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        try {
            sauvegarder("dist/niveaux.ser");
            System.out.println("Niveaux enregistrés avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
