package server;

import game.model.Product;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import network.FileHelper;
import network.Protocol;
import model.Account;
import model.User;

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
					writer.send();
				} else {
					writer.respOK();
					writer.send();
				}
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
