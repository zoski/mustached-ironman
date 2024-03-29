package fr.ensisa.hassenforder.game.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.User;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

/**
 * 
 * @author michel Gère la session coté serveur
 * 
 */
public class SessionServer {

	private Socket connection;
	private Document document; // le modèle métier

	public SessionServer(Document document, Socket connection) {
		this.setDocument(document);
		this.connection = connection;
	}

	/**
	 * définit les différent cas en fonction du protocole
	 * 
	 * @return
	 */
	public boolean operate() {
		try {
			Writer writer = new Writer(connection.getOutputStream());
			Reader reader = new Reader(connection.getInputStream());
			reader.receive();
			switch (reader.getType()) {
			
			case 0:
				return false; // socket closed
				
			case Protocol.CONNECT: 
				User user = document.connect(reader.getUsername(),
						reader.getPassword());
				if (user == null) {
					writer.respKO();
				} else {
					writer.respOK(user.getId()); // on envoie en paramétre ID (le token valide ou non..)
				}
				break;

			case Protocol.DISCONNECT:
				Boolean conn = document.disconnect(reader.getUsername(), reader.getId());
				if(conn == true) {
					writer.decoOK();
				} else {
					writer.decoKO();
				}
				break;
				
			case Protocol.ADD_CASH:
				boolean bool = document.addCash(reader.getUsername(), reader.getId(), reader.getCash());
				System.out.println(bool);
				if(bool == true) {
					writer.cashOK();
				} else {
					writer.cashKO();
				}
				break;
				
				
			case Protocol.GET_STATS:
				Account a = document.getStatistics(reader.getUsername(), reader.getId());
				System.out.println(a);
				if ( a != null){
					writer.statsOK(a);
				} else {
					writer.statsKO();
				}
				break;
				
				
			case Protocol.GET_INV:
				Collection<Product> cp = document.getProducts(reader.getUsername(), reader.getId());
				System.out.println(cp);
				if ( cp != null){
					writer.invOK(cp);
				} else {
					writer.invKO();
				}
				break;
				
			case Protocol.GET_SHOP:
				Collection<Product> shop = document.getShop(reader.getUsername(), reader.getId());
				System.out.println(shop);
				if ( shop != null){
					writer.shopOK(shop);
				} else {
					writer.shopKO();
				}
				break;
				
			case Protocol.REFRESH_SHOP:
				boolean b = document.refreshShop(reader.getUsername(), reader.getId());
				System.out.println(b);
				if ( b == true){
					writer.refreshOK();
				} else {
					writer.refreshKO();
				}
				break;
				
			case Protocol.SELL_SHOP:
				boolean b2 = document.sellProduct(reader.getUsername(), reader.getId(), reader.getItem());
				System.out.println(b2);
				if(b2 == true) {
					writer.sellOK();
				} else {
					writer.sellKO();
				}
				break;
				
			case Protocol.BUY_SHOP:
				boolean b3 = document.buyProduct(reader.getUsername(), reader.getId(), reader.getItem());
				System.out.println(b3);
				if(b3 == true) {
					writer.buyOK();
				} else {
					writer.buyKO();
				}
				break;
				
			case Protocol.CLEAR:
				boolean b4 = document.clearProducts(reader.getUsername(), reader.getId());
				System.out.println(b4);
				if(b4 == true) {
					writer.clearOK();
				} else {
					writer.clearKO();
				}
				break;
				
				
			case Protocol.CONSUME:
				boolean b5 = document.consumeProducts(reader.getUsername(), reader.getId());
				System.out.println(b5);
				if(b5 == true) {
					writer.consumeOK();
				} else {
					writer.consumeKO();
				}
				break;
				
			case Protocol.GET_IMG:
				writer.getImageOK(reader.getImage());
				break;
				
			case -1:
				break;
				
				
			default:
				return false; // connection jammed
				
			}
			writer.send();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
