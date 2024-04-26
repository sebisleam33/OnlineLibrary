package com.example.projectp3sem1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class IndividualBooksController implements Initializable {

    @FXML
    public Text title;

    @FXML
    public Text description;

    @FXML
    public Text author;

    @FXML
    public ImageView thumb;

    @FXML
    public void bookList(ActionEvent actionEvent) {
        App.changeScene("BookList.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Book book = App.db.getBook(App.getBookSelected());
        description.setText(book.getDescription());
        thumb.setImage(new Image(App.class.getResourceAsStream(book.getCover())));
        title.setText(book.getName());
        author.setText(book.getAuthor());

    }
}
