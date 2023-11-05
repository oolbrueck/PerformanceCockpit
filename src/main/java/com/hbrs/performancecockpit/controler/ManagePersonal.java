package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.utils.DatabaseConnectionException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ManagePersonal {

    void createSalesMan( SalesMan record );
    SalesMan readSalesMan( int employeeNumber );
    void updateSalesMan( SalesMan record );
    void deleteSalesMan( SalesMan record );
    List<SalesMan> readAll();

}
