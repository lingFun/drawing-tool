//Ling Fang
//CSC221 Assignment 5
//SampleController.java
package application;

import java.awt.Dialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.xml.bind.JAXB;
import javax.xml.transform.Result;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SampleController {

    // Holds the current selected color
    private Color fillColor = Color.BLACK;

    // holds the current selected radius
    private double radius = 10;
    
    // stores the Dots 
	Dots dots = new Dots();
    


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton rbBlack;

    @FXML
    private ToggleGroup togGrpColor;

    @FXML
    private RadioButton rbRed;

    @FXML
    private ToggleGroup groupDrawingColor;

    @FXML
    private RadioButton rbGreen;

    @FXML
    private RadioButton rbBlue;

    @FXML
    private RadioButton rbSmall;

    @FXML
    private ToggleGroup togGrpSize;

    @FXML
    private RadioButton rbMedium;

    @FXML
    private ToggleGroup groupDrawingColor1;

    @FXML
    private RadioButton rbLarge;
    
    @FXML
    private RadioButton rbCircle;

    @FXML
    private ToggleGroup togGrpSize1;

    @FXML
    private RadioButton rbSquare;

    @FXML
    private Button btnUndo;

    @FXML
    private Button btnClear;

    @FXML
    private Pane paneDraw;
    
    @FXML
    private Button btnSerialize;

    /**
     * clears the drawing area
     * 
     * @param event
     */
    @FXML
    void btnClearClicked(ActionEvent event) {

        paneDraw.getChildren().clear();
    }

    /**
     * undo the last added shape
     * 
     * @param event
     */
    @FXML
    void btnUndo(ActionEvent event) {

        if (!paneDraw.getChildren().isEmpty())
            paneDraw.getChildren().remove(paneDraw.getChildren().size() - 1);
    }
    
    /**
     * serialize the Circle objects
     *
     * @param event
     */    
    @FXML
    void btnSerialize(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("File name");
    	dialog.setHeaderText("File Name Format: "
    			+ "\n\t1. Starts with an uppercase letter"
    			+ "\n\t2. Followed by two or more letter"
    			+ "\n\t3. Followed by at least one more number"
    			+ "\n\t4. Followed by zero or more letters"
    			+ "\n\t5. Ends with .XML");
    	dialog.setContentText("Enter a file name: ");
    	
    	Optional<String> result = dialog.showAndWait();
    	Alert alert = new Alert(AlertType.INFORMATION);
    	
    	//clicked 'ok'
    	if (result.isPresent()) {
    		// file name matches
    		if(result.get().matches("[A-Z][a-zA-Z]{2,}\\d+[a-zA-Z]*[.][X][M][L]")) {
    			//open file, write ojects to it then close file
    			try(BufferedWriter output = Files.newBufferedWriter(Paths.get(result.get()))) {
    				//write DotList's XML to output
    				JAXB.marshal(dots, output);
				} catch (IOException e) {
					e.printStackTrace();
				}
    				
    			
    			alert.setTitle("Serialization");
        		alert.setHeaderText("Successfully written all circles to file " + result.get());
        		alert.setContentText("");
    		} 
    		
    		// file name failed to match
    		else {
        		alert.setTitle("Serialization cancelled");
        		alert.setHeaderText("File serialization cancelled by user or does not match RegEx");
        		alert.setContentText("");
			}

    	} 
    	
    	// clicked 'cancel'
    	else {
    		alert.setTitle("Serialization cancelled");
    		alert.setHeaderText("File serialization cancelled by user or does not match RegEx");
    		alert.setContentText("");
			
		}
    	alert.showAndWait();
    }

    /**
     * Called when mouse is dragged on the drawing panel
     * 
     * @param event
     */
    @FXML
    void drawPaneMouseDrag(MouseEvent event) {
    	
    	if (rbCircle.isSelected()) {
    		Dot record = new Dot(event.getX(), event.getY(), radius);
    		dots.getDots().add(record);
    		paneDraw.getChildren().add(new Circle(event.getX(), event.getY(), radius, fillColor));
            
    	} else {
    		Rectangle r = new Rectangle(radius, radius, fillColor);
    		r.setX(event.getX());
    		r.setY(event.getY());
    		paneDraw.getChildren().add(r);
    	}
    }

    @FXML
    void initialize() {

        assert rbBlack != null : "fx:id=\"rbBlack\" was not injected: check your FXML file 'Sample.fxml'.";
        assert togGrpColor != null : "fx:id=\"togGrpColor\" was not injected: check your FXML file 'Sample.fxml'.";
        assert rbRed != null : "fx:id=\"rbRed\" was not injected: check your FXML file 'Sample.fxml'.";
        assert groupDrawingColor != null : "fx:id=\"groupDrawingColor\" was not injected: check your FXML file 'Sample.fxml'.";
        assert rbGreen != null : "fx:id=\"rbGreen\" was not injected: check your FXML file 'Sample.fxml'.";
        assert rbBlue != null : "fx:id=\"rbBlue\" was not injected: check your FXML file 'Sample.fxml'.";
        assert rbSmall != null : "fx:id=\"rbSmall\" was not injected: check your FXML file 'Sample.fxml'.";
        assert togGrpSize != null : "fx:id=\"togGrpSize\" was not injected: check your FXML file 'Sample.fxml'.";
        assert rbMedium != null : "fx:id=\"rbMedium\" was not injected: check your FXML file 'Sample.fxml'.";
        assert groupDrawingColor1 != null : "fx:id=\"groupDrawingColor1\" was not injected: check your FXML file 'Sample.fxml'.";
        assert rbLarge != null : "fx:id=\"rbLarge\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnUndo != null : "fx:id=\"btnUndo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Sample.fxml'.";
        assert paneDraw != null : "fx:id=\"paneDraw\" was not injected: check your FXML file 'Sample.fxml'.";

        // change listener for the color toggle group
        togGrpColor.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (rbBlue.isSelected())
                    fillColor = Color.BLUE;
                else if (rbRed.isSelected())
                    fillColor = Color.RED;
                else if (rbGreen.isSelected())
                    fillColor = Color.GREEN;
                else
                    fillColor = Color.BLACK;
            }
        });

        // change listener for the size toggle group
        togGrpSize.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (rbLarge.isSelected())
                    radius = 15;
                else if (rbMedium.isSelected())
                    radius = 10;
                else
                    radius = 5;
            }
        });
    }
}