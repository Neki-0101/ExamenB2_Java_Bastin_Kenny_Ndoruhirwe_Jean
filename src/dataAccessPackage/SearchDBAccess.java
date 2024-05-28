package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Section;
import modelPackage.StudyYear;
import modelPackage.TeachingUnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchDBAccess implements ISearchDBAccess {
    @Override
    public ArrayList<HashMap<String, Object>> listSearch1(Date date1, Date date2, TeachingUnit tu) throws SearchException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<HashMap<String, Object>> search = new ArrayList<>();

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT LA.ActivityTitle AS Title, S.Date AS SessionDate, S.TimeStart AS SessionStartTime, S.EndTime AS SessionEndTime, UA.LastName AS ProfessorLastName, UA.FirstName AS ProfessorFirstName FROM Session AS S INNER JOIN LearningActivity AS LA ON S.ActivityCode = LA.ActivityCode INNER JOIN TeachingUnit AS TU ON LA.UnitCode = TU.UnitCode INNER JOIN Professor AS P ON S.Trainer = P.Login INNER JOIN UserAccount AS UA ON P.Login = UA.LoginID WHERE TU.UnitTitle = ? AND S.Date BETWEEN ? AND ? ORDER BY Title;";
            statement = dbAccess.prepareStatement(sql);
            statement.setString(1, tu.getTitle());
            statement.setDate(2, date1);
            statement.setDate(3, date2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HashMap<String, Object> session = new HashMap<>();
                session.put("Title", resultSet.getString("Title"));
                session.put("SessionDate", resultSet.getDate("SessionDate"));
                session.put("SessionStartTime", resultSet.getTime("SessionStartTime"));
                session.put("SessionEndTime", resultSet.getTime("SessionEndTime"));
                session.put("ProfessorLastName", resultSet.getString("ProfessorLastName"));
                session.put("ProfessorFirstName", resultSet.getString("ProfessorFirstName"));
                search.add(session);
            }
        } catch (SQLException e) {
            throw new SearchException("Error process the search from the database");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return search;
    }

    @Override
    public ArrayList<HashMap<String, Object>> listSearch2(Section section) throws SearchException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<HashMap<String, Object>> search = new ArrayList<>();

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT ua.FirstName, ua.LastName, sy.Type, sy.Number, g.Letter FROM Student s JOIN UserAccount ua ON s.Login = ua.LoginID JOIN `Group` g ON s.Affectation = g.StudyYear JOIN Section sec ON g.Letter = sec.Label JOIN StudyYear sy ON g.StudyYear = sy.Number WHERE sec.Label = ? ORDER BY ua.LastName;";
            statement = dbAccess.prepareStatement(sql);
            statement.setString(1, section.getLabel());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HashMap<String, Object> students = new HashMap<>();
                students.put("FirstName", resultSet.getString("FirstName"));
                students.put("LastName", resultSet.getString("LastName"));
                students.put("Type", resultSet.getString("Type"));
                students.put("Number", resultSet.getInt("Number"));
                students.put("Letter", resultSet.getString("Letter").charAt(0));
                search.add(students);
            }
        } catch (SQLException e) {
            throw new SearchException("Error process the search from the database");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return search;
    }

    @Override
    public ArrayList<HashMap<String, Object>> listSearch3(City city, boolean selected) throws SearchException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<HashMap<String, Object>> search = new ArrayList<>();

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "";
            if (!selected) {
                sql = "SELECT ua.FirstName, ua.LastName, ua.BirthDate\n" + "FROM UserAccount ua\n" + "JOIN Student s ON ua.LoginID = s.Login\n" + "JOIN City c ON ua.City = c.CityID\n" + "WHERE c.CityID = ?";
            } else {
                sql = "SELECT ua.FirstName, ua.LastName, ua.BirthDate\n" + "FROM UserAccount ua\n" + "JOIN Professor p ON ua.LoginID = p.Login\n" + "JOIN City c ON ua.City = c.CityID\n" + "WHERE c.CityID = ?";
            }
            statement = dbAccess.prepareStatement(sql);
            statement.setInt(1, city.getCityID());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HashMap<String, Object> students = new HashMap<>();
                students.put("FirstName", resultSet.getString("FirstName"));
                students.put("LastName", resultSet.getString("LastName"));
                students.put("BirthDate", resultSet.getDate("BirthDate"));
                search.add(students);
            }
        } catch (SQLException e) {
            throw new SearchException("Error process the search from the database");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return search;
    }

    @Override
    public ArrayList<TeachingUnit> getAllTU() throws AllTUException {
        Connection dbAccess = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<TeachingUnit> teachingUnits = new ArrayList<>();

        try {
            dbAccess = SingletonConnection.getInstance();
            String sql = "SELECT * FROM TeachingUnit ORDER BY TeachingUnit.UnitCode";
            statement = dbAccess.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer unitCode = resultSet.getInt("UnitCode");
                String unitTitle = resultSet.getString("UnitTitle");
                int idStudyYear = resultSet.getInt("IDStudyYear");

                StudyYear studyYear = new StudyYear(idStudyYear);

                TeachingUnit teachingUnit = new TeachingUnit(unitCode, unitTitle, studyYear);
                teachingUnits.add(teachingUnit);
            }
        } catch (SQLException | TeachingUnitStudyYearNullException | TeachingUnitCodeNullException |
                 TeachingUnitCodeValueException | TeachingUnitTitleNullException | TeachingUnitTitleLenghtException |
                 StudyYearIDValueException | StudyYearIDNullException e) {
            throw new AllTUException("Error retrieving all Teaching Units from the database");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return teachingUnits;
    }

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
}
