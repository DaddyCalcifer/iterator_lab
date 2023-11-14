package com.example.iterator_lab;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView screen;

    public ConcreteAggregate conaggr = new ConcreteAggregate("img");
    public Iterator iter = conaggr.getIterator();
    public Timeline time =new Timeline();

    @FXML
    protected void onHelloButtonClick()
    {
        //welcomeText.setText("Welcome to JavaFX Application!");
        screen.setImage((Image)iter.next());
        //EvHandler();
    }
    @FXML
    protected void prevButtonClick()
    {
        screen.setImage((Image)iter.preview());
        //EvHandler();
    }
    public void EvHandler()
    {
        time.setCycleCount(Timeline.INDEFINITE); //кол-во повторов
        time.getKeyFrames().add(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (iter.hasNext(1))
                    screen.setImage((Image)
                            iter.next());
            }
        }));
    }
}