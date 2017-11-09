package com.demo.UDP;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 服务端
 * 创建服务端+端口
 * 准备接受容器
 * 封装成包
 * 接受数据
 * 分析数据     字节数组---double
 * @author a c e r
 *
 */
public class myserver {
	public static void main(String[] args) throws IOException {
		DatagramSocket server =new DatagramSocket(8888);
		byte[] container = new byte[1024];
		DatagramPacket packet = new DatagramPacket(container,container.length);
		server.receive(packet);
		double data =convert(packet.getData());
		System.out.println(data);
		server.close();
	}
	
	public static double convert(byte[] data) throws IOException{
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		double num=dis.readDouble();
		dis.close();
		return num;
	}
}
