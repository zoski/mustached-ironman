package fr.ensisa.hassenforder.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BasicAbstractReader {

	protected DataInputStream inputStream;
	protected int type;

	public BasicAbstractReader(InputStream inputStream2) {
		this.inputStream = new DataInputStream (inputStream2);
	}

	protected boolean readBoolean() {
		try {
			int x = inputStream.readInt();
			if (x != 0) return true;
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	protected short readShort () {
		try {
			return inputStream.readShort();
		} catch (IOException e) {
			return 0;
		}
	}

	protected int readInt() {
		try {
			return inputStream.readInt();
		} catch (IOException e) {
			return 0;
		}
	}

	protected long readLong() {
		try {
			return inputStream.readLong();
		} catch (IOException e) {
			return 0;
		}
	}

	protected String readString() {
		try {
			return inputStream.readUTF();
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * Permet de lire un tableau de byte depuis l'inputStream
	 * @param length	: taille du tableau de byte à lire
	 * @return			: un tableau de byte 
	 */
	protected byte[] readBytes(long length) {
		try { 
			byte[] readedBytes = new byte[(int) length];
			for(int i=0; i<length ; i++) {
				readedBytes[i]=inputStream.readByte();
			}
			return readedBytes;
		} catch (IOException e) {
			System.out.println("Lecture du tableau de byte impossible");
			return null;
		}
	}

	public int getType() {
		return type;
	}

}