package util;

import java.util.ArrayList;


public abstract class AbstractModeleEcoutable implements ModeleEcoutable{
    private ArrayList<EcouteurModele> ecouteurs;

    /**
     * Constructeur de la classe abstraite AbstractModeleEcoutable.
     * Sert à créer la liste d'écouteurs.
     */
    public AbstractModeleEcoutable(){
        this.ecouteurs = new ArrayList<EcouteurModele>();
    }

    /**
     * Ajoute un écouteur à la liste d'écouteurs.
     * @param e Ecouteur à ajouter
     */
    @Override
    public void ajoutEcouteur(EcouteurModele e){
      
        this.ecouteurs.add(e);
    }

    /**
     * Retire un écouteur de la liste d'écouteurs.
     * @param e Ecouteur à retirer
     */
    @Override
    public void retraitEcouteur(EcouteurModele e){
        this.ecouteurs.remove(e);
    }

    /**
     * Permet de signaler tous les écouteurs d'un changement.
     * @param c Objet envoyé à l'écouteur (le plus souvent un objet Data)
     */
    protected void fireChangement(Object c){
        for (EcouteurModele e : this.ecouteurs){
            e.modeleMisAJour(this, c);
        }
    }
}
