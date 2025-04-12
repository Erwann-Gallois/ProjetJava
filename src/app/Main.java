package app;

import app.view.GameView;
import app.view.MenuView;

public class Main {
    public static void main(String[] args) {
        GameView gameView = new GameView(true);
        gameView.restartGame();
        MenuView menuView = new MenuView(gameView);
        menuView.setVisible(true);
    }
}
