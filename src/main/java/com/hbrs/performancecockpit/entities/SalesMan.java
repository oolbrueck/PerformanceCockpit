/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.entities;

import org.bson.Document;

import java.time.LocalDate;

public class SalesMan {

    private final int employeeNumber;
    private final String firstName;
    private final String lastName;

    private final String location;

    public SalesMan(int employeeNumber, String firstName, String lastName, String location) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() { return location; }

    @Override
    public String toString() {
        return "SalesMan{" +
                "employeeNumber=" + employeeNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Document toDocument() {
        var document = new Document();
        document.append("firstName" , this.firstName );
        document.append("lastName" , this.lastName );
        document.append("employeeNumber" , this.employeeNumber);
        document.append("location" , this.location);
        return document;
    }

}
