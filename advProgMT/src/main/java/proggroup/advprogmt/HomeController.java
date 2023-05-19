package proggroup.advprogmt;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
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
import java.util.ArrayList;

public class HomeController {
    Button tempBtnIgnore = new Button();
    Label tempLabelIgnore = new Label();
    Button tempBtnIgnoreRent = new Button();
    Button tempBtnIgnoreRemove = new Button();
    String tempStrIgnore;
    String tempStrIgnore1;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button focusBtn;
    @FXML
    HBox hbox;
    @FXML
    ImageView imgView;
    @FXML
    Label usernameDisplay;
    @FXML
    Label typeDisplay;
    @FXML
    Pane homePane;
    @FXML
    Pane mainPane;
    @FXML
    Pane settingsPane;
    @FXML
    GridPane gridPane;
    @FXML
    HBox settingsBtns;
    @FXML
    Pane adduserPane;
    @FXML
    Pane addbookPane;
    @FXML
    Pane bookOL;
    @FXML
    JFXButton booksBtn;
    @FXML
    JFXButton usersBtn;
    @FXML
    TextField searchField;
    @FXML
    JFXButton searchBtn;
    @FXML
    ImageView usersIco;
    Search search = new Search();
    @FXML
    ListView<HomeController.HBoxCell> bookListview;
    @FXML
    ListView<HomeController.HBoxCell> userListview;
    User user = new User();
    @FXML
    JFXButton settingsBtn;
    @FXML
    ImageView settingsIco;
    @FXML
    JFXButton addUserBtn;
    @FXML
    JFXButton addBookBtn;
    @FXML
    JFXButton bookOLBtn;
    @FXML
    TextField userNameField;
    @FXML
    PasswordField passField;
    @FXML
    TextField passFieldShown;
    @FXML
    ImageView hideIco;
    @FXML
    ImageView showIco;
    @FXML
    Button showPassBtn;
    @FXML
    JFXComboBox type;
    String Type;
    @FXML
    TextField firstnameField;
    @FXML
    Label usernameErr;
    @FXML
    Label passErr;
    @FXML
    Label typeErr;
    @FXML
    Label firstnameErr;
    @FXML
    Label lastnameErr;
    @FXML
    Label emailErr;
    @FXML
    Label cellphoneErr;
    @FXML
    Label addressErr;
    @FXML
    TextField lastnameField;
    @FXML
    TextField emailField;
    @FXML
    TextField cellphoneField;
    @FXML
    TextField addressField;
    @FXML
    JFXCheckBox blockedCB;
    @FXML
    TextField booktitleField;
    @FXML
    Label bookfieldErr;
    @FXML
    JFXButton addBtn1;
    @FXML
    Label userAdded;
    @FXML
    Label bookAdded;
    @FXML
    Button saveBtn;
    @FXML
    Button backBtn;
    @FXML
    Label userEdited;
    @FXML
    ListView<HomeController.HBoxCell> olListview;
    @FXML
    Line line1;
    @FXML
    Button logoutBtn;
    Scene loginScene;
    Librarian librarian = new Librarian();
    Book book = new Book();
    boolean visible = false;
    static boolean inBooks = true;
    static boolean userExist = false;
    static boolean bookExist = false;
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
    public void enter(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER))
            searchBtn.fire();
    }
    public void checkType(){
        settingsBtn.setVisible(!User.getType().equals("Reader"));
        settingsIco.setVisible(!User.getType().equals("Reader"));
        line1.setVisible(!User.getType().equals("Reader"));
        usersBtn.setVisible(!User.getType().equals("Reader"));
        usersIco.setVisible(!User.getType().equals("Reader"));
    }
    public void displayBooks(){
        setvisible("main");
        booksBtn.getStyleClass().add("selected");
        clearFields();
        searchField.setPromptText("Search For Books");
        bookListview.setVisible(true);
        inBooks = true;
        search.viewallBooks();
        bookListview.setItems(user.searchBooks());
        hboxCell(bookListview,"Rent");
        bookListview.setClip(roundedListview(957,548));
    }
    public void displayUsers(){
        setvisible("main");
        usersBtn.getStyleClass().add("selected");
        clearFields();
        searchField.setPromptText("Search For Users");
        userListview.setVisible(true);
        inBooks = false;
        search.viewallUsers();
        userListview.setItems(librarian.searchUsers());
        hboxCell(userListview,"Edit");
        userEdited.setText("");
        userListview.setClip(roundedListview(957,548));
    }
    public void back(){
        settingsPane.setVisible(false);
        setvisible("none");
//        setvisible("settings");
        displayUsers();
    }
    public void search(){
        if (inBooks){
            search.searchforBooks(searchField.getText());
            if (search.i==0) {
                bookListview.setItems(user.searchBooks());
                hboxCell(bookListview,"Rent");
            }else searchResult();
        }else {
            search.searchforUsers(searchField.getText());
            if (search.i==0) {
                userListview.setItems(librarian.searchUsers());
                hboxCell(userListview,"Edit");
            }else searchResult();
        }
    }
    public void searchResult(){
        ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
        list.add(new HomeController.HBoxCell(search.result));
        ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
        userListview.setItems(myObservableList);
    }
    public void settings(){
        setvisible("settings");
        settingsBtn.getStyleClass().add("selected");
    }
    public void newUser(){
        clearFields();
        setvisible("settings");
        settingsBtn.getStyleClass().add("selected");
        addUserBtn.getStyleClass().add("selected");
        addBookBtn.getStyleClass().remove("selected");
        bookOLBtn.getStyleClass().remove("selected");
        showPassBtn.setVisible(true);
        showIco.setVisible(true);
        hideIco.setVisible(true);
        showPassBtn.setVisible(true);
        adduserPane.setVisible(true);
        gridPane.setVisible(true);
        userAdded.setVisible(true);
        addBtn1.setVisible(true);
    }
    public void saveUser(){
        userAdded.setText("");
        if (User.validate(userNameField,usernameErr,passField,passFieldShown,passErr,type.getValue(),typeErr,firstnameField,firstnameErr,lastnameField,lastnameErr,emailField,emailErr,cellphoneField,cellphoneErr,addressField,addressErr)){
            try {
                type.getValue().toString();
                Type = type.getValue().toString();
            } catch (NullPointerException | NumberFormatException e) {
            }
            try {
                search.viewallUsers();
                for (String i : Search.usersArr) {
                    if (userNameField.getText().equals(i)) {
                        userExist = true;
                        break;
                    } else userExist = false;
                }
                if (!userExist) {
                    passField.setText(passFieldShown.getText());
                    librarian.addUser(new User(userNameField.getText(), passField.getText(), Type, firstnameField.getText(), lastnameField.getText(), addressField.getText(), cellphoneField.getText(), emailField.getText(), false).getUserData());
                    clearFields();
                    userAdded.setText("User added");
                    userAdded.setStyle("-fx-text-fill:limegreen");
                    userExist = false;
                } else {
                    userAdded.setText("Username already exists");
                    userAdded.setStyle("-fx-text-fill:red");
                }
            } catch (IOException | NumberFormatException e) {
            }
        }
    }
    public void newBook(){
        clearFields();
        setvisible("settings");
        addbookPane.setVisible(true);
        settingsBtn.getStyleClass().add("selected");
        addUserBtn.getStyleClass().remove("selected");
        addBookBtn.getStyleClass().add("selected");
        bookOLBtn.getStyleClass().remove("selected");
    }
    public void saveBook(){
        bookAdded.setText("");
        if (!User.checkEmpty(booktitleField,bookfieldErr)){
            search.viewallBooks();
            for (String i : Search.booksArr) {
                if (booktitleField.getText().equals(i)) {
                    bookExist = true;
                    break;
                } else bookExist = false;
            }if(bookExist){
                bookAdded.setText("Book already exists");
                bookAdded.setStyle("-fx-text-fill:red");
            }else {
                try {
                    book.addBook(new Book(booktitleField.getText()).getTitle());
                    clearFields();
                    bookAdded.setText("Book Added");
                    bookAdded.setStyle("-fx-text-fill:limegreen");
                } catch (NullPointerException e) {
                }
            }
        }
    }
    public void orderList(){
        clearFields();
        setvisible("settings");
        bookOL.setVisible(true);
        olListview.setItems(librarian.bookList());
        olListview.setClip(roundedListview(957,463));
        hboxCell(olListview);
        settingsBtn.getStyleClass().add("selected");
        addUserBtn.getStyleClass().remove("selected");
        addBookBtn.getStyleClass().remove("selected");
        bookOLBtn.getStyleClass().add("selected");
    }
    private void clearFields() {
        searchField.clear();
        userNameField.clear();
        type.getSelectionModel().clearSelection();
        passField.clear();
        firstnameField.clear();
        lastnameField.clear();
        emailField.clear();
        cellphoneField.clear();
        addressField.clear();
        booktitleField.clear();
        blockedCB.setSelected(false);
        typeErr.setText("");
        usernameErr.setText("");
        defaultStyle(userNameField);
        passErr.setText("");
        defaultStyle(passField);
        passFieldShown.setText("");
        defaultStyle(passFieldShown);
        firstnameErr.setText("");
        defaultStyle(firstnameField);
        lastnameErr.setText("");
        defaultStyle(lastnameField);
        emailErr.setText("");
        defaultStyle(emailField);
        cellphoneErr.setText("");
        defaultStyle(cellphoneField);
        addressErr.setText("");
        defaultStyle(addressField);
        userAdded.setText("");
        bookfieldErr.setText("");
        defaultStyle(booktitleField);
        bookAdded.setText("");
    }
    private void defaultStyle(TextField field){
        field.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;");
    }
    public void hboxCell(ListView<HomeController.HBoxCell> listView,String btnlbl){
        for(Node entity: listView.getItems()){
            for(Node nested: ((HBoxCell)entity).getChildren()){
                if(nested.getClass() == tempLabelIgnore.getClass()){
                    tempStrIgnore = ((Label) nested).getText();
                }
                else if(nested.getClass() == tempBtnIgnore.getClass()){
                    if(((Button) nested).getText().equals(btnlbl)){
                        tempBtnIgnoreRent = ((Button) nested);
                        setEvent(tempStrIgnore, "",tempBtnIgnoreRent, tempBtnIgnoreRemove);
                    }
                }
            }
        }
        for(Node entity: listView.getItems() ){
            for(Node nested: ((HBoxCell)entity).getChildren()){
                if(nested.getClass() == tempLabelIgnore.getClass()){
                    tempStrIgnore = ((Label) nested).getText();
                } else if(nested.getClass() == tempBtnIgnore.getClass()) {
                    if (((Button) nested).getText().equals("Remove")) {
                        tempBtnIgnoreRemove = ((Button) nested);
                        setEvent(tempStrIgnore, "",tempBtnIgnoreRent, tempBtnIgnoreRemove);
                    }
                }
            }
        }
    }
    public void hboxCell(ListView<HomeController.HBoxCell> listView){
        int i=0;
        for(Node entity: listView.getItems() ){
            for(Node nested: ((HBoxCell)entity).getChildren()){
                if(nested.getClass() == tempLabelIgnore.getClass()){
                    if (i % 2 == 0){
                        tempStrIgnore1 = ((Label) nested).getText();
                    }else tempStrIgnore = ((Label) nested).getText();
                } else if(nested.getClass() == tempBtnIgnore.getClass()) {
                    if (((Button) nested).getText().equals("✔")) {
                        tempBtnIgnoreRent = ((Button) nested);
                        setEvent(tempStrIgnore,tempStrIgnore1,tempBtnIgnoreRent, tempBtnIgnoreRemove);
                    }
                }
                i++;
            }
        }
        i=0;
        for(Node entity: listView.getItems() ){
            for(Node nested: ((HBoxCell)entity).getChildren()){
                if(nested.getClass() == tempLabelIgnore.getClass()){
                    if (i % 2 == 0){
                        tempStrIgnore1 = ((Label) nested).getText();
                    }else tempStrIgnore = ((Label) nested).getText();
                } else if(nested.getClass() == tempBtnIgnore.getClass()) {
                    if (((Button) nested).getText().equals("X")) {
                        tempBtnIgnoreRemove = ((Button) nested);
                        setEvent(tempStrIgnore, tempStrIgnore1,tempBtnIgnoreRent, tempBtnIgnoreRemove);
                    }
                }
                i++;
            }
        }
    }
    public void setEvent(String lbl,String lbl1, Button btn1, Button btn2){
        if (btn1.getText().equals("Edit")){
            btn1.setOnAction(e -> {
                setvisible("none");
                clearFields();
                setvisible("edit");
                focusBtn.requestFocus();
                String[] data;
                userNameField.setText(lbl);
                showPassBtn.setVisible(true);
                hideIco.setVisible(true);
                showPassBtn.setVisible(true);
                try {
                    data = User.requestInfo(lbl);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                passField.setText(data[1]);

                firstnameField.setText(data[3]);
                type.setValue(data[2]);
                lastnameField.setText(data[4]);
                addressField.setText(data[5]);
                cellphoneField.setText(data[6]);
                emailField.setText(data[7]);
                if(data[8].equals("true"))
                    blockedCB.setSelected(true);
                else
                    blockedCB.setSelected(false);

                saveBtn.setOnAction(y -> {
                    userEdited.setText("");
                    if (User.validate(userNameField,usernameErr,passField,passFieldShown,passErr,type.getValue(),typeErr,firstnameField,firstnameErr,lastnameField,lastnameErr,emailField,emailErr,cellphoneField,cellphoneErr,addressField,addressErr)){
                        try {
                            Type = type.getValue().toString();
                        } catch (NullPointerException | NumberFormatException exc) {
                        }
                        try {
                            Object[] newData = {
                                    userNameField.getText(),
                                    passField.getText(),
                                    type.getValue().toString(),
                                    firstnameField.getText(),
                                    lastnameField.getText(),
                                    addressField.getText(),
                                    cellphoneField.getText(),
                                    emailField.getText(),
                                    blockedCB.isSelected(),
                            };
                            if(User.checkChange(newData, data)){
                                System.out.println(" EDIT BUTTON PRESSED");
                                if(User.getUserName().equals(data[0])){
                                    if(new Alert().Alert("Hold it right there!", "Applying these changes will log you out, are you sure you want to continue?", "red", "Yes", "No")){
                                        librarian.removeUser(lbl);
                                        librarian.addUser(new User(((String)newData[0]), ((String)newData[1]), ((String)newData[2]), ((String)newData[3]), ((String)newData[4]), ((String)newData[5]), ((String)newData[6]), ((String)newData[7]), ((Boolean)newData[8])).getUserData());
                                        logoutBtn.fire();
                                    }
                                }
                                else{
                                    librarian.removeUser(lbl);
                                    librarian.addUser(new User(((String)newData[0]), ((String)newData[1]), ((String)newData[2]), ((String)newData[3]), ((String)newData[4]), ((String)newData[5]), ((String)newData[6]), ((String)newData[7]), ((Boolean)newData[8])).getUserData());
                                    userEdited.setText("");
                                    userEdited.setText("Info updated");
                                    userEdited.setStyle("-fx-text-fill:limegreen");
                                }

                            }
                            else{
                                userEdited.setText("Please update one of the fields");
                                userEdited.setStyle("-fx-text-fill:red");
                            }

                        } catch( IOException | NumberFormatException exc) {
                        }
                    }
                });
            });
            btn2.setOnAction(e ->{
                if(User.getUserName().equals(lbl)){
                    if (new Alert().Alert("Warning","This account will be deleted and you will be logged out.\nAre you sure you want to continue?","red","Yes","No")){
                        librarian.removeUser(lbl);
                        logoutBtn.fire();
                    }
                }else {
                    if (new Alert().Alert("Warning","Are you sure you want to delete "+ lbl +" ?","red","Yes","No")){
                        librarian.removeUser(lbl);
                    }
                    if (!searchField.getText().equals("") ) {
                        searchBtn.fire();
                    }else{
                        bookListview.setItems(null);
                        displayUsers();
                    }
                }
            });
        }else if(btn1.getText().equals("Rent")){
            System.out.println("This ran for some reason");
            btn1.setOnAction(e->{
                if (!book.checkRent(User.getUserName(),lbl)){
                    Book.rentBook(lbl);
                    new Alert("Info",lbl+" was added to your order list","green");
                }
            });
            btn2.setOnAction(e -> {
                if (new Alert().Alert("Warning","Are you sure you want to delete "+ lbl +" ?","red","Yes","No")){
                    Book.removeBook(lbl);
                }
                if (!searchField.getText().equals("") ) {
                    searchBtn.fire();
                }else{
                    bookListview.setItems(null);
                    displayBooks();
                }
            });
        }else if(btn1.getText().equals("✔")){
            btn1.setOnAction(e->{
                Librarian.acceptRequest(lbl1,lbl);
                orderList();
            });
            btn2.setOnAction(e->{
                Librarian.denyRequest(lbl1,lbl);
                orderList();
            });
        }
    }

    public static class HBoxCell extends HBox {
        Label text = new Label();
        Label text1 = new Label();
        Button btn1 = new Button();
        Button btn2 = new Button();
        HBoxCell(String labelText, String buttonText1, String color1, String buttonText2, String color2, String type) {
            super();
            text.setStyle("-fx-font-size:21;-fx-font-weight:bold;");
            text.setText(labelText);
            text.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(text, Priority.ALWAYS);
            getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
            this.getChildren().add(text);
            if (type.equals("Librarian")){
                btn2 = new Button();
                btnColor(btn2,color2);
                btn2.setText(buttonText2);
                btn2.setPadding(new Insets(4, 46, 4, 46));
                this.getChildren().add(btn2);
                HBox.setMargin(btn2, new Insets(0, 10, 0, 0));
                btnColor(btn1,color1);
                this.getChildren().add(btn1);
                btn1.setText(buttonText1);
                btn1.setPadding(new Insets(4, 30, 4, 30));
            }else {
                this.getChildren().add(btn1);
                btnColor(btn1,color1);
                btn1.setText(buttonText1);
                btn1.setPadding(new Insets(4,46,4,46));
            }
        }
        HBoxCell(String users, String books){
            super();
            Button confirm = new Button("✔");
            Button reject = new Button("X");
            btnColor(confirm,"99ff33");
            btnColor(reject,"ff4c33");
            confirm.setPadding(new Insets(5,10,5,10));
            reject.setPadding(new Insets(5,12,4,12));
            HBox.setMargin(confirm, new Insets(0, 10, 0, 0));
            text.setText(users);
            text1.setText(books);
            text.setStyle("-fx-font-size:20;");
            text1.setStyle("-fx-font-size:20");
            text.setMaxWidth(Double.MAX_VALUE);
            text1.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(text, Priority.ALWAYS);
            HBox.setHgrow(text1, Priority.ALWAYS);
            this.getChildren().addAll(text,text1,confirm,reject);
        }
        public void btnColor(Button btn,String color){
            switch (color) {
                case "crimson" -> btn.getStyleClass().add("ButtonR");
                case "limegreen" -> btn.getStyleClass().add("ButtonG");
                case "#FFC107" -> btn.getStyleClass().add("ButtonY");
                case "99ff33" -> btn.getStyleClass().add("orderBtn1");
                case "ff4c33" -> btn.getStyleClass().add("orderBtn2");
            }
        }
        HBoxCell(String labelText){
            super();
            text.setText(labelText);
            text.setStyle("-fx-font-size:21;-fx-font-weight:bold;-fx-alignment:center");
            text.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(text, Priority.ALWAYS);
            this.getChildren().add(text);
        }
        HBoxCell(String col1, String col2, int width){
            super();
            text.setText(col1);
            text1.setText(col2);
            text.setStyle("-fx-font-size:24;-fx-font-weight:bold;");
            text1.setStyle("-fx-font-size:24;-fx-font-weight:bold;");
            text.setMaxWidth((double) width/2);
            HBox.setHgrow(text, Priority.ALWAYS);
            this.getChildren().addAll(text,text1);
        }
    }
    public Rectangle roundedListview(int width, int height){
        Rectangle clip = new Rectangle();
        clip.setWidth(width);
        clip.setHeight(height);
        clip.setArcHeight(20);
        clip.setArcWidth(20);
        return clip;
    }
    public void logout(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            loginScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
            Stage window = (Stage)(logoutBtn.getScene().getWindow());
            window.setTitle("Library System - Login");
            window.setScene(loginScene);
            window.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setvisible(String window){
        switch (window) {
            case "settings" -> {
                settingsPane.setVisible(true);
                settingsBtns.setVisible(true);
                adduserPane.setVisible(false);
                gridPane.setVisible(false);
                addbookPane.setVisible(false);
                userAdded.setVisible(false);
                addBtn1.setVisible(false);
                saveBtn.setVisible(false);
                blockedCB.setVisible(false);
                backBtn.setVisible(false);
                userEdited.setVisible(false);
                bookOL.setVisible(false);
                mainPane.setVisible(false);
                showPassBtn.setVisible(false);
                showIco.setVisible(false);
                hideIco.setVisible(false);
                showPassBtn.setVisible(false);
                passFieldShown.setVisible(false);
                booksBtn.getStyleClass().remove("selected");
                usersBtn.getStyleClass().remove("selected");
                settingsBtn.getStyleClass().remove("selected");
                addUserBtn.getStyleClass().remove("selected");
                addBookBtn.getStyleClass().remove("selected");
                bookOLBtn.getStyleClass().remove("selected");
            }
            case "main" -> {
                mainPane.setVisible(true);
                searchField.setVisible(true);
                searchBtn.setVisible(true);
                bookListview.setVisible(false);
                userListview.setVisible(false);
                settingsPane.setVisible(false);
                showPassBtn.setVisible(false);
                showIco.setVisible(false);
                hideIco.setVisible(false);
                showPassBtn.setVisible(false);
                booksBtn.getStyleClass().remove("selected");
                usersBtn.getStyleClass().remove("selected");
                settingsBtn.getStyleClass().remove("selected");
                addUserBtn.getStyleClass().remove("selected");
                addBookBtn.getStyleClass().remove("selected");
                bookOLBtn.getStyleClass().remove("selected");
            }
            case "edit" -> {
                mainPane.setVisible(false);
                settingsPane.setVisible(true);
                settingsBtns.setVisible(false);
                adduserPane.setVisible(true);
                addbookPane.setVisible(false);
                gridPane.setVisible(true);
                userAdded.setVisible(false);
                addBtn1.setVisible(false);
                saveBtn.setVisible(true);
                blockedCB.setVisible(true);
                backBtn.setVisible(true);
                userEdited.setVisible(true);
                bookOL.setVisible(false);
                showPassBtn.setVisible(false);
                showIco.setVisible(false);
                hideIco.setVisible(false);
                showPassBtn.setVisible(false);
            }
            case "none" -> {
                mainPane.setVisible(false);
                settingsPane.setVisible(false);
                booksBtn.getStyleClass().remove("selected");
                usersBtn.getStyleClass().remove("selected");
                settingsBtn.getStyleClass().remove("selected");
                addUserBtn.getStyleClass().remove("selected");
                addBookBtn.getStyleClass().remove("selected");
                bookOLBtn.getStyleClass().remove("selected");
            }
        }
    }
    public void showPass(){
        if(!visible){
            hideIco.setVisible(false);
            showIco.setVisible(true);
            passFieldShown.setText(passField.getText());
            passFieldShown.setVisible(true);
            passField.setVisible(false);
            visible=true;
        }else {
            hideIco.setVisible(true);
            showIco.setVisible(false);
            passField.setText(passFieldShown.getText());
            passField.setVisible(true);
            passFieldShown.setVisible(false);
            visible=false;
        }
    }
    public void initialize(){
        if (usernameDisplay != null) {
            usernameDisplay.setText(User.getUserName());
            typeDisplay.setText(User.getType());
            checkType();
        }
        mainPane.setVisible(false);
        settingsPane.setVisible(false);
        settingsBtns.setVisible(false);
        adduserPane.setVisible(false);
        bookListview.setVisible(false);
        userListview.setVisible(false);
        bookOL.setVisible(false);
        type.getItems().addAll("Librarian","Reader");
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