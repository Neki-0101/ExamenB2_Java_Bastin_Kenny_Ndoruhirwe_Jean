package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.UserAccount;

import java.sql.*;
import java.util.ArrayList;

public class UserDBAccess implements IUserDBAccess {
    @Override
    public ArrayList<UserAccount> getAllUsers() throws AllUserException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<UserAccount> users = new ArrayList<>();

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT * FROM UserAccount u JOIN City c ON u.City = c.CityID ORDER BY u.LoginID";
            statement = dbAccess.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer LoginID = resultSet.getInt("LoginID");
                String Login = resultSet.getString("Login");
                Character Gender = resultSet.getString("Gender").charAt(0);
                String FirstName = resultSet.getString("FirstName");
                String SecondFirstName = resultSet.getString("SecondFirstName");
                String LastName = resultSet.getString("LastName");
                String BoxNumber = resultSet.getString("BoxNumber");
                String Password = resultSet.getString("Password");
                Date BirthDate = resultSet.getDate("BirthDate");
                Boolean IsActive = resultSet.getBoolean("IsActive");

                int cityID = resultSet.getInt("CityID");
                String cityName = resultSet.getString("Name");
                int postalCode = resultSet.getInt("PostalCode");
                City City = new City(cityID, cityName, postalCode);

                UserAccount user = new UserAccount(LoginID, Login, Gender, FirstName, SecondFirstName, LastName, BoxNumber, Password, BirthDate, City, IsActive);
                users.add(user);
            }
        } catch (UserAccountActiveNullException | UserAccountLastNameLenghtException |
                 UserAccountBirthDateTimeException | UserAccountPasswordNullException |
                 UserAccountFirstNameLenghtException | UserAccountLoginNullException | UserAccountGenderNullException |
                 UserAccountBirthDateNullException | UserAccountFirstNameNullException | UserAccountCityNullException |
                 UserAccountGenderException | UserAccountsecondFirstNameLenghtException |
                 UserAccountBoxLenghtException | UserAccountPasswordLenghtException | UserAccountLoginLenghtException |
                 UserAccountLastNameNullException | UserAccountLoginIDNullException | CityPostalNullException |
                 CityPostalFormatException | SQLException | CityNameLengthException | CityNameNullException |
                 CityIDNullException | CityIDException e) {
            throw new AllUserException("Error retrieving all Users from the database");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    @Override
    public void addUser(UserAccount user) throws AddUserException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "INSERT INTO UserAccount (Login, Gender, FirstName, SecondFirstName, LastName, BoxNumber, Password, BirthDate, City, IsActive) VALUES\n" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            statement = dbAccess.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, String.valueOf(user.getGender()));
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getBoxNumber());
            statement.setString(7, user.getPassword());
            statement.setDate(8, user.getBirthDate());
            statement.setInt(9, user.getCity().getCityID());
            statement.setBoolean(10, user.isActive());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new AddUserException(e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void alterUser(UserAccount user) throws AlterUserException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "update UserAccount set Login=?, Gender=?, FirstName=?, SecondFirstName=?, LastName=?, BoxNumber=?, Password=?, BirthDate=?, City=?, IsActive=? where LoginID=?";
            statement = dbAccess.prepareStatement(sql);
            statement.setString(1, String.valueOf(user.getLogin()));
            statement.setString(2, String.valueOf(user.getGender()));
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getBoxNumber());
            statement.setString(7, user.getPassword());
            statement.setDate(8, user.getBirthDate());
            statement.setInt(9, user.getCity().getCityID());
            statement.setBoolean(10, user.isActive());
            statement.setInt(11, user.getLoginID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new AlterUserException(e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUser(Integer userID) throws removeUserException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "DELETE FROM PAE WHERE Login = ?";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();

            sql = "DELETE FROM Student WHERE Login = ?";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();

            sql = "DELETE FROM Assignment WHERE Login = ?";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();

            sql = "DELETE FROM Dispensing WHERE Professor = ?";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();

            sql = "DELETE FROM Session WHERE Trainer = ?";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();

            sql = "DELETE FROM Professor WHERE Login = ?";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();

            sql = "DELETE FROM UserAccount WHERE LoginID = ?";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new removeUserException();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean checkLoginValidity(String login) {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int i = 0;
        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT * from UserAccount where Login=?";
            statement = dbAccess.prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i == 0;
    }

    @Override
    public ArrayList<City> getAllCities() throws AllCityException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<City> cities = new ArrayList<>();

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT * FROM City order by City.PostalCode";
            statement = dbAccess.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer cityID = resultSet.getInt("CityID");
                String cityName = resultSet.getString("Name");
                Integer cityPostalCode = resultSet.getInt("PostalCode");

                City city = new City(cityID, cityName, cityPostalCode);
                cities.add(city);
            }
        } catch (SQLException | CityPostalNullException | CityIDException | CityIDNullException |
                 CityNameNullException | CityNameLengthException | CityPostalFormatException e) {
            throw new AllCityException("Error retrieving all Cities from the database");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cities;
    }
}
