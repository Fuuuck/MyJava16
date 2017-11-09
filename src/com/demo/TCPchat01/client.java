package com.demo.TCPchat01;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost",9999);
		new Thread(new send(client)).start();//一条路径
		new Thread(new receive(client)).start();//另一条路径
	}

}
