package proggroup.advprogmt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Reader extends User{

    public Reader(){

    }

    public Reader(String Username,String Password,String Type,String FirstName,String LastName,String Address,String CellPhone,String Email,boolean isBlocked){
        super(Username,Password,Type,FirstName,LastName,Address,CellPhone,Email, isBlocked);
    }

    public static boolean validate(Label msg){
        if (isBlocked) {
            msg.setText("This user is blocked. Please contact the library for more information.");
            msg.setStyle("-fx-text-fill:red");
            return false;
        }
        return true;
    }
}
