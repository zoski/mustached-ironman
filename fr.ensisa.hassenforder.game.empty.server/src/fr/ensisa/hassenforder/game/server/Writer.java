package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;



import java.util.Iterator;



import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.game.model.Product;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void respOK(long id) {
		
		writeInt(Protocol.CONNECT_OK);
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
	
	
	public void statsOK(Account a) { //yolo
		writeInt(Protocol.GET_STATS_OK);		//discriminant
		writeInt(a.getCash());					//cash
		writeString(a.getImage());				//nom image
												
		System.out.println("Stats success");
	}
		
	public void invOK(Collection<Product> cp) {
		writeInt(Protocol.GET_INV_OK);
		
		Iterator<Product> it = cp.iterator();
		int nb = cp.size();
		writeInt(nb);

		while(it.hasNext())
		{
			Product p = it.next();
			writeString(p.getCategory().toString()); //			private Category category;
			writeString(p.getName()); 				 //			private String name;
			writeString(p.getImage());				 // 		private String image;
			writeInt(p.getDuration());			     //         private int duration;
			writeBoolean(p.isStackable());			 //			private boolean stackable;
			writeInt(p.getCount());					 //			private int count;
			writeLong(p.getTime());			     	 //		 	private long time;
		}
		System.out.println("Inv Suck Sex");
	}	
	
	public void shopOK(Collection<Product> shop) {
		writeInt(Protocol.GET_SHOP_OK);
		
		Iterator<Product> it = shop.iterator();
		int nb = shop.size();
		writeInt(nb);

		while(it.hasNext())
		{
			Product p = it.next();
			writeString(p.getCategory().toString()); //			private Category category;
			writeString(p.getName()); 				 //			private String name;
			writeString(p.getImage());				 // 		private String image;
			writeInt(p.getDuration());			     //         private int duration;
			writeBoolean(p.isStackable());			 //			private boolean stackable;
			writeInt(p.getCount());					 //			private int count;
			writeLong(p.getTime());			     	 //		 	private long time;
		}
		System.out.println("shop success");
	}

	public void getImageOK(String imageName) {
		System.out.println("Ecriture de l'image");
		writeInt(Protocol.GET_IMG_OK);
		writeString(imageName);
		System.out.println("Image Name :" + "./res/"+imageName+".png");		//"./res/"+imageName+".png"
		byte[] img = FileHelper.readContent("./res/"+imageName+".png");
		writeLong(FileHelper.getFileSize("./res/"+imageName+".png"));				//		taille image
		writeBytes(img);															//		byte[] représentant l'image
		
		System.out.println("Get Image OKay");
	}
	
	public void refreshKO() {
		writeInt(Protocol.REFRESH_SHOP_KO);
		System.out.println("refresh fail");
	}
	
	public void refreshOK() {
		writeInt(Protocol.REFRESH_SHOP_OK);
		System.out.println("refresh gud");
	}
	
	public void sellOK() {
		writeInt(Protocol.SELL_SHOP_OK);
		System.out.println("sold gud");
	}
	
	public void sellKO() {
		writeInt(Protocol.SELL_SHOP_KO);
		System.out.println("sell failure");
	}
	
	
	public void buyOK() {
		writeInt(Protocol.BUY_SHOP_OK);
		System.out.println("buy ok");
	}
	
	public void buyKO() {
		writeInt(Protocol.BUY_SHOP_KO);
		System.out.println("buy failure");
	}
	
	
	public void clearOK() {
		writeInt(Protocol.CLEAR_OK);
		System.out.println("clear okaaaay");
	}
	
	public void clearKO() {
		writeInt(Protocol.CLEAR_KO);
		System.out.println("clear koooo");
	}
	
	
	public void consumeOK() {
		writeInt(Protocol.CONSUME_OK);
		System.out.println("consume okaaaay");
	}
	
	public void consumeKO() {
		writeInt(Protocol.CONSUME_KO);
		System.out.println("consume kookoko");
	}

	public void statsKO() {
		writeInt(Protocol.GET_STATS_KO);
		System.out.println("Stats fail");
	}
	
	public void invKO() {
		writeInt(Protocol.GET_INV_KO);
		System.out.println("Inv fail");
	}
	
	public void shopKO() {
		writeInt(Protocol.GET_SHOP_KO);
		System.out.println("shop fail");
	}
}

