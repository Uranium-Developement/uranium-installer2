package fr.uranium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class MainMenu {
    private JPanel mainPanel;
    private JButton installButton;
    private JLabel uraniumLogoLabel;
    private JButton updateButton;
    private JLabel loadingLabel;

    public MainMenu() throws IOException {
        // Initialisation du mainPanel ici
        mainPanel = new JPanel();
        installButton = new JButton("Installer Uranium sur le launcher minecraft");
        JLabel uraniumLogoLabel = new JLabel(new ImageIcon(new URL("https://cdn.discordapp.com/attachments/1150326378743463987/1183062569678807191/imageLauncher.png")));
        updateButton = new JButton("mettre a jour Uranium");
        loadingLabel = new JLabel("");
        mainPanel.setPreferredSize(new Dimension(730, 600));
        mainPanel.setLayout(null);

        mainPanel.add(uraniumLogoLabel);
        mainPanel.add(loadingLabel);
        mainPanel.add(installButton);
        mainPanel.add(updateButton);
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
                        downloadZip.main();
                        return null;
                    }

                    @Override
                    protected void done() {
                        JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(updateButton);
                        if (thisFrame != null) {
                            thisFrame.dispose();
                        }
                    }
                };
                worker.execute();
            }
        });

        installButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                installButton.setBackground(new Color(57, 175, 88));
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Tâche de téléchargement
                        loadingLabel.setText("DOWNLOADING ...");
                        installButton.setEnabled(false);
                        updateButton.setEnabled(false);
                        addToLauncherProfile.main();
                        downloadZip.main();
                        return null;
                    }

                    @Override
                    protected void done() {
                        JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(installButton);
                        if (thisFrame != null) {
                            thisFrame.dispose();
                        }
                    }
                };
                worker.execute();
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
