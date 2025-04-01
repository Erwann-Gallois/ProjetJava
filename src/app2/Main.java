package app2;

import app2.view.GameView;
import app2.model.dessin.ShapeDrawer;

public class Main {
    public static void main(String[] args) {
        GameView gameView = new GameView();
        ShapeDrawer shapeDrawer = new ShapeDrawer(gameView);

        gameView.setVisible(true);
    }
}
