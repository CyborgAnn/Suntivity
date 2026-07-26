module edu.utsa.cs3443.suntivity {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.suntivity to javafx.fxml;
    exports edu.utsa.cs3443.suntivity;
}