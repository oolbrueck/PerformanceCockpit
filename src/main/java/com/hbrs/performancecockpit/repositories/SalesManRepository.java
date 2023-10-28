/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.repositories;

import com.hbrs.performancecockpit.entities.SalesMan;

import java.util.List;

public class SalesManRepository extends BaseRepository<SalesMan> {


    public SalesManRepository() {
        super("salesManCollection");
    }

    public void createSalesMan(SalesMan salesMan) {
        this.create(salesMan);
    }

    public SalesMan readSalesMan(int employeeNumber) {
        var identifier = new KeyValue<Integer>("employeeNumber", employeeNumber);
        return this.read(identifier);
    }

    public void updateSalesMan(SalesMan salesMan) {
        var identifier = new KeyValue<Integer>("employeeNumber", salesMan.getEmployeeNumber());
        this.update(salesMan, identifier);
    }

    public void deleteSalesMan(SalesMan salesMan) {
        var identifier = new KeyValue<Integer>("employeeNumber", salesMan.getEmployeeNumber());
        this.delete(identifier);
    }

    public List<SalesMan> selectSalesMenByAttribute(String key, String value) {
        var identifier = new KeyValue<String>(key, value);
        return this.readMultiple(identifier);
    }

}

