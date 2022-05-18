//You will need to add the necessary imports.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Midterm_2 extends Application{

    @Override
    public void start(Stage primaryStage) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);
        TextField tfNumber1 = new TextField();
        TextField tfNumber2 = new TextField();
        TextField tfResult = new TextField();
        tfResult.setEditable(false);
        tfNumber1.setPrefColumnCount(3);
        tfNumber2.setPrefColumnCount(3);
        tfResult.setPrefColumnCount(3);
        pane.getChildren().addAll(new Label("Number 1: "), tfNumber1,
                new Label("Number 2: "), tfNumber2, new Label("Result: "), tfResult);


        // Create four buttons
        HBox hBox = new HBox(5);
        Button btAdd = new Button("Add");
        Button btSubtract = new Button("Subtract");
        Button btMultiply = new Button("Multiply");
        Button btDivide = new Button("Divide");
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);

        //button event handling
        EventHandler<ActionEvent> btnHandler = event -> {
            Button sourceButton = (Button) event.getSource();
            double result = 0;
            switch (sourceButton.getText()){
                case "Add":
                    result = safeGetFromTf(tfNumber1) + safeGetFromTf(tfNumber2);
                    break;
                case "Subtract":
                    result = safeGetFromTf(tfNumber1) - safeGetFromTf(tfNumber2);
                    break;
                case "Multiply":
                    result = safeGetFromTf(tfNumber1) * safeGetFromTf(tfNumber2);
                    break;
                case "Divide":
                    result = safeGetFromTf(tfNumber1) / safeGetFromTf(tfNumber2);
                    break;
            }
            tfResult.setText(result + "");
        };

        btAdd.setOnAction(btnHandler);
        btSubtract.setOnAction(btnHandler);
        btMultiply.setOnAction(btnHandler);
        btDivide.setOnAction(btnHandler);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.TOP_CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 350, 150);
        primaryStage.setTitle("Calculator"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        //put button handling code here. Anonymous inner classes work nicely!
    }//end start

    public static void main(String[] args) {
        launch(args);
    }

    public double safeGetFromTf(TextField tf){
        if(tf.getText().isEmpty()){
            return 0;
        }
        else{
            return Double.valueOf(tf.getText());
        }
    }

}