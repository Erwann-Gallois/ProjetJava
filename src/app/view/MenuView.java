package app.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuView extends JFrame {
    private JButton joueurVsIAButton;
    private JButton joueurVsAleatoireButton;
    private JButton joueurVsJoueurButton;
    private JButton quitterButton;
    private GameView gameView;

    /**
     * Constructeur de la classe MenuView.
     * @param gameView La vue principale du jeu associée à ce menu
     */
    public MenuView(GameView gameView) {
        this.gameView = gameView;
        setTitle("Menu de Sélection");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Background bg = new Background("image", "images/menu2.png");
        setContentPane(bg);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.anchor = GridBagConstraints.CENTER;
        // gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(100, 0, 0, 0);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1,0, 30));
        panel.setBackground(new Color(0, 0, 0, 0));

        joueurVsIAButton = new JButton("Par niveau");
        joueurVsIAButton.setBackground(Color.YELLOW);
        joueurVsAleatoireButton = new JButton("Joueur contre Aléatoire");
        joueurVsAleatoireButton.setBackground(Color.BLUE);
        joueurVsJoueurButton = new JButton("Joueur contre Joueur");
        joueurVsJoueurButton.setBackground(Color.GREEN);
        quitterButton = new JButton("Quitter");
        quitterButton.setBackground(Color.red);

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

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pour quitter le jeu
                dispose();
            }
        });

        // Ajouter les boutons au layout
        panel.add(joueurVsIAButton);
        panel.add(joueurVsAleatoireButton);
        panel.add(joueurVsJoueurButton);
        panel.add(quitterButton);
        add(panel, gbc);
    }

    /**
     * Affiche l'écran de dessin.
     */
    public void showDrawingScreen() {
        // Changer de vue pour l'interface de dessin
        setVisible(false);
        gameView.setVisible(true);
        gameView.getDrawingPanel().setRandomShapesMode(true);  // Passer en mode aléatoire
    }
}
