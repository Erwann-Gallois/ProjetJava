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

public class NiveauxFactory {

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

        return niveaux;
    }

    public static void sauvegarder(String fichier) throws IOException {
        Map<String, Niveau> niveaux = creerTousLesNiveaux();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(niveaux);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Niveau> charger(String fichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
            return (Map<String, Niveau>) ois.readObject();
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
