package testPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.Role;
import modelPackage.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    private Role role;

    @BeforeEach
    void setUp() throws RoleIDValueException, RoleNameNullException, RoleNameLengthException, RoleIDNullException {
        role = new Role(1,"ADMIN");
    }

    @Test
    void testValidRoleCreation() {
        assertNotNull(role);
        assertEquals(1, role.getRoleId());
        assertEquals("ADMIN", role.getRoleName());
        assertNotNull(role.getUserAccounts());
    }

    @Test
    void TestSetRoleId_Null() {
        assertThrows(RoleIDNullException.class, () -> role.setRoleId(null));
    }
    @Test
    void TestSetRoleId_Negative() {
        assertThrows(RoleIDValueException.class, () -> role.setRoleId(-1));
    }

    @Test
    void TestSetRoleName_Null() {
        assertThrows(RoleNameNullException.class, () -> role.setRoleName(null));
    }

    @Test
    void TestSetRoleName_LengthExceeded() {
        assertThrows(RoleNameLengthException.class, () -> role.setRoleName("a".repeat(33)));
    }

    @Test
    void TestaddUser() throws CityPostalNullException, CityPostalFormatException, CityNameLengthException, CityNameNullException, CityIDNullException, CityIDException, UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountGenderNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountBirthDateTimeException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountLoginIDNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException {
        City city = new City(1, "Test City", 1234);
        UserAccount user = new UserAccount(1, "testUser", 'M', "John", "Doe", "Smith", "123", "password", new Date(System.currentTimeMillis() - 1000), city, true);
        role.addUser(user);
        assertTrue(role.getUserAccounts().contains(user));
    }

    @Test
    void TestremoveUser() throws CityPostalNullException, CityPostalFormatException, CityNameLengthException, CityNameNullException, CityIDNullException, CityIDException, UserAccountActiveNullException, UserAccountFirstNameLenghtException, UserAccountLoginNullException, UserAccountGenderNullException, UserAccountBirthDateNullException, UserAccountLastNameLenghtException, UserAccountBirthDateTimeException, UserAccountPasswordNullException, UserAccountFirstNameNullException, UserAccountCityNullException, UserAccountLoginIDNullException, UserAccountGenderException, UserAccountsecondFirstNameLenghtException, UserAccountBoxLenghtException, UserAccountPasswordLenghtException, UserAccountLoginLenghtException, UserAccountLastNameNullException {
        City city = new City(1, "Test City", 1234);
        UserAccount user = new UserAccount(1, "testUser", 'M', "John", "Doe", "Smith", "123", "password", new Date(System.currentTimeMillis() - 1000), city, true);
        user.addRole(role);
        user.removeRole(role);
        assertFalse(user.getRoles().contains(role));
    }
}