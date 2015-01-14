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
 * @author michel
 * Gère la session coté serveur
 * 
 */
public class SessionServer {

	private Socket connection;
	private Document document;	//le modèle métier

	public SessionServer(Document document, Socket connection) {
		this.setDocument(document);
		this.connection = connection;
	}

	public boolean operate() {
		try {
			Writer writer = new Writer(connection.getOutputStream());
			Reader reader = new Reader(connection.getInputStream());
			reader.receive();
			switch (reader.getType()) {
			case 0:
				return false; // socket closed
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
