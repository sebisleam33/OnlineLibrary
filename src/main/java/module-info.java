module com.example.projectp3sem1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.projectp3sem1 to javafx.fxml;
    exports com.example.projectp3sem1;
}