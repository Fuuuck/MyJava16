package com.demo.TCPchat02;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流的方法
 * @author a c e r
 *
 */
public class closeutil {
		public static void closeall(Closeable... io) {
			for(Closeable temp:io){
				if(null!=temp){
					try {
						temp.close();
					} catch (IOException e) {
						
					}
				}
			}
		}
}
