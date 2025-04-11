package app2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JButton joueurVsIAButton;
    private JButton joueurVsAleatoireButton;
    private JButton joueurVsJoueurButton;
    private GameView gameView;

    public MenuView(GameView gameView) {
        this.gameView = gameView;
        setTitle("Menu de Sélection");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        joueurVsIAButton = new JButton("Par niveaux");
        joueurVsAleatoireButton = new JButton("Joueur contre Aléatoire");
        joueurVsJoueurButton = new JButton("Joueur contre Joueur");

        // Ajouter des ActionListener pour chaque bouton
        joueurVsIAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On ajoutera la logique pour "Joueur contre IA" plus tard
                setVisible(false);
                gameView.setVisible(true);
                gameView.getDrawingPanel().setRandomShapesMode(false);  // Passer en mode IA
            }
        });

        joueurVsAleatoireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lorsque l'option "Joueur contre Aléatoire" est sélectionnée, passer à la vue de dessin
                showDrawingScreen();
            }
        });

        joueurVsJoueurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On ajoutera la logique pour "Joueur contre Joueur" plus tard
                System.out.println("Joueur contre Joueur");
            }
        });

        // Ajouter les boutons au layout
        add(joueurVsIAButton);
        add(joueurVsAleatoireButton);
        add(joueurVsJoueurButton);
    }

    public void showDrawingScreen() {
        // Changer de vue pour l'interface de dessin
        setVisible(false);
        gameView.setVisible(true);
        gameView.getDrawingPanel().setRandomShapesMode(true);  // Passer en mode aléatoire
    }
}
