/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.records;

public class SocialPerformanceEvaluation {
    private NumberEnum leaderShipCompetence;
    private NumberEnum opennessToEmployee;
    private NumberEnum socialBehaviourToEmployee;
    private NumberEnum attitudeTowardsClient;
    private NumberEnum communicationSkills;
    private NumberEnum integrityToCompany;

    public SocialPerformanceEvaluation(NumberEnum leaderShipCompetence, NumberEnum opennessToEmployee, NumberEnum socialBehaviourToEmployee,
                                       NumberEnum attitudeTowardsClient, NumberEnum communicationSkills, NumberEnum integrityToCompany) {
        this.leaderShipCompetence = leaderShipCompetence;
        this.opennessToEmployee = opennessToEmployee;
        this.socialBehaviourToEmployee = socialBehaviourToEmployee;
        this.attitudeTowardsClient = attitudeTowardsClient;
        this.communicationSkills = communicationSkills;
        this.integrityToCompany = integrityToCompany;
    }

    public NumberEnum getLeaderShipCompetence() {
        return leaderShipCompetence;
    }

    public NumberEnum getOpennessToEmployee() {
        return opennessToEmployee;
    }

    public NumberEnum getSocialBehaviourToEmployee() {
        return socialBehaviourToEmployee;
    }

    public NumberEnum getAttitudeTowardsClient() {
        return attitudeTowardsClient;
    }

    public NumberEnum getCommunicationSkills() {
        return communicationSkills;
    }

    public NumberEnum getIntegrityToCompany() {
        return integrityToCompany;
    }

    public enum NumberEnum { //TODO besseren Namen ausdenken
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4);

        private int value;

        NumberEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static NumberEnum fromInt(int value) {
            for (NumberEnum num : NumberEnum.values()) {
                if (num.getValue() == value) {
                    return num;
                }
            }
            throw new IllegalArgumentException("Ungültiger Wert für NumberEnum: " + value);
        }
    }

    @Override
    public String toString() {
        return "SocialPerformanceEvaluation{" +
                "leaderShipCompetence=" + leaderShipCompetence +
                ", opennessToEmployee=" + opennessToEmployee +
                ", socialBehaviourToEmployee=" + socialBehaviourToEmployee +
                ", attitudeTowardsClient=" + attitudeTowardsClient +
                ", communicationSkills=" + communicationSkills +
                ", integrityToCompany=" + integrityToCompany +
                '}';
    }
}
