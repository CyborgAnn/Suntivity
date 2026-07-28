module edu.utsa.cs3443.suntivity {

    requires javafx.controls;
    requires javafx.fxml;

    opens edu.utsa.cs3443.suntivity.controller to javafx.fxml;

    exports edu.utsa.cs3443.suntivity.application;
    exports edu.utsa.cs3443.suntivity.model;
    exports edu.utsa.cs3443.suntivity.controller;
}