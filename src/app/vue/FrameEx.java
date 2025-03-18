package app.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameEx extends JFrame{


    /**
     * Constructeur de la page présentant les regles.
     */
    public FrameEx(){
        JPanel panel = new JPanel();
        panel.setBounds(50,50,500,750);
        panel.setBackground(Color.gray);
        panel.setSize(300,300);  

        // bouton de retour
        JButton btnQuit = new JButton("Retour");
        
        btnQuit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
          JFrame fen = new FrameMenu();
          fen.setTitle("Menu Page");
          fen.setSize(800, 800);
          fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          fen.setVisible(true);
          setVisible(false);
          dispose();
        }});
        //zone de texte non-modifiable pour les explications
        JTextArea area=new JTextArea("Le but de ce devoir est de réaliser une application de jeu, dotée d'une interface graphique,qui consiste à assembler des formes de sorte qu'elles occupent le moins de place possible.\r\n" + "\n" +
            "L'idée s'inspire du fameux Tetris : \r\n" + 
                "- avec des pièces de différentes formes(T,L,Rectangle,U) \r\n" +
                "- avec une IA en adversaire \r\n"+ "\n" +
                "On peut utiliser, pour bouger les pièces, le clavier ou des bouttons sur le jeu. ");  
        area.setBounds(125,150, 750,150);
        area.setEditable(false);
        area.setLineWrap(true);

        panel.add(area);
        panel.add(btnQuit);
    
        add(panel);
        setVisible(true);
        
    }
}
