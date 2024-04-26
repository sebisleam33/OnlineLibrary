package com.example.projectp3sem1;

import com.example.projectp3sem1.Database.MySQL;
import com.example.projectp3sem1.Database.MySQLSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.example.projectp3sem1.Database.Database;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    private static Stage stage;

    private static boolean loggedIn = false;
    private static int bookSelected = 0;

    public static Database db;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage _stage) throws IOException {
        stage = _stage;

        MySQLSettings settings = new MySQLSettings("localhost", "3306", "root", "", "bookShelf");

        db = new MySQL(settings) {
            @Override
            public void defaultTables() {
                execute("CREATE TABLE IF NOT EXISTS `users` ( `ID` INT(11) NOT NULL AUTO_INCREMENT , `username` VARCHAR(255) NOT NULL , `password` VARCHAR(255) NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;");
                execute("CREATE TABLE IF NOT EXISTS `Books` ( `ID` INT(11) NOT NULL AUTO_INCREMENT , `book_name` VARCHAR(255) NOT NULL , `author` VARCHAR(255) NOT NULL , `description` LONGTEXT NOT NULL, `cover` VARCHAR(255) NOT NULL, PRIMARY KEY (`ID`)) ENGINE = InnoDB;");
            }
        };

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("BookList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 771, 601);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }

    public static void changeScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml)));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setBookSelected(int bookSelected) {
        App.bookSelected = bookSelected;
    }

    public static int getBookSelected() {
        return bookSelected;
    }

    public static void setLoggedIn(boolean loggedIn) {
        App.loggedIn = loggedIn;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void main(String[] args) {
        launch();
    }
}