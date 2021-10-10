package socketprogramming;

import java.net.*;
import java.io.*;
import java.util.*;
/**
* �޿�B�z�u�{
*/
public class TCPThread extends Thread {
	Socket s;
	static Random r = new Random();
	public TCPThread(Socket s){
		this.s = s;
		start(); //�Ұʽu�{
	}
	public void run(){
		//�ͦ��@��[0,50]���H����
		int randomNumber = Math.abs(r.nextInt() % 51);
		//�Τ�q������
		int guessNumber = 0;
		InputStream is = null;
		OutputStream os = null;
		byte[] data = new byte[2];
		try{
			//��o��J�y
			is = s.getInputStream();
			//��o��X�y
			os = s.getOutputStream();
			while(true){ //�h���B�z
				//Ū���Ȥ�ݵo�e���ƾ�
				byte[] b = new byte[1024];
				int n = is.read(b);
				String send = new String(b,0,n);
				//�����P�O
				if(send.equals("quit")){
					break;
				}
				//�ѪR�B�P�_
				try{
					int num = Integer.parseInt(send);
					//�B�z
					guessNumber++; //�q�����ƼW�[1
					data[1] = (byte)guessNumber;
					//�P�_
					if(num > randomNumber){
						data[0] = 1;
					}else if(num < randomNumber){
						data[0] = 2;
					}else{
						data[0] = 0;
						//�p�G�q��
						guessNumber = 0; //�M�s
						randomNumber = Math.abs(r.nextInt() % 51);
					}
					//���X���Ȥ��
					os.write(data);
				}catch(Exception e){ //�ƾڮ榡���~
					data[0] = 3;
					data[1] = (byte)guessNumber;
					os.write(data); //�o�e���~����
					break;
				}
				os.flush(); //�j��o�e
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