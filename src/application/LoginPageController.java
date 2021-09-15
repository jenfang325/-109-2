package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoginPageController implements Initializable {
	
	@FXML
	private JFXButton button,button1,button2,button3;
	@FXML
	private TextField username;
	@FXML 
	private PasswordField password;
	@FXML
	private Label wrongInput,passwordLabel,usernameLabel,state;
	@FXML
	private AnchorPane signUpLayer1,logInLayer1;
    @FXML
	private Pane pane;
	
    private String transUsername;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
	public Pane getPane() {
		return pane;
	}
	
	
	public void setTransUsername(String username) {
		transUsername=username;
	}
	public String getTransUsername() {
		System.out.println(transUsername);
		return transUsername;
	}
	public void signUp(ActionEvent event) throws IOException{
	    	try {
	    		//Class.forName("com.mysql.jdbc.Driver");
	    		if(username.getText().isEmpty()||password.getText().isEmpty()) {
	    		wrongInput.setText("Please enter username or password");
	   			 System.out.println("Please enter username or password");
	   			 
	   		 }
	   		 else if(!username.getText().matches("[a-zA-Z]+")) {
	   			wrongInput.setText("Username only allows alphabat");
	   			 System.out.println("Username only allows alphabat");
	   			 
	   		 }
	   		 else if(!password.getText().matches("[0-9]+")) {
	   			wrongInput.setText("Password only allows number");
	   			 System.out.println("Password only allows number");
	   		 }
	   		 else {	
	    		Connection conn = DriverManager.getConnection("jdbc:mysql://140.119.19.73:9306/TG07","TG07","cd0SZH");
	    		PreparedStatement state = conn.prepareStatement("insert into Account(username,password) value(?,?)");
	    		state.setString(1,username.getText());
	    		state.setString(2, password.getText());
	    		
	    		
	    		PreparedStatement state2 = conn.prepareStatement("select * from `Account` where username=?");
				state2.setString(1, username.getText());
				ResultSet result=state2.executeQuery();
				if(result.next()) {
					wrongInput.setText("Username already exists");
			    }
				else {
	    		int x = state.executeUpdate();
	    		if(x>0) {
	    			wrongInput.setText("Registration succeeds");
	    			System.out.println("Registration success");
	    		}
	    		else{
	    			System.out.println("Registration fail");
	    		}
				}
	    	 }
	    	}
	    	catch(Exception e){
	    		System.out.println(e);
	    	}
	    }
	 
	public void switchToSelectPage(ActionEvent event) throws IOException {
		 
		 if(username.getText().isEmpty()||password.getText().isEmpty()) {
			 wrongInput.setText("Please enter username or password");
			 System.out.println("Please enter username or password");
			 
		 }
		 else if(!username.getText().matches("[a-zA-Z]+")) {
			 wrongInput.setText("Username only allows alphabat");
			 System.out.println("Username only allows alphabat");
			 
		 }
		 else if(!password.getText().matches("[0-9]+")) {
			 wrongInput.setText("Password only allows number");
			 System.out.println("Password only allows number");
		 }
		 else {
			 
			 try {
				 
				 String user=username.getText();
				 String pass=password.getText();
				 Connection conn = DriverManager.getConnection("jdbc:mysql://140.119.19.73:9306/TG07","TG07","cd0SZH");
		    	 PreparedStatement state = conn.prepareStatement("select * from `Account` where username=? and password=?");
				 state.setString(1, user);
				 state.setString(2, pass);
				 ResultSet result=state.executeQuery();
				 if(result.next()) {
					// u = new User(user);
					 
		    	     //root = FXMLLoader.load(getClass().getResource("SelectPage.fxml"));
					 FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectPage.fxml"));
					 root=loader.load();
					 SelectPageController sc=loader.getController();
					 sc.displayName(user);
				     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				     scene = new Scene(root);
				     scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			         stage.setScene(scene);
				     stage.show();
	                 System.out.println("Success");
				 }
				 else {
					 wrongInput.setText("User does not exist");
					 System.out.println("Failed");
				 }
			 }
			 catch(Exception e) {
				 //System.out.println(e);
			 }
			 
		 }
	  
	    
	    
	 }
	 
    public void switchToSignUp(ActionEvent event) throws IOException{
    	
		
		TranslateTransition trans = new TranslateTransition();
	    trans.setDuration(Duration.seconds(0.55));
		trans.setNode(signUpLayer1);
		trans.setToX(430);
		trans.play();
		
		TranslateTransition trans2 = new TranslateTransition();
		trans2.setDuration(Duration.seconds(0.25));
		trans2.setNode(logInLayer1);
		trans2.setToX(-380);
		trans2.play();
		
		button.setVisible(false);
		button1.setVisible(false);
		/*password.setVisible(false);
		passwordLabel.setVisible(false);
		signUpLayer1.setVisible(false);
		username.setVisible(false);
		usernameLabel.setVisible(false);*/
		state.setText("Sign Up");
		wrongInput.setVisible(true);
		wrongInput.setText("");
		password.setText("");
		username.setText("");
		logInLayer1.setVisible(true);
		signUpLayer1.setVisible(true);
		button2.setVisible(true);
		button3.setVisible(true);
		
		
	}
    
    public void switchToLogIn(ActionEvent event) throws IOException{
    	

    	TranslateTransition trans = new TranslateTransition();
	    trans.setDuration(Duration.seconds(0.55));
		trans.setNode(signUpLayer1);
		trans.setToX(0);
		trans.play();
		
		TranslateTransition trans2 = new TranslateTransition();
		trans2.setDuration(Duration.seconds(0.25));
		trans2.setNode(logInLayer1);
		trans2.setToX(0);
		trans2.play();
		
		state.setText("Log In");
		button.setVisible(true);
		button1.setVisible(true);
		
		password.setVisible(true);
		passwordLabel.setVisible(true);
		signUpLayer1.setVisible(true);
		logInLayer1.setVisible(true);
		username.setVisible(true);
		usernameLabel.setVisible(true);
		
		wrongInput.setVisible(true);
		wrongInput.setText("");
		password.setText("");
		username.setText("");
		button2.setVisible(false);
		button3.setVisible(false);
		
		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//button.setVisible(true);
		button1.setVisible(true);
		logInLayer1.setVisible(true);
		password.setVisible(true);
		passwordLabel.setVisible(true);
		signUpLayer1.setVisible(true);
		username.setVisible(true);
		usernameLabel.setVisible(true);
		state.setVisible(true);
		wrongInput.setVisible(true);
		
		button2.setVisible(false);
		button3.setVisible(false);
		
		
		
	}
	 


}
