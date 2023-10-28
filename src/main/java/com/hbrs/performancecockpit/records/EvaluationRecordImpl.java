/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.records;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class EvaluationRecordImpl implements EvaluationRecord {

    private List<ClientEvaluation> clientEvaluation;
    private SocialPerformanceEvaluation socialPerformanceEvaluation;
    private int year;
    private int employeeNumber;



    public EvaluationRecordImpl(List<ClientEvaluation> clientEvaluation, SocialPerformanceEvaluation socialPerformanceEvaluation, int year, int employeeNumber) {

        if(clientEvaluation.isEmpty()) {
            throw new IllegalArgumentException("EvaluationRecord needs at least one client evaluation");
        }

        this.clientEvaluation = clientEvaluation;
        this.socialPerformanceEvaluation = socialPerformanceEvaluation;
        this.year = year;
        this.employeeNumber = employeeNumber;
    }

    @Override
    public List<ClientEvaluation> getClientEvaluation() {
        return clientEvaluation;
    }

    @Override
    public SocialPerformanceEvaluation getSocialPerformanceEvaluation() {
        return socialPerformanceEvaluation;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getEmployeeNumber() {
        return employeeNumber;
    }


    @Override
    public String toString() {
        return "EvaluationRecordImpl{" +
                "clientEvaluation=" + clientEvaluation +
                ", socialPerformanceEvaluation=" + socialPerformanceEvaluation +
                ", year=" + year +
                '}';
    }
}
