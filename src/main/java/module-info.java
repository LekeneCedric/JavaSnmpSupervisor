module com.example.supervisionnetwork {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.supervisionnetwork to javafx.fxml;
    exports com.example.supervisionnetwork;
}