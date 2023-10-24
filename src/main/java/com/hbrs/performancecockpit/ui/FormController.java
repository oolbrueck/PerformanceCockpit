package com.hbrs.performancecockpit.ui;


import com.hbrs.performancecockpit.controler.ManagePersonalImpl;
import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.utils.DatabaseConnectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class FormController {

    @FXML
    private TextField employeeNumberTextField;
    @FXML
    private TextField fistNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField locationTextField;

    @FXML
    protected void submit(ActionEvent event) {
        SalesMan salesMan = new SalesMan(
                parseInt(employeeNumberTextField.getText()),
                fistNameTextField.getText(),
                lastNameTextField.getText(),
                locationTextField.getText());
        try {
            new ManagePersonalImpl().createSalesMan(salesMan);
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
            //TODO Fehlerausgabe gegenüber User
        }
        (new SceneController()).switchToCockpit(event);
    }

    @FXML
    protected void goBack(ActionEvent event) {
        (new SceneController()).switchToCockpit(event);
    }
}