package com.example.projectp3sem1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public TextField username;
    @FXML
    public PasswordField password;

    @FXML
    public Label status;

    @FXML
    public void login(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();

        boolean existsUser = App.db.exists(String.format("SELECT * FROM `users` WHERE `username`='%s';", user));
        if (existsUser) {
            boolean rightPass = App.db.exists(String.format("SELECT * FROM `users` WHERE `username`='%s' AND `password`='%s';", user, pass));
            if (rightPass) {
                System.out.println("LOGGED IN");
                // TODO: 1/7/2022 LOGGED IN SUCCESSFULLY.
                App.setLoggedIn(true);
                App.changeScene("BookList.fxml");
            } else {
                System.out.println("WRONG PASSWORD");
                // TODO: 1/7/2022 WRONG PASSWORD
                status.setText("Wrong Password.");
            }
        } else {
            System.out.println("USER DOESN'T EXIST");
            // TODO: 1/7/2022 USER DOESN'T EXIST.
            status.setText("User doesn't exist.");
        }
    }

    @FXML
    public void bookList(ActionEvent actionEvent) {
        App.changeScene("BookList.fxml");
    }
}