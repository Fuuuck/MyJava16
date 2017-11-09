package com.demo.TCPchat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {
	private List<mychannel> all = new ArrayList<mychannel>();
	public static void main(String[] args) throws IOException {
		new server().start();
	}
	public void start() throws IOException{
		ServerSocket server = new ServerSocket(9999);
		while(true){
		Socket client = server.accept();
		mychannel channel = new mychannel(client);
		all.add(channel);
		new Thread(channel).start();
		}
	}
	/**
	 * 一个客户端一条道路
	 * 输入流    输出流  用来接收数据 发送数据
	 * @author a c e r
	 *
	 */
	class mychannel implements Runnable{
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isrunning=true;
		public mychannel(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				isrunning = false;
				closeutil.closeall(dis,dos);
			}
			
		} 
		/**
		 * 读取数据
		 * @return
		 */
		private String receive(){
			String msg = "";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				isrunning = false;
				closeutil.closeall(dis);
				all.remove(this);
			}
			return msg;
		}
		/**
		 * 发送数据
		 */
		private void send(String msg){
			if(msg==null||msg.equals("")){
				return ;
			}
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isrunning = false;
				closeutil.closeall(dos);
				all.remove(this);
			}
			
	}
		
		/**
		 * 发送给其它客户端
		 */
		private void sendothers(){
			String msg = receive();
			for(mychannel others:all){
				if(others==this){
					continue;
				}
				//发送给其它客户端
				others.send(msg);
			}
		}
		@Override
		
		public void run() {
			while(isrunning){
				sendothers();
			}
		}
		
	}
}


