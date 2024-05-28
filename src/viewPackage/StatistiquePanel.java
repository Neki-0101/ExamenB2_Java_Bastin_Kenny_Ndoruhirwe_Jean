package viewPackage;

import controllerPackage.StatistiqueController;
import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Section;
import modelPackage.SemiCircleBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StatistiquePanel extends JPanel {
    private SemiCircleBar genderGraph;
    private StatistiqueController controller;
    private JTable cityTable;
    private JTable sectionTable;
    private JComboBox<Section> sectionComboBox;
    private JButton exportButton;

    public StatistiquePanel() {
        initController();
        initComponents();
    }

    private void initController() {
        controller = new StatistiqueController();
    }

    private void initComponents() {
        JScrollPane cityScrollPane = new JScrollPane();
        cityTable = new JTable();
        JSeparator separator1 = new JSeparator();
        JSeparator separator2 = new JSeparator();
        JScrollPane sectionScrollPane = new JScrollPane();
        sectionTable = new JTable();
        JSeparator separator3 = new JSeparator();
        JLabel cityLabel = new JLabel("% D'ÉTUDIANTS PAR VILLE", SwingConstants.CENTER);
        JLabel sectionLabel = new JLabel("% D'ÉTUDIANTS PAR SECTION", SwingConstants.CENTER);
        JSeparator separator4 = new JSeparator();
        sectionComboBox = new JComboBox<>();
        JSeparator separator5 = new JSeparator();
        JSeparator separator6 = new JSeparator();
        JSeparator separator7 = new JSeparator();
        JSeparator separator8 = new JSeparator();
        JPanel color1 = new JPanel();
        JPanel color2 = new JPanel();
        JLabel genderLabel = new JLabel("% DE GENRE PAR SECTION", SwingConstants.CENTER);
        JLabel maleLabel = new JLabel("-> ♂");
        JLabel femaleLabel = new JLabel("-> ♀");
        genderGraph = new SemiCircleBar(0);
        genderGraph.setForegroundColor(new Color(0xca9dd7));
        genderGraph.setBackgroundColor(new Color(0x7fcbd7));
        exportButton = new JButton("Export");

        setLayout(null);

        cityTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Ville", "%"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        loadCityData();
        cityScrollPane.setViewportView(cityTable);
        add(cityScrollPane);
        cityScrollPane.setBounds(15, 50, 220, 240);

        separator1.setOrientation(SwingConstants.VERTICAL);
        add(separator1);
        separator1.setBounds(240, 20, 10, 270);

        separator2.setOrientation(SwingConstants.VERTICAL);
        add(separator2);
        separator2.setBounds(437, 15, 10, 280);

        sectionTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Section", "%"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        loadSectionData();
        sectionScrollPane.setViewportView(sectionTable);
        add(sectionScrollPane);
        sectionScrollPane.setBounds(445, 50, 220, 240);

        add(separator3);
        separator3.setBounds(15, 40, 220, 10);
        add(cityLabel);
        cityLabel.setBounds(53, 20, 144, 15);
        add(sectionLabel);
        sectionLabel.setBounds(475, 20, 154, 16);
        add(separator4);
        separator4.setBounds(445, 40, 220, 10);
        add(separator7);
        separator7.setBounds(255, 225, 175, 10);
        add(separator8);
        separator8.setBounds(255, 185, 175, 10);
        add(sectionComboBox);
        sectionComboBox.setBounds(255, 235, 170, 25);
        sectionComboBox.addItemListener(new SectionComboBoxItemListener());
        loadComboBoxData();
        add(genderGraph);
        genderGraph.setBounds(255, 90, 170, 170);
        color1.setBounds(260, 195, 10, 10);
        color1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        color1.setBackground(genderGraph.getBackground());
        add(color1);
        color2.setBounds(260, 211, 10, 10);
        color2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        color2.setBackground(genderGraph.getForeground());
        add(color2);
        maleLabel.setBounds(275, 192, 60, 15);
        add(maleLabel);
        femaleLabel.setBounds(275, 207, 60, 15);
        add(femaleLabel);
        genderLabel.setBounds(265, 60, 144, 15);
        add(genderLabel);
        add(separator5);
        separator5.setBounds(245, 75, 180, 10);
        add(separator6);
        separator6.setBounds(245, 55, 180, 10);

        exportButton.setBounds(255, 270, 170, 30);
        add(exportButton);

        exportButton.addActionListener(new SaveButtonListener());
        setPreferredSize(new Dimension(680, 300));
    }

    private void loadCityData() {
        DefaultTableModel cityTableModel = (DefaultTableModel) cityTable.getModel();
        cityTableModel.setRowCount(0);
        try {
            for (City city : controller.getAllCities()) {
                cityTableModel.addRow(new Object[]{city.getName(), controller.getCityStudentPercentage(city)});
            }
        } catch (AllCityException | CityCountException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Count error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSectionData() {
        DefaultTableModel sectionTableModel = (DefaultTableModel) sectionTable.getModel();
        sectionTableModel.setRowCount(0);
        try {
            for (Section section : controller.getAllSection()) {
                sectionTableModel.addRow(new Object[]{section.getLabel(), controller.getSectionStudentPercentage(section)});
            }
        } catch (SectionCountException | AllSectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Count error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadComboBoxData() {
        sectionComboBox.removeAllItems();
        try {
            for (Section section : controller.getAllSection()) {
                sectionComboBox.addItem(section);
            }
        } catch (AllSectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Count error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class SectionComboBoxItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    genderGraph.setPercentage(controller.getSectionGenderCount((Section) e.getItem())[0]);
                } catch (SectionCountException | SectionGenderException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Count error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class SaveButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder data = new StringBuilder();

            data.append("% D'ÉTUDIANTS PAR VILLE\n");
            data.append("--------------------------------------\n");
            DefaultTableModel cityTableModel = (DefaultTableModel) cityTable.getModel();
            for (int i = 0; i < cityTableModel.getRowCount(); i++) {
                data.append(cityTableModel.getValueAt(i, 0)).append(" - ").append(cityTableModel.getValueAt(i, 1)).append("\n");
            }

            data.append("\n% D'ÉTUDIANTS PAR SECTION\n");
            data.append("--------------------------------------\n");
            DefaultTableModel sectionTableModel = (DefaultTableModel) sectionTable.getModel();
            for (int i = 0; i < sectionTableModel.getRowCount(); i++) {
                data.append(sectionTableModel.getValueAt(i, 0)).append(" - ").append(sectionTableModel.getValueAt(i, 1)).append("\n");
            }

            data.append("\n% DE GENRE PAR SECTION\n");
            data.append("--------------------------------------\n");
            data.append("♂                               ♀\n");
            try{
                for (Section section : controller.getAllSection()){
                    Integer malePercentage = controller.getSectionGenderCount(section)[1];
                    Integer femalePercentage = controller.getSectionGenderCount(section)[0];
                    data.append(malePercentage).append(" ".repeat(32-malePercentage.toString().length())).append(femalePercentage).append("\n");
                }
            } catch(AllSectionException | SectionCountException | SectionGenderException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Save error", JOptionPane.ERROR_MESSAGE);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Out.txt"))) {
                writer.write(data.toString());
                JOptionPane.showMessageDialog(null, "Statistics exported successfully!", "Export", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error while exporting statistics: " + ex.getMessage(), "Export error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

