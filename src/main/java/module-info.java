module com.hbrs.performancecockpit {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires com.fasterxml.jackson.databind;


    opens com.hbrs.performancecockpit to javafx.fxml;
    exports com.hbrs.performancecockpit;
    exports com.hbrs.performancecockpit.ui;
    opens com.hbrs.performancecockpit.ui to javafx.fxml;
}