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
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!close) {
                        try {
                            PrintWriter writer = new PrintWriter(client.getSocket().getOutputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                            BufferedReader reader2 = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
                            String line = reader.readLine();
                            if (line != null) {
                                writer.println(InetAddress.getLocalHost().getHostName() + ": " +line);
                                writer.flush();
                                System.out.println(reader2.readLine());
                            }

                        } catch (IOException ex) {
                            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, "Erro ao conectar ao servidor...\nServer: " + client.getHost());
                            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });

            thread.start();

        } catch (IOException ex) {
            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
