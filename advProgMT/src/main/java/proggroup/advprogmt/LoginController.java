package proggroup.advprogmt;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    @FXML
    Button focus;
    @FXML
    JFXTextField usernameField;
    @FXML
    JFXPasswordField passwordField;
    @FXML
    JFXTextField passwordShown;
    @FXML
    Button showPassBtn;
    @FXML
    Label errmsgLable;
    @FXML
    JFXButton loginBtn;
    @FXML
    ImageView hideIco;
    @FXML
    ImageView showIco;
    @FXML
    ImageView imgView;
    boolean visible = false;
    @FXML
    AnchorPane anchorPane;
    @FXML
    HBox hbox;
    @FXML
    public void closeWindow(){
        System.exit(0);
    }
    @FXML
    public void minimizeWindow(){
        ((Stage) anchorPane.getScene().getWindow()).setIconified(true);
    }
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void enter1(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER))
            passwordField.requestFocus();
    }
    @FXML
    public void enter2(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER)) {
            loginBtn.fire();
            loginBtn.requestFocus();
        }
    }

    @FXML
    protected void logIn(){
        if(User.validate(usernameField, passwordField, passwordShown, errmsgLable)) {
            if(User.getType().equals("Reader")){
                if (!Reader.validate(errmsgLable)){
                }else homeScreen();
            } else if (User.getType().equals("Librarian")) {
                homeScreen();
            }
        }
    }
    public void homeScreen(){
        try {
            new Alert("Welcome "+ User.getUserName(),"Login Successful!","green");
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene homeScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
            Stage window = (Stage)(loginBtn.getScene().getWindow());
            window.setTitle("Library System - Home - " + User.getType() + ": " + User.getUserName());
            window.setScene(homeScene);
            window.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showPass(){
        if(!visible){
            hideIco.setVisible(false);
            showIco.setVisible(true);
            passwordShown.setText(passwordField.getText());
            passwordShown.setVisible(true);
            passwordField.setVisible(false);
            visible=true;
        }else {
            hideIco.setVisible(true);
            showIco.setVisible(false);
            passwordField.setText(passwordShown.getText());
            passwordField.setVisible(true);
            passwordShown.setVisible(false);
            visible=false;
        }
    }
    public void initialize(){
        anchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        hbox.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        if (imgView != null){
            imgView.setImage(new Image("file:src/main/resources/proggroup/advprogmt/loginBG.jpg",false));
            Rectangle clip = new Rectangle();
            clip.setWidth(1280.0);
            clip.setHeight(720);
            clip.setArcHeight(40);
            clip.setArcWidth(40);
            clip.setStroke(Color.BLACK);
            imgView.setClip(clip);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = imgView.snapshot(parameters, null);
            imgView.setClip(null);
            imgView.setImage(image);
        }
    }
}