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

    public List<ClientEvaluation> getClientEvaluation() {
        return clientEvaluation;
    }

    public SocialPerformanceEvaluation getSocialPerformanceEvaluation() {
        return socialPerformanceEvaluation;
    }

    @Override
    public int getYear() {
        return year;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("year", year);
        document.append("employeeNumber", employeeNumber);

        List<Document> clientEvaluations = new ArrayList<>();
        for (ClientEvaluation clientEvaluation : clientEvaluation) {
            Document clientEvalDoc = new Document()
                    .append("clientRating", clientEvaluation.getClientRating().toString())
                    .append("clientCategory", clientEvaluation.getClientCategory().toString())
                    .append("soldItems", clientEvaluation.getSoldItems());

            clientEvaluations.add(clientEvalDoc);
        }
        document.append("clientEvaluation", clientEvaluations);

        if (socialPerformanceEvaluation != null) {
            Document socialEvalDoc = new Document()
                    .append("leaderShipCompetence", socialPerformanceEvaluation.getLeaderShipCompetence().getValue())
                    .append("opennessToEmployee", socialPerformanceEvaluation.getOpennessToEmployee().getValue())
                    .append("socialBehaviourToEmployee", socialPerformanceEvaluation.getSocialBehaviourToEmployee().getValue())
                    .append("attitudeTowardsClient", socialPerformanceEvaluation.getAttitudeTowardsClient().getValue())
                    .append("communicationSkills", socialPerformanceEvaluation.getCommunicationSkills().getValue())
                    .append("integrityToCompany", socialPerformanceEvaluation.getIntegrityToCompany().getValue());

            document.append("socialPerformanceEvaluation", socialEvalDoc);
        }

        return document;
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
