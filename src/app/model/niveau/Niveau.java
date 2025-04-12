package app.model.niveau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.model.formes.AbstractForme;

public class Niveau implements Serializable {

    private String nom;
    private List<AbstractForme> formes;

    public Niveau(String nom) {
        this.nom = nom;
        this.formes = new ArrayList<>();
    }

    public void ajouter(AbstractForme forme) {
        formes.add(forme);
    }

    public String getNom() {
        return nom;
    }

    public List<AbstractForme> getFormes() {
        return formes;
    }
}
