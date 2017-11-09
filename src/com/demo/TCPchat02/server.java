package com.demo.TCPchat02;

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
	 * 加入名称
	 * @author a c e r
	 *
	 */
	class mychannel implements Runnable{
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isrunning=true;
		private String name;
		public mychannel(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				this.name=dis.readUTF();
				send("系统消息：欢迎进入聊天室");
				sendothers(this.name+"进入了聊天室",true);
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
		private void sendothers(String msg,boolean sys){
			if(msg.startsWith("@")&&msg.indexOf(":")>-1){
				String name=msg.substring(1, msg.indexOf(":"));
				String content = msg.substring(msg.indexOf(":")+1);
				for(mychannel others:all){
					if(others.name.equals(name)){
						others.send(this.name+" 对您悄悄地说："+content);
					}
				}
			}else{
				for(mychannel others:all){
					if(others==this){
					continue;
					}
					if(sys==true){
						//发送给其它客户端
						others.send("系统消息:"+msg);
					}else{
						others.send(this.name+":"+msg);
					}
				
					}
				}
		}
		@Override
		
		public void run() {
			while(isrunning){
				sendothers(receive(),false);
			}
		}
		
	}
}


