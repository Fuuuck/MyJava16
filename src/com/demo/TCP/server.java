package com.demo.TCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器  指定端口
 * 接收客户端的连接  阻塞式的
 * 发送数据+接收数据
 * @author a c e r
 *
 */
public class server {
		public static void main(String[] args) throws IOException {
			ServerSocket server = new ServerSocket(8888);
			Socket socket=server.accept();
			System.out.println("一个客户端建立连接");
			String msg="欢迎使用";
			//方法一
//			BufferedWriter bw= new BufferedWriter(
//					new OutputStreamWriter(
//							socket.getOutputStream())
//					);
//			bw.write(msg);
//			bw.newLine();
//			bw.flush();
			//方法二
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(msg);
			dos.flush();
	
		}
}
