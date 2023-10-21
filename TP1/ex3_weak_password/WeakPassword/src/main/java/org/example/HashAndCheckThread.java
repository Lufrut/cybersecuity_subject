package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.example.WeakPassword.start;

public class HashAndCheckThread extends Thread {

    byte[] encryptedPassword;
    byte[] password;
    MessageDigest md;

    public HashAndCheckThread(byte[] encryptedPassword, byte[] password) {
        this.encryptedPassword = encryptedPassword;
        this.password = password;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void run() {

        if(
                Arrays.equals(encryptedPassword ,md.digest(password))
        ){
            System.out.println(new String(password));
            System.out.println(System.currentTimeMillis() - start + "ms");
            System.exit(130);
        }
    }
}
