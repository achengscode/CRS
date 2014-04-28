package manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class GenerateReport {
	 private Parent parent;
	 private Scene scene;
	 private Stage stage;

	 public GenerateReport() {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Report.fxml"));
	        fxmlLoader.setController(this);
	        try {
	            parent = (Parent) fxmlLoader.load();
	            scene = new Scene(parent, 600, 400);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void redirectReport(Stage stage){
		 try{
			 
			 
		    stage.setTitle("Report");
	        stage.setScene(scene);
	        stage.hide();
	        stage.show();
		 }
		 catch(Exception e){
			  e.printStackTrace();
		 }
	        
	 }
}