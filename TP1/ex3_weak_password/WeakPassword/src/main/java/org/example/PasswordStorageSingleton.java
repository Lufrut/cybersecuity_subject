package org.example;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class PasswordStorageSingleton {
    private static volatile PasswordStorageSingleton instance;
    Queue<String> passwordDictionary;
    private PasswordStorageSingleton(){
        try  {
            passwordDictionary  = new ArrayDeque<>(
                    FileUtils.readLines(new File(getClass().getClassLoader().getResource("rockyou_utf8.txt").toURI())));
                     //Files.lines(Path.of(getClass().getClassLoader().getResource("rockyou_utf8.txt").toURI())
                       //     ).toList());
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static PasswordStorageSingleton getInstance(){
        PasswordStorageSingleton result = instance;
        if(result != null) return result;
        synchronized(PasswordStorageSingleton.class){
            if (instance == null) {
                instance = new PasswordStorageSingleton();
            }
            return instance;
        }
    }
}

