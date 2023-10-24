/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.utils;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
