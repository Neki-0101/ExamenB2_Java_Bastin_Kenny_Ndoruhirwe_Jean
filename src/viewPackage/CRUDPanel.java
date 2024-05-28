package viewPackage;

import controllerPackage.UserManagementController;
import exceptionPackage.*;
import modelPackage.City;
import modelPackage.UserAccount;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CRUDPanel extends JPanel {
    private JTable userTable;
    private UserManagementController controller;

    private void initController() {
        controller = new UserManagementController();
    }

    public CRUDPanel() {
        initController();
        initComponents();
    }

    private UserAccount parseUserFromTable(int row) throws ParseException, UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountGenderNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountLoginIDNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException, UserAccountPasswordLenghtException, UserAccountBirthDateTimeException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        Integer userId = Integer.parseInt(userTable.getModel().getValueAt(row, 0).toString());
        String login = (String) userTable.getModel().getValueAt(row, 1);
        Character gender = userTable.getModel().getValueAt(row, 2) == null ? null : ((String) userTable.getValueAt(row, 2)).charAt(0);
        String firstName = (String) userTable.getModel().getValueAt(row, 3);
        String secondFirstName = (String) userTable.getModel().getValueAt(row, 4);
        String lastName = (String) userTable.getModel().getValueAt(row, 5);
        String boxNumber = (String) userTable.getModel().getValueAt(row, 6);
        String password = (String) userTable.getModel().getValueAt(row, 7);
        Date birthDate = userTable.getModel().getValueAt(row, 8) == null ? null : dateFormat.parse((String) userTable.getValueAt(row, 8));
        City city = (City) userTable.getModel().getValueAt(row, 9);
        Boolean isActive = (Boolean) userTable.getModel().getValueAt(row, 10);

        return new UserAccount(userId, login, gender, firstName, secondFirstName, lastName, boxNumber, password, birthDate, city, isActive);
    }

    private void initComponents() {
        JScrollPane tableScrollPane = new JScrollPane();
        userTable = new JTable();

        JButton addButton = new JButton("+");
        JButton deleteButton = new JButton("-");
        JButton saveButton = new JButton("✓");
        JLabel panelTitle = new JLabel("Gestion des utilisateurs");

        setLayout(null);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Login", "Gender", "Firstname", "Second Firstname", "Lastname", "BoxNumber", "Password", "Birthdate", "City", "IsActive"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex != 0;
            }
        };

        panelTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        panelTitle.setBounds(45, 42, 565, 30);
        panelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(panelTitle);

        tableModel.addTableModelListener(new UserTableModelListener());
        userTable.setModel(tableModel);
        userTable.addPropertyChangeListener(new UserTablePropertyChangeListener());
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tableScrollPane.setViewportView(userTable);
        add(tableScrollPane);
        tableScrollPane.setBounds(45, 70, 565, 200);

        saveButton.addActionListener(new SaveButtonListener());
        add(saveButton);
        saveButton.setBounds(615, 70, 24, 24);

        deleteButton.addActionListener(new DeleteButtonListener());
        add(deleteButton);
        deleteButton.setBounds(640, 70, 24, 24);

        addButton.addActionListener(new AddButtonListener());
        add(addButton);
        addButton.setBounds(18, 70, 24, 200);

        updateTable();

        setPreferredSize(new Dimension(680, 300));
    }

    private void updateTable() {
        try {
            List<UserAccount> users = controller.getAllUsers();
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0);

            users.forEach(user -> model.addRow(new Object[]{user.getLoginID(), user.getLogin(), user.getGender().toString(), user.getFirstName(), user.getSecondFirstName(), user.getLastName(), user.getBoxNumber(), user.getPassword(), user.getBirthDate().toString(), user.getCity(), user.isActive()}));
        } catch (AllUserException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class UserTableModelListener implements TableModelListener {
        @Override
        public void tableChanged(TableModelEvent event) {
            if (event.getType() == TableModelEvent.UPDATE) {
                int row = event.getFirstRow();
                int column = event.getColumn();
                Object newValue = userTable.getModel().getValueAt(row, column);
                if (newValue != null) {
                    try {
                        UserAccount user = new UserAccount();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateFormat.setLenient(false);

                        switch (column) {
                            case 1 -> {
                                if (controller.checkLoginValidity((String) newValue)) {
                                    user.setLogin((String) newValue);
                                } else {
                                    if (userTable.getClientProperty("update") != null && (boolean) userTable.getClientProperty("update")) {
                                        userTable.putClientProperty("update", false);
                                    } else {
                                        userTable.putClientProperty("update", true);
                                        throw new UserAccountLoginValidityException("A user already use that login");
                                    }
                                }
                            }
                            case 2 -> user.setGender(((String) newValue).charAt(0));
                            case 3 -> user.setFirstName((String) newValue);
                            case 4 -> user.setSecondFirstName((String) newValue);
                            case 5 -> user.setLastName((String) newValue);
                            case 6 -> user.setBoxNumber((String) newValue);
                            case 7 -> user.setPassword((String) newValue);
                            case 8 -> user.setBirthDate(dateFormat.parse((String) newValue));
                            case 9 -> user.setCity((City) newValue);
                            case 10 -> user.setActive((Boolean) newValue);
                        }
                    } catch (ParseException | UserAccountActiveNullException | UserAccountLoginNullException |
                             UserAccountLoginLenghtException | UserAccountFirstNameLenghtException |
                             UserAccountGenderException | UserAccountsecondFirstNameLenghtException |
                             UserAccountLastNameNullException | UserAccountCityNullException |
                             UserAccountFirstNameNullException | UserAccountPasswordNullException |
                             UserAccountLastNameLenghtException | UserAccountBirthDateNullException |
                             UserAccountBirthDateTimeException | UserAccountBoxLenghtException |
                             UserAccountGenderNullException | UserAccountPasswordLenghtException |
                             UserAccountLoginValidityException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        userTable.setValueAt(userTable.getClientProperty("oldValue"), row, column);
                    }
                }
            }

            JComboBox<City> cityComboBox = new JComboBox<>();
            try {
                controller.getAllCities().forEach(cityComboBox::addItem);
            } catch (AllCityException ex) {
                throw new RuntimeException(ex);
            }

            userTable.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(cityComboBox));
            userTable.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        }
    }

    private class UserTablePropertyChangeListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("tableCellEditor".equals(evt.getPropertyName()) && userTable.isEditing()) {
                if ((userTable.getSelectedRow()) >= 0 && (userTable.getSelectedColumn() >= 0))
                    userTable.putClientProperty("oldValue", userTable.getModel().getValueAt(userTable.getSelectedRow(), userTable.getSelectedColumn()));
            }
        }
    }

    private class SaveButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    UserAccount user = parseUserFromTable(selectedRow);
                    List<UserAccount> allUsers = controller.getAllUsers();
                    if (allUsers.stream().anyMatch(existingUser -> existingUser.getLoginID().equals(user.getLoginID()) && userTable.convertRowIndexToModel(selectedRow) == allUsers.indexOf(existingUser))) {
                        controller.alterUser(user);
                    } else {
                        controller.addUser(user);
                    }
                    updateTable();
                } catch (ParseException | AllUserException | NoSuchAlgorithmException | UserAccountPasswordLenghtException |
                         UserAccountBirthDateTimeException | InvalidKeySpecException | UserAccountActiveNullException |
                         UserAccountFirstNameLenghtException | UserAccountLoginNullException |
                         UserAccountGenderNullException | UserAccountBirthDateNullException |
                         UserAccountLastNameLenghtException | UserAccountPasswordNullException |
                         UserAccountFirstNameNullException | UserAccountCityNullException |
                         UserAccountLoginIDNullException | UserAccountGenderException |
                         UserAccountsecondFirstNameLenghtException | UserAccountBoxLenghtException |
                         UserAccountLoginLenghtException | UserAccountLastNameNullException | AddUserException |
                         AlterUserException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (userTable.getRowCount() > 0) {
                int lastRow = userTable.getRowCount() - 1;
                try {
                    parseUserFromTable(lastRow);
                } catch (UserAccountActiveNullException | UserAccountFirstNameLenghtException |
                         UserAccountLoginNullException | UserAccountGenderNullException |
                         UserAccountBirthDateNullException | UserAccountLastNameLenghtException |
                         UserAccountPasswordNullException | UserAccountFirstNameNullException |
                         UserAccountCityNullException | UserAccountGenderException |
                         UserAccountsecondFirstNameLenghtException | UserAccountBoxLenghtException | ParseException |
                         UserAccountLoginLenghtException | UserAccountLoginIDNullException |
                         UserAccountLastNameNullException | UserAccountPasswordLenghtException |
                         UserAccountBirthDateTimeException ex) {
                    JOptionPane.showMessageDialog(null, "Il existe déjà une nouvelle ligne à remplir.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            JComboBox<City> cityComboBox = new JComboBox<>();
            try {
                controller.getAllCities().forEach(cityComboBox::addItem);
            } catch (AllCityException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }

            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            Object[] emptyRow = new Object[model.getColumnCount()];
            emptyRow[emptyRow.length - 1] = false;
            emptyRow[0] = 0;
            model.addRow(emptyRow);
        }
    }

    private class DeleteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                int confirmation = JOptionPane.showOptionDialog(null, "Vous comprenez que toute valeur associée à cet utilisateur sera supprimée tel que: PAE, rôle, cours, session,...", "Verification de suppression", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirmation == JOptionPane.OK_OPTION) {
                    for (int row : userTable.getSelectedRows()) {
                        try {
                            controller.removeUser((Integer) userTable.getModel().getValueAt(row, 0));
                        } catch (removeUserException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Suppression erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    updateTable();
                }
            }
        }
    }
}