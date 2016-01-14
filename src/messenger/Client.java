/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Hugo
 */
public class Client {

    private final String host;
    private Socket socket;

    public Client(String host) {
        this.host = host;
    }
    
    public String getHost(){
        return this.host;
    }
    
    public Socket getSocket(){
        return this.socket;
    }

    public Client openConnection() throws IOException {
        socket = new Socket(host, 1055);
        return this;
    }
    

}
