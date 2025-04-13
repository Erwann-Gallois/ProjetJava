package app.model.dessin.command;

/**
 * Interface représentant une commande d'opération.
 * Cette interface définit les méthodes pour exécuter et annuler une commande.
 */
public interface OperationCommand {
    /**
     * Exécute la commande.
     */
    public void operate();

    /**
     * Annule la commande.
     */
    public void compensate();
}