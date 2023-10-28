/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.records.EvaluationRecord;
import com.hbrs.performancecockpit.repositories.EvaluationRepositiory;
import java.util.Collections;
import java.util.List;


public class ManageEvaluationImpl implements ManageEvaluation {

    @Override
    public void createEvaluationRecord(EvaluationRecord record) {
        new EvaluationRepositiory().createEvaluation(record);
    }

    @Override
    public List<EvaluationRecord> readEvaluationRecords(int employeeNumber) {
        //TODO implement
        return Collections.emptyList();
    }

    @Override
    public void updateEvaluationRecord(EvaluationRecord record) {
        new EvaluationRepositiory().updateEvaluation(record);
    }

    @Override
    public void deleteEvaluationRecord(EvaluationRecord record) {
        new EvaluationRepositiory().deleteEvaluation(record);
    }
}
