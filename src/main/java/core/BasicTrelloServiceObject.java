package core;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import java.util.Map;
import static org.hamcrest.Matchers.lessThan;

public class BasicTrelloServiceObject {
    protected Method requestMethod;
    protected Map<String, String> queryParams;
    protected Map<String, String> pathParams;

    public BasicTrelloServiceObject(Method requestMethod, Map<String, String> queryParams, Map<String, String> pathParams) {
        this.requestMethod = requestMethod;
        this.queryParams = queryParams;
        this.pathParams = pathParams;
    }

    public static ResponseSpecification goodResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification notFoundResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }

    public String getPathParamsStr() {
        String pathParamsStr = "";
        for (String param: pathParams.keySet()) {
            pathParamsStr += "{" + param + "}" + "/";
        }
        return pathParamsStr;
    }
}