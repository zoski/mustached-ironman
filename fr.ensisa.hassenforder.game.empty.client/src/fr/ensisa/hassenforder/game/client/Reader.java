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
			length = readLong();
			imgBytes = readBytes(length);
			break;
			
		case Protocol.GET_STATS_KO:
			break;
			
		case Protocol.GET_INV_OK:
			
			int size = readInt();
			System.out.println("Nombre d'élement dans l'inventaire " +size);
			for (int i=0;i<size;i++)
			{
				String catStr = readString();
				Category category = Category.valueOf(catStr);
				String itemName = readString();
				
				String imgPath = "./res/"+readString()+".png";
				length = readLong();
				imgBytes = readBytes(length);
				FileHelper.writeContent(imgPath, imgBytes);		// Sauvegarde l'image -> fonctinne
				
				int duration = readInt();
				boolean stackable = readBoolean();
				int quantity = readInt();
				long time = readLong();
				Product p = new Product(category, itemName, imgPath, duration, stackable, quantity, time);
				System.out.println(p.toString());
				inventory.add(p);
				
			}
			System.out.println("Tout à été envoyé");

		break;
		
		case Protocol.GET_INV_KO:
			break;
			
			
		case Protocol.GET_SHOP_KO:
			break;
			
		case Protocol.GET_SHOP_OK:
			
			int sizeShop = readInt();
			System.out.println("Nombre d'élement dans le shop " +sizeShop);
			for (int i=0;i<sizeShop;i++)
			{
				String catStr = readString();
				Category category = Category.valueOf(catStr);
				String itemName = readString();
				
				String imgPath = "./res/"+readString()+".png";
				length = readLong();
				imgBytes = readBytes(length);
				FileHelper.writeContent(imgPath, imgBytes);		// Sauvegarde l'image -> fonctinne
				
				int duration = readInt();
				boolean stackable = readBoolean();
				int quantity = readInt();
				long time = readLong();
				Product p = new Product(category, itemName, imgPath, duration, stackable, quantity, time);
				System.out.println(p.toString());
				shop.add(p);
				
			}
			System.out.println("Tout à été envoyé");
			
			
			break;
			
		}
	}

}
