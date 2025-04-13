package app.model.niveau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.model.formes.AbstractForme;

/**
 * Classe Niveau
 * Représente un niveau de jeu contenant une liste de formes.
 * Elle implémente l'interface Serializable pour permettre la sérialisation.
 */
public class Niveau implements Serializable {

    private String nom;
    private List<AbstractForme> formes;

    /**
     * Constructeur de la classe Niveau
     *
     * @param nom Le nom du niveau
     */
    public Niveau(String nom) {
        this.nom = nom;
        this.formes = new ArrayList<>();
    }

    /**
     * Ajoute une forme au niveau
     *
     * @param forme La forme à ajouter
     */
    public void ajouter(AbstractForme forme) {
        formes.add(forme);
    }

    /**
     * Accesseur pour le nom du niveau
     * @return Le nom du niveau
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur pour la liste des formes
     * @return La liste des formes
     */
    public List<AbstractForme> getFormes() {
        return formes;
    }
}
