package proggroup.advprogmt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Search{
    BufferedReader brs;
    BufferedReader brl;
    BufferedReader bra;
    String temp = "";
    String result = null;
    static String [] booksArr = null;
    static String [] usersArr = null;
    Alert alert;
    int lines;
    int i = 0;
    public void searchforBooks(String searchbar){
        if (searchbar.isEmpty()){
            new Alert("Error","Nothing to search!", "red");
            result = null;
        } else{
            try {
                brs = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Books.txt"));
                brl = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Books.txt"));
                lines = 0;
                while (brl.readLine()!= null){
                    lines++;
                }
                booksArr = new String[lines];
                for (int i =0;i < lines ;i++) {
                    temp = brs.readLine();
                    if (temp.contains(searchbar.toLowerCase()) || temp.contains(searchbar.toUpperCase()) || searchbar.equals(temp) || searchbar.equals(temp.toLowerCase()) || temp.toLowerCase().contains(searchbar.toLowerCase())){
                        booksArr[i]=temp;
                    }
                }
                for (String e: booksArr) {
                    if (e != null) {
                        i = 0;
                        break;
                    }
                    else i = -1;
                }
                if(i == -1){
                    result = "Book " + "\"" + searchbar + "\"" + " Was Not Found!";
                }
                brs.close();
                brl.close();
            }catch (IOException e){
            }
        }
    }
    public void viewallBooks(){
        try {
            bra = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Books.txt"));
            brl = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Books.txt"));
            lines = 0;
            while (brl.readLine()!= null){
                lines++;
            }
            booksArr = new String[lines];
            for (int i =0;i < lines ;i++) {
                booksArr[i] = bra.readLine();
            }
        } catch (IOException e) {
        }
    }
    public void searchforUsers(String searchbar){
        if (searchbar.isEmpty()){
            new Alert("Error","Nothing to search!", "red");
            result = null;
        } else{
            try {
                brs = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
                brl = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
                lines = 0;
                while (brl.readLine()!= null){
                    lines++;
                }
                usersArr = new String[lines-1];
                temp = brs.readLine();
                for (int i = 0; i < lines-1; i++) {
                    temp = brs.readLine();
                    if (temp.split(",")[0].contains(searchbar.toLowerCase()) || temp.split(",")[0].contains(searchbar.toUpperCase()) || searchbar.equals(temp.split(",")[0]) || searchbar.equals(temp.split(",")[0].toLowerCase()) || temp.split(",")[0].toLowerCase().contains(searchbar.toLowerCase())){
                        usersArr[i]=temp.split(",")[0];
                    }
                }
                for (String e: usersArr) {
                    if (e != null) {
                        i = 0;
                        break;
                    }
                    else i = -1;
                }
                if(i == -1){
                    result = "User " + "\"" + searchbar + "\"" + " Was Not Found!";
                }
                brs.close();
                brl.close();
            }catch (IOException e){
            }
        }
    }
    public void viewallUsers(){
        try {
            bra = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
            brl = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
            lines = 0;
            while (brl.readLine()!= null){
                lines++;
            }
            usersArr = new String[lines-1];
            bra.readLine();
            for (int i =0;i < lines-1 ;i++)
            {
                usersArr[i] = bra.readLine().split(",")[0];
            }
        } catch (IOException e) {
        }
    }
}


