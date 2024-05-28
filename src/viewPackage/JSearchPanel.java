package viewPackage;

import controllerPackage.SearchController;
import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Section;
import modelPackage.TeachingUnit;
import modelPackage.ToggleCustom;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class JSearchPanel extends JTabbedPane {
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JComboBox<TeachingUnit> comboBox1;
    private JTable table1;
    private JTable table2;
    private JComboBox<Section> comboBox2;
    private ToggleCustom togglesearchButton1;
    private JTable table3;
    private JComboBox<City> comboBox3;
    private SearchController controller;

    public JSearchPanel() {
        initController();
        initComponents();
    }
    private void initController() {
        controller = new SearchController();
    }
    private void initComponents() {
        JPanel panel1 = new JPanel();
        spinner1 = new JSpinner();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        spinner2 = new JSpinner();
        JLabel label3 = new JLabel();
        JSeparator separator1 = new JSeparator();
        JSeparator separator2 = new JSeparator();
        JLabel label4 = new JLabel();
        JSeparator separator3 = new JSeparator();
        comboBox1 = new JComboBox<>();
        JLabel label5 = new JLabel();
        JSeparator separator4 = new JSeparator();
        JScrollPane scrollPane1 = new JScrollPane();
        table1 = new JTable();
        JSeparator separator6 = new JSeparator();
        JLabel label6 = new JLabel();
        JSeparator separator5 = new JSeparator();
        JButton searchButton1 = new JButton();
        JLabel label8 = new JLabel();
        JPanel panel2 = new JPanel();
        JSeparator separator7 = new JSeparator();
        JLabel label11 = new JLabel();
        JSeparator separator10 = new JSeparator();
        JScrollPane scrollPane2 = new JScrollPane();
        table2 = new JTable();
        JSeparator separator11 = new JSeparator();
        JLabel label13 = new JLabel();
        JSeparator separator12 = new JSeparator();
        JButton searchButton2 = new JButton();
        JLabel label14 = new JLabel();
        JLabel label15 = new JLabel();
        comboBox2 = new JComboBox<>();
        JPanel panel3 = new JPanel();
        togglesearchButton1 = new ToggleCustom();
        JSeparator separator8 = new JSeparator();
        JLabel label12 = new JLabel();
        JSeparator separator13 = new JSeparator();
        JScrollPane scrollPane3 = new JScrollPane();
        table3 = new JTable();
        JSeparator separator14 = new JSeparator();
        JLabel label16 = new JLabel();
        JSeparator separator15 = new JSeparator();
        JSeparator separator16 = new JSeparator();
        JButton searchButton3 = new JButton();
        JLabel label17 = new JLabel();
        JLabel label18 = new JLabel();
        comboBox3 = new JComboBox<>();

        addChangeListener(new TabbedPanelStateChangeListener());

        panel1.setLayout(null);
        spinner1.setModel(new SpinnerDateModel(new Date(), new Date(Long.MIN_VALUE), null, java.util.Calendar.DAY_OF_MONTH));
        spinner1.addChangeListener(new spinnerChangeListener());
        panel1.add(spinner1);
        spinner1.setBounds(55, 35, 210, 22);

        spinner2.setModel(new SpinnerDateModel(new Date(), new Date(Long.MIN_VALUE), null, java.util.Calendar.DAY_OF_MONTH));
        panel1.add(spinner2);
        spinner2.setBounds(55, 60, 210, 22);

        label1.setText("Date 1");
        panel1.add(label1);
        label1.setBounds(20, 40, 35, 16);

        label2.setText("Date 2");
        panel1.add(label2);
        label2.setBounds(20, 65, 35, 16);

        label3.setText("Unité d’enseignement");
        panel1.add(label3);
        label3.setBounds(85, 95, 130, 16);

        panel1.add(separator1);
        separator1.setBounds(20, 30, 270, 10);
        panel1.add(separator2);
        separator2.setBounds(20, 85, 270, 10);

        label4.setText("Dates");
        panel1.add(label4);
        label4.setBounds(130, 15, 45, 16);

        panel1.add(separator3);
        separator3.setBounds(20, 110, 270, 10);

        panel1.add(comboBox1);
        comboBox1.setBounds(140, 120, 105, 22);

        label5.setText("Unité d’enseignement");
        panel1.add(label5);
        label5.setBounds(20, 120, 116, 16);

        separator4.setOrientation(SwingConstants.VERTICAL);
        panel1.add(separator4);
        separator4.setBounds(295, 10, 20, 240);

        table1.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Title", "Date de la session", "Heure de début", "Heure de fin", "Nom de la famille du professeur", "Prénom du professeur"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scrollPane1.setViewportView(table1);
        panel1.add(scrollPane1);
        scrollPane1.setBounds(305, 40, 355, 195);

        panel1.add(separator6);
        separator6.setBounds(305, 30, 360, 10);

        label6.setText("Résultat");
        panel1.add(label6);
        label6.setBounds(455, 15, 70, 16);

        panel1.add(separator5);
        separator5.setBounds(20, 145, 270, 10);

        searchButton1.setText("Recherche");
        searchButton1.addActionListener(new searchButton1ActionListener());
        panel1.add(searchButton1);
        searchButton1.setBounds(130, 152, 100, 28);

        label8.setText("Lancer la recherche:");
        panel1.add(label8);
        label8.setBounds(20, 158, 145, 16);
        addTab("Session de cours entre 2 dates : ", panel1);

        panel2.setLayout(null);
        panel2.add(separator7);
        separator7.setBounds(20, 30, 270, 10);

        label11.setText("Sections");
        panel2.add(label11);
        label11.setBounds(130, 15, 60, 16);

        separator10.setOrientation(SwingConstants.VERTICAL);
        panel2.add(separator10);
        separator10.setBounds(295, 10, 20, 240);

        table2.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Type", "Number", "Letter", "FirstName", "LastName"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scrollPane2.setViewportView(table2);
        panel2.add(scrollPane2);
        scrollPane2.setBounds(305, 40, 355, 195);

        panel2.add(separator11);
        separator11.setBounds(305, 30, 360, 10);

        label13.setText("Résultat");
        panel2.add(label13);
        label13.setBounds(455, 15, 70, 16);

        panel2.add(separator12);
        separator12.setBounds(20, 65, 270, 10);

        searchButton2.setText("Recherche");
        searchButton2.addActionListener(new searchButton2ActionListener());
        panel2.add(searchButton2);
        searchButton2.setBounds(130, 70, 100, 28);

        label14.setText("Lancer la recherche:");
        panel2.add(label14);
        label14.setBounds(20, 75, 145, 16);

        label15.setText("Section: ");
        panel2.add(label15);
        label15.setBounds(20, 37, 60, 16);

        panel2.add(comboBox2);
        comboBox2.setBounds(85, 35, 200, 23);
        addTab("Elèves par section : ", panel2);

        panel3.setLayout(null);
        togglesearchButton1.setBackground(new Color(0x7fcbd7));
        togglesearchButton1.setSelectedBackground(new Color(0x005699));
        togglesearchButton1.setOptions(new String[]{"Etudiant", "Professeur"});
        panel3.add(togglesearchButton1);
        togglesearchButton1.setBounds(130, 70, 100, 30);

        label18.setText("Type recherche: ");
        panel3.add(label18);
        label18.setBounds(20, 78, 100, 16);

        panel3.add(separator15);
        separator8.setBounds(20, 30, 270, 10);

        panel3.add(separator16);
        separator16.setBounds(20, 102, 270, 10);

        label12.setText("Localité");
        panel3.add(label12);
        label12.setBounds(20, 40, 60, 16);

        separator13.setOrientation(SwingConstants.VERTICAL);
        panel3.add(separator13);
        separator13.setBounds(295, 10, 20, 240);

        table3.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Name", "FirstName", "Birthdate"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        scrollPane3.setViewportView(table3);
        panel3.add(scrollPane3);
        scrollPane3.setBounds(305, 40, 355, 195);

        panel3.add(separator14);
        separator14.setBounds(305, 30, 360, 10);

        label16.setText("Résultat");
        panel3.add(label16);
        label16.setBounds(455, 15, 70, 16);

        panel3.add(separator15);
        separator15.setBounds(20, 65, 270, 10);

        searchButton3.setText("Recherche");
        searchButton3.addActionListener(new searchButton3ActionListener());
        panel3.add(searchButton3);
        searchButton3.setBounds(130, 110, 100, 28);

        label17.setText("Lancer la recherche:");
        panel3.add(label17);
        label17.setBounds(20, 115, 145, 16);

        panel3.add(comboBox3);
        comboBox3.setBounds(85, 35, 200, 23);
        addTab("Elèves/professeur par localité : ", panel3);
    }
    private void loadTeachingUnits() {
        comboBox1.removeAllItems();
        try {
            for (TeachingUnit teachingUnit : controller.getAllTU()) {
                comboBox1.addItem(teachingUnit);
            }
        } catch (AllTUException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Listing error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadSections() {
        comboBox2.removeAllItems();
        try {
            for (Section section : controller.getAllSection()) {
                comboBox2.addItem(section);
            }
        } catch (AllSectionException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Listing error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadCities() {
        comboBox3.removeAllItems();
        try {
            for (City city : controller.getAllCities()) {
                comboBox3.addItem(city);
            }
        } catch (AllCityException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Listing error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class TabbedPanelStateChangeListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            switch (getSelectedIndex()) {
                case 0 -> loadTeachingUnits();
                case 1 -> loadSections();
                case 2 -> loadCities();
            }
        }
    }
    private class spinnerChangeListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            spinner2.setModel(new SpinnerDateModel((Date) spinner1.getValue(), (Date) spinner1.getValue(), null, java.util.Calendar.DAY_OF_MONTH));
        }
    }
    private class searchButton1ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<HashMap<String, Object>> searchResults = controller.listSearch1((Date) spinner1.getValue(), (Date) spinner2.getValue(), (TeachingUnit) comboBox1.getSelectedItem());
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                for (HashMap<String, Object> session : searchResults) {
                    model.addRow(new Object[]{
                            session.get("Title"),
                            session.get("SessionDate"),
                            session.get("SessionStartTime"),
                            session.get("SessionEndTime"),
                            session.get("ProfessorLastName"),
                            session.get("ProfessorFirstName")
                    });
                }
            } catch (SearchException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class searchButton2ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<HashMap<String, Object>> searchResults = controller.listSearch2((Section) comboBox2.getSelectedItem());
                DefaultTableModel model = (DefaultTableModel) table2.getModel();
                model.setRowCount(0);
                for (HashMap<String, Object> students : searchResults) {
                    model.addRow(new Object[]{
                            students.get("Type"),
                            students.get("Number"),
                            students.get("Letter"),
                            students.get("FirstName"),
                            students.get("LastName")
                    });
                }
            } catch (SearchException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class searchButton3ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<HashMap<String, Object>> searchResults = controller.listSearch3((City) comboBox3.getSelectedItem(), togglesearchButton1.isSelected());
                DefaultTableModel model = (DefaultTableModel) table3.getModel();
                model.setRowCount(0);
                for (HashMap<String, Object> users : searchResults) {
                    model.addRow(new Object[]{
                            users.get("LastName"),
                            users.get("FirstName"),
                            users.get("BirthDate")
                    });
                }
            } catch (SearchException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
