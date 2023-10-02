package org.example;

import java.io.*;
import java.util.HashSet;

public class WordStorageSingleton {
    private static volatile WordStorageSingleton instance;
    HashSet<String> dictionary;
    private WordStorageSingleton(){
        dictionary = new HashSet<>();
        try ( InputStream is = getClass().getClassLoader().getResourceAsStream("english3.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static WordStorageSingleton getInstance(){
        WordStorageSingleton result = instance;
        if(result != null) return result;
        synchronized(WordStorageSingleton.class){
            if (instance == null) {
                instance = new WordStorageSingleton();
            }
            return instance;
        }
    }
}
