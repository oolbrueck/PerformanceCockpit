/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.records;

import org.bson.Document;

public interface EvaluationRecord {

    public Document toDocument();

    public String toString();

    public int getYear();
}
