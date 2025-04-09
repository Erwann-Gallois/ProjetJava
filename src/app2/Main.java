package app2;

import app2.view.GameView;
import app2.view.MenuView;

public class Main {
    public static void main(String[] args) {
        GameView gameView = new GameView(true);
        gameView.restartGame();
        MenuView menuView = new MenuView(gameView);
        menuView.setVisible(true);
    }
}
