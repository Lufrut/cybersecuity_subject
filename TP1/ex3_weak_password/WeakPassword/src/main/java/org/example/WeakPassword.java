package org.example;

import org.apache.commons.codec.digest.DigestUtils;


public class WeakPassword {
    public static long start;

    public static void main(String[] args) {
        String encryptedPass = "5a74dd4eef347734c8a0a9a3188abd11";
        start = System.currentTimeMillis();
        PasswordStorageSingleton passwordStorage = PasswordStorageSingleton.getInstance();
        int size = passwordStorage.passwordDictionary.size();
        for (int i = 0; i < size; i++) {
            String pass = passwordStorage.passwordDictionary.poll();
            String md5Hex = DigestUtils.md5Hex(pass);
            if (
                    md5Hex.equals(encryptedPass)
            ) {
                System.out.println(pass);
                System.out.println(System.currentTimeMillis() - start + "ms");
                System.exit(0);
            }

        }
    }
}