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
 * ���A��������A�D�n�ӳB�z�h�ӫȤ�ݪ��ШD
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
            //�]�w�ӫȤ�ݪ����I�a�}
            socketName = socket.getRemoteSocketAddress().toString();
            System.out.println("[email protected]"+socketName+"�w�[�J���");
            //Client.mainStage.setTitle("Socket Programming"+socketName);
            print("[email protected]"+socketName+"�w�[�J���");
            boolean flag = true;
            while (flag)
            {
                //����A���ݸӫȤ�ݪ���X�y
                String line = reader.readLine();
                //�Y�Ȥ�ݰh�X�A�h�h�X�s�u�C
                if (line == null){
                    flag = false;
                    continue;
                }
                String msg = "[email protected]"+socketName+":"+line;
               
                System.out.println(msg);
                
                //�V�u�W�Ȥ�ݿ�X��T
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
     * �V�Ҧ��u�W�Ȥ��socket��o�T��
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
         * ������socket���s�u
     * @throws IOException
     */
   
	
    public void closeConnect() throws IOException {
        System.out.println("[email protected]"+socketName+"�w�h�X���");
        print("[email protected]"+socketName+"�w�h�X���");
        //�����S�s�u�W���Ȥ��
        synchronized (sockets){
            sockets.remove(socket);
        }
        socket.close();
    }
    
    
   
}