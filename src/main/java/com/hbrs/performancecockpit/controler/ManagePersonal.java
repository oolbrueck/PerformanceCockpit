package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.records.EvaluationRecord;
import com.hbrs.performancecockpit.utils.DatabaseConnectionException;

import java.util.List;

public interface ManagePersonal {

    public void createSalesMan( SalesMan record ) throws DatabaseConnectionException;
    public SalesMan readSalesMan( int employeeNumber ) throws DatabaseConnectionException;
    public void updateSalesMan( SalesMan record ) throws DatabaseConnectionException;
    public void deleteSalesMan( SalesMan record ) throws DatabaseConnectionException;
    public List<SalesMan> readAllSalesMan() throws DatabaseConnectionException;
    public List<SalesMan> querySalesMan(String attribute , String key ) throws DatabaseConnectionException;

    public void createEvaluationRecord(EvaluationRecord record , int sid ) throws DatabaseConnectionException;
    public List<EvaluationRecord> readEvaluationRecords( int sid ) throws DatabaseConnectionException;
    public void updateEvaluationRecord(EvaluationRecord record , int sid ) throws DatabaseConnectionException;
    public void deleteEvaluationRecord(EvaluationRecord record , int sid ) throws DatabaseConnectionException;

}
