/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.records;


import java.util.List;

public interface EvaluationRecord {

    public String toString();

    public int getYear();

    public int getEmployeeNumber();

    public List<ClientEvaluation> getClientEvaluation();

    public SocialPerformanceEvaluation getSocialPerformanceEvaluation();
}
