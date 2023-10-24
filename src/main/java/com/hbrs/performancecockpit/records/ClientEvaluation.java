/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.records;

public class ClientEvaluation {
    private ClientRating clientRating;
    private ClientCategory clientCategory;
    private int soldItems;

    public ClientEvaluation(ClientRating clientRating, ClientCategory clientCategory, int soldItems) {
        this.clientRating = clientRating;
        this.clientCategory = clientCategory;
        this.soldItems = soldItems;
    }

    public enum ClientRating {
        EXCELLENT,
        VERYGOOD,
        GOOD,
        BAD,
        VERYBAD
    }

    public enum ClientCategory {
        PREMIUMCUSTOMER,
        CORECUSTOMER,
        STANDARDCUSTOMERS,
        OCCASIONALCUSTOMERS
    }

    public ClientRating getClientRating() {
        return clientRating;
    }

    public ClientCategory getClientCategory() {
        return clientCategory;
    }

    public int getSoldItems() {
        return soldItems;
    }


    @Override
    public String toString() {
        return "ClientEvaluation{" +
                "clientRating=" + clientRating +
                ", clientCategory=" + clientCategory +
                ", soldItems=" + soldItems +
                '}';
    }
}
