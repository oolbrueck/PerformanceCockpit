module com.hbrs.performancecockpit {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires com.fasterxml.jackson.databind;


    opens com.hbrs.performancecockpit to javafx.fxml;
    opens com.hbrs.performancecockpit.entities to com.fasterxml.jackson.databind;
    exports com.hbrs.performancecockpit;
    exports com.hbrs.performancecockpit.ui;
    exports com.hbrs.performancecockpit.entities;
    exports com.hbrs.performancecockpit.records;
    opens com.hbrs.performancecockpit.ui to javafx.fxml;
}