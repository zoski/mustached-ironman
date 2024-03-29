package fr.ensisa.hassenforder.game.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {

	String username;
	String password;
	long id;
	int cash_asked;
	String item_sold;
	String imgName;
	
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
			
		case Protocol.DISCONNECT:
			id = readLong();
			username = readString();
			System.out.println(id);
			System.out.println(username);
			break;
			
		case Protocol.ADD_CASH:
			username = readString();
			id = readLong();
			cash_asked = readInt();
			
			System.out.println(cash_asked);
			break;
			
		case Protocol.GET_STATS:
			username = readString();
			id = readLong();
			break;
			
		case Protocol.GET_INV:
			username = readString();
			id = readLong();
			break;
			
		case Protocol.GET_SHOP:
			username = readString();
			id = readLong();
			break;
			
		case Protocol.REFRESH_SHOP:
			username = readString();
			id = readLong();
			break;
			
		case Protocol.SELL_SHOP:
			username = readString();
			id = readLong();
			item_sold = readString();
			break;
			
		case Protocol.BUY_SHOP:
			username = readString();
			id = readLong();
			item_sold = readString();
			break;
			
		case Protocol.CLEAR:
			username = readString();
			id = readLong();
			break;
			
		case Protocol.CONSUME:
			username = readString();
			id = readLong();
			break;
			
		case Protocol.GET_IMG:
			imgName = readString();
			break;
		}
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public long getId() {
		return id;
	}
	
	public int getCash() {
		return cash_asked;
	}
	
	public String getItem() {
		return item_sold;
	}

	public String getImage() {
		return imgName;
	}
}

