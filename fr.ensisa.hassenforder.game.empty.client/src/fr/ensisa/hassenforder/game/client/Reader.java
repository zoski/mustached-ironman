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
	byte[] imgBytes;
	
	Collection<Product> inventory = new ArrayList<Product>();
	Collection<Product> shop = new ArrayList<Product>();
	
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
			for (int i=0;i<size;i++) {
				String catStr = readString();
				Category category = Category.valueOf(catStr);
				String itemName = readString();
				
				String imgPath = readString();
				int duration = readInt();
				boolean stackable = readBoolean();
				int quantity = readInt();
				long time = readLong();
				Product p = new Product(category, itemName, imgPath, duration, stackable, quantity, time);
				System.out.println(p.toString());
				inventory.add(p);
			}
			System.out.println("Inventaire envoyé");
		break;
		
		case Protocol.GET_INV_KO:
			break;
			
		case Protocol.GET_SHOP_KO:
			break;
			
		case Protocol.GET_SHOP_OK:
			int sizeShop = readInt();
			for (int i=0;i<sizeShop;i++) {
				String catStr = readString();
				Category category = Category.valueOf(catStr);
				String itemName = readString();
				
				String imgPath = readString();
			
				int duration = readInt();
				boolean stackable = readBoolean();
				int quantity = readInt();
				long time = readLong();
				Product p = new Product(category, itemName, imgPath, duration, stackable, quantity, time);
				System.out.println(p.toString());
				shop.add(p);
			}
			System.out.println("Shop envoyé");
			break;
			
		case Protocol.GET_IMG_OK:
			image = readString(); 
			long length = readLong();
			imgBytes = readBytes(length);
			
		case Protocol.REFRESH_SHOP_KO:
			break;
			
		case Protocol.REFRESH_SHOP_OK:
			break;
			
		case Protocol.SELL_SHOP_OK:
			break;
			
		case Protocol.SELL_SHOP_KO:
			break;
			
		case Protocol.BUY_SHOP_OK:
			break;
			
		case Protocol.BUY_SHOP_KO:
			break;
			
		case Protocol.CLEAR_OK:
			break;
			
		case Protocol.CLEAR_KO:
			break;
			
		case Protocol.CONSUME_OK:
			break;
			
		case Protocol.CONSUME_KO:
			break;
			
		}
	}

}
