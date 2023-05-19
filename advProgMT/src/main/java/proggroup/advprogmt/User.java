package proggroup.advprogmt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
//    (Username(ID),Password,Type,FirstName,LastName,Address,CellPhone,Email,isBlocked).
    private static String userName;
    private String newType;
    private boolean newIsBlocked = false;
    private String newUserName;
    private String password;
    private static String type;
    private String firstName;
    private String lastName;
    private String address;
    private String cellPhone;
    private String email;
    static BufferedReader brv;
    static String temp;
    static boolean isBlocked = false;
    public User(){}
    public User(String Username,String Password,String type,String FirstName,String LastName,String Address,String CellPhone,String Email, boolean isBLocked){
        this.newUserName=Username;
        this.password=Password;
        this.newType=type;
        this.firstName=FirstName;
        this.lastName=LastName;
        this.address=Address;
        this.cellPhone=CellPhone;
        this.email=Email;
        this.newIsBlocked = isBLocked;
    }
    public static boolean validate(TextField username, PasswordField password,TextField passShown , Label msg){
        if (!checkFields(username,password,passShown,msg)){
            return false;
        }
        boolean matches = false;
        try {
            brv = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
            temp = brv.readLine();
            while ((temp = brv.readLine())!= null){
                if (username.getText().equals(temp.split(",")[0])){
                    if (password.getText().equals(temp.split(",")[1]) || passShown.getText().equals(temp.split(",")[1])) {
                        isBlocked = temp.split(",")[8].equals("true");
                        matches = true;
                        type = temp.split(",")[2];
                        userName = temp.split(",")[0];
                        msg.setText("");
                        break;
                    }else {
                        msg.setText("Wrong Credentials");
                        msg.setStyle("-fx-text-fill:red");
                    }
                    username.clear();
                    passShown.clear();
                    password.clear();
                } else {
                    msg.setText("Wrong Credentials");
                    msg.setStyle("-fx-text-fill:red");
                }
            }
            brv.close();
        } catch (IOException e) {
        }
        return matches;
    }
    public static boolean checkFields(TextField username, PasswordField password,TextField passShown , Label msg) {
        if (password.getText().isEmpty())
            password.setText(passShown.getText());
        if (username.getText().isEmpty() && password.getText().isEmpty()) {
            msg.setText("Please enter your Username & Password");
            msg.setStyle("-fx-text-fill:red");
            return false;
        } else if (username.getText().isEmpty()) {
            msg.setText("Please enter your Username");
            msg.setStyle("-fx-text-fill:red");
            return false;
        } else if (password.getText().isEmpty() && passShown.getText().isEmpty()) {
            msg.setText("Please enter your Password");
            msg.setStyle("-fx-text-fill:red");
            return false;
        }
        return true;
    }
    public static boolean validate(TextField username, Label usernameErr, PasswordField password, TextField passShown, Label passErr, Object type, Label typeErr, TextField firstname, Label firstnameErr, TextField lastname, Label lastnameErr, TextField email, Label emailErr, TextField mobile, Label cellphoneErr, TextField address, Label addressErr){
        boolean typeEmpty = true;
        boolean firstnameValid = true;
        boolean lastnameVaild = true;
        boolean emailValid = true;
        boolean mobileValid = true;

        checkEmpty(username,usernameErr);
        checkEmpty(password,passErr);
        checkEmpty(passShown,passErr);
        checkEmpty(firstname,firstnameErr);
        checkEmpty(lastname,lastnameErr);
        checkEmpty(email,emailErr);
        checkEmpty(mobile,cellphoneErr);
        checkEmpty(address,addressErr);
        if (!checkEmpty(firstname,firstnameErr)) {
            if (firstname.getText().matches("^\\d+$")) {
                firstnameErr.setText("Firstname can not contain numbers");
                errorStyle(firstname);
                firstnameValid = false;
                return firstnameValid;
            }else defaultStyle(firstname);
        }
        if(!checkEmpty(lastname,lastnameErr)){
            if(lastname.getText().matches("^\\d+$")){
                lastnameErr.setText("Lastname can not contain numbers");
                errorStyle(lastname);
                lastnameVaild = false;
                return lastnameVaild;
            }else defaultStyle(lastname);
        }
        if(!checkEmpty(email,emailErr)){
            if(!email.getText().contains("@")){
                emailErr.setText("Email must contain '@'");
                errorStyle(email);
                emailValid = false;
                return emailValid;
            }else defaultStyle(email);
        }
        if(!checkEmpty(mobile,cellphoneErr)){
            if (!mobile.getText().matches("\\d{8}|\\d{11}")){
                cellphoneErr.setText("Enter a valid Cellphone");
                errorStyle(mobile);
                mobileValid = false;
                return mobileValid;
            }else defaultStyle(mobile);
        }
        if (type == null) {
            typeErr.setText("Please select user type");
        }else {
            typeErr.setText("");
            typeEmpty = false;
        }
        return firstnameValid && lastnameVaild && emailValid && mobileValid && !checkEmpty(username,usernameErr) && !checkEmpty(password,passErr) && !typeEmpty && !checkEmpty(firstname,firstnameErr) && !checkEmpty(lastname,lastnameErr) && !checkEmpty(email,emailErr) && !checkEmpty(mobile,cellphoneErr) && !checkEmpty(address,addressErr);
    }
    public static boolean checkEmpty(TextField field,Label label){
        if(field.getText().isEmpty()){
            label.setText("This field can't be empty");
            errorStyle(field);
            return true;
        }else {
            label.setText("");
            defaultStyle(field);
            return false;
        }
    }
    private static void defaultStyle(TextField field){
        field.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;");
    }
    private static void errorStyle(TextField field){
        field.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-border-color:red;");
    }
    public ObservableList<HomeController.HBoxCell> searchBooks(){
        ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
        if (Search.booksArr != null) {
            for (String i: Search.booksArr) {
                if (i!= null) {
                    if (type.equals("Librarian")) {
                        list.add(new HomeController.HBoxCell(i, "Remove","crimson","Rent","limegreen",type));
                    }else {
                        list.add(new HomeController.HBoxCell(i, "Rent","limegreen","","transparent",type));
                    }
                }
            }
        }
        ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
        return myObservableList;
    }
    public String getUserData() {
        return newUserName + "," + password + "," + newType + "," + firstName + "," + lastName + "," + address + "," + cellPhone + "," + email + "," + newIsBlocked;
    }

    public static String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public static String getType(){
        return type;
    }
    public String getNewType(){
        return newType;
    }
    public String getFirstname(){
        return firstName;
    }
    public String getLastname(){
        return lastName;
    }
    public String getAddress(){
        return address;
    }
    public String getCellphone(){
        return cellPhone;
    }
    public String getEmail(){
        return email;
    }
    public boolean getisBlocked(){
        return isBlocked;
    }

    public static String[] requestInfo(String userName) throws IOException {
        String[] data;

        brv = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
        data = brv.readLine().split(",");
        String line = data[0];

        while(data != null){
            if(userName.equals(line)){
                break;
            }
            try{
                data = brv.readLine().split(",");
                line = data[0];
            }
            catch(NullPointerException e){
                break;
            }
        }
        return data;
    }

    public static boolean checkChange(Object[] newData, Object[] oldData){
        if( !((String)newData[0]).equals(((String)oldData[0]))){
            return true;
        }
        if( !((String)newData[1]).equals(((String)oldData[1]))){
            return true;
        }
        if( !((String)newData[2]).equals(((String)oldData[2]))){
            return true;
        }
        if( !((String)newData[3]).equals(((String)oldData[3]))){
            return true;
        }
        if( !((String)newData[4]).equals(((String)oldData[4]))){
            return true;
        }
        if( !((String)newData[5]).equals(((String)oldData[5]))){
            return true;
        }
        if( !((String)newData[6]).equals(((String)oldData[6]))){
            return true;
        }
        if( !((String)newData[7]).equals(((String)oldData[7]))){
            return true;
        }
        if( !(newData[8].toString().equals(oldData[8]))){
            return true;
        }
        return false;
    }
}

