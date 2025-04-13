package app.model.dessin.command;

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