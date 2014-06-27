package org.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WebSocketObject {
	
	public Session session;
	
	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.printf("Connection closed: %d - %s%n", statusCode, reason);
		this.session = null;
	
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		System.out.printf("Got connect: %s%n", session);
		this.session = session;
			
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		System.out.println("Message Recieved : "+ msg.toString());
	}
	
	
	public void  sendMessage(String message){
		
		session.getRemote().sendStringByFuture(message);
		
	}
	
	public void closeConnection() {
		session.close(StatusCode.NORMAL, "[Consumer]Closing the session with the Server!!");
		
	}

}