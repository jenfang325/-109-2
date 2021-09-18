package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SummaryController implements Initializable{
	
	  
	    @FXML
	    private TextField smm;

	    @FXML
	    private TextField bfm;

	    @FXML
	    private TextField bmi;

	    @FXML
	    private TextField bfp;

	    @FXML
	    private DatePicker date;

	    @FXML
	    private LineChart<String, Double> lineChart;

	    @FXML
	    private JFXComboBox<String> infoCombo;
	    
	    @FXML
	    private TextField height;
	    
	    @FXML
	    private TextField weight;
	    
	    @FXML
	    private TextField bmr;
	    
	    @FXML
	    private CategoryAxis x;

	    @FXML
	    private NumberAxis y;
	    
	    @FXML
	    private Label change;
	    
	    @FXML
	    private Label wrongInput;

	
	 @FXML
	 private Label userL;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 private String username;
	 
	 ObservableList <String>list=FXCollections.observableArrayList("Height","Weight","SMM","Body Fat Mass","BMI","Body Fat Percentage","BMR");
	 
	 public String getUsername() {
		 return username;
	 }
	 
	 public void displayName(String user) {
		 username=user;
		 userL.setText(user+"'s Personal Info");
	 }
	 
	 @SuppressWarnings("unchecked")
	public void showL(ActionEvent event) {
		 XYChart.Series<String,Double> series = new XYChart.Series<String,Double>();
		 
		 try {
			 lineChart.getData().clear();
	         Connection conn=getConnection();
	         Statement stmt = conn.createStatement();
	         Statement stmt2 = conn.createStatement();
	         Statement stmt3 = conn.createStatement();
	         if(infoCombo.getSelectionModel().getSelectedItem().equals("Height")) {
	        	 String query="SELECT height,date FROM Personal WHERE username=\'"+getUsername()+"\' ORDER BY date ASC";
	        	 String query2="SELECT height FROM `Personal` WHERE date IN (SELECT max(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 String query3="SELECT  height FROM `Personal` WHERE date IN (SELECT min(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 ResultSet rs = stmt.executeQuery(query);
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 ResultSet rs3 = stmt3.executeQuery(query3);
	        	 while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("height")));
		            }
	        	 while(rs2.next()) {
	        		 System.out.println("r2");
	        		 while(rs3.next()) {
	        			 System.out.println("r3");
	        		 double x = rs2.getDouble("height")-rs3.getDouble("height");
	        		 if(x>=0) {
	        		     change.setText("+"+x+" cm");
	        		 }
	        		 else if(x<0) {
	        			 change.setText(""+x+" cm");
	        		 }
	        		 }
	        	 }
	        	    
		            x.setLabel("Date");
		            y.setLabel("Height");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	         }
	         
	         if(infoCombo.getSelectionModel().getSelectedItem().equals("Weight")) {
	        	 String query="SELECT weight,date FROM Personal WHERE username=\'"+username+"\' ORDER BY date ASC";
	        	 String query2="SELECT weight FROM `Personal` WHERE date IN (SELECT max(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 String query3="SELECT weight FROM `Personal` WHERE date IN (SELECT min(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 ResultSet rs = stmt.executeQuery(query);
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 ResultSet rs3 = stmt3.executeQuery(query3);
	        	 while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("weight")));
		            }
	        	 while(rs2.next()) {
	        		 while(rs3.next()) {
	        		 double x = rs2.getDouble("weight")-rs3.getDouble("weight");
	        		 if(x>=0) {
	        		     change.setText("+"+x+" kg");
	        		 }
	        		 else if(x<0) {
	        			 change.setText(""+x+" kg");
	        		 }
	        		 }
	        	 }
		            x.setLabel("Date");
		            y.setLabel("Weight");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	         }
	         
	         if(infoCombo.getSelectionModel().getSelectedItem().equals("SMM")) {
	        	 String query="SELECT smm,date FROM Personal WHERE username=\'"+username+"\' ORDER BY date ASC";
	        	 String query2="SELECT smm FROM `Personal` WHERE date IN (SELECT max(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 String query3="SELECT smm FROM `Personal` WHERE date IN (SELECT min(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 ResultSet rs = stmt.executeQuery(query);
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 ResultSet rs3 = stmt3.executeQuery(query3);
	        	 while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("smm")));
		            }
	        	 while(rs2.next()) {
	        		 while(rs3.next()) {
	        		 double x = rs2.getDouble("smm")-rs3.getDouble("smm");
	        		 if(x>=0) {
	        		     change.setText("+"+x+" kg");
	        		 }
	        		 else if(x<0) {
	        			 change.setText(""+x+" kg");
	        		 }
	        		 }
	        	 }
		            x.setLabel("Date");
		            y.setLabel("SMM");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	         }
	         
	         if(infoCombo.getSelectionModel().getSelectedItem().equals("Body Fat Mass")) {
	        	 String query="SELECT bfm,date FROM Personal WHERE username=\'"+username+"\' ORDER BY date ASC";
	        	 String query2="SELECT bfm FROM `Personal` WHERE date IN (SELECT max(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 String query3="SELECT bfm FROM `Personal` WHERE date IN (SELECT min(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 ResultSet rs = stmt.executeQuery(query);
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 ResultSet rs3 = stmt3.executeQuery(query3);
	        	 while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("bfm")));
		            }
	        	 while(rs2.next()) {
	        		 while(rs3.next()) {
	        		 double x = rs2.getDouble("bfm")-rs3.getDouble("bfm");
	        		 if(x>=0) {
	        		     change.setText("+"+x+" kg");
	        		 }
	        		 else if(x<0) {
	        			 change.setText(""+x+" kg");
	        		 }
	        		 }
	        	 }
		            x.setLabel("Date");
		            y.setLabel("Body Fat Mass");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	         }
	         
	         if(infoCombo.getSelectionModel().getSelectedItem().equals("BMI")) {
	        	 String query="SELECT bmi,date FROM Personal WHERE username=\'"+username+"\' ORDER BY date ASC"; 
	        	 String query2="SELECT bmi FROM `Personal` WHERE date IN (SELECT max(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 String query3="SELECT bmi FROM `Personal` WHERE date IN (SELECT min(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 ResultSet rs = stmt.executeQuery(query);
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 ResultSet rs3 = stmt3.executeQuery(query3);
	        	 while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("bmi")));
		            }
	        	 while(rs2.next()) {
	        		 while(rs3.next()) {
	        		 double x = rs2.getDouble("bmi")-rs3.getDouble("bmi");
	        		 if(x>=0) {
	        		     change.setText("+"+x);
	        		 }
	        		 else if(x<0) {
	        			 change.setText(""+x);
	        		 }
	        		 }
	        	 }
		            x.setLabel("Date");
		            y.setLabel("BMI");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	         }
	         
	         if(infoCombo.getSelectionModel().getSelectedItem().equals("Body Fat Percentage")) {
	        	 String query="SELECT bfp,date FROM Personal WHERE username=\'"+username+"\' ORDER BY date ASC";
	        	 String query2="SELECT bfp FROM `Personal` WHERE date IN (SELECT max(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 String query3="SELECT bfp FROM `Personal` WHERE date IN (SELECT min(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 ResultSet rs = stmt.executeQuery(query);
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 ResultSet rs3 = stmt3.executeQuery(query3);
	        	 while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("bfp")));
		            }
	        	 while(rs2.next()) {
	        		 while(rs3.next()) {
	        		 double x = rs2.getDouble("bfp")-rs3.getDouble("bfp");
	        		 if(x>=0) {
	        		     change.setText("+"+x);
	        		 }
	        		 else if(x<0) {
	        			 change.setText(""+x);
	        		 }
	        		 }
	        	 }
		            x.setLabel("Date");
		            y.setLabel("Body Fat Percentage");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	         }
	         
	         if(infoCombo.getSelectionModel().getSelectedItem().equals("BMR")) {
	        	 String query="SELECT bmr,date FROM Personal WHERE username=\'"+username+"\' ORDER BY date ASC";
	        	 String query2="SELECT bmr FROM `Personal` WHERE date IN (SELECT max(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 String query3="SELECT bmr FROM `Personal` WHERE date IN (SELECT min(date) FROM `Personal` WHERE username=\'"+getUsername()+"\') LIMIT 1";
	        	 ResultSet rs = stmt.executeQuery(query);
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 ResultSet rs3 = stmt3.executeQuery(query3);
	        	 while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("bmr")));
		            }
	        	 while(rs2.next()) {
	        		 while(rs3.next()) {
	        		 double x = rs2.getDouble("bmr")-rs3.getDouble("bmr");
	        		 if(x>=0) {
	        		     change.setText("+"+x+" kcal");
	        		 }
	        		 else if(x<0) {
	        			 change.setText(""+x+" kcal");
	        		 }
	        		 }
	        	 }
		            x.setLabel("Date");
		            y.setLabel("bmr");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	         }
	         
	       
	         
	
	         
		 }
		 catch (Exception e) {
	            System.out.println(e);;
	        }
		 
	 }
	 
	 public void confirm() {
		 
         try {
        	 if(height.getText().isEmpty()|| weight.getText().isEmpty()||smm.getText().isEmpty()|| 
        			    bfm.getText().isEmpty()|| bmi.getText().isEmpty()||bfp.getText().isEmpty()||bmr.getText().isEmpty()||date.getValue()==null) {
        		 wrongInput.setText("Please enter all required information");
        	 }
        	 else {
			 String query="INSERT INTO `Personal`(height,weight,smm,bfm,bmi,bfp,date,bmr,username) VALUE("+height.getText()+","+weight.getText()+","+smm.getText()+","+bfm.getText()+","
			              +bmi.getText()+","+bfp.getText()+",\'"+date.getValue().toString()+"\',"+bmr.getText()+",\'"+username+"\'"+")";
			 executeQuery(query);
			 
			 
			 height.setText("");
			 weight.setText("");
			 smm.setText("");
			 bfm.setText("");
			 bmi.setText("");
			 bfp.setText("");
			 bmr.setText("");
			 date.setValue(null);
			 System.out.println("success");
        	 }
		}
         catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public Connection getConnection() {
			Connection conn;
			try {
			conn=DriverManager.getConnection("jdbc:mysql://140.119.19.73:9306/TG07","TG07","cd0SZH");
			return conn;
			}
			catch(Exception e) {
				 System.out.println(e);
				 return null;
			 }	
			
		}
	 private void executeQuery(String query) {
			// TODO Auto-generated method stub
			Connection conn = getConnection();
			Statement st;
			try {
				st=conn.createStatement();
				st.executeUpdate(query);
			}
			catch(Exception e) {
				 System.out.println(e);
			 }
		}
	 
	 public void switchToSelectPage(ActionEvent event) throws IOException {
	     //root = FXMLLoader.load(getClass().getResource("SelectPage.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectPage.fxml"));
		 root=loader.load();
		 SelectPageController sc=loader.getController();
		 sc.displayName(getUsername());
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
		 ec.displayName(getUsername());
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }
	
	 public void switchToAnalysis(ActionEvent event) throws IOException {
		  //root = FXMLLoader.load(getClass().getResource("Analysis.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Analysis.fxml"));
		 root=loader.load();
		 AnalysisController ac=loader.getController();
		 ac.displayName(getUsername());
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		userL.setVisible(true);
		infoCombo.setItems(list);
	}

}

