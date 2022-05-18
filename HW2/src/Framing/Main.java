package Framing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application{

    ArrayList<MealItem> mealItems = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{

        //setting all of our food into the list
        mealItems = addAllMealItems(mealItems);
        BorderPane borderPaneLayout = new BorderPane();

        //title at the top
        HBox title = addTitleBox();
        borderPaneLayout.setTop(title);

        Stage popupTotal = new Stage();
        popupTotal.initModality(Modality.APPLICATION_MODAL);
        popupTotal.initOwner(primaryStage);
        popupTotal.hide();
        VBox popupTextBox = new VBox(10);
        Text popupText = new Text("Your Order");
        popupText.setFont(Font.font(20));
        popupTextBox.getChildren().add(popupText);


        //daily special
        VBox dailySpecial = new VBox();
        dailySpecial.getChildren().add(0,new Label("Daily Special!"));
        dailySpecial.getChildren().add(1,new Label(mealItems.get(0).displayName));
        dailySpecial.getChildren().add(2,new Label("$" + mealItems.get(0).price));
        dailySpecial.getChildren().add(3,addDailySpecial(mealItems.get(0)));
        dailySpecial.setAlignment(Pos.TOP_CENTER);
        borderPaneLayout.setRight(dailySpecial);

        //starting on the left side, making rows of new content
        VBox rowOfItems = new VBox();
        rowOfItems.getChildren().add(0,addMealItem(mealItems.get(1)));
        rowOfItems.getChildren().add(1,addMealItem(mealItems.get(2)));
        rowOfItems.getChildren().add(2,addMealItem(mealItems.get(3)));
        rowOfItems.getChildren().add(3,addMealItem(mealItems.get(4)));
        borderPaneLayout.setLeft(rowOfItems);

        //adding the button to calculate total and open new window.
        Button submitButton = new Button("Place Order");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                //new window popup for totalling.
                double total = 0;
                int i = 1;
                for(Node n : rowOfItems.getChildren()){
                    HBox box = (HBox) n;

                    Spinner<Integer> spinner = (Spinner<Integer>) box.getChildren().get(2);
                    int spinnerValue = (int) spinner.getValueFactory().getValue();
                    if(spinnerValue >= 0){
                        MealItem item = mealItems.get(i);
                        popupTextBox.getChildren().add(new Text(spinnerValue + " " + item.displayName + "= $" + item.price*spinnerValue));
                        total+= item.price*spinnerValue;
                    }
                    i++;

                }
                VBox box = (VBox) dailySpecial.getChildren().get(3);
                Spinner<Integer> spinner = (Spinner<Integer>) box.getChildren().get(1);
                int spinnerValue = (int) spinner.getValueFactory().getValue();
                if(spinnerValue >= 0){
                    MealItem item = mealItems.get(0);
                    popupTextBox.getChildren().add(new Text(spinnerValue + " " + item.displayName + "= $" + item.price*spinnerValue));
                    total+= item.price*spinnerValue;
                }



                popupTextBox.getChildren().add(new Text("Total = $" + total));
                popupTextBox.setPadding(new Insets(5,5,5,15));
                Scene popupScene = new Scene(popupTextBox, 300, 200);
                popupTotal.setScene(popupScene);
                popupTotal.show();

            }
        };
        submitButton.setOnAction(event);
        borderPaneLayout.setBottom(addSubmitButton(submitButton));



        //finally finishing the stage and showing
        primaryStage.setTitle("Taco Bell Online Ordering");
        Image tacoBellLogo1 = new Image(new FileInputStream("src/Data/TacoBellLogo1.jpg"));
        primaryStage.getIcons().add(tacoBellLogo1);
        primaryStage.setScene(new Scene(borderPaneLayout, 1000, 1000));
        primaryStage.show();
    }



    private HBox addSubmitButton(Button button) {
        HBox submitBox = new HBox();

        submitBox.alignmentProperty().set(Pos.CENTER);

        submitBox.setPadding(new Insets(5,5,20,5));
        button.setFont(Font.font(30));

        submitBox.getChildren().add(button);

        return submitBox;
    }

    private ArrayList<MealItem> addAllMealItems(ArrayList<MealItem> list) throws FileNotFoundException {
        list.add(new MealItem("Bacon Chalupa", 1.89, new Image(new FileInputStream("src/Data/BaconChalupa.jpg"))));
        list.add(new MealItem("Quesarito", 4.29, new Image(new FileInputStream("src/Data/Quesarito.jpg"))));
        list.add(new MealItem("Nachos", 1.49, new Image(new FileInputStream("src/Data/Nachos.jpg"))));
        list.add(new MealItem("Baja Blast", 2.19, new Image(new FileInputStream("src/Data/BajaBlast.jpg"))));
        list.add(new MealItem("Taco", 0.99, new Image(new FileInputStream("src/Data/Taco.jpg"))));
        return list;

    }

    private VBox addDailySpecial(MealItem item){
        VBox dailySpecial = new VBox();

        ImageView view = new ImageView(item.image);
        view.setFitWidth(200);
        view.setFitHeight(200);

        Spinner<Integer> spinner = new Spinner<>();
        SpinnerValueFactory<Integer> field = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2000, 0);
        spinner.setValueFactory(field);

        dailySpecial.getChildren().add(0,view);
        dailySpecial.getChildren().add(1,spinner);
        dailySpecial.setPadding(new Insets(15,15,15,15));

        return dailySpecial;
    }

    private HBox addMealItem(MealItem item){
        HBox foodItem = new HBox();

        Label label = new Label(item.displayName + " $" + item.price);
        label.setPadding(new Insets(25,10,25,5));

        ImageView view = new ImageView(item.image);
        view.setFitWidth(100);
        view.setFitHeight(100);
        label.setLabelFor(view);

        Spinner<Integer> spinner = new Spinner<>();
        SpinnerValueFactory<Integer> field = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2000, 0);
        spinner.setValueFactory(field);

        foodItem.getChildren().add(0,label);
        foodItem.getChildren().add(1,view);
        foodItem.getChildren().add(2,spinner);
        foodItem.setPadding(new Insets(15,15,15,15));

        return foodItem;
    }

    public HBox addTitleBox(){
        HBox titleBox = new HBox();

        titleBox.alignmentProperty().set(Pos.CENTER);

        Text title = new Text("Taco Bell");
        title.setFont(Font.font(40));

        titleBox.getChildren().add(title);

        return titleBox;

    }

    public static void main(String[] args) {
        launch(args);
    }
}


