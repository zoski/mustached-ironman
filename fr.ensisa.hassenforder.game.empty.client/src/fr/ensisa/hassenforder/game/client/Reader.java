package fr.ensisa.hassenforder.game.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.game.model.Category;
import fr.ensisa.hassenforder.game.model.Product;

public class Reader extends BasicAbstractReader {

	long id;
	int cash;
	String image;
	long length;
	byte[] imgBytes;
	
	Collection<Product> cp = new ArrayList<Product>();

	
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
			length = readLong();
			imgBytes = readBytes(length);
			break;
			
		case Protocol.GET_STATS_KO:
			break;
			
		case Protocol.GET_INV_OK:
			
			int size = readInt();
			int i;
			System.out.println(size);
			for (i=0;i<size;i++)
			{
				String str = readString();
				Category cat = Category.valueOf(str);
				System.out.println(cat);
				
				String s = readString();
				System.out.println(s);
				
				String s2 = "./res/"+readString()+".png";
				System.out.println(s2);
				
				int er = readInt();
				System.out.println(er);
				
				boolean b = readBoolean();
				System.out.println(b);
				
				int idf = readInt();
				System.out.println(idf);
				
				long llglg = readLong();
				System.out.println(llglg);
				
				Product p = new Product(cat, s, s2, er, b, idf);
				System.out.println(p);

				boolean e = cp.add(p);
				System.out.println(e);
			}

		break;
		
		case Protocol.GET_INV_KO:
			break;
			
		}
	}

}
