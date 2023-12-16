package fr.uranium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import static javax.swing.SwingUtilities.getWindowAncestor;

public class mainMenu {

    private static JFrame instance;


    private JPanel mainPanel;
    private JButton installButton;
    private JButton updateButton;
    private JLabel loadingLabel;

    private mainMenu() throws IOException {
        // Initialisation du mainPanel ici
        //creation d'un buildeur de processus pour lancer minecraft avec les chemins les plus communs
        //creation des elements
        mainPanel = new JPanel();
        installButton = new JButton("Installer Uranium sur le launcher minecraft");
        JLabel uraniumLogoLabel = new JLabel(new ImageIcon(new URL("https://cdn.discordapp.com/attachments/1150326378743463987/1183062569678807191/imageLauncher.png")));
        updateButton = new JButton("mettre a jour Uranium");
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
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Tâche de téléchargement
                        loadingLabel.setText("DOWNLOADING ...");
                        updateButton.setEnabled(false);
                        installButton.setEnabled(false);
                        getInstance().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                        downloadZip.start();
                        return null;
                    }

                    @Override
                    protected void done() {
                        JFrame thisFrame = (JFrame) getWindowAncestor(updateButton);
                        if (thisFrame != null) {
                            thisFrame.dispose();
                        }
                    }
                };
                worker.execute();
            }
        });

        //installation
        installButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                installButton.setBackground(new Color(57, 175, 88));
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Tâche de téléchargement
                        getInstance().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        loadingLabel.setText("DOWNLOADING ...");
                        installButton.setEnabled(false);
                        updateButton.setEnabled(false);
                        addToLauncherProfile.main();
                        downloadZip.start();
                        return null;
                    }

                    @Override
                    protected void done() {
                        JFrame thisFrame = (JFrame) getWindowAncestor(installButton);
                        if (thisFrame != null) {

                            thisFrame.dispose();

                        }
                    }
                };
                worker.execute();
            }
        });
    }

    public static JFrame getInstance() {
        if (instance == null) {
            instance = new JFrame("Uranium Installer");
        }
        return instance;
    }

    public static void start() {
        try {
            JFrame mainMenu = getInstance();
            mainMenu.setContentPane(new mainMenu().mainPanel);
            mainMenu.setIconImage(
                new ImageIcon(
                    new URL("https://cdn.discordapp.com/attachments/1148746446481391628/1182059252995588136/export202311240318235408_1.png")
                ).getImage()
            );

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