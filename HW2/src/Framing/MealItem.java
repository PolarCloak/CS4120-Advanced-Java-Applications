package Framing;

import javafx.scene.image.Image;

public class MealItem{

    String displayName;
    double price;
    Image image;

    public MealItem(String displayName, double price, Image image) {
        this.displayName = displayName;
        this.price = price;
        this.image = image;
    }
}
