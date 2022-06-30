module com.example.all {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.all to javafx.fxml;
    exports com.example.all;
    exports com.example.all.Class;
    opens com.example.all.Class to javafx.fxml;
    exports com.example.all.Files;
    opens com.example.all.Files to javafx.fxml;
}