package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.example.BruteForce.start;

public class Request extends Thread {

    private String ip;
    private int port;

    private int passSegment;

    Request(String ip, int port, int passSegment) {
        this.ip = ip;
        this.port = port;
        this.passSegment = passSegment;
    }
    void sendPassword(){
        String pass =
                String.valueOf(passSegment / 1000)
                        + String.valueOf((passSegment % 1000) / 100)
                        + String.valueOf((passSegment % 100) / 10)
                        + String.valueOf(passSegment % 10);
        try (
                Socket socket = new Socket(ip, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            out.println(pass);

            in.readLine();
            String pin = in.readLine();
            if(pin.contains("!")) {
                System.out.println("password:");
                System.out.println(pass);
                System.out.println("It has taken:");
                System.out.println(System.currentTimeMillis() - start + "ms");
                System.exit(130);
            }

        } catch (IOException e) {
            sendPassword();
        }
    }

    @Override
    public void run() {
        sendPassword();
    }

}
