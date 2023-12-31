package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.records.ClientEvaluation;
import com.hbrs.performancecockpit.records.EvaluationRecordImpl;
import com.hbrs.performancecockpit.records.SocialPerformanceEvaluation;
import com.hbrs.performancecockpit.utils.DatabaseConnectionException;
import com.hbrs.performancecockpit.utils.Utils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagePersonalImplRoundTripTest { //TODO eigenen container/DB für Tests!!

    @AfterEach
    void cleanDB() {
        try (MongoClient client = new MongoClient("localhost", Utils.getDatabasePort())) {
            MongoDatabase database = client.getDatabase(Utils.getDatabaseName());
            MongoCollection<Document> collectionEvaluationRecords = database.getCollection("evaluationCollection");
            collectionEvaluationRecords.deleteMany(new Document());
            MongoCollection<Document> collectionSalesMan = database.getCollection("salesManCollection");
            collectionSalesMan.deleteMany(new Document());
        }
    }

    @Test
    public void testInsertSalesManWithEvaluationRecord() {
        //given
        int employeeNumber = 12345;
        SalesMan salesMan = new SalesMan(employeeNumber, "John", "Doe", "New York");
        List<ClientEvaluation> clientEvaluationList = new ArrayList<>();
        clientEvaluationList.add(new ClientEvaluation(
                                                      ClientEvaluation.ClientRating.EXCELLENT,
                                                      ClientEvaluation.ClientCategory.PREMIUMCUSTOMER,
                                                      50));
        var socialPerformanceEvaluation = new SocialPerformanceEvaluation(SocialPerformanceEvaluation.NumberEnum.THREE,
                                                                                                  SocialPerformanceEvaluation.NumberEnum.THREE,
                                                                                                  SocialPerformanceEvaluation.NumberEnum.THREE,
                                                                                                  SocialPerformanceEvaluation.NumberEnum.THREE,
                                                                                                  SocialPerformanceEvaluation.NumberEnum.THREE,
                                                                                                  SocialPerformanceEvaluation.NumberEnum.THREE);
        var evaluationRecord = new EvaluationRecordImpl(clientEvaluationList, socialPerformanceEvaluation, 2023, employeeNumber);
        var managePersonalController = new ManagePersonalImpl();
        var manageEvaluationController = new ManageEvaluationImpl();


        //when
        managePersonalController.createSalesMan(salesMan);
        manageEvaluationController.createEvaluationRecord(evaluationRecord);
        var salesManFromDB = managePersonalController.readSalesMan(employeeNumber);
        //var evaluationRecordFromDB = manageEvaluationController.readEvaluationRecords(employeeNumber).get(0);


        //then
        assertEquals(salesMan.toString(), salesManFromDB.toString());
        //assertEquals(evaluationRecord.toString(), evaluationRecordFromDB.toString());
    }

    @Test
    public void testUpdateSalesManWithEvaluationRecord() {
        //given
        int employeeNumber = 12345;
        SalesMan salesMan = new SalesMan(employeeNumber, "John", "Doe", "New York");
        List<ClientEvaluation> clientEvaluationList = new ArrayList<>();
        clientEvaluationList.add(new ClientEvaluation(
                ClientEvaluation.ClientRating.EXCELLENT,
                ClientEvaluation.ClientCategory.PREMIUMCUSTOMER,
                50));
        var socialPerformanceEvaluation = new SocialPerformanceEvaluation(SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE);
        var evaluationRecord = new EvaluationRecordImpl(clientEvaluationList, socialPerformanceEvaluation, 2023, employeeNumber);
        var managePersonalController = new ManagePersonalImpl();
        var manageEvaluationController = new ManageEvaluationImpl();
        managePersonalController.createSalesMan(salesMan);
        manageEvaluationController.createEvaluationRecord(evaluationRecord);


        //when
        var salesManChanged = new SalesMan(employeeNumber, "John", "Doe", "Boston");
        managePersonalController.updateSalesMan(salesManChanged);
        var socialPerformanceEvaluationChanged = new SocialPerformanceEvaluation(SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.TWO,
                SocialPerformanceEvaluation.NumberEnum.TWO,
                SocialPerformanceEvaluation.NumberEnum.TWO,
                SocialPerformanceEvaluation.NumberEnum.TWO,
                SocialPerformanceEvaluation.NumberEnum.TWO);
        var evaluationRecordChanged = new EvaluationRecordImpl(clientEvaluationList, socialPerformanceEvaluationChanged, 2023, employeeNumber);
        manageEvaluationController.updateEvaluationRecord(evaluationRecordChanged); //TODO fix Can't find a codec for class com.hbrs.performancecockpit.records.EvaluationRecordImpl
        var salesManFromDB = managePersonalController.readSalesMan(employeeNumber);
        //var evaluationRecordFromDB = managePersonalController.readEvaluationRecords(employeeNumber).get(0); //


        //then
        assertEquals(salesManFromDB.toString(), salesManChanged.toString());
        //assertEquals(evaluationRecordFromDB.toString(), evaluationRecordChanged.toString()); //
    }

    @Test
    public void testDeleteSalesManWithEvaluationRecord() {
        //given
        int employeeNumber = 12345;
        SalesMan salesMan = new SalesMan(employeeNumber, "John", "Doe", "New York");
        List<ClientEvaluation> clientEvaluationList = new ArrayList<>();
        clientEvaluationList.add(new ClientEvaluation(
                ClientEvaluation.ClientRating.EXCELLENT,
                ClientEvaluation.ClientCategory.PREMIUMCUSTOMER,
                50));
        var socialPerformanceEvaluation = new SocialPerformanceEvaluation(SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE,
                SocialPerformanceEvaluation.NumberEnum.THREE);
        var evaluationRecord = new EvaluationRecordImpl(clientEvaluationList, socialPerformanceEvaluation, 2023, employeeNumber);
        var managePersonalController = new ManagePersonalImpl();
        var manageEvaluationController = new ManageEvaluationImpl();
        managePersonalController.createSalesMan(salesMan);
        manageEvaluationController.createEvaluationRecord(evaluationRecord);


        //when
        managePersonalController.deleteSalesMan(salesMan);
        //managePersonalController.deleteEvaluationRecord(evaluationRecord, employeeNumber); //TODO fix Can't find a codec for class com.hbrs.performancecockpit.records.EvaluationRecordImpl


        //then
        assertNull(managePersonalController.readSalesMan(employeeNumber));
        //assertEquals(managePersonalController.readEvaluationRecords(employeeNumber).toString(), "[]");
    }

}