package org.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
 
/**
 * Sample Websocket Client
 */
public class SocketClient {
 
    public static void main(String[] args) {
        
      //  String destUri = "ws://localhost:8080/webSocketSample/server";
        String destUri = "ws://echo.websocket.org";
        String message;
        
        WebSocketClient client = new WebSocketClient();
        WebSocketObject socket = new WebSocketObject();
        try {
            client.start();
        
            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
            System.out.println("Connecting to :" +echoUri);
            Thread.sleep(1000);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
            
            while (true) {
            	
				System.out.print("Enter Message : ");
				message = myReader.readLine();
				socket.sendMessage(message);
				Thread.sleep(1000);
				
			}
            
                       
          } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
            	socket.closeConnection();
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}