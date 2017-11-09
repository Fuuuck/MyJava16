package com.demo.TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 创建客户端  必须指定服务器+端口
 * 接收数据+发送数据
 * @author a c e r
 *
 */
public class client {
		public static void main(String[] args) throws UnknownHostException, IOException {
			Socket client= new Socket("localhost",8888);
			//方法一
//			BufferedReader br=new BufferedReader(
//					new InputStreamReader(
//							client.getInputStream())
//					);
//			String echo = br.readLine();
//			System.out.println(echo);
			//方法二
			DataInputStream dis = new DataInputStream(client.getInputStream());
			System.out.println(dis.readUTF());
		
		}
}
