package viewPackage;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JComponent currentPanel;

    public MainFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
    }

    private void setPanel(JComponent panel) {
        if (currentPanel != null) {
            getContentPane().remove(currentPanel);
        }
        currentPanel = panel;
        getContentPane().add(panel);
        panel.setBounds(125, 0, 680, 330);
        revalidate();
        repaint();
    }

    private class showWelcomePanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setPanel(new WelcomePanel());
        };
    }

    private class showStatPanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setPanel(new StatistiquePanel());
        };
    }

    private class showSearchPanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setPanel(new JSearchPanel());
        };
    }

    private class showCRUDPanel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setPanel(new CRUDPanel());
        };
    }

    private void initComponents() {
        JPanel sidePanel = new JPanel();
        JSeparator separator1 = new JSeparator();
        AnimationPanel animationPanel = new AnimationPanel();

        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sidePanel.setBackground(new Color(0x009999));
        sidePanel.setLayout(null);
        sidePanel.add(separator1);
        separator1.setBounds(0, 65, 125, 10);
        animationPanel.setBackground(new Color(0x009999));
        animationPanel.setLayout(null);
        for (int i = 1; i < 6; i++)
            animationPanel.addAnimationFrame("resources/Animation/State" + i + ".png");

        sidePanel.add(animationPanel);
        animationPanel.setBounds(0, 0, 115, 64);

        contentPane.add(sidePanel);
        sidePanel.setBounds(0, 0, 125, 330);

        contentPane.setPreferredSize(new Dimension(805, 330));
        setPanel(new WelcomePanel());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 70, 125, 30);
        menuBar.setBackground(new Color(0x009999));

        JMenu navigateMenu = new JMenu("Menu");
        navigateMenu.setForeground(Color.WHITE);

        JMenuItem menuItemWelcome = new JMenuItem("Acceuil");
        menuItemWelcome.addActionListener(new showWelcomePanel());
        navigateMenu.add(menuItemWelcome);

        JMenuItem menuItemStat = new JMenuItem("Stat");
        menuItemStat.addActionListener(new showStatPanel());
        navigateMenu.add(menuItemStat);

        JMenuItem menuItemSearch = new JMenuItem("Recherche");
        menuItemSearch.addActionListener(new showSearchPanel());
        navigateMenu.add(menuItemSearch);

        JMenuItem menuItemCRUD = new JMenuItem("CRUD");
        menuItemCRUD.addActionListener(new showCRUDPanel());
        navigateMenu.add(menuItemCRUD);

        menuBar.add(navigateMenu);
        sidePanel.add(menuBar);

        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }
}
