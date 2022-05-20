module com.example.supervisionnetwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.snmp4j;


    opens com.example.supervisionnetworkInterface to javafx.fxml;
    exports com.example.supervisionnetworkInterface;
}