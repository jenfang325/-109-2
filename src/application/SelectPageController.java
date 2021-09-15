package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.util.Duration;

public class SelectPageController implements Initializable{
	
	
	@FXML
	ImageView imageView;
	@FXML 
	Label userL;
	@FXML
    private ImageView background;
	
     int count;
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 Image image = new Image(getClass().getResourceAsStream("/好了1.png"));
	
	 
	 public void displayName(String user) {
		 userL.setText(user);
	 }
	 
	 public void slideShow() {
		 ArrayList<Image> image = new ArrayList<>();
		 image.add(new Image("/Users/justinlien/eclipse-workspace/FitnessDiary/Image/p1.jpg"));
		 image.add(new Image("//Users/justinlien/eclipse-workspace/FitnessDiary/Image/p2.jpg"));
		 image.add(new Image("/Users/justinlien/eclipse-workspace/FitnessDiary/Image/p3.jpg"));
		 
		 Timeline t = new Timeline(new KeyFrame(Duration.seconds(5), event-> {
			 imageView.setImage(image.get(count));
			 count++;
			 if(count==3) {
				 count=0;
			 }
		 }));
		 t.setCycleCount(Animation.INDEFINITE);
		 t.play();
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
	 public void switchToExercise(ActionEvent event) throws IOException {
		  //root = FXMLLoader.load(getClass().getResource("Exercise.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Exercise.fxml"));
		 root=loader.load();
		 ExcerciseController ec=loader.getController();
		 ec.displayName(userL.getText());
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
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//slideShow();
		userL.setVisible(false);		
		background.setImage(image);

	}
	 
	 

}
