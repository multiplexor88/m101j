import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by multiplexor88 on 31.03.2016.
 */
public class hw02_1 {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> col = db.getCollection("grades");
        List<Document> sorted = col.find(new Document("type", "homework"))
                .sort(Sorts.orderBy(Sorts.ascending("student_id"), Sorts.descending("score")))
                .into(new ArrayList<Document>());
        List<Document> result = new ArrayList<Document>();
        for(int i = 0; i < sorted.size()-1; i++){
            if(!sorted.get(i).get("student_id").equals(sorted.get(i+1).get("student_id"))) {
                col.deleteOne(sorted.get(i));
            }
        }
        if(sorted.get(sorted.size()-1).get("student_id").equals(sorted.get(sorted.size()-2).get("student_id")))
            col.deleteOne(sorted.get(sorted.size()-1));

        for(Document d:sorted){
            MongoDBOperations.printJSON(d);
        }
        System.out.println(result.size());
    }
}
