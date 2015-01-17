package server;

import game.model.Category;
import game.model.Product;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

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
	
	
	public void statsOK(Account a) { //yolo
		writeInt(Protocol.GET_STATS_OK);
		writeInt(a.getCash());
		writeString(a.getImage());
		System.out.println("Stats success");
	}
	
	public void statsKO() {
		writeInt(Protocol.GET_STATS_KO);
		System.out.println("Stats fail");
	}
	
	
	public void invKO() {
		writeInt(Protocol.GET_INV_KO);
		System.out.println("Inv fail");
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
			writeLong(p.getTime());			     	 //		 	private long time;
			writeBoolean(p.isStackable());			 //			private boolean stackable;
			writeInt(p.getCount());					 //			private int count;
			nb++;
		}
		System.out.println("Inv Suck Sex");
	}
}
