package socketprogramming2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Servers {
    //�N�����쪺socket�ܦ��@�Ӷ��X
  protected static   List<Socket> sockets = new Vector<>();
 
  public static void main(String[] args) throws IOException {

        //�إߪA�Ⱥ�
        ServerSocket server = new ServerSocket(5200);
        boolean flag = true;
        //�����Ȥ�ݽШD
        while (flag){
            try {
             //���뵥�ݫȤ�ݪ��s�u
            Socket accept = server.accept();
            synchronized (sockets){
                sockets.add(accept);
            }
            //�h�Ӧ��A��������i���Ȥ�ݪ��T��
            Thread thread = new Thread(new ServerThead(accept));
            thread.start();
            //���򲧱`�C
            }catch (Exception e){
                flag = false;
                e.printStackTrace();
            }
        }
        //�������A��
        server.close();
        
    }
   
}