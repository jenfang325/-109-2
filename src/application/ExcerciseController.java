package application;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ExcerciseController implements Initializable{
	@FXML
	Button e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15;
	@FXML 
	Label userL;
	
	
	@FXML 
	ImageView p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13;
	Image i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void displayName(String user) {
		 userL.setText(user);
	 }

	 public void switchToSelectPage(ActionEvent event) throws IOException {
	     //root = FXMLLoader.load(getClass().getResource("SelectPage.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectPage.fxml"));
		 root=loader.load();
		 SelectPageController sc=loader.getController();
		 sc.displayName(userL.getText());
	     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	     scene = new Scene(root);
	     scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	     stage.setScene(scene);
	     stage.show();
	     
	 }
	 public void switchToLoginPage(ActionEvent event) throws IOException {
		 Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Logout");
			alert.setHeaderText("You're about to logout!");
			alert.setContentText("Are you sure you want to logout?");
			
			if (alert.showAndWait().get() == ButtonType.OK){
				root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
				  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				  scene = new Scene(root);
				  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				  stage.setScene(scene);
				  stage.show();
			} 

		  
		 }
	 public void switchToAnalysis(ActionEvent event) throws IOException {
		  //root = FXMLLoader.load(getClass().getResource("Analysis.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Analysis.fxml"));
		 root=loader.load();
		 AnalysisController ac=loader.getController();
		 ac.displayName(userL.getText());
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }
	
	 public void switchToSummary(ActionEvent event) throws IOException {
		  //root = FXMLLoader.load(getClass().getResource("Summary.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Summary.fxml"));
		 root=loader.load();
		 SummaryController sc=loader.getController();
		 sc.displayName(userL.getText());
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToRecord(ActionEvent event) throws IOException {
		 Button b = (Button)event.getSource();
		 b.setStyle("-fx-text-fill: #899de2");
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Record.fxml"));
		 root=loader.load();
		 RecordController rc=loader.getController();
		 rc.displayName(b.getText());
		 rc.transUsername(userL.getText());
		  //root = FXMLLoader.load(getClass().getResource("Record.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToRecord2(ActionEvent event) throws IOException {
		 Button b = (Button)event.getSource();
		 b.setStyle("-fx-text-fill: #899de2");
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Record.fxml"));
		 root=loader.load();
		 
		 RecordController rc=loader.getController();
		 rc.displayName(b.getText());
		 rc.displayUnitLabel("Distance (km)");
		 rc.transUsername(userL.getText());
		  //root = FXMLLoader.load(getClass().getResource("Record.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 
	 
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		userL.setVisible(false);
		i1 = new Image(getClass().getResourceAsStream("/二頭肌.jpg"));
		p1.setImage(i1);
		
		i2 = new Image(getClass().getResourceAsStream("/二頭肌2.jpg"));
		p2.setImage(i2);
		i3 = new Image(getClass().getResourceAsStream("/引體向上.jpg"));
		p3.setImage(i3);
		i4 = new Image(getClass().getResourceAsStream("/曲腿.jpg"));
		p4.setImage(i4);
		i5 = new Image(getClass().getResourceAsStream("/伸腿.jpg"));
		p5.setImage(i5);
		i6 = new Image(getClass().getResourceAsStream("/背肌.jpg"));
		p6.setImage(i6);
		i7 = new Image(getClass().getResourceAsStream("/飛鳥.jpg"));
		p7.setImage(i7);
		i8 = new Image(getClass().getResourceAsStream("/飛輪.jpg"));
		p8.setImage(i8);
		i9 = new Image(getClass().getResourceAsStream("/倒蹬機.jpg"));
		p9.setImage(i9);
		i10 = new Image(getClass().getResourceAsStream("/啞鈴.jpg"));
		p10.setImage(i10);
		i11 = new Image(getClass().getResourceAsStream("/跑步機.jpg"));
		p11.setImage(i11);
		i12 = new Image(getClass().getResourceAsStream("/槓鈴.jpg"));
		p12.setImage(i12);
		i13 = new Image(getClass().getResourceAsStream("/蝴蝶機.jpg"));
		p13.setImage(i13);
	}

}


