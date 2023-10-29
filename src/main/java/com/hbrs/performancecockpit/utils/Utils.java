/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.utils;

public abstract class Utils {

    public static boolean isRunningInTest() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }

    public static int getDatabasePort() {
        return isRunningInTest() ? 27018 : 27017;
    }

    public static String getDatabaseHost() {
        return "localhost";
    }

    public static String getDatabaseName() {
        return isRunningInTest() ? "mongoTestDB" : "mongoDB";
    }
}
