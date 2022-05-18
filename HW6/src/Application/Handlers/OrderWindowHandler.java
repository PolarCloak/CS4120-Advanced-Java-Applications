package Application.Handlers;


import Application.AppItems.MyButton;
import Application.Window.OrderWindow;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class OrderWindowHandler implements EventHandler<Event> {

    private Stage primaryStage;

    public OrderWindowHandler(Stage primaryStage) {
        setPrimaryStage(primaryStage);
    }

    @Override
    public void handle(Event event) {
        String eventType = event.getEventType().toString();
        Object source = event.getSource();
        if (source instanceof MyButton) {
            MyButton button = (MyButton) source;
            switch (button.getText()) {
                case "Confirm":
                    if (eventType == "ACTION") {

                    }
                    break;
                case "Cancel":
                    if (eventType == "ACTION") {
                        button.getScene().getWindow().hide();
                    }
                    break;
            }
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}