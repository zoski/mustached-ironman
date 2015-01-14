package client;

import game.model.Player;
import game.model.Product;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import network.FileHelper;
import network.Protocol;

public class SessionClient {

	private Socket connection;
    private String name;
    private long id;
	
	public SessionClient (Socket connection) {
		this.connection = connection;
		this.name = null;
		this.id = 0;
	}

	public boolean connect (String username, String password) {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());
			
			writer.reqConnect(username, password);
			writer.send();//on sépare l'envoie au cas ou le message n'est plus envoyable. On à la possiblité d'envoyer
			
			reader.receive();
			
			
			return true;
			
		} catch (IOException e) {
			return false;
		}
	}

	public boolean disconnect () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean addCash (int amount) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean clearProducts () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean consumeProducts () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Player getStatistics () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getProducts () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getShop () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean refreshShop () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean buyProduct (String productName) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean sellProduct (String productName) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public String getImage (String imageName) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return "";
		} catch (IOException e) {
			return null;
		}
	}
}
