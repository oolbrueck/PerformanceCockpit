/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.repositories;

import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.records.EvaluationRecord;
import com.hbrs.performancecockpit.utils.DocumentMapper;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class EvaluationRepositiory extends BaseRepository<EvaluationRecord> {

    public EvaluationRepositiory() {
        super("evaluationCollection", EvaluationRecord.class);
    }

    public void createEvaluation(EvaluationRecord evaluation) {
        this.create(evaluation);
    }

    public SalesMan readEvaluation(int employeeNumber, int year) {
        //TODO implement
        return new SalesMan(0, "0", "0", "0");
    }

    public void updateEvaluation(EvaluationRecord evaluation) {
        Bson filter = and(
                eq("employeeNumber", evaluation.getEmployeeNumber()),
                eq("year", evaluation.getYear())
        );
        var updatedDocument = new DocumentMapper<EvaluationRecord>().toDocument(evaluation);
        this.collection.replaceOne(filter, updatedDocument);
    }

    public void deleteEvaluation(EvaluationRecord evaluation) {
        Bson filter = and(
                eq("employeeNumber", evaluation.getEmployeeNumber()),
                eq("year", evaluation.getYear()));
        collection.deleteOne(filter);
    }

    public List<SalesMan> selectEvaluationsByAttribute(String key, String value) {
        //TODO implement
        return Collections.emptyList();
    }
}
