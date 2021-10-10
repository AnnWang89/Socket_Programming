package socketprogramming;
import java.net.*;
import java.io.*;
/**
* 猜數字客戶端
*/
public class TCPClient {
	public static void main(String[] args) {
			Socket socket = null;
			OutputStream os = null;
			InputStream is = null;
			BufferedReader br = null;
			byte[] data = new byte[2];
			try{
//建立連接
				socket = new Socket("127.0.0.1",10001);
//發送數據
				os= socket.getOutputStream();
//讀取反饋數據
				is = socket.getInputStream();
//鍵盤輸入流
				br = new BufferedReader(new InputStreamReader(System.in));
//多次輸入
				while(true){
					System.out.println("請輸入數字：");
//接收輸入
					String s = br.readLine();

//結束條件
					if(s.equals("quit")){
						os.write("quit".getBytes());
						break;
					}
//校驗輸入是否合法
					boolean b = true;
					try{
						Integer.parseInt(s);
					}catch(Exception e){
						b = false;
					}
					if(b){ //輸入合法
//發送數據
						os.write(s.getBytes());
//接收反饋
						is.read(data);
//判斷
						switch(data[0]){
						case 0:
							System.out.println("相等！祝賀你！");
							break;
						case 1:
							System.out.println("大了！");
							break;
						case 2:
							System.out.println("小了！");
							break;
						default:
							System.out.println("其它錯誤！");
						}
//提示猜的次數
						System.out.println("你已經猜了"+ data[1] +"次！");
//判斷次數是否達到5次
						if(data[1] >= 5){
							System.out.println("你掛了！");
	//給伺服器端線程關閉的機會
							os.write("quit".getBytes());
	//結束客戶端程序
							break;
						}
					}else{ //輸入錯誤
						System.out.println("輸入錯誤！");
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
//關閉連接
					br.close();
					is.close();
					os.close();
					socket.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
}
