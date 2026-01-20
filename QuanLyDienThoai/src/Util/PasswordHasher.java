package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for securely hashing and verifying passwords using SHA-256 with salt
 */
public class PasswordHasher {
    
    private static final String ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;
    
    /**
     * Generates a random salt for password hashing
     * @return Base64 encoded salt string
     */
    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    /**
     * Hashes a password with the given salt using SHA-256
     * @param password The plain text password
     * @param salt The salt to use
     * @return Base64 encoded hash
     */
    private static String hashPasswordWithSalt(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi khi tạo mật khẩu", e);
        }
    }
    
    /**
     * Hashes a password with a newly generated salt
     * @param password The plain text password to hash
     * @return Hashed password in format "salt:hash"
     */
    public static String hashPassword(String password) {
        String salt = generateSalt();
        String hash = hashPasswordWithSalt(password, salt);
        return salt + ":" + hash;
    }
    
    /**
     * Verifies a password against a stored hash
     * @param password The plain text password to verify
     * @param storedHash The stored hash in format "salt:hash"
     * @return true if password matches, false otherwise
     */
    public static boolean verifyPassword(String password, String storedHash) {
        try {
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                // If stored hash doesn't have salt (old plain text password)
                // This allows backward compatibility during migration
                return password.equals(storedHash);
            }
            
            String salt = parts[0];
            String hash = parts[1];
            String computedHash = hashPasswordWithSalt(password, salt);
            
            return hash.equals(computedHash);
        } catch (Exception e) {
            // If any error occurs, try plain text comparison for backward compatibility
            return password.equals(storedHash);
        }
    }
    
    /**
     * Checks if a password is already hashed
     * @param password The password to check
     * @return true if password is hashed, false if plain text
     */
    public static boolean isHashed(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        // Hashed passwords have format "salt:hash" where both parts are Base64
        String[] parts = password.split(":");
        if (parts.length != 2) {
            return false;
        }
        try {
            Base64.getDecoder().decode(parts[0]);
            Base64.getDecoder().decode(parts[1]);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
