package socketprogramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import socketprogramming2.controller.MainController;

/**
 * 伺服器執行緒，主要來處理多個客戶端的請求
 */
public class ServerThead extends Servers implements Runnable{

    Socket socket;
    String socketName;
    public static String output;
    
    public ServerThead(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //設定該客戶端的端點地址
            socketName = socket.getRemoteSocketAddress().toString();
            System.out.println("[email protected]"+socketName+"已加入聊天");
            //Client.mainStage.setTitle("Socket Programming"+socketName);
            print("[email protected]"+socketName+"已加入聊天");
            boolean flag = true;
            while (flag)
            {
                //阻塞，等待該客戶端的輸出流
                String line = reader.readLine();
                //若客戶端退出，則退出連線。
                if (line == null){
                    flag = false;
                    continue;
                }
                String msg = "[email protected]"+socketName+":"+line;
               
                System.out.println(msg);
                
                //向線上客戶端輸出資訊
                print(msg);
            }

            closeConnect();
        } catch (IOException e) {
            try {
                closeConnect();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * 向所有線上客戶端socket轉發訊息
     * @param msg
     * @throws IOException
     */
    
    private void print(String msg) throws IOException {
        PrintWriter out = null;
        output=msg;
        //System.out.println("IN:"+output);
        synchronized (sockets){
        for (Socket sc : sockets){
            out = new PrintWriter(sc.getOutputStream());            
            out.println(msg);
            out.flush();
        }
    }
    }
    /**
         * 關閉該socket的連線
     * @throws IOException
     */
   
	
    public void closeConnect() throws IOException {
        System.out.println("[email protected]"+socketName+"已退出聊天");
        print("[email protected]"+socketName+"已退出聊天");
        //移除沒連線上的客戶端
        synchronized (sockets){
            sockets.remove(socket);
        }
        socket.close();
    }
    
    
   
}