import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.operation.UpdateOperation;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

import static spark.Spark.*;
public class test
{
    public static void main(String []args) throws IOException, TemplateException {
//        final Configuration configuration = new Configuration();
//        configuration.setClassForTemplateLoading(test.class, "/");
//
//        MongoClient client = new MongoClient();
//        MongoDatabase db = client.getDatabase("test");
//        final MongoCollection<Document> collection = db.getCollection("names");
//
//
//        get("/", new Route() {
//            public Object handle(Request request, Response response) throws Exception {
//                Template helloTmpl = configuration.getTemplate("test.ftl");
//                StringWriter sw = new StringWriter();
////                Map<String, Object> map = new HashMap<String, Object>();
////                map.put("name", "Igor");
////                helloTmpl.process(map, sw);
//                helloTmpl.process(collection.find().first(), sw);
//                System.out.println(sw);
//                return sw;
//            }
//        });


    }


}
