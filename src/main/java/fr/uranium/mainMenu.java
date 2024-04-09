package fr.uranium;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static javax.swing.SwingUtilities.getWindowAncestor;

public class mainMenu {

    private static JFrame instance;


    private final JPanel mainPanel;
    private final JButton installButton;
    private final JButton updateButton;
    private final JLabel loadingLabel;

    private mainMenu() throws IOException {
        // Initialisation du mainPanel ici
        //creation d'un buildeur de processus pour lancer minecraft avec les chemins les plus communs
        //creation des elements
        mainPanel = new JPanel();
        installButton = new JButton("Installer TERRAFIRMACRAFT sur le launcher minecraft");
        JLabel uraniumLogoLabel = new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(mainMenu.class.getResourceAsStream("/imageLauncher.jpg")))));
        updateButton = new JButton("mettre a jour TERRAFIRMACRAFT");
        loadingLabel = new JLabel("");
        //taille fenetre
        mainPanel.setPreferredSize(new Dimension(730, 600));
        mainPanel.setLayout(null);
        //ajout des elements au panel
        mainPanel.add(uraniumLogoLabel);
        mainPanel.add(loadingLabel);
        mainPanel.add(installButton);
        mainPanel.add(updateButton);
        //tailles et couleurs
        mainPanel.setBackground(new Color(66, 69, 73));
        installButton.setBackground(new Color(35, 39, 42));
        updateButton.setBackground(new Color(35, 39, 42));
        updateButton.setForeground(new Color(255, 255, 255));
        installButton.setForeground(new Color(255, 255, 255));
        loadingLabel.setForeground(new Color(255, 255, 255));
        uraniumLogoLabel.setBounds(0, 0, 740, 416);
        updateButton.setBounds(270, 400, 160, 30);
        installButton.setBounds(210, 460, 290, 30);
        loadingLabel.setBounds(240, 520, 290, 30);
        loadingLabel.setFont(new Font(loadingLabel.getFont().getName(), Font.PLAIN, 20));

        //update, comme install mais sans creation de profile
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButton.setBackground(new Color(57, 175, 88));
                buttonPressed(false);

            }
        });

        //installation
        installButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                installButton.setBackground(new Color(57, 175, 88));
                buttonPressed(true);
            }
        });
    }

    public void buttonPressed(boolean createProfile) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Tâche de téléchargement
                getInstance().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                loadingLabel.setText("DOWNLOADING ...");
                installButton.setEnabled(false);
                updateButton.setEnabled(false);
                if (createProfile) {
                    addToLauncherProfile.start();
                }
                downloadZip.start();
                return null;
            }

            @Override
            protected void done() {
                JFrame thisFrame = (JFrame) getWindowAncestor(installButton);
                if (thisFrame == null) return;


                ProcessBuilder minecraftProcessBuilder = new ProcessBuilder("C:\\XboxGames\\Minecraft Launcher\\Content\\Minecraft.exe");


                try {
                    minecraftProcessBuilder.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                thisFrame.dispose();
            }
        };
        worker.execute();
    }

    /**
     * @return l'instance de la fenêtre
     */
    public static JFrame getInstance() {
        if (instance == null) {
            instance = new JFrame("Uranium Installer");
        }
        return instance;
    }

    /**
     * Instancie et affiche la fenêtre
     */
    public static void start() {
        try {
            JFrame mainMenu = getInstance();
            mainMenu.setContentPane(new mainMenu().mainPanel);
            mainMenu.setIconImage(new ImageIcon(ImageIO.read(Objects.requireNonNull(mainMenu.class.getResourceAsStream("/128uranium.png")))).getImage());
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
