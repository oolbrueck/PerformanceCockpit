module com.hbrs.performancecockpit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hbrs.performancecockpit to javafx.fxml;
    exports com.hbrs.performancecockpit;
}