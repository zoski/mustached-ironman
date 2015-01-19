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
	


	public static final int REFRESH_SHOP = 40;
	
	public static final int REFRESH_SHOP_OK = 41;
	
	public static final int REFRESH_SHOP_KO = 42;
	
	
	public static final int SELL_SHOP = 45;
	
	public static final int SELL_SHOP_OK = 46;
	
	public static final int SELL_SHOP_KO = 47;
	
	
	public static final int BUY_SHOP = 50;
	
	public static final int BUY_SHOP_OK = 51;
	
	public static final int BUY_SHOP_KO = 52;
	
	
	public static final int CLEAR = 55;
	
	public static final int CLEAR_OK = 56;
	
	public static final int CLEAR_KO = 57;
	
	
	public static final int CONSUME = 60;
	
	public static final int CONSUME_OK = 61;
	
	public static final int CONSUME_KO = 62;
	
	
	public static final int GET_IMG = 70;
	
	public static final int GET_IMG_OK = 71;
	
	public static final int GET_IMG_KO = 70;
	
}

