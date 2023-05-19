package proggroup.advprogmt;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    Stage MainStage;;
    Scene loginScene;
    @Override
    public void start(Stage mainStage){
        MainStage=mainStage;
        mainSetup();
        mainStage.show();
    }
    public void mainSetup(){
        MainStage.setTitle("Library System - Login");

        MainStage.setResizable(false);
        MainStage.getIcons().add(new Image(Main.class.getResourceAsStream("online-library.png")));

        loginWindow();
        MainStage.setScene(loginScene);
        MainStage.initStyle(StageStyle.TRANSPARENT);
    }

    public void loginWindow(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            loginScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
            loginScene.getStylesheets().add(this.getClass().getResource("styling.css").toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}