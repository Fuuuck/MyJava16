package com.demo.TCPchat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class receive implements Runnable{
	private DataInputStream dis;
	private boolean isrunning = true;
	public receive() {
	}
	public receive(Socket client) {
		try {
			dis = new  DataInputStream(client.getInputStream());
		} catch (IOException e) {
			isrunning = false;
			closeutil.closeall(dis);
		}
	}
	public String receive(){
		String msg="";
		try {
			msg=dis.readUTF();
		} catch (IOException e) {
			isrunning = false;
			closeutil.closeall(dis);
		}
		return msg;
	}
	@Override
	public void run() {
		while(isrunning){
			 System.out.println(receive());
		}
	}

}
