package client;

import java.io.InputStream;

import network.BasicAbstractReader;
import network.Protocol;

public class Reader extends BasicAbstractReader {

	long id;
	int cash;
	String image;
	
	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:
			break;
			
		case Protocol.CONNECT_OK:
			id = readLong();
			System.out.println(id);
			break;
			
		case Protocol.CONNECT_KO:
			break;
			
		case Protocol.DISCONNECT_OK:
			break;	
			
		case Protocol.DISCONNECT_KO:
			break;
			
		case Protocol.ADD_CASH_OK:
			break;
			
		case Protocol.ADD_CASH_KO:
			break;
			
		case Protocol.GET_STATS_OK:
			cash = readInt();
			image = readString();
			break;
			
		case Protocol.GET_STATS_KO:
			break;
			
		}
	}

}
