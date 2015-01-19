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
				System.out.println("id= "+ id);
				System.out.println("name= "+ name);
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
			
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqClear(name, id);
			writer.send();

			reader.receive();
			System.out.println("clear");

			if (reader.getType() == Protocol.CLEAR_OK) {
				return true;
			}
			if (reader.getType() == Protocol.CLEAR_KO) {
				return false;
			}
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean consumeProducts() {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqConsume(name, id);
			writer.send();

			reader.receive();
			System.out.println("consume");

			if (reader.getType() == Protocol.CONSUME_OK) {
				return true;
			}
			if (reader.getType() == Protocol.CONSUME_KO) {
				return false;
			}
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public Player getStatistics () {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());
			
			writer.reqStat(name,id);
			writer.send();
			
			reader.receive();
			
			if ( reader.getType() == Protocol.GET_STATS_OK ) {
				Player p = new Player(name,getImage(reader.image) , reader.cash);
				System.out.println(p.toString());
				return p;
			}
			
			if ( reader.getType() == Protocol.GET_STATS_KO ) {
				System.out.println("échec stats");
				return null;
			}
		}
		catch (IOException e) {
			return null;
		}
		return null;
		
	}

	public Collection<Product> getProducts() {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());
			
			writer.reqInv(name,id);
			writer.send();
			
			reader.receive();
			System.out.println("Réception de l'inventaire");
			
			if ( reader.getType() == Protocol.GET_INV_OK ) {
				return reader.inventory; 
			}
			if ( reader.getType() == Protocol.GET_INV_KO ) {
				return null;
			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getShop() {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());
			
			writer.reqShop(name,id);
			writer.send();
			
			reader.receive();
			System.out.println("Réception du shop");
			
			if ( reader.getType() == Protocol.GET_SHOP_OK ) {
				return reader.shop; 
			}
			if ( reader.getType() == Protocol.GET_SHOP_KO )	{
				return null;
			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean refreshShop() {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());
			
			writer.reqRefresh(name,id);
			writer.send();
			
			reader.receive();
			System.out.println("Refresh du shop");
			
			if ( reader.getType() == Protocol.REFRESH_SHOP_OK ) {
				System.out.println("come here nigaga");
				return true; 
			}
			
			if ( reader.getType() == Protocol.REFRESH_SHOP_KO )
			{
				return false;
			}
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean buyProduct(String productName) {
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqBuy(name, id, productName);
			writer.send();

			reader.receive();
			System.out.println("buy");

			if (reader.getType() == Protocol.BUY_SHOP_OK) {
				return true;
			}

			if (reader.getType() == Protocol.BUY_SHOP_KO) {
				return false;
			}
			return false;
			
			
		} catch (IOException e) {
			return false;
		}
	}

	public boolean sellProduct(String productName) {
		try {
			
			
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());

			writer.reqSell(name, id, productName);
			writer.send();

			reader.receive();
			System.out.println("sell");

			if (reader.getType() == Protocol.SELL_SHOP_OK) {
				return true;
			}

			if (reader.getType() == Protocol.SELL_SHOP_KO) {
				return false;
			}
			return false;
			
			
		} catch (IOException e) {
			return false;
		}
	}

	public String getImage(String imageName) {	//retourne le nom du fichier
		// a pour vocation de télécharger l'image depuis le réseau et de la sauvegarder
		try {
			Reader reader = new Reader(connection.getInputStream());
			Writer writer = new Writer(connection.getOutputStream());
			
			writer.reqImg(imageName);
			writer.send();
			
			reader.receive();
			System.out.println("Réception d'une image");
			
			if ( reader.getType() == Protocol.GET_IMG_OK ) {
				System.out.println("Sauvegarde de l'image" + imageName);
				FileHelper.writeContent(imageName, reader.imgBytes); //"./res/" + imageName + ".png"
				return imageName;
			}
			if ( reader.getType() == Protocol.GET_IMG_KO ) {
				System.out.println("Echec du chargement de l'image");
				return null;
			}
			return null;
			
		} catch(IOException e) {
			
		}
		
		
		return imageName;
	}
}
