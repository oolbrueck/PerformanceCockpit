/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SalesMenOverviewController {

    @FXML
    protected void goBack(ActionEvent event) {
        (new SceneController()).switchToCockpit(event);
    }
}
