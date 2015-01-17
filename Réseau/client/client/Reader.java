package client;

import game.model.Category;
import game.model.Product;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import network.BasicAbstractReader;
import network.Protocol;

public class Reader extends BasicAbstractReader {

	long id;
	int cash;
	String image;
	
	Collection<Product> cp;
	
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
			
		case Protocol.GET_INV_OK:
			
				int size = readInt();
				int i;
				for (i=0;i<size;i++)
				{
					String str = readString();
					Category cat = Category.valueOf(str);
					cp.add(new Product(cat, readString(), readString(), readInt(), readBoolean(), readInt(), readLong()));
				}
	
			break;
			
		case Protocol.GET_INV_KO:
			break;
			
		}
	}

}
