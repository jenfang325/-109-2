module FitnessDiary {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires com.jfoenix;
	requires javafx.base;
	requires javafx.media;
	  
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
