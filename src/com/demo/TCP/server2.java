package com.demo.TCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收多个客户端
 * @author a c e r
 *
 */
public class server2 {
		public static void main(String[] args) throws IOException {
			ServerSocket server = new ServerSocket(8888);
			while(true){
			Socket socket=server.accept();
			System.out.println("一个客户端建立连接");
			String msg="欢迎使用";
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(msg);
			dos.flush();
		}
		}
}
