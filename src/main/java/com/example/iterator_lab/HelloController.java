package com.example.iterator_lab;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView screen;
    ConcreteAggregate conaggr;
    public Iterator iter;
    boolean isReversed;
    public Timeline time =new Timeline();
    ScaleTransition trans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isReversed=true;
        time = new Timeline(new KeyFrame(new Duration(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(isReversed) {
                    screen.setImage((Image) iter.next());

                }
                isReversed=!isReversed;
                setTransitionOpt();
            }
        }));
        conaggr = new ConcreteAggregate();
        trans = new ScaleTransition(Duration.seconds(2), screen);
        iter = conaggr.getIterator();
        time.setCycleCount(Timeline.INDEFINITE);
        onHelloButtonClick();
    }

    @FXML
    protected void onHelloButtonClick()
    {
        screen.setImage((Image)iter.next());
    }
    public void startShow() {
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
        setTransitionOpt();
    }
    public void endShow() {
        time.stop();
        trans.stop();
        isReversed=true;
    }
    @FXML
    protected void prevButtonClick()
    {
        screen.setImage((Image)iter.preview());
    }
    public void setTransitionOpt()
    {
        trans.setFromX(1.0);
        trans.setToX(0.0);
        trans.setFromY(1.0);
        trans.setToY(0.0);
        trans.setCycleCount(ScaleTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();
    }
    private class ShowHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            if( iter.hasNext()) {
                Image name = (Image) iter.next();
                screen.setImage(name);
            }
        }

    }
}