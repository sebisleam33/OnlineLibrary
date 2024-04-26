package com.example.projectp3sem1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class BookListController implements Initializable {

    @FXML
    public Button loginButton;

    @FXML
    public Button addBookButton;

    @FXML
    public ListView<HBox> lv;

    public void login(ActionEvent actionEvent) {
        if (!App.isLoggedIn()) {
            App.changeScene("Login.fxml");
        } else {
            App.setLoggedIn(false);
            loginButton.setText("Log In");
            addBookButton.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (App.isLoggedIn()) {
            loginButton.setText("Log Out");
        } else {
            addBookButton.setVisible(false);
        }

        for (Book book : App.db.getBooks()) {
            lv.getItems().add(generateBook(book.getName(), book.getAuthor(), book.getCover(), book.getId()));
        }

    }

    public HBox generateBook(String bookName, String author, String cover, int bookSelected) {
        HBox hBox = new HBox();
        ImageView thumb = new ImageView(new Image(App.class.getResourceAsStream(cover)));
        thumb.setFitWidth(152);
        thumb.setFitHeight(209);
        hBox.getChildren().add(thumb);

        VBox vBox = new VBox();

        vBox.setSpacing(7);

        Label bookNameLabel = new Label(bookName);
        Label authorLabel = new Label(author);
        bookNameLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        authorLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        vBox.getChildren().add(bookNameLabel);
        vBox.getChildren().add(authorLabel);

        Button details = new Button("Details");
        details.setOnAction(actionEvent -> {
                    App.setBookSelected(bookSelected);
                    App.changeScene("IndividualBooks.fxml");
                }
        );
        vBox.getChildren().add(details);

        hBox.getChildren().add(vBox);

        hBox.setPadding(new Insets(10));
        return hBox;
    }

    public void addBook(ActionEvent actionEvent) {
        App.changeScene("UploadNewBookUI.fxml");
    }

}
