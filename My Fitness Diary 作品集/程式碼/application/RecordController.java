package application;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RecordController implements Initializable{
	@FXML
    private DatePicker date;

    @FXML
    private TextField set;

    @FXML
    private TextField weight;

    @FXML
    private Label setLabel;

    @FXML
    private Label unitLabel;

    @FXML
    private Label exerciseLabel;

    @FXML
    private TableView<Table> table;
    
    @FXML
    private TableColumn<Table, Integer> IDC;

    @FXML
    private TableColumn<Table, String> exC;

    @FXML
    private TableColumn<Table, Integer> setC;

    @FXML
    private TableColumn<Table, Double> weightC;

    @FXML
    private TableColumn<Table, Double> repC;

    @FXML
    private TableColumn<Table , String > dateC;

    @FXML
    private Label unitLabel1;

    @FXML
    private TextField rep;

    @FXML
    private JFXButton addBtn;

    @FXML
    private Label wrongInput;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton deleteBtn;
    
    @FXML
    private Label userLabel;
    
    @FXML
    private Label idLabel;
    
    @FXML
    private TextField ps;
    
    @FXML
    private Label psLabel;
   
    
    
    
	
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 
 	 public void transUsername(String user) {
		 userLabel.setText(user);
	 }
	 
	 public void displayName(String exercise) {
		 exerciseLabel.setText(exercise);
	 }
	 public void displayUnitLabel(String unit) {
		 unitLabel.setText(unit);
		 weightC.setText(unitLabel.getText());
	 }
	 
	 
	 
	 
	 public void switchToAnalysis(ActionEvent event) throws IOException {
	     //root = FXMLLoader.load(getClass().getResource("Analysis.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Analysis.fxml"));
		 root=loader.load();
		 AnalysisController ac=loader.getController();
		 ac.displayName(userLabel.getText());
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
		 // root = FXMLLoader.load(getClass().getResource("Exercise.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Exercise.fxml"));
		 root=loader.load();
		 ExcerciseController ec=loader.getController();
		 ec.displayName(userLabel.getText());
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
		 sc.displayName(userLabel.getText());
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToSelectPage(ActionEvent event) throws IOException {
		  //root = FXMLLoader.load(getClass().getResource("SelectPage.fxml"));
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectPage.fxml"));
		 root=loader.load();
		 SelectPageController sc=loader.getController();
		 sc.displayName(userLabel.getText());
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setScene(scene);
		  stage.show();
		 }
	 
public void buttonHandler(ActionEvent event) throws IOException {
	if(event.getSource()==addBtn) {
		addData();
	}
	else if(event.getSource()==updateBtn) {
		update();
	}
	else if(event.getSource()==deleteBtn) {
		delete();
	}
}
	 
public void addData(){
	try { 
		 if(set.getText().isEmpty()||weight.getText().isEmpty()||rep.getText().isEmpty()||date.getValue()==null) {
			 wrongInput.setText("Please enter full information");
			 System.out.println("Please enter full information");
			 
		 }
		 else if(!set.getText().matches("[0-9]+")) {
			 wrongInput.setText("Set only allows number");
			 System.out.println("Set only allows number");
			 
		 }
		 else if(!weight.getText().matches("[0-9.]+")) {
			 wrongInput.setText(unitLabel.getText()+" only allows number");
			 System.out.println(unitLabel.getText()+" only allows number");
		 }
		 else if(!rep.getText().matches("[0-9.]+")) {
			 wrongInput.setText("Reptition only allows number");
			 System.out.println("Reptition only allows number");
		 }
		 
		 else {
			    /*Connection conn=getConnection();
	            Statement stmt = conn.createStatement();
			    String query2="SELECT * FROM Record WHERE exercise =\'"+exerciseLabel.getText()+"\'AND sets="+set.getText()+"AND date=\'"+date.getValue().toString()+"\'";
			    ResultSet rs=stmt.executeQuery(query2);
			    if(rs.next()) {
			    	wrongInput.setText("Data already exists");
			    }
			    	else {*/
			    String query="INSERT INTO Record(exercise,sets,weight,rep,date,username) VALUES(\""+exerciseLabel.getText()+"\", "+set.getText()+", "+weight.getText()+","+
		                       rep.getText()+", \""+date.getValue().toString()+"\",\""+userLabel.getText()+"\")";
			    executeQuery(query);
			    showData();
		    	set.setText("");
		    	weight.setText("");
		    	rep.setText("");
		    	date.setValue(null);
		    	wrongInput.setText("");
			    	}
			    
			 }
	
			 catch(Exception e) {
				 System.out.println(e);
			 }
			 
		 }

public void update() {
	if(set.getText()==null||weight.getText()==null||rep.getText()==null||date.getValue()==null) {
		wrongInput.setText("Please choose the data for updating");
	}
	else {
	String query = "UPDATE Record SET exercise =\'"+exerciseLabel.getText()+"\', sets="+set.getText()+", weight="+weight.getText()+
			        ", rep="+rep.getText()+", date=\'"+date.getValue().toString()+"\' WHERE id="+idLabel.getText();
	executeQuery(query);
    showData();
    set.setText("");
	weight.setText("");
	rep.setText("");
	date.setValue(null);
	wrongInput.setText("Successfully Updated");
	}
}

public void delete() {
	if(set.getText()==null||weight.getText()==null||rep.getText()==null||date.getValue()==null) {
		wrongInput.setText("Please choose the data for deleting");
	}
	else {
	String query = "DELETE FROM Record WHERE id="+idLabel.getText(); 
			//usernaem=\'"+userLabel.getText()+"\' AND date=\'"+date.getValue().toString()+"\' AND sets="+set.getText();  
	executeQuery(query);
    showData();
    set.setText("");
	weight.setText("");
	rep.setText("");
	date.setValue(null);
    wrongInput.setText("Successfully Deleted");
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
public void clickTable(MouseEvent event) {
	wrongInput.setText("");
	Table t =table.getSelectionModel().getSelectedItem();
	LocalDate localDate = LocalDate.parse(t.getDate());
	idLabel.setText(""+t.getId());    
	set.setText(""+t.getSets());
	weight.setText(""+t.getWeight());
	rep.setText(""+t.getRep());
	date.setValue(localDate);
}


@Override
 public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
	userLabel.setVisible(false);
	idLabel.setVisible(false);
	
		//userLabel.setText(u.getUserList());
	IDC.setVisible(false);
	showData();
	
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

public void showData() {
	ObservableList<Table> list=getDataList();
	IDC.setCellValueFactory(new PropertyValueFactory<Table,Integer>("id"));
	exC.setCellValueFactory(new PropertyValueFactory<Table,String>("exercise"));
	setC.setCellValueFactory(new PropertyValueFactory<Table,Integer>("sets"));
	 weightC.setCellValueFactory(new PropertyValueFactory<Table,Double>("weight"));
	repC.setCellValueFactory(new PropertyValueFactory<Table,Double>("rep"));
	dateC.setCellValueFactory(new PropertyValueFactory<Table,String>("date"));
	table.setItems(list);
}


public ObservableList<Table> getDataList(){
	ObservableList<Table> tableList=FXCollections.observableArrayList();
	Connection conn = getConnection();
	String query="SELECT * FROM `Record` WHERE username=\""+userLabel.getText()+"\" AND exercise=\'"+exerciseLabel.getText()+"\'";
	Statement st;
	ResultSet re;
	
	try {
		st=conn.createStatement();
		re=st.executeQuery(query);
		Table table;
		while(re.next()) {
			table=new Table(re.getInt("id"),re.getString("exercise"),re.getInt("sets"),re.getDouble("weight"),re.getDouble("rep"),re.getString("date"));
			tableList.add(table);
		}
		//re.getInt("id"),
		//re.getInt("id"),
    }
	catch(Exception e) {
		e.printStackTrace();
		 System.out.println(e);
		 
	 }	
	return tableList;
}




	    
	    
	 }
	 


