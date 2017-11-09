package com.demo.UDP;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 创建客户端+端口
 * 准备数据    double--字节数组  字节数组输出流
 * 打包（发送的端口及地点）
 * 发送
 * 释放
 * @author a c e r
 *
 */
public class myclient {
		public static void main(String[] args) throws IOException {
			DatagramSocket client =new DatagramSocket(6666);
			Double num = 889.12;
			byte[] data= convert(num);
			DatagramPacket packet=new  DatagramPacket(data,data.length,new InetSocketAddress("localhost",8888));
			client.send(packet);
			client.close();
		}
		
		public static byte[] convert(double num) throws IOException{
			byte[] data = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeDouble(num);
			dos.flush();
			data = bos.toByteArray();
			dos.close();
			return data;
		}
}
