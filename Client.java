package socketprogramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class  Client extends Application{
	public static Stage mainStage;
	public static Scene mainScene;
	public static Slider _learning_rate;
	
	@Override
		
	public void start(Stage primaryStage) throws IOException{
		mainStage = primaryStage;
	    URL fxmlURL = this.getClass().getResource("view/socket.fxml");
	    FXMLLoader loader = new FXMLLoader(fxmlURL);
	    Parent main = loader.load();    
	    mainScene = new Scene(main);
	    mainStage.setTitle("Socket Programming");//title name
	    mainStage.setScene(mainScene);//set scene to mainScene
	    mainStage.show();//show the Stage
		    
	}
	public static Socket socket;
	public static BufferedReader reader;
	public static PrintWriter out;
	public static BufferedReader in;
	public static String  line;
	
    public static void main(String[] args) throws IOException {
        //建立連線指定Ip和埠的socket
        socket = new Socket("127.0.0.1",5200);
        
        
        //獲取系統標準輸入流
        reader = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //建立一個執行緒用於讀取伺服器的資訊
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        System.out.println(in.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        launch(args);
        //寫資訊給客戶端
        line = reader.readLine();
        while (!"end".equalsIgnoreCase(line)){
            //將從鍵盤獲取的資訊給到伺服器
            out.println(line);
            out.flush();
            //顯示輸入的資訊
            line = reader.readLine();
        }
        out.close();
        in.close();
        socket.close();
    }
    @FXML
	TextArea _message;
	@FXML
	TextField _input_message;
	
	public static String inthetextarea=""; 
	public static String answer;
	public String inthetextarea2=""; 
	public void _enter(ActionEvent e) throws Exception {     
		line=_input_message.getText();
		out.println(line);
        out.flush();
        
        if("end".equalsIgnoreCase(line))
        {
        	out.close();
            in.close();
            socket.close();
        }
		inthetextarea2 = inthetextarea + line+"\n";
		_message.setText(inthetextarea2);
		_input_message.setText("");
	}
}