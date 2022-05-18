
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Junk extends Application {
    TextField tfSeconds = new TextField();

    Timeline animation = new Timeline(
            new KeyFrame(Duration.millis(1000), e -> {
                int seconds = Integer.parseInt(tfSeconds.getText());
                tfSeconds.setText(seconds - 1 + "");
                if (seconds - 1 <= 0) {
                    stopAnimation();
                }
            }));
    String absolute = "file:///C://_code//ClassExamples03312020//assets//us.mp3";
    //String relative = "./assets//us.mp3";
    private final static String URLBase =
            "https://liveexample.pearsoncmg.com/common"+"/audio/anthem/anthem" + 6 + ".mp3";
    // Media media = new Media("https://liveexample.pearsoncmg.com/common/audio/anthem/anthem/1.mp3");
    Media media;
    MediaPlayer mp;


    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        //media = new Media(URLBase);
        media = new Media(absolute);

        mp = new MediaPlayer(media);

        mp.setCycleCount(Timeline.INDEFINITE);

        animation.setCycleCount(Timeline.INDEFINITE);

        tfSeconds.setAlignment(Pos.CENTER);
        tfSeconds.setOnAction(e -> {
            animation.play();
        });

        tfSeconds.setFont(Font.font("Times", 35));

        // Create a scene and place it in the stage
        Scene scene = new Scene(tfSeconds, 200, 100);
        primaryStage.setTitle("Exercise16_21"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void stopAnimation() {
        animation.stop();

        mp.play();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
