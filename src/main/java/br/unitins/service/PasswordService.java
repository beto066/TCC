package br.unitins.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Singleton;

@Singleton
public class PasswordService {

    private String salt = "blAh%@#11!";
    private Integer iterationCount = 405;
    private Integer keylength = 512;

    public String getHash(CharSequence password) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(
                            new PBEKeySpec(password.toString().toCharArray(), salt.getBytes(), iterationCount, keylength)
                        )
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        PasswordService service = new PasswordService();
        System.out.println(service.getHash("123"));
    }

}