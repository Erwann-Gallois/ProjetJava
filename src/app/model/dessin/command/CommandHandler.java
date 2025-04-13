package app.model.dessin.command;

import java.util.*;

/**
 * Classe CommandHandler
 * Gère l'exécution et l'annulation des commandes.
 */
public class CommandHandler {

    ArrayList<OperationCommand> commands = new ArrayList<>();
    ArrayList<OperationCommand> undoCommands = new ArrayList<>();

    /**
     * Constructeur de la classe CommandHandler.
     * Initialise les listes de commandes et de commandes annulées.
     */
    public CommandHandler()
    {

    }

    /**
     * Permet d'annuler la dernière commande exécutée.
     * Si aucune commande n'est disponible, rien ne se passe.
     * La commande est ajoutée à la liste des commandes annulées.
     */
    public void undo()
    {
        int size = commands.size();
        if (size > 0)
        {
            undoCommands.add(commands.get(size - 1));
            commands.get(size - 1).compensate();
            commands.remove(size - 1);
        }
    }

    /**
     * Permet de rétablir la dernière commande annulée.
     * Si aucune commande n'est disponible, rien ne se passe.
     * La commande est retirée de la liste des commandes annulées.
     */
    public void redo()
    {
        int size = undoCommands.size();
        if (size > 0)
        {
            undoCommands.get(size - 1).operate();
            undoCommands.remove(size - 1);
        }
    }

    /**
     * Permet d'exécuter une commande.
     * La commande est ajoutée à la liste des commandes exécutées.
     * @param o La commande à exécuter.
     */
    public void handle(OperationCommand o)
    {
        commands.add(o);
        o.operate();
    }
}