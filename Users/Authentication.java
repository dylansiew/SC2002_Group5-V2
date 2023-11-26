package Users;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * The `Authentication` class represents user authentication information, including username and password.
 * It provides methods for setting and checking authentication status and changing passwords.
 */
public class Authentication implements Serializable {
    private String username;
    private String password = this.encrypt("password");
    private Boolean authenticated = false;
    private boolean firstLogIn;
    /**
     * Constructs a new `Authentication` instance with the specified username.
     *
     * @param username The username associated with this authentication.
     */
    public Authentication(String username) {
        this.username = username;
        firstLogIn = true;
    }
    /**
     * Sets the password for this authentication. The password is encrypted before storage.
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        if (authenticated) {
            this.password = this.encrypt(password);
        } else {
            System.out.println("Please login to change your password...");
        }
    }
    /**
     * Gets the username associated with this authentication.
     * @return The username.
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Determine whether the login is the first time login for the user
     * @return True for first login, False otherwise.
     */
    public boolean getFirstLogin() {
        return this.firstLogIn;
    }

    public void setFirstLogin(boolean value) {
        this.firstLogIn = value;
    }
    /**
     * Sets the authenticated status based on the provided username and password.
     * @param username The username to check.
     * @param password The password to check.
     */
    public void setAuthenticated(String username, String password) {
        this.authenticated = this.username.toLowerCase().equals(username.toLowerCase())
                && this.password.equals(this.encrypt(password));
    }
    /**
     * Checks if the provided new password matches the old password.
     * @param newpassword The new password to check.
     * @return True if the new password matches the old password, false otherwise.
     */
    public boolean checkVsOldPassword(String newpassword) {
        if (this.password.equals(this.encrypt(newpassword)))
            return true;
        else
            return false;
    }
    /**
     * Gets the authentication status.
     * @return True if authenticated, false otherwise.
     */
    public boolean getauthenticated() {
        return this.authenticated;
    }
    /**
     * Encrypts the input string using SHA-256 hashing algorithm.
     * @param input The input string to encrypt.
     * @return The encrypted string.
     */
    private String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte[] hash = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
