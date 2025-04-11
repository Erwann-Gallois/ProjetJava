package app2;

import app2.view.GameView;
import app2.view.MenuView;

/**
 * Point d'entrée principal de l'application.
 * Cette classe initialise la vue du jeu ainsi que le menu principal.
 */
public class Main {

    /**
     * Méthode principale exécutée au démarrage du programme.
     *
     * @param args les arguments de ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {
        GameView gameView = new GameView(true);
        gameView.restartGame();
        MenuView menuView = new MenuView(gameView);
        menuView.setVisible(true);
    }
}
