package manager;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
    private Parent parent;
    private Scene scene;
    private Stage stage;
    private GenerateReport generateReport;
    @FXML
    private Text welcomeText;

    @FXML
    private Button Generate;
    
    public ManagerController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Manager2Login.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void redirectHome(Stage stage) {
    	this.stage = stage;
        stage.setTitle("Manager");
        stage.setScene(scene);
     //  welcomeText.setText("Hello " + name + "! You are welcome.");
       
      
      
        stage.hide();
        stage.show();
    }
    
  /*//  @FXML
    protected void handleGenerateReport(ActionEvent event){
    	System.out.println("WOW This is great!!!!!!!");
    	generateReport = new GenerateReport();
    	generateReport.redirectReport(stage);
    } */
}