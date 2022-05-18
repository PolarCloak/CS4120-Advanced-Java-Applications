package Application;

import Application.AppItems.MyButton;
import Application.Handlers.MainMenuHandler;
import Repository.DatabaseItems.MenuItem;
import Repository.DatabaseItems.OrderItem;
import Repository.SQLRepository;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class App extends Application {

    public static final double HEIGHT = 1000;
    public static final double WIDTH = 1280;
    //trying to get the scroll to go down further and show more empty space
    public static final double ScrollPaneHeight = 4000;
    public static final double PictureHeight = 200;
    public static final double PictureWidth = 200;

    private Stage primaryStage;
    private Scene mainScene;
    private ScrollPane scrollPane;
    private Pane basePane;
    private SQLRepository repository;
    private EventHandler handler;

    private long specialId;
    public static final double specialDiscountPercent = 0.75;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setRepository(new SQLRepository());
        setHandler(new MainMenuHandler(primaryStage));

        setSpecialId(generateRandomIdForSpecial(repository.getMenuItemMaxId()));

        setPrimaryStage(primaryStage);
        setBasePane(buildPane());
        setScrollPane(buildScrollPane());

        setMainScene(new Scene(scrollPane, WIDTH, ScrollPaneHeight));
        getMainScene().getStylesheets().add("assets/MainMenuStyle.css");
        getPrimaryStage().setScene(getMainScene());
        getPrimaryStage().setHeight(HEIGHT);
        getPrimaryStage().setWidth(WIDTH);
        getPrimaryStage().show();
    }

    private Pane buildPane() throws SQLException {
        Pane pane = new Pane();
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);

        pane.getChildren().add(buildTitle());
        pane.getChildren().add(buildTopBody());
        pane.getChildren().add(buildMainBody());
        pane.getChildren().add(buildBottom());

        return pane;
    }

    private Node buildBottom() {
        HBox bottom = new HBox();

        return bottom;
    }

    private Node buildTopBody() throws SQLException {
        MenuItem originalItem = repository.getMenuItem(getSpecialId());
        MenuItem specialItem = editSpecialItem(originalItem);

        HBox topBody = buildItemContainer(specialItem);
        VBox details = (VBox) topBody.getChildren().get(2);

        Label name = (Label) details.getChildren().get(0);
        name.setText("SPECIAL: " + specialItem.getName());

        Label price = (Label) details.getChildren().get(2);
        price.setText("$"+String.format("%.2f", specialItem.getPrice()));

        Text strike = buildStrikeText(originalItem);
        strike.setId("strikeText");
        details.getChildren().add(2,strike);

        topBody.setId("topBody");
        return topBody;
    }

    private Node buildMainBody() throws SQLException {
        VBox body = new VBox();
        body.setId("body");

        ArrayList<MenuItem> menuItems = getRepository().getAllMenuItems();
        for(int i = 0; i<menuItems.size(); i++){
            MenuItem item = menuItems.get(i);
            if(item.isIncluded() && item.getId() != getSpecialId()){
                HBox itemContainer = buildItemContainer(item);
                body.getChildren().add(itemContainer);
            }
        }
        return body;
    }

    private Node buildTitle() {
        HBox title = new HBox();
        title.setId("title");

        Label label = new Label("Menu");
        label.setId("titleLabel");

        MyButton order = new MyButton("Order",getHandler());
        order.setId("orderButton");

        title.getChildren().add(label);
        title.getChildren().add(order);
        title.setMinWidth(WIDTH);

        return title;
    }

    private ScrollPane buildScrollPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(basePane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToHeight(true);
        //trying to get the scroll to go down further and show more empty space
        scrollPane.setMaxHeight(ScrollPaneHeight);
        return scrollPane;
    }

    private long generateRandomIdForSpecial(long max){
        Random r = new Random();
        long random = r.nextLong()%max;
        return Math.abs(random) + 1;
    }

    private HBox buildItemContainer(MenuItem item){
        HBox itemContainer = new HBox();
        itemContainer.setId("menuItem");

        //setting up the label that I will use to find the item via ID
        Label idLabel = new Label(Long.toString(item.getId()));
        idLabel.setVisible(false);
        idLabel.setManaged(false);
        itemContainer.getChildren().add(0,idLabel);

        //setting up the image
        Pane menuItemImage = new Pane();
        Image itemImage = null;
        try{ itemImage = new Image(item.getPictureFilename());}
        catch(Exception e){itemImage = new Image("assets/img/menu_items/missing.png");}
        ImageView itemImageView = new ImageView(itemImage);
        itemImageView.setFitWidth(PictureWidth);
        itemImageView.setFitHeight(PictureHeight);
        itemImageView.setTranslateX(2);
        itemImageView.setTranslateY(2);
        menuItemImage.getChildren().add(itemImageView);
        menuItemImage.setMaxWidth(PictureWidth+4);
        menuItemImage.setMaxHeight(PictureHeight+4);
        menuItemImage.setId("menuItemImage");

        //setting up the vbox that has all the details
        VBox menuItemDetails = new VBox();

        //first detail is the name
        Label menuItemName = new Label(item.getName());
        menuItemName.setId("menuItemName");

        //then the description
        Label menuItemDescription = new Label(item.getDescription());
        menuItemDescription.setId("menuItemDescription");

        //then the price
        Label menuItemPrice = new Label("$"+String.format("%.2f", item.getPrice()));
        menuItemPrice.setId("menuItemPrice");

        //add the 3 items to the details
        menuItemDetails.getChildren().add(menuItemName);
        menuItemDetails.getChildren().add(menuItemDescription);
        menuItemDetails.getChildren().add(menuItemPrice);
        menuItemDetails.setId("menuItemDetails");

        //add the input box
        TextField menuItemInput = new TextField("0");
        menuItemInput.setId("menuItemInput");

        //add all of these items to the item container
        itemContainer.getChildren().addAll(menuItemImage,menuItemDetails,menuItemInput);

        return itemContainer;

    }

    private MenuItem editSpecialItem(MenuItem specialItem) throws SQLException {
        MenuItem editedItem = getRepository().getMenuItem(specialItem.getId());
        editedItem.setPrice(specialItem.getPrice() * specialDiscountPercent);
        return editedItem;
    }

    private Text buildStrikeText(MenuItem originalitem){
        Text strikeText = new Text("$" + String.format("%.2f", originalitem.getPrice()));
        strikeText.setStrikethrough(true);
        strikeText.setId("strikeText");
        return strikeText;
    }


    public SQLRepository getRepository() {
        return repository;
    }

    public void setRepository(SQLRepository repository) {
        this.repository = repository;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Pane getBasePane() {
        return basePane;
    }

    public void setBasePane(Pane basePane) {
        this.basePane = basePane;
    }

    public EventHandler getHandler() {
        return handler;
    }

    public void setHandler(EventHandler handler) {
        this.handler = handler;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(long specialId) {
        this.specialId = specialId;
    }
}
