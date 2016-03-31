import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by multiplexor88 on 27.03.2016.
 */
public class MongoDBOperations
{
    void someOperations(){
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("names");

        Document doc = new Document();
        doc.append("Name", "Igor");
        doc.append("Age", 25);
//        collection.insertOne(doc);
//        doc.remove("_id");
        System.out.println("Find first");
        printJSON(collection.find().first());

        System.out.println("Find all");
        List<Document> list = collection.find().into(new ArrayList<Document>());
        for(Document d:list){
            printJSON(d);
        }
        System.out.println("Find all with iterator");
        MongoCursor<Document> cur = collection.find().iterator();
        try{
            while(cur.hasNext()){
                printJSON(cur.next());
            }
        }finally {
            cur.close();
        }
        System.out.println("Count");
        System.out.println(collection.count());

        System.out.println("Filter");
        Bson filter = new Document("Age", new Document("$gt", 10));
        Bson filter2 = Filters.and(Filters.gt("Age", 10));
        printJSON(collection.find(filter2).first());

        System.out.println("Include-exclude");
        Bson exclude = new Document("_id", 0);
        Bson include = new Document("_id", 1);
        Bson includeExclude = Projections.fields(Projections.exclude("_id"), Projections.include("name"));
        printJSON(collection.find().projection(includeExclude).first());

        System.out.println("Sorting");
        Bson sort = new Document("Age", 1);
        Bson sort2 = Sorts.orderBy(Sorts.ascending("Age"), Sorts.descending("Name"));
        printJSON(collection.find().sort(sort2).skip(2).limit(3).first());

        System.out.println("Update");
        collection.replaceOne(Filters.eq("Age", 25), new Document());
        collection.updateOne(Filters.eq("Age", 25), new Document());
        collection.updateOne(Filters.eq("Age", 25), Updates.combine(Updates.set("x", 20)), new UpdateOptions().upsert(true));
        //new Document("$set", new Document("examiner", "Jones"))
    }

    static public void printJSON(Document doc){
        JsonWriter writer = new JsonWriter(new StringWriter(), new JsonWriterSettings(JsonMode.SHELL, false));
        new DocumentCodec().encode(writer, doc, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        System.out.println(writer.getWriter());
        System.out.flush();
    }
}
