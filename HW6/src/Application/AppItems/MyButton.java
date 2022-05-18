package Application.AppItems;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

public class MyButton extends Button {

    public MyButton(String name, EventHandler handler){
        this.setText(name);
        this.setId("myButton");
//        this.setBackground(Background.EMPTY);
        this.setEventHandler(EventType.ROOT, handler);
    }
}
