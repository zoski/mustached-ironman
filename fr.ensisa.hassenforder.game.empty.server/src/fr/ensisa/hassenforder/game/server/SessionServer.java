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
				
				
			case Protocol.GET_INV:
				Collection<Product> cp = document.getProducts(reader.getUsername(), reader.getId());
				System.out.println(cp);
				if ( cp != null){
					writer.invOK(cp);
				} else {
					writer.invKO();
				}
				
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