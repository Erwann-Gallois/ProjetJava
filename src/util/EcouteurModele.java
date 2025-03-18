package util;

public interface EcouteurModele {

    /**
     * Permet d'executer des modifications après un signalement.
     * @param m Objet écoutable qui a signalé une modification
     * @param c data envoyé à l'écouteur
     */
    public void modeleMisAJour(Object m, Object c);
}
