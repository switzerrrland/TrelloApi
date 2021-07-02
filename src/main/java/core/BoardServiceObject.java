package core;

import beans.Board;
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

public class BoardServiceObject extends BasicTrelloServiceObject {
    public static final URI TRELLO_BOARD_URI = URI.create(getProperty("boardsUrl"));

    public BoardServiceObject(Method requestMethod, Map<String, String> queryParams, Map<String, String> pathParams) {
        super(requestMethod, queryParams, pathParams);
    }

    public static TrelloBoardRequestBuilder trelloBoardRequestBuilder() {
        return new TrelloBoardRequestBuilder();
    }

    public static class TrelloBoardRequestBuilder {
        private Map<String, String> queryParams = new HashMap<>();
        private Map<String, String> pathParams = new HashMap<>();
        private Method requestMethod = Method.GET;

        public TrelloBoardRequestBuilder setMethod(Method method){
            requestMethod = method;
            return this;
        }

        public TrelloBoardRequestBuilder setId(String id) {
            pathParams.put(ID, id);
            return this;
        }

        public TrelloBoardRequestBuilder setName(String name) {
            queryParams.put(NAME, name);
            return this;
        }

        public TrelloBoardRequestBuilder setDesc(String desc) {
            queryParams.put(BOARD_DESC, desc);
            return this;
        }

        public TrelloBoardRequestBuilder setClosed(String closed) {
            queryParams.put(BOARD_CLOSED, closed);
            return this;
        }

        public BoardServiceObject buildRequest() {
            return new BoardServiceObject(requestMethod, queryParams, pathParams);
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

    public static Board getBoardObject(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<Board>() {}.getType());
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri(TRELLO_BOARD_URI)
                .build();
    }
}