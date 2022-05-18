package Application.Handlers;

import Application.AppItems.MyButton;
import Application.Window.OrderWindow;
import Repository.DatabaseItems.OrderItem;
import Repository.SQLRepository;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainMenuHandler implements EventHandler<Event> {

    private Stage primaryStage;
    private OrderItem tempitem;
    private SQLRepository repository;

    public MainMenuHandler(Stage primaryStage) throws SQLException, ClassNotFoundException {
        setPrimaryStage(primaryStage);
        setRepository(new SQLRepository());
    }

    @Override
    public void handle(Event event) {
        String eventType = event.getEventType().toString();
        Object source = event.getSource();
        if (source instanceof TextField) {
            TextField field = (TextField) source;
            if(field.getText() != null) {

                HBox box = (HBox) field.getParent();
                Label invisLabel = (Label) box.getChildren().get(0);
                long menuItemId = Long.parseLong(invisLabel.getText());
                long nextId = -1;
                try { nextId = getRepository().getOrderMaxId(); } catch (SQLException throwables) {}



            }
        }
        if (source instanceof MyButton) {
            MyButton button = (MyButton) source;
            switch (button.getText()) {
                case "Order":
                    if (eventType == "ACTION") {
                        new OrderWindow(getPrimaryStage(), getTempitem());
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

    public OrderItem getTempitem() {
        return tempitem;
    }

    public void setTempitem(OrderItem tempitem) {
        this.tempitem = tempitem;
    }

    public SQLRepository getRepository() {
        return repository;
    }

    public void setRepository(SQLRepository repository) {
        this.repository = repository;
    }
}
