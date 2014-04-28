package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * AlertBox class.
 * 
 * Loads a modal dialog box containing a message to the user.
 * @author Aaron Cheng 
 *
 */
public class AlertBox {
    
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    
    @FXML
    private static Label alertMessage;
    
    @FXML
    private static Button closeWindow;
           
    /**
     * Sets the message of the alert box, and displays the alert
     * in a modal fashion.
     * @param message
     */
    public static void show(String message)
    {
       loadScene();
       alertMessage.setText(message);
       stage = new Stage();
       stage.setTitle("Whoops!");
       stage.setResizable(false);
       stage.setScene(scene);
       stage.initModality(Modality.APPLICATION_MODAL);
       stage.show();
    }
        
    /**
     * Closes the dialog window.
     */
    @FXML
    public static void closeDialog()
    {
        stage.close();
    }
    
    /**
     * Loads the alertBox.fxml page to define the window.
     */
    private static void loadScene()
    {
        FXMLLoader loader = new FXMLLoader(AlertBox.class.getResource("ui.alertBox.fxml"));
        try {
           root = (Parent) loader.load();
           scene = new Scene(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
