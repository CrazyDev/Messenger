/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static messenger.Messenger.close;

/**
 *
 * @author Hugo
 */
public class Client implements Runnable {

    private final String host;
    private Socket socket;

    public Client(String host) {
        this.host = host;
    }

    public String getHost() {
        return this.host;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public Client openConnection() throws IOException {
        socket = new Socket(host, 1055);
        return this;
    }

    @Override
    public void run() {
        while (true) {
            try {
                PrintWriter writer = new PrintWriter(getSocket().getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
                String line = reader.readLine();
                if (line != null) {
                    writer.println(InetAddress.getLocalHost().getHostName() + ": " + line);
                    writer.flush();
                    System.out.println(reader2.readLine());
                }
            } catch (IOException ex) {
                Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
