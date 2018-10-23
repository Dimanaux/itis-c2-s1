package app.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

class SecurityService {
//    private static final byte[] SALT = new byte[] { 100, 16, -85, -9, -32, -81, -85, 109, 44, -10, -89, 62, -65, 42, 111, -32 };
    String hashPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("No sha-512 algorithm.");
        }
        md.update(new byte[] { 100, 16, -85, -9, -32, -81, -85, 109, 44, -10, -89, 62, -65, 42, 111, -32 });

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    String createToken(String seed) {
        return hashPassword(seed + new Date());
    }
}
