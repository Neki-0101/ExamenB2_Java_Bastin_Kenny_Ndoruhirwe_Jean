package controllerPackage;

import businessPackage.UserManagementManager;
import exceptionPackage.*;
import modelPackage.City;
import modelPackage.UserAccount;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class UserManagementController {
    private UserManagementManager manager;

    public void setManager(UserManagementManager manager) {
        this.manager = manager;
    }

    public UserManagementController() {
        setManager(new UserManagementManager());
    }

    public ArrayList<UserAccount> getAllUsers() throws AllUserException {
        return manager.getAllUsers();
    }

    public void addUser(UserAccount user) throws AddUserException, NoSuchAlgorithmException, InvalidKeySpecException, UserAccountPasswordNullException, UserAccountPasswordLenghtException {
        manager.addUser(user);
    }

    public void removeUser(Integer userID) throws removeUserException {
        manager.removeUser(userID);
    }

    public void alterUser(UserAccount user) throws AlterUserException, NoSuchAlgorithmException, InvalidKeySpecException, UserAccountPasswordNullException, UserAccountPasswordLenghtException {
        manager.alterUser(user);
    }

    public ArrayList<City> getAllCities() throws AllCityException {
        return manager.getAllCities();
    }

    public boolean checkLoginValidity(String login) {
        return manager.checkLoginValidity(login);
    }
}
