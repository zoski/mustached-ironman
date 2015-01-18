package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;




import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

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
		writeInt(Protocol.GET_STATS_OK);		//discriminant
		writeInt(a.getCash());					//cash
		writeString(a.getImage());				//nom image
		byte[] img = FileHelper.readContent("./res/"+a.getImage()+".png");				
		writeLong(img.length);					//taille image
		writeBytes(img);			//byte[] représentant l'image
												
		System.out.println("Stats success");
	}
	
	public void statsKO() {
		writeInt(Protocol.GET_STATS_KO);
		System.out.println("Stats fail");
	}
	
}

