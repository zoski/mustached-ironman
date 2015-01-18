package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Player;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class SessionClient {

	private Socket connection;
	private String name;
	private long id;

	public SessionClient(Socket connection) {
		this.connection = connection;
		this.name = null;
		this.id = 0;
	}

	public boolean connect(String username, String password) {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqConnect(username, password);
			writer.send();// on sépare l'envoie au cas ou le message n'est plus
							// envoyable. On à la possiblité d'envoyer

			reader.receive();

			if (reader.getType() == Protocol.CONNECT_OK) {
				id = reader.id;
				name = username;
				System.out.println("Connexion OK");
				System.out.println("id= " + id);
				System.out.println("name= " + name);
				return true;
			}

			if (reader.getType() == Protocol.CONNECT_KO) {
				id = 0;
				System.out.println("Connexion KO");
				return false;
			}

			return false;

		} catch (IOException e) {
			return false;
		}
	}

	public boolean disconnect() {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqDisconnect(id, name);
			writer.send();

			reader.receive();

			if (reader.getType() == Protocol.DISCONNECT_OK) {
				id = 0;
				return true;
			}

			if (reader.getType() == Protocol.DISCONNECT_KO) {
				return false;
			}

			return false;

		} catch (IOException e) {
			return false;
		}
	}

	public boolean addCash(int amount) {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqCash(name, id, amount);
			writer.send();

			reader.receive();
			System.out.println("cash");

			if (reader.getType() == Protocol.ADD_CASH_OK) {
				return true;
			}

			if (reader.getType() == Protocol.ADD_CASH_KO) {
				return false;
			}

			return false;

		} catch (IOException e) {
			return false;
		}
	}

	public boolean clearProducts() {
		try {
			if (true)
				throw new IOException("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean consumeProducts() {
		try {
			if (true)
				throw new IOException("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Player getStatistics() {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqStat(name, id);
			writer.send();

			reader.receive();

			if (reader.getType() == Protocol.GET_STATS_OK) {
				/* Sauvegarde de l'image */
				FileHelper.writeContent(getImage("./res/" + reader.image
						+ ".png"), reader.imgBytes);

				Player p = new Player(name, getImage("./res/" + reader.image
						+ ".png"), reader.cash);
				System.out.println(p.toString());
				return p;
			}

			if (reader.getType() == Protocol.GET_STATS_KO) {
				System.out.println("échec stats");
				return null;
			}

		} catch (IOException e) {
			return null;
		}
		return null;

	}

	public Collection<Product> getProducts() {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqInv(name, id);
			writer.send();

			reader.receive();
			System.out.println("Réception de l'inventaire");

			if (reader.getType() == Protocol.GET_INV_OK) {
				FileHelper.writeContent(getImage("./res/" + reader.image
						+ ".png"), reader.imgBytes);
				return reader.cp; // CREER LARRAY ICI ????????????????????
			}

			if (reader.getType() == Protocol.GET_INV_KO) {
				return null;
			}
			return null;

		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getShop() {
		try {
			if (true)
				throw new IOException("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean refreshShop() {
		try {
			if (true)
				throw new IOException("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean buyProduct(String productName) {
		try {
			if (true)
				throw new IOException("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean sellProduct(String productName) {
		try {
			if (true)
				throw new IOException("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public String getImage(String imageName) {

		return imageName;
	}
}
