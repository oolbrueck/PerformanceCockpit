package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.utils.DatabaseConnectionException;

import java.util.List;

public interface ManagePersonal {

    void createSalesMan( SalesMan record ) throws DatabaseConnectionException;
    SalesMan readSalesMan( int employeeNumber ) throws DatabaseConnectionException;
    void updateSalesMan( SalesMan record ) throws DatabaseConnectionException;
    void deleteSalesMan( SalesMan record ) throws DatabaseConnectionException;

}
