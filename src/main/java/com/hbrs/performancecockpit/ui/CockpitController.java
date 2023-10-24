/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CockpitController {

    @FXML
    protected void goToForm(ActionEvent event) {
        (new SceneController()).switchToForm(event);
    }

    @FXML
    protected void goTosalesManOverview(ActionEvent event) {
        (new SceneController()).switchToSalesMenOverview(event);
    }
}
