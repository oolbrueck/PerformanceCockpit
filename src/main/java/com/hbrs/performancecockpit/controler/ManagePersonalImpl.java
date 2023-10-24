/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.records.ClientEvaluation;
import com.hbrs.performancecockpit.records.EvaluationRecord;
import com.hbrs.performancecockpit.records.EvaluationRecordImpl;
import com.hbrs.performancecockpit.records.SocialPerformanceEvaluation;
import com.hbrs.performancecockpit.utils.DataBase;
import com.hbrs.performancecockpit.utils.DatabaseConnectionException;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class ManagePersonalImpl implements ManagePersonal {
    @Override
    public void createSalesMan(SalesMan record) throws DatabaseConnectionException {
        try(MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");
            db.getCollection("salesMan").insertOne(record.toDocument());
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to database '" + "dbName" + "' on port " + "port", e);
        }
    }


    @Override
    public SalesMan readSalesMan(int employeeNumber) throws DatabaseConnectionException {
        try(MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");
            Bson filter = eq("employeeNumber", employeeNumber);
            MongoCursor<Document> cursor = db.getCollection("salesMan").find(filter).iterator();
            if (cursor.hasNext()) {
                Document document = cursor.next();
                int foundEmployeeNumber = document.getInteger("employeeNumber");
                String foundFirstName = document.getString("firstName");
                String foundLastName = document.getString("lastName");
                String foundLocation = document.getString("location");
                SalesMan salesman = new SalesMan(foundEmployeeNumber, foundFirstName, foundLastName, foundLocation);
                return salesman;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to database '" + "dbName" + "' on port " + "port", e);
        }
    }

    @Override
    public void updateSalesMan(SalesMan salesMan) throws DatabaseConnectionException {
        try (MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");
            Bson filter = eq("employeeNumber", salesMan.getEmployeeNumber());

            Document updatedDocument = new Document("$set", new Document()
                    .append("firstName", salesMan.getFirstName())
                    .append("lastName", salesMan.getLastName())
                    .append("location", salesMan.getLocation()));

            try {
                db.getCollection("salesMan").updateOne(filter, updatedDocument);
            } catch (Exception e) {
                throw new DatabaseConnectionException("Failed to update Salesman in the database.", e);
            }
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void deleteSalesMan(SalesMan salesMan) throws DatabaseConnectionException {
        try (MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");
            Bson filter = eq("employeeNumber", salesMan.getEmployeeNumber());

            try {
                db.getCollection("salesMan").deleteOne(filter);
            } catch (MongoException e) {
                throw new DatabaseConnectionException("Failed to delete Salesman from the database.", e);
            }
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database.", e);
        }
    }

    @Override
    public List<SalesMan> readAllSalesMan() throws DatabaseConnectionException {
        List<SalesMan> salesMen = new ArrayList<>();

        try (MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");

            try (MongoCursor<Document> cursor = db.getCollection("salesMan").find().iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    int employeeNumber = document.getInteger("employeeNumber");
                    String firstName = document.getString("firstName");
                    String lastName = document.getString("lastName");
                    String location = document.getString("location");

                    SalesMan salesman = new SalesMan(employeeNumber, firstName, lastName, location);
                    salesMen.add(salesman);
                }
            } catch (MongoException e) {
                throw new DatabaseConnectionException("Failed to retrieve Salesmen from the database.", e);
            }
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database.", e);
        }

        return salesMen;
    }

    @Override
    public List<SalesMan> querySalesMan(String attribute, String key) throws DatabaseConnectionException {
        var collection = DataBase.getDB().getCollection("salesMan");
        Document query = new Document(attribute, key);
        FindIterable<Document> documents = collection.find(query);
        MongoCursor<Document> cursor = documents.iterator();
        List<SalesMan> salesMen = new ArrayList<>();

        while (cursor.hasNext()) {
            Document document = cursor.next();
            SalesMan salesman = new SalesMan(
                    (int)document.get("employeeNumber"),
                    document.get("firstName").toString(),
                    document.get("lastName").toString(),
                    document.get("location").toString()
            );
            salesMen.add(salesman);
        }
        return salesMen;
    }

    @Override
    public void createEvaluationRecord(EvaluationRecord record, int employeeNumber) throws DatabaseConnectionException {
        try (MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");
            var collection = db.getCollection("evaluationRecords");

            Document document = record.toDocument();

            collection.insertOne(document);
        } catch (MongoException e) {
            throw new DatabaseConnectionException("Failed to create EvaluationRecord in the database.", e);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database.", e);
        }
    }

    @Override
    public List<EvaluationRecord> readEvaluationRecords(int employeeNumber) throws DatabaseConnectionException {
        List<EvaluationRecord> records = new ArrayList<>();

        try (MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");
            var collection = db.getCollection("evaluationRecords");

            Bson filter = eq("employeeNumber", employeeNumber);

            try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    EvaluationRecord record = documentToEvaluationRecord(document);
                    records.add(record);
                }
            } catch (MongoException e) {
                throw new DatabaseConnectionException("Failed to retrieve EvaluationRecords from the database.", e);
            }
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database.", e);
        }

        return records;
    }

    @Override
    public void updateEvaluationRecord(EvaluationRecord record, int employeeNumber) throws DatabaseConnectionException {
        System.out.println("fdgfg");
        try (MongoClient client = new MongoClient("localhost", 27017)) {
            System.out.println("sadfdf");
            var db = client.getDatabase("mongoDB");
            var collection = db.getCollection("evaluationRecords");

            System.out.println("ffff");
            Bson filter = and(
                    eq("employeeNumber", employeeNumber),
                    eq("year", record)
            );

            Document updatedDocument = record.toDocument();

            System.out.println("ggg");
            UpdateResult result = collection.replaceOne(filter, updatedDocument);

            System.out.println("hhhh");
            if (result.getModifiedCount() == 0) {
                System.out.println("Nichts zum updaten da");
            }
        } catch (MongoException e) {
            throw new DatabaseConnectionException("Failed to update EvaluationRecord in the database.", e);
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new DatabaseConnectionException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void deleteEvaluationRecord(EvaluationRecord record, int employeeNumber) throws DatabaseConnectionException {
        try (MongoClient client = new MongoClient("localhost", 27017)) {
            var db = client.getDatabase("mongoDB");
            var collection = db.getCollection("evaluationRecords");

            int employeeNumberToDelete = -1;

            Bson filter = and(
                    eq("employeeNumber", employeeNumber),
                    eq("year", record));

            DeleteResult result = collection.deleteOne(filter);

            if (result.getDeletedCount() == 0) {
                //TODO
            }
        } catch (MongoException e) {
            throw new DatabaseConnectionException("Failed to delete EvaluationRecord from the database.", e);
        } catch (Exception e) {
            System.out.println(e);
            throw new DatabaseConnectionException("Failed to connect to the database.", e);
        }
    }

    private EvaluationRecord documentToEvaluationRecord(Document document) {
        int year = document.getInteger("year");
        int employeeNumber = document.getInteger("employeeNumber");
        List<ClientEvaluation> clientEvaluation = new ArrayList<>();

        List<Document> clientEvalDocuments = (List<Document>) document.get("clientEvaluation");
        for (Document clientEvalDoc : clientEvalDocuments) {
            ClientEvaluation.ClientRating clientRating = ClientEvaluation.ClientRating.valueOf(clientEvalDoc.getString("clientRating"));
            ClientEvaluation.ClientCategory clientCategory = ClientEvaluation.ClientCategory.valueOf(clientEvalDoc.getString("clientCategory"));
            int soldItems = clientEvalDoc.getInteger("soldItems");

            ClientEvaluation clientEval = new ClientEvaluation(clientRating, clientCategory, soldItems);
            clientEvaluation.add(clientEval);
        }

        SocialPerformanceEvaluation socialEval = null;
        if (document.containsKey("socialPerformanceEvaluation")) {
            Document socialEvalDoc = (Document) document.get("socialPerformanceEvaluation");
            SocialPerformanceEvaluation.NumberEnum leaderShipCompetence = SocialPerformanceEvaluation.NumberEnum.fromInt(socialEvalDoc.get("leaderShipCompetence", Integer.class));
            SocialPerformanceEvaluation.NumberEnum opennessToEmployee = SocialPerformanceEvaluation.NumberEnum.fromInt(socialEvalDoc.get("opennessToEmployee", Integer.class));
            SocialPerformanceEvaluation.NumberEnum socialBehaviourToEmployee = SocialPerformanceEvaluation.NumberEnum.fromInt(socialEvalDoc.get("socialBehaviourToEmployee", Integer.class));
            SocialPerformanceEvaluation.NumberEnum attitudeTowardsClient = SocialPerformanceEvaluation.NumberEnum.fromInt(socialEvalDoc.get("attitudeTowardsClient", Integer.class));
            SocialPerformanceEvaluation.NumberEnum communicationSkills = SocialPerformanceEvaluation.NumberEnum.fromInt(socialEvalDoc.get("communicationSkills", Integer.class));
            SocialPerformanceEvaluation.NumberEnum integrityToCompany = SocialPerformanceEvaluation.NumberEnum.fromInt(socialEvalDoc.get("integrityToCompany", Integer.class));

            socialEval = new SocialPerformanceEvaluation(leaderShipCompetence, opennessToEmployee, socialBehaviourToEmployee,
                    attitudeTowardsClient, communicationSkills, integrityToCompany);
        }

        return new EvaluationRecordImpl(clientEvaluation, socialEval, year, employeeNumber);
    }
}
