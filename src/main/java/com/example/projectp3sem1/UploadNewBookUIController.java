package com.example.projectp3sem1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class UploadNewBookUIController implements Initializable {

    public TextField nameOfTheBook;
    public TextField author;
    public TextArea description;
    public Label fileLocation;
    public String fileName;

    private FileChooser fileChooser;

    public void addBook(ActionEvent actionEvent) {
        App.db.execute(String.format("INSERT INTO `books` VALUES (NULL, '%s', '%s', '%s', '%s');", nameOfTheBook.getText(), author.getText(), description.getText(), fileName));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser = new FileChooser();
    }

    @FXML
    public void bookList(ActionEvent actionEvent) {
        App.changeScene("BookList.fxml");
    }

    public void browse(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(App.getStage());
        fileName = file.getName();
        fileLocation.setText(file.getAbsolutePath());
        try {
            File to = new File("src/main/resources/com/example/projectp3sem1/" + file.getName());
            System.out.println(to.getAbsolutePath());
            if (to.exists())
                to.delete();
            Files.copy(file.toPath(), to.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
