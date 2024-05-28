package businessPackage;

import dataAccessPackage.IUserDBAccess;
import dataAccessPackage.UserDBAccess;
import exceptionPackage.*;
import modelPackage.City;
import modelPackage.UserAccount;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class UserManagementManager {
    private IUserDBAccess dataAccess;

    public UserManagementManager() {
        setDao(new UserDBAccess());
    }

    public void setDao(IUserDBAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public ArrayList<City> getAllCities() throws AllCityException {
        return dataAccess.getAllCities();
    }

    public ArrayList<UserAccount> getAllUsers() throws AllUserException {
        return dataAccess.getAllUsers();
    }

    public void addUser(UserAccount user) throws AddUserException, NoSuchAlgorithmException, InvalidKeySpecException, UserAccountPasswordNullException, UserAccountPasswordLenghtException {
        if (!isPasswordHashed(user.getPassword())) user.setPassword(generatePasswordHash(user.getPassword()));
        dataAccess.addUser(user);
    }

    public void removeUser(Integer userID) throws removeUserException {
        dataAccess.removeUser(userID);
    }

    public void alterUser(UserAccount user) throws AlterUserException, NoSuchAlgorithmException, InvalidKeySpecException, UserAccountPasswordNullException, UserAccountPasswordLenghtException {
        if (!isPasswordHashed(user.getPassword())) user.setPassword(generatePasswordHash(user.getPassword()));
        dataAccess.alterUser(user);
    }

    private static String generatePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static boolean isPasswordHashed(String password) {
        if (password == null || password.split(":").length != 3) {
            return false;
        }

        String[] parts = password.split(":");

        try {
            int iterations = Integer.parseInt(parts[0]);
            if (iterations <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            fromHex(parts[1]);
            fromHex(parts[2]);
        } catch (NumberFormatException | NoSuchAlgorithmException e) {
            return false;
        }

        return true;
    }

    private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public boolean checkLoginValidity(String login) {
        return dataAccess.checkLoginValidity(login);
    }
}
