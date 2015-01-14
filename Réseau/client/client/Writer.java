package client;

import java.io.OutputStream;

import network.BasicAbstractWriter;
import network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void reqConnect(String username, String password) {
		writeInt(Protocol.CONNECT);
		writeString(username);
		writeString(password);
	}
	
	

}
