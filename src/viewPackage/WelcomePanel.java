package viewPackage;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel(){
        initComponents();
    }

    private void initComponents(){
        JLabel welcomeMessage = new JLabel("<html>" +
                "Bienvenue dans ce programme de gestion pour université.<br>" +
                "Ce programme propose différentes fonctions telles que :<br>" +
                "<ul>" +
                "<li>La gestion des utilisateurs</li>" +
                "<li>Des recherches utiles sur différents critères</li>" +
                "<li>Un onglet reprenant quelques statistiques sur l'université</li>" +
                "</ul>" +
                "</html>");
        setLayout(new BorderLayout());
        welcomeMessage.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessage, BorderLayout.CENTER);
        setPreferredSize(new Dimension(680, 300));
    }
}
