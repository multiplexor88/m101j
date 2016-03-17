import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;
public class test
{
    public static void main(String []args)
    {
        get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Hello, World!s";
            }
        });
    }
}
