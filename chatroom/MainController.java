package socketprogramming2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import socketprogramming2.GUIstart;
import socketprogramming2.ServerThead;
import socketprogramming2.Servers;

public class MainController {
	
	@FXML
	TextArea _message;
	@FXML
	TextField _input_message;
	
	public static String inthetextarea=""; 
	public static String answer;
	public void _enter(ActionEvent e) throws Exception {
		System.out.println(ServerThead.output);
		inthetextarea = inthetextarea + _input_message.getText()+"\n"+"\n";
		answer=_input_message.getText();
		_message.setText(inthetextarea);
		_input_message.setText("");
	}
}
