/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;


public class SalesMan {

    private final int employeeNumber;
    private final String firstName;
    private final String lastName;

    private final String location;

    @JsonCreator
    public SalesMan(@JsonProperty("employeeNumber") int employeeNumber,
                    @JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName,
                    @JsonProperty("location") String location) {
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

}
