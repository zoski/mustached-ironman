package server;

import java.io.OutputStream;
import java.util.Collection;

import network.BasicAbstractWriter;
import network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void respOK() {
		writeInt(Protocol.CONNECT_OK);
		// TOKEN 
		System.out.println("Connection success");
	}

	public void respKO() {
		writeInt(Protocol.CONNECT_KO);
		System.out.println("Connection failed");
	}

}
