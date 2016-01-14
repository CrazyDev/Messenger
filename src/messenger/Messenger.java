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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
public class Messenger {

    public static boolean close;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client client = new Client(args[0]);
        System.out.println("Client: Created client");
        try {
            client.openConnection();
            System.out.println("Client: Opened connection to server");

            Thread thread = new Thread(client);
            thread.start();

        } catch (IOException ex) {
            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, "Erro ao conectar ao servidor...\nServer: " + client.getHost());
            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
