package core;

import beans.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static utils.Constants.*;
import static utils.PropertiesReader.getProperty;

public class ListServiceObject extends BasicTrelloServiceObject {
    public static final URI TRELLO_LIST_URI = URI.create(getProperty("listsUrl"));

    public ListServiceObject(Method requestMethod, Map<String, String> queryParams, Map<String, String> pathParams) {
        super(requestMethod, queryParams, pathParams);
    }

    public static TrelloListRequestBuilder trelloListRequestBuilder() {
        return new TrelloListRequestBuilder();
    }

    public static class TrelloListRequestBuilder {
        private Map<String, String> queryParams = new HashMap<>();
        private Map<String, String> pathParams = new HashMap<>();
        private Method requestMethod = Method.GET;

        public TrelloListRequestBuilder setMethod(Method method){
            requestMethod = method;
            return this;
        }

        public TrelloListRequestBuilder setId(String id) {
            pathParams.put(ID, id);
            return this;
        }

        public TrelloListRequestBuilder setIdBoard(String idBoard) {
            queryParams.put(ID_BOARD, idBoard);
            return this;
        }

        public TrelloListRequestBuilder setName(String name) {
            queryParams.put(NAME, name);
            return this;
        }

        public ListServiceObject buildRequest() {
            return new ListServiceObject(requestMethod, queryParams, pathParams);
        }
    }

    public Response sendRequest() {
        return RestAssured
                .given(requestSpecification()).log().all()
                .pathParams(pathParams)
                .queryParams(queryParams)
                .queryParam(KEY, getProperty("apiKey"))
                .queryParam(TOKEN, getProperty("token"))
                .request(requestMethod, getPathParamsStr())
                .prettyPeek();
    }

    public static List getListObject(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List>() {}.getType());
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri(TRELLO_LIST_URI)
                .build();
    }

}
