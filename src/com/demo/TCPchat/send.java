package com.demo.TCPchat;
/**
 * 发送数据线程
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class send implements Runnable{
	private BufferedReader console;
	private DataOutputStream dos;
	private boolean isrunning = true;
	public send() {
		console = new BufferedReader(new InputStreamReader(System.in));
	}
	public send(Socket client) {
	    this();	
	    try {
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			isrunning=false;
			closeutil.closeall(dos,console);
		}
	}
	
	private String getmsgfromconsole(){
		try {
			return console.readLine();
		} catch (IOException e) {
			
		}
		return null;	
	}
	//发送数据
	public void send(){
			String msg = getmsgfromconsole();
			if(msg!=null&&!msg.equals("")){
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					isrunning = false;
					closeutil.closeall(dos,console);
				}
			}
	}
	@Override
	public void run() {
		while(isrunning){
			send();
		}	
	}

}
