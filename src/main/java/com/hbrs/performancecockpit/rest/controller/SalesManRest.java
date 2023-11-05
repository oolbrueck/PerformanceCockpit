/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.rest.controller;

import com.hbrs.performancecockpit.controler.ManagePersonal;
import com.hbrs.performancecockpit.entities.SalesMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("salesMenRest")
@RequestMapping(value = "/salesmen")
public class SalesManRest {

    @Autowired
    private ManagePersonal managePersonal;


    @GetMapping(value = "/all")
    public ResponseEntity<List<SalesMan>> all() {
        try {
            return new ResponseEntity<>(managePersonal.readAll(), HttpStatus.ACCEPTED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<SalesMan> readSalesMan(@PathVariable int id) {
        try {
            return new ResponseEntity<>(managePersonal.readSalesMan(id), HttpStatus.ACCEPTED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}