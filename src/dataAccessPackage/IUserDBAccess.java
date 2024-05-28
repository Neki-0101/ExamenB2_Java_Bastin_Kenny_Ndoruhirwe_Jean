package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.City;
import modelPackage.UserAccount;

import java.util.ArrayList;

public interface IUserDBAccess {

    ArrayList<UserAccount> getAllUsers() throws AllUserException;

    void addUser(UserAccount user) throws AddUserException;

    void alterUser(UserAccount user) throws AlterUserException;

    void removeUser(Integer userID) throws removeUserException;

    boolean checkLoginValidity(String login);

    ArrayList<City> getAllCities() throws AllCityException;
}
