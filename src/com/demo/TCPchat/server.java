package com.demo.TCPchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9999);
		Socket client = server.accept();
		
		
		DataInputStream dis = new DataInputStream(client.getInputStream());
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		while(true){
		String msg = dis.readUTF();
		System.out.println(msg);
		dos.writeUTF("服务器---"+msg);
		dos.flush();
		}
	}

}
