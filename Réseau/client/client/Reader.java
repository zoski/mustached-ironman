package client;

import java.io.InputStream;

import network.BasicAbstractReader;
import network.Protocol;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:
			break;
		}
	}

}
