package core;

import beans.Label;
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

public class LabelServiceObject extends BasicTrelloServiceObject {
    public static final URI TRELLO_LABEL_URI = URI.create(getProperty("labelsUrl"));

    public LabelServiceObject(Method requestMethod, Map<String, String> queryParams, Map<String, String> pathParams) {
        super(requestMethod, queryParams, pathParams);
    }

    public static TrelloLabelRequestBuilder trelloLabelRequestBuilder() {
        return new TrelloLabelRequestBuilder();
    }

    public static class TrelloLabelRequestBuilder {
        private Map<String, String> queryParams = new HashMap<>();
        private Map<String, String> pathParams = new HashMap<>();
        private Method requestMethod = Method.GET;

        public TrelloLabelRequestBuilder setMethod(Method method){
            requestMethod = method;
            return this;
        }

        public TrelloLabelRequestBuilder setId(String id) {
            pathParams.put(ID, id);
            return this;
        }

        public TrelloLabelRequestBuilder setIdBoard(String idBoard) {
            queryParams.put(ID_BOARD, idBoard);
            return this;
        }

        public TrelloLabelRequestBuilder setName(String name) {
            queryParams.put(NAME, name);
            return this;
        }

        public TrelloLabelRequestBuilder setColor(String color) {
            queryParams.put(COLOR, color);
            return this;
        }

        public LabelServiceObject buildRequest() {
            return new LabelServiceObject(requestMethod, queryParams, pathParams);
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

    public static Label getLabelObject(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<Label>() {}.getType());
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri(TRELLO_LABEL_URI)
                .build();
    }

}
