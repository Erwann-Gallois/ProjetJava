package util;

public interface ModeleEcoutable {


    /**
     * Permet d'ajouter un écouteur à un objet écoutable.
     * @param e Objet qui veut écouter l'objet écoutable 
     */
    public void ajoutEcouteur(EcouteurModele e);


    /**
     * Permet de retirer un écouteur d'un objet écoutable.
     * @param e Objet qui ne veut plus écouter l'objet écoutable 
     */
    public void retraitEcouteur(EcouteurModele e);
}
