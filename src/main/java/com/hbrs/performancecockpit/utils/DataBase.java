/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DataBase {
    private static MongoDatabase db;
    private final static String dbName = "mongoDB";
    private final static int port = 27017;

    public static MongoDatabase getDB() throws DatabaseConnectionException {
        if(db != null) {
            return db;
        } else {
            try(MongoClient client = new MongoClient("localhost", port)) {
                db = client.getDatabase(dbName);
                return db;
            } catch (Exception e) {
                throw new DatabaseConnectionException("Failed to connect to database '" + dbName + "' on port " + port, e);
            }
        }
    }
}
