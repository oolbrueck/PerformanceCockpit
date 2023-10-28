/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbrs.performancecockpit.records.ClientEvaluation;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentMapper<T> {

    ObjectMapper objectMapper = new ObjectMapper();

    public Document toDocument(T object) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Document.parse(json);
    }

    public T fromDocument(Document document, TypeReference<T> typeReference) {
        String json = document.toJson();
        T obj = null;
        try {
            obj = objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
