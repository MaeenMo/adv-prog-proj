package proggroup.advprogmt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;

public class Librarian extends User{
    private BufferedWriter bw;
    private BufferedReader br;
    private static String file = "Users.txt";
    private static String path = "src/main/java/proggroup/advprogmt/Database/" + file;
    public Librarian(String Username,String Password,String Type,String FirstName,String LastName,String Address,String CellPhone,String Email, boolean isBlocked){
        super(Username,Password,Type,FirstName,LastName,Address,CellPhone,Email, isBlocked);
    }
    public Librarian() {
    }
    public void addUser(String data) throws IOException {
        setFile("Users.txt");
        bw = new BufferedWriter(new FileWriter(path, true));
        bw.write(data);
        bw.newLine();
        bw.flush();
    }
    public void removeUser(String nameToRemove){
        String[] users;
        int size = 0;
        try {
            br = new BufferedReader(new FileReader(path));

            String line = br.readLine();
            while(line != null){
                line = br.readLine();
                size++;
            }
            users = new String[size-1];
            br = new BufferedReader(new FileReader(path));

            line = br.readLine();
            for(int i = 0; line != null;){
                if(!(nameToRemove.equals(line.split(",")[0]))){
                    users[i] = line;
                    i++;
                }
                line = br.readLine();
            }
            br.close();
            bw = new BufferedWriter(new FileWriter(path));

            for(int i = 0; i < size-1; i++){
                bw.write(users[i]);
                bw.newLine();
            }
            bw.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }
    public ObservableList<HomeController.HBoxCell> bookList(){
        ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
        String[] users = new Database("Requests.txt", 'r').getPart1();
        String[] books = new Database("Requests.txt", 'r').getPart2();
        list.add(new HomeController.HBoxCell("Users","Books",835));
        for(int j = 0; j < users.length; j++){
                list.add(new HomeController.HBoxCell(users[j],books[j]));
        }
        ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
        return myObservableList;
    }
    public static void acceptRequest(String name, String book){
        new Database("Loans.txt", 'w').saveP1P2(name, book);
        new Database("Requests.txt", 'm').removeUser(name,book);

    }

    public static void denyRequest(String name,String book){
        new Database("Requests.txt", 'm').removeUser(name,book);
    }

    public ObservableList<HomeController.HBoxCell> searchUsers(){
        ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
        if (Search.usersArr != null) {
            for (String i: Search.usersArr) {
                if (i!= null) {
                    list.add(new HomeController.HBoxCell(i, "Remove","crimson","Edit","#FFC107",User.getType()));
                }
            }
        }
        ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
        return myObservableList;
    }

    private static void setFile(String path){
        file = path;
    }
}