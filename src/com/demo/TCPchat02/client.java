package com.demo.TCPchat02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("请输入名称：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		Socket client = new Socket("localhost",9999);
		new Thread(new send(client,name)).start();//一条路径
		new Thread(new receive(client)).start();//另一条路径
	}

}
