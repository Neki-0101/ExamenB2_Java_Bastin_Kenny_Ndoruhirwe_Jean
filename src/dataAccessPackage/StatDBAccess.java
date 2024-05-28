package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatDBAccess implements IStatDBAccess {
    @Override
    public ArrayList<Section> getAllSection() throws AllSectionException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Section> sections = new ArrayList<>();

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT * FROM Section ORDER BY Section.Label";
            statement = dbAccess.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String sectionLabel = resultSet.getString("Label");
                Integer sectionBuildingNumber = resultSet.getInt("BuildingNumber");
                Section section = new Section(sectionLabel, sectionBuildingNumber);
                sections.add(section);
            }
        } catch (SQLException | SectionLabelLengthException | SectionLabelNullException |
                 SectionBuildingNumberNullException | SectionBuildingNumberValueException e) {
            throw new AllSectionException("Error retrieving all Section from the database");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return sections;
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

    @Override
    public Integer getSectionStudentCount(Section section) throws SectionCountException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT COUNT(*) AS StudentCount\n" + "FROM Section sec\n" + "JOIN StudyYear sy ON sec.Label = sy.Organization\n" + "JOIN `Group` g ON sy.IDStudyYear = g.StudyYear\n" + "JOIN Student s ON g.IDGroup = s.Affectation\n" + "WHERE sec.Label = ?\n" + "GROUP BY sec.Label\n";
            statement = dbAccess.prepareStatement(sql);
            statement.setString(1, section.getLabel());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("StudentCount");
            }
        } catch (SQLException e) {
            throw new SectionCountException("Error retrieving the number of students");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Integer[] getSectionGenderCount(Section section) throws SectionGenderException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int femaleStudent = 0;
        int maleStudent = 0;
        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT SUM(CASE WHEN ua.Gender = 'F' THEN 1 ELSE 0 END) AS FemaleStudents, SUM(CASE WHEN ua.Gender = 'M' THEN 1 ELSE 0 END) AS MaleStudents\n" + "FROM Student s\n" + "INNER JOIN UserAccount ua ON s.Login = ua.LoginID\n" + "INNER JOIN `Group` g ON s.Affectation = g.IDGroup\n" + "INNER JOIN StudyYear sy ON g.StudyYear = sy.IDStudyYear\n" + "INNER JOIN Section sec ON sy.Organization = sec.Label\n" + "where sec.Label = ?\n" + "GROUP BY sec.Label";
            statement = dbAccess.prepareStatement(sql);
            statement.setString(1, section.getLabel());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                femaleStudent = resultSet.getInt("FemaleStudents");
                maleStudent = resultSet.getInt("MaleStudents");
            }
        } catch (SQLException e) {
            throw new SectionGenderException("Error retrieving the gender of students");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Integer[]{femaleStudent, maleStudent};
    }

    @Override
    public Integer getCityStudentCount(City city) throws CityCountException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT COUNT(*) AS StudentCount\n" + "FROM Student s\n" + "INNER JOIN UserAccount ua ON s.Login = ua.LoginID\n" + "INNER JOIN City ci ON ua.City = ?\n" + "GROUP BY ci.Name";
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, city.getCityID());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("StudentCount");
            }
        } catch (SQLException e) {
            throw new CityCountException("Error retrieving the number of students");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Integer getTotalStudentCount() {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT COUNT(*) AS StudentCount from Student";
            statement = dbAccess.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("StudentCount");
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
        return 0;
    }
}
