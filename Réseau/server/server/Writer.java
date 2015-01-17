package server;

import java.io.OutputStream;
import java.util.Collection;

import model.Account;
import model.User;
import network.BasicAbstractWriter;
import network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void respOK(long id) {
		
		writeInt(Protocol.CONNECT_OK);
		// TOKEN
		writeLong(id);
		System.out.println("Connection success");
	}

	public void respKO() {
		writeInt(Protocol.CONNECT_KO);
		System.out.println("Connection failed");
	}
	
	
	public void decoKO() {
		writeInt(Protocol.DISCONNECT_KO);
		System.out.println("Disconnection failed");
	}

	public void decoOK() {
		writeInt(Protocol.DISCONNECT_OK);
		System.out.println("Disconnection success");
	}
	
	
	public void cashOK() {
		writeInt(Protocol.ADD_CASH_OK);
		System.out.println("Cash success");
	}
	
	public void cashKO() {
		writeInt(Protocol.ADD_CASH_KO);
		System.out.println("Cash fail");
	}
	
	
	public void statsOK(Account a) {
		writeInt(Protocol.GET_STATS_OK);
		writeInt(a.getCash());
		writeString(a.getImage());
		System.out.println("Stats success");
	}
	
	public void statsKO() {
		writeInt(Protocol.GET_STATS_KO);
		System.out.println("Stats fail");
	}
	
}
