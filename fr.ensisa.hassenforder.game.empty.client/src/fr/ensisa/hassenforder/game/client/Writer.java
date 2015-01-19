package fr.ensisa.hassenforder.game.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void reqConnect(String username, String password) {
		writeInt(Protocol.CONNECT);
		writeString(username);
		writeString(password);
	}
	
	public void reqDisconnect(long token,String name)
	{
		writeInt(Protocol.DISCONNECT);
		writeLong(token);
		writeString(name);
	}

	public void reqCash(String name, long token, int amount) {
		writeInt(Protocol.ADD_CASH);
		writeString(name);
		writeLong(token);
		writeInt(amount);
		
	}
	
	public void reqStat(String name, long token) {
		writeInt(Protocol.GET_STATS);
		writeString(name);
		writeLong(token);
		
	}
	
	public void reqInv(String name, long token) {
		writeInt(Protocol.GET_INV);
		writeString(name);
		writeLong(token);
		
	}
	
	public void reqShop(String name, long token) {
		writeInt(Protocol.GET_SHOP);
		writeString(name);
		writeLong(token);
		
	}
	
	public void reqRefresh(String name, long token) {
		writeInt(Protocol.REFRESH_SHOP);
		writeString(name);
		writeLong(token);
		
	}

	
	public void reqSell(String name, long token, String productName) {
		writeInt(Protocol.SELL_SHOP);
		writeString(name);
		writeLong(token);
		writeString(productName);
		
	}
	
	
	public void reqBuy(String name, long token, String productName) {
		writeInt(Protocol.BUY_SHOP);
		writeString(name);
		writeLong(token);
		writeString(productName);
		
	}
	
	
	public void reqClear(String name, long token) {
		writeInt(Protocol.CLEAR);
		writeString(name);
		writeLong(token);
		
	}
	
	
	public void reqConsume(String name, long token) {
		writeInt(Protocol.CONSUME);
		writeString(name);
		writeLong(token);
		
	}
	
}