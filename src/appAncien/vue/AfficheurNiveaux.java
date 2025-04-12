package appAncien.vue;

import javax.swing.*;

import appAncien.model.formes.AbstractForme;
import appAncien.model.formes.FormePlace;

import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class AfficheurNiveaux extends JPanel {

    private Map<String, FormePlace> formes;

    public AfficheurNiveaux(Map<String, FormePlace> formes) {
        this.formes = formes;
        setPreferredSize(new Dimension(400, 400)); // taille de la zone d'affichage
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Map.Entry<String, FormePlace> entry : formes.entrySet()) {
            FormePlace fp = entry.getValue();
            AbstractForme f = fp.getForme();
            Boolean[][] config = AbstractForme.ensConfig.get(f.getId());
            if (config == null) continue;

            int x = fp.getX();
            int y = fp.getY();
            int scale = 10; // taille d’un bloc

            for (int i = 0; i < config.length; i++) {
                for (int j = 0; j < config[i].length; j++) {
                    if (config[i][j] != null && config[i][j]) {
                        g.fillRect((x + j) * scale, (y + i) * scale, scale, scale);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dist/niveaux.ser"))) {
            Map<String, Map<String, FormePlace>> niveaux = (Map<String, Map<String, FormePlace>>) ois.readObject();

            for (Map.Entry<String, Map<String, FormePlace>> niveau : niveaux.entrySet()) {
                JFrame frame = new JFrame(niveau.getKey());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new AfficheurNiveaux(niveau.getValue()));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                Thread.sleep(2000); // pause entre les affichages si besoin
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
