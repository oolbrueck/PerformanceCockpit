/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.controler;

import com.hbrs.performancecockpit.records.EvaluationRecord;
import com.hbrs.performancecockpit.utils.DatabaseConnectionException;

import java.util.List;

public interface ManageEvaluation {

    void createEvaluationRecord(EvaluationRecord record);
    List<EvaluationRecord> readEvaluationRecords(int sid );
    void updateEvaluationRecord(EvaluationRecord record);
    void deleteEvaluationRecord(EvaluationRecord record);
}
