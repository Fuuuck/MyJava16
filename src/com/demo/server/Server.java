package com.demo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器并启动
 * @author a c e r
 *
 */
public class Server {
	private ServerSocket server;
	public static void main(String[] args) {
		Server server = new Server();
		server.start(); 
	}
	/**
	 * 开始方法
	 */ 
	public void start(){
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 接收方法
	 */
	private void receive(){
		try {
			Socket client = server.accept();
			StringBuilder sb = new StringBuilder();
			String msg = null;//接收客户端的请求信息
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while((msg = br.readLine()).length()>0){
				sb.append(msg);
				sb.append("\r\n");
				if(msg==null){
					break;
				}
			}
			String requestInfo = sb.toString().trim();
			System.out.println(requestInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 停止方法
	 */
	public void stop(){
		
	}

}
