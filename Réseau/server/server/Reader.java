package server;


import game.model.Player;

import java.io.InputStream;

import network.BasicAbstractReader;
import network.Protocol;
/**
 * 
 * @TODO 
 *
 */
public class Reader extends BasicAbstractReader {

	String username;
	String password;
	int id;
	
	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {	//implementer les différents messages
		type = readInt (); //lecture du discriminant
		switch (type) {
		case 0 :
			break;
			
		case Protocol.CONNECT:
			username = readString();
			password = readString();
			break;
//		case Protocol.DISCONNECT:
//			id = readInt();
//			break;
		}
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
