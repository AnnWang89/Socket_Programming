package socketprogramming;
import java.net.*;
/**
* TCP�s���覡�����A����
* ��{�\��G�����Ȥ�ݪ��ƾ�,�P�_�Ʀr���Y
*/
public class TCPServer {
	public static void main(String[] args){
		try{
			//��ť��
			ServerSocket ss = new ServerSocket(10001);
			System.out.println("���A���w�ҰʡG");
			//�޿�B�z
			while(true){
				//��o�s��
				Socket s = ss.accept();
				//�Ұʽu�{�B�z
				new TCPThread(s);
			}
		}catch(Exception e){
			e.printStackTrace();

		}
	}
}

