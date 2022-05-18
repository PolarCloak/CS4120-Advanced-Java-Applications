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

public class PlaySong extends Application {

    TextField tfSeconds = new TextField();

    Timeline animation = new Timeline(
            new KeyFrame(Duration.millis(1000), e->{
                int seconds = Integer.parseInt(tfSeconds.getText());
                tfSeconds.setText(seconds - 1 + "");
                if (seconds - 1 <= 0){
                    stopAnimation();
                }
            })
    );

    Media media;
    MediaPlayer mp;

    private final static String URLBase =
            "http://liveexample.pearsoncmg.com/common/audio/anthem/anthem/6.mp3";

   String absolute = "file:///D://__working//_code//_CS4120_Spring_2021//ClassMeeting03232021//Assets//us.mp3";
    public void start(Stage primaryStage){
        media = new Media(absolute);
        mp = new MediaPlayer(media);
        mp.setCycleCount(Timeline.INDEFINITE);

        animation.setCycleCount(Timeline.INDEFINITE);

        tfSeconds.setAlignment(Pos.CENTER);
        tfSeconds.setOnAction(e-> {
            animation.play();
        });

        tfSeconds.setFont(Font.font("Times", 35));

        //Scene and stage
        Scene scene = new Scene(tfSeconds, 300, 200);
        primaryStage.setTitle("Play Anthem");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void stopAnimation(){
        animation.stop();
        mp.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
