package socketprogramming;

import java.net.*;
import java.io.*;
import java.util.*;
/**
* 邏輯處理線程
*/
public class TCPThread extends Thread {
	Socket s;
	static Random r = new Random();
	public TCPThread(Socket s){
		this.s = s;
		start(); //啟動線程
	}
	public void run(){
		//生成一個[0,50]的隨機數
		int randomNumber = Math.abs(r.nextInt() % 51);
		//用戶猜的次數
		int guessNumber = 0;
		InputStream is = null;
		OutputStream os = null;
		byte[] data = new byte[2];
		try{
			//獲得輸入流
			is = s.getInputStream();
			//獲得輸出流
			os = s.getOutputStream();
			while(true){ //多次處理
				//讀取客戶端發送的數據
				byte[] b = new byte[1024];
				int n = is.read(b);
				String send = new String(b,0,n);
				//結束判別
				if(send.equals("quit")){
					break;
				}
				//解析、判斷
				try{
					int num = Integer.parseInt(send);
					//處理
					guessNumber++; //猜的次數增加1
					data[1] = (byte)guessNumber;
					//判斷
					if(num > randomNumber){
						data[0] = 1;
					}else if(num < randomNumber){
						data[0] = 2;
					}else{
						data[0] = 0;
						//如果猜對
						guessNumber = 0; //清零
						randomNumber = Math.abs(r.nextInt() % 51);
					}
					//反饋給客戶端
					os.write(data);
				}catch(Exception e){ //數據格式錯誤
					data[0] = 3;
					data[1] = (byte)guessNumber;
					os.write(data); //發送錯誤標識
					break;
				}
				os.flush(); //強制發送
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				is.close();
				os.close();
				s.close();
			}catch(Exception e){}
		}
	}
}