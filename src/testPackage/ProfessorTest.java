package testPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Professor;
import modelPackage.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private Professor professor;
    private City city;

    @BeforeEach
    void setUp() throws CityPostalNullException, CityPostalFormatException, CityNameLengthException, CityNameNullException, CityIDNullException, CityIDException, UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountGenderNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountBirthDateTimeException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountLoginIDNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException, ProfessorGradeLenghtException, ProfessorGradeNullException {
        city = new City(1, "Test City", 1234);
        professor = new Professor(1, "testUser", 'M', "John", "Doe", "Smith", "123", "password", new Date(System.currentTimeMillis() - 1000), city, true, "Professor");
    }

    @Test
    void TestValidProfessorCreation() {
        assertNotNull(professor);
        assertEquals("testUser", professor.getLogin());
        assertEquals('M', professor.getGender());
        assertEquals("John", professor.getFirstName());
        assertEquals("Doe", professor.getSecondFirstName());
        assertEquals("Smith", professor.getLastName());
        assertEquals("123", professor.getBoxNumber());
        assertEquals("password", professor.getPassword());
        assertEquals(city, professor.getCity());
        assertTrue(professor.isActive());
        assertEquals("Professor", professor.getGrade());
        assertNotNull(professor.getRoles());
    }

    @Test
    void TestSetGrade_Null() {
        assertThrows(ProfessorGradeNullException.class, () -> professor.setGrade(null));
    }

    @Test
    void TestSetGrade_LengthExceeded() {
        String longGrade = "a".repeat(21);
        assertThrows(ProfessorGradeLenghtException.class, () -> professor.setGrade(longGrade));
    }
}