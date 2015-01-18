package fr.ensisa.hassenforder.network;

public interface Protocol {

	static final public int GAME_PORT_ID = 4269;

	public static final Object EXIT_TEXT = "exit";
	
	
	static final public int CONNECT = 1 ;	//représente le discriminant pr la connexion

	public static final int CONNECT_OK = 10;

	public static final int CONNECT_KO = 11;
	

	public static final int DISCONNECT = 14;
	
	public static final int DISCONNECT_OK = 15;
	
	public static final int DISCONNECT_KO = 16;

	
	public static final int ADD_CASH = 20;
	
	public static final int ADD_CASH_OK = 21;
	
	public static final int ADD_CASH_KO = 22;
	
	
	public static final int GET_STATS = 25;

	public static final int GET_STATS_OK = 26;

	public static final int GET_STATS_KO = 27;
	
	
	public static final int GET_INV = 30;
	
	public static final int GET_INV_OK = 31;
	
	public static final int GET_INV_KO = 32;


	public static final int GET_SHOP = 35;
	
	public static final int GET_SHOP_OK = 36;
	
	public static final int GET_SHOP_KO = 37;
	
}
