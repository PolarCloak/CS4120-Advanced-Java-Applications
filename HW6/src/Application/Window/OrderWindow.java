package Application.Window;

import Application.AppItems.MyButton;
import Application.Handlers.OrderWindowHandler;
import Repository.DatabaseItems.OrderItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrderWindow extends Stage {

    public static final double HEIGHT = 400;
    public static final double WIDTH = 400;

    private Stage primaryStage;
    private Scene orderScene;
    private Pane basePane;
    private OrderWindowHandler handler;


    public OrderWindow(Stage primaryStage, OrderItem tempitem) {
        //add this popup onto the first stage
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(primaryStage);
        setPrimaryStage(primaryStage);
        setHandler(new OrderWindowHandler(primaryStage));
        this.setResizable(false);

        //build the scene and pane off of this stage
        setBasePane(buildPane());
        setOrderScene(new Scene(getBasePane(), WIDTH, HEIGHT));


        getOrderScene().getStylesheets().add("assets/MainMenuStyle.css");
        this.setTitle("Confirm Exit");
        this.setScene(getOrderScene());
        this.show();
    }

    private Pane buildPane() {
        Pane basePane = new Pane();
        MyButton confirmOrder = new MyButton("Confirm", handler);
        MyButton cancelOrder = new MyButton("Cancel", handler);

        //vbox
        VBox vBox = new VBox(40);

        Label confirmText = new Label("Would you like to confirm this order?");
        confirmText.setId("confirmText");
        vBox.getChildren().add(confirmText);



        HBox buttons = new HBox(40);
        buttons.getChildren().addAll(confirmOrder, cancelOrder);
        buttons.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(buttons);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(7, 0, 0, 0));

        basePane.getChildren().add(vBox);
        basePane.setId("popupWindow");
        return basePane;

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getOrderScene() {
        return orderScene;
    }

    public void setOrderScene(Scene orderScene) {
        this.orderScene = orderScene;
    }

    public Pane getBasePane() {
        return basePane;
    }

    public void setBasePane(Pane basePane) {
        this.basePane = basePane;
    }

    public OrderWindowHandler getHandler() {
        return handler;
    }

    public void setHandler(OrderWindowHandler handler) {
        this.handler = handler;
    }
}
