/**
 * Author: Oliver Olbrück
 */

package com.hbrs.performancecockpit.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hbrs.performancecockpit.entities.SalesMan;
import com.hbrs.performancecockpit.utils.DocumentMapper;
import com.hbrs.performancecockpit.utils.Utils;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public abstract class BaseRepository<T> {

    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected MongoCollection<Document> collection;

    public BaseRepository(String collection) {
        var dbPort = Utils.getDatabasePort();
        var dbHost = Utils.getDatabaseHost();
        var dbName = Utils.getDatabaseName();
        this.mongoClient = new MongoClient(dbHost, dbPort);;
        this.database = mongoClient.getDatabase(dbName);
        this.collection = database.getCollection(collection);
    }

    protected void create(T objectToBeCrated) {
        this.collection.insertOne(new DocumentMapper<T>().toDocument(objectToBeCrated));
    }

    protected <M> T read(KeyValue<M> identifier) {
            Bson filter = eq(identifier.getKey(), identifier.getValue());
            MongoCursor<Document> cursor = this.collection.find(filter).iterator();
            if (cursor.hasNext()) {
                Document document = cursor.next();
                return new DocumentMapper<T>().fromDocument(document, new TypeReference<T>() {});
            } else {
                return null;
            }
    }

    protected <M> void update(T objectToBeUpdated, KeyValue<M> identifier) {
        Bson filter = eq(identifier.getKey(), identifier.getValue());
        Document updatedDocument = new Document("$set", new DocumentMapper<T>().toDocument(objectToBeUpdated));
        this.collection.updateOne(filter, updatedDocument);
    }

    protected <M> void delete(KeyValue<M> identifier) {
        Bson filter = eq(identifier.getKey(), identifier.getValue());
        this.collection.deleteOne(filter);
    }

    public List<T> readAll() {
        List<T> resultList = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            resultList.add(new DocumentMapper<T>().fromDocument(document, new TypeReference<T>() {}));
        }
        return resultList;
    }

    protected <M> List<T> readMultiple(KeyValue<M> identifier) {
        Document query = new Document(identifier.getKey(), identifier.getValue());
        FindIterable<Document> documents = collection.find(query);
        MongoCursor<Document> cursor = documents.iterator();
        List<T> objects = new ArrayList<>();

        while (cursor.hasNext()) {
            Document document = cursor.next();
            T obj = new DocumentMapper<T>().fromDocument(document, new TypeReference<T>() {});
            objects.add(obj);
        }
        return objects;
    }

    protected class KeyValue<M> {
        private String key;
        private M value;

        public KeyValue(String key, M value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public M getValue() {
            return value;
        }
    }
}
