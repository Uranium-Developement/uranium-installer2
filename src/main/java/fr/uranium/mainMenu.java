package fr.uranium;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
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
        JLabel uraniumLogoLabel = new JLabel(new ImageIcon(ImageIO.read(mainMenu.class.getResourceAsStream("/imageLauncher.png"))));
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
    public void buttonPressed(boolean createProfile){
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Tâche de téléchargement
                getInstance().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                loadingLabel.setText("DOWNLOADING ...");
                installButton.setEnabled(false);
                updateButton.setEnabled(false);
                if (createProfile){addToLauncherProfile.main();}
                addToLauncherProfile.main();
                downloadZip.start();
                return null;
            }

            @Override
            protected void done() {
                JFrame thisFrame = (JFrame) getWindowAncestor(installButton);
                if (thisFrame != null) {
                    ProcessBuilder minecraftProcessBuilder = null;
                    minecraftProcessBuilder = new ProcessBuilder("C:\\XboxGames\\Minecraft Launcher\\Content\\Minecraft.exe");


                    if (Files.exists(FileSystems.getDefault().getPath("C:\\Program Files (x86)\\Minecraft Launcher\\MinecraftLauncher.exe"))){
                        minecraftProcessBuilder = new ProcessBuilder("C:\\Program Files (x86)\\Minecraft Launcher\\MinecraftLauncher.exe");

                    };

                    try {
                        assert minecraftProcessBuilder != null;
                        Process minecraftProcess = minecraftProcessBuilder.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    thisFrame.dispose();


                }
            }
        };
        worker.execute();
    };

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
            mainMenu.setIconImage(new ImageIcon(ImageIO.read(mainMenu.class.getResourceAsStream("/128uranium.png"))).getImage());
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
