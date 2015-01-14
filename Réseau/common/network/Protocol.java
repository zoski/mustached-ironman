package network;

public interface Protocol {

	static final public int GAME_PORT_ID = 4269;

	public static final Object EXIT_TEXT = "exit";
	
	static final public int CONNECT = 1 ;	//représente le discriminant pr la connexion

	public static final int CONNECT_OK = 10;

	public static final int CONNECT_KO = 11;

	public static final int DISCONNECT = 14;

}
