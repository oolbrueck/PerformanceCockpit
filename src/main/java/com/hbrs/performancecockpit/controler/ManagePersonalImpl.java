/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.repositories.SalesManRepository;
import java.util.List;


public class ManagePersonalImpl implements ManagePersonal {

    System.Logger log = System.getLogger(this.getClass().getName() + "Logger");
    @Override
    public void createSalesMan(SalesMan record) {
            new SalesManRepository().createSalesMan(record);
    }


    @Override
    public SalesMan readSalesMan(int employeeNumber) {
        return new SalesManRepository().readSalesMan(employeeNumber);
    }

    @Override
    public void updateSalesMan(SalesMan salesMan) {
        new SalesManRepository().updateSalesMan(salesMan);
    }

    @Override
    public void deleteSalesMan(SalesMan salesMan) {
        new SalesManRepository().deleteSalesMan(salesMan);
    }

    public List<SalesMan> readAllSalesMen() {
        return new SalesManRepository().readAll();
    }

    public List<SalesMan> querySalesMan(String attribute, String key) {
        return new SalesManRepository().selectSalesMenByAttribute(attribute, key);
    }

}
