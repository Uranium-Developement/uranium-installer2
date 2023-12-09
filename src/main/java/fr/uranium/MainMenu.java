package fr.uranium;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MainMenu {
    private JPanel mainPanel;
    private JButton button1;
    private JLabel uraniumLogoLabel;
    private JCheckBox versionLite;
    BufferedImage uraniumLogo = ImageIO.read(new URL("https://cdn.discordapp.com/attachments/1150326378743463987/1183062569678807191/imageLauncher.png"));
    ImageIcon uraniumLogoIcon = new ImageIcon(uraniumLogo);


    public MainMenu() throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Initialisez votre mainPanel ici
        mainPanel = new JPanel();
        button1 = new JButton("Installer Uranium sur le launcher minecraft");
        JLabel uraniumLogoLabel = new JLabel(uraniumLogoIcon);
        versionLite = new JCheckBox("installer la version lite");
        // Ajoutez d'autres composants à mainPanel si nécessaire
        JFrame.setDefaultLookAndFeelDecorated(true);





        mainPanel.setPreferredSize(new Dimension(730,600));
        mainPanel.setLayout (null);
        UIManager.put("", new Color(51, 51, 72));

        mainPanel.add(uraniumLogoLabel);
        mainPanel.add(button1);
        mainPanel.add(versionLite);
        mainPanel.setBackground(new Color(66,69,73));
        button1.setBackground(new Color(35,39,42));
        versionLite.setBackground(new Color(35,39,42));
        versionLite.setForeground(new Color(255, 255, 255));
        button1.setForeground(new Color(255, 255, 255));
        System.out.println(javax.swing.UIManager.getDefaults().getFont("Label.font"));
        uraniumLogoLabel.setBounds (0, 0, 740, 416);
        versionLite.setBounds      (280, 460, 150, 30);
        button1.setBounds          (210, 520, 290, 30);
        versionLite.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Vérifiez si la case à cocher est cochée
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Exécutez l'action liée à la version Lite
                    versionLite.setBackground(new Color(57, 175, 88));

                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    versionLite.setBackground(new Color(35,39,42));

                }
            }
        });
    }


    public static void main() {
        try {
            JFrame mainMenu = new JFrame("MainMenu");
            mainMenu.setContentPane(new MainMenu().mainPanel);
            mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainMenu.pack();
            mainMenu.setLocationRelativeTo(null);
            mainMenu.setVisible(true);
            mainMenu.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}