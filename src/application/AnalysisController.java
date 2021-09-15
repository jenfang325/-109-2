package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AnalysisController implements Initializable{
	    @FXML
        private Label maxL;

        @FXML
        private Label maxWeight;

	    @FXML
	    private JFXButton weightB;

	    @FXML
	    private JFXButton repB;
	    
	    @FXML
	    private DatePicker date;

	    @FXML
	    private ComboBox<String> exerciseCombo;
	    
	    @FXML
	    private LineChart<String, Double> lineChart;
	    
	    @FXML
	    private CategoryAxis x;

	    @FXML
	    private NumberAxis y;
	    
	    @FXML
	    private Label userL;
	    
	    @FXML
	    private PieChart pieChart;
	    
	    @FXML
	    private JFXButton pieBtn;
	    
	    
	    
	    ObservableList<String> options = FXCollections.observableArrayList("Bicep Curls Machine","Bicep Chair","Pull Up Machine","Leg Curl Machine(lying)"
	    		,"Leg Curl Machine(seated)","Back Machine","Chest Fly","Spinning Bike","Hack Squat Machine","Dumbbell","Treadmill","Barbell","Peck Deck Machine");
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 public void displayName(String user) {
		 userL.setText(user);
	 }
	
	 public String select() {
		  return exerciseCombo.getSelectionModel().getSelectedItem().toString();
	 }
	 
	 
	 
	 @SuppressWarnings("unchecked")
	public void showWL(ActionEvent event) {
		 //lineChart.getData().clear();
		 XYChart.Series<String,Double> series = new XYChart.Series<String,Double>();
		 try 
	        {
			    lineChart.getData().clear();
	            Connection conn=getConnection();
	            Statement stmt = conn.createStatement();
	            JFXButton b = (JFXButton)event.getSource();
	            if(b.getText().equals("Weight")) {
	            	String query="SELECT weight,date  FROM Record Where exercise=\'"+select()+"\' AND username=\'"+userL.getText()+"\'ORDER BY date ASC";
	            	ResultSet rs = stmt.executeQuery(query);

		            
		            while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("weight")));
		            }
		            x.setLabel("Date");
		            y.setLabel("Weight");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	            }
	            else if(b.getText().equals("Rep")) {
	            	String query="SELECT rep, date FROM Record Where exercise=\'"+select()+"\' AND username=\'"+userL.getText()+"\'ORDER BY date ASC";
		            ResultSet rs = stmt.executeQuery(query);
		            
		            while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("rep")));
		                
		            }
		            x.setLabel("Date");
		            y.setLabel("Reptition");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	            }
	            else if(b.getText().equals("Distence")) {
	            	String query="SELECT weight,date  FROM Record Where exercise=\'"+select()+ "\' AND username=\'"+userL.getText()+"\'ORDER BY date ASC";
	            	ResultSet rs = stmt.executeQuery(query);

		            
		            while(rs.next())
		            {
		                series.getData().add(new XYChart.Data<String,Double>(rs.getString("date"), rs.getDouble("weight")));
		            }
		            x.setLabel("Date");
		            y.setLabel("Distence");
		            x.setAnimated(false);
		            lineChart.getData().addAll(series);
	            }
	            
	            
	        }
	        catch (Exception e) {
	            System.out.println(e);;
	        }

	 }
	 
	 public void changeBtnText(ActionEvent event){
		 
		 
		 try {
			 lineChart.getData().clear();
			 Connection conn =getConnection();
			 Statement stmt = conn.createStatement();
			 if(exerciseCombo.getSelectionModel().getSelectedItem().toString().equals("Treadmill")) {
				 weightB.setText("Distance");
				 maxL.setText("Max Distance");
				 String query="SELECT MAX(weight) \"weight\" FROM Record WHERE exercise=\'Treadmill\'"+"AND username=\'"+userL.getText()+"\'";
				 
				    ResultSet rs = stmt.executeQuery(query);
		            while(rs.next())
		            {
		               maxWeight.setText(rs.getDouble("weight")+" km");
		            }
			 }
			 else if(exerciseCombo.getSelectionModel().getSelectedItem().toString().equals("Spinning Bike")) {
				 weightB.setText("Distance");
				 maxL.setText("Max Distance");
				 String query="SELECT MAX(weight) \"weight\" FROM Record WHERE exercise=\'Spinnig Bike\'"+"AND username=\'"+userL.getText()+"\'";
				 
				    ResultSet rs = stmt.executeQuery(query);
		            while(rs.next())
		            {
		               maxWeight.setText(rs.getDouble("weight")+" km");
		            }
			 }
			 else{
				 String query="SELECT MAX(weight) \"weight\" FROM Record WHERE exercise=\'"+select()+"\'"+"AND username=\'"+userL.getText()+"\'";
			 
			    ResultSet rs = stmt.executeQuery(query);
	            while(rs.next())
	            {
	               maxWeight.setText(rs.getDouble("weight")+" kg");
	            }
			 }
		 }
		 catch (Exception e) {
	            System.out.println(e);;
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
		exerciseCombo.setItems(options);
		userL.setVisible(false);
		
	}
	
	
	public void showPie(ActionEvent event) {
		try {
			pieBtn.setText("Exercise Ratio");
			pieChart.getData().clear();
			Connection conn =getConnection();
			Statement stmt2 = conn.createStatement();
			ObservableList<PieChart.Data>pieChartData=FXCollections.observableArrayList();
			String query2="SELECT exercise, count(exercise) AS CountOf FROM `Record` WHERE username=\'"+userL.getText()+"\'GROUP BY exercise";
			ResultSet rs2=stmt2.executeQuery(query2);
				while(rs2.next()) {
				pieChartData.add(new PieChart.Data(rs2.getString("exercise"), rs2.getDouble("COUNTOF")));
				}
			pieChart.getData().addAll(pieChartData);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	
	
	
}

