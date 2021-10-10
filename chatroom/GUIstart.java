package socketprogramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class GUIstart  extends Application{
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
	public static void main(String[] args) throws Exception{	
		launch(args);
	}
}
