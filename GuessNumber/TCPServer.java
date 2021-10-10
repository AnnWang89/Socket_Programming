package socketprogramming;
import java.net.*;
/**
* TCP連接方式的伺服器端
* 實現功能：接收客戶端的數據,判斷數字關係
*/
public class TCPServer {
	public static void main(String[] args){
		try{
			//監聽埠
			ServerSocket ss = new ServerSocket(10001);
			System.out.println("伺服器已啟動：");
			//邏輯處理
			while(true){
				//獲得連接
				Socket s = ss.accept();
				//啟動線程處理
				new TCPThread(s);
			}
		}catch(Exception e){
			e.printStackTrace();

		}
	}
}

