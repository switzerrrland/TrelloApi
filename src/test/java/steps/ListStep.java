package steps;

import beans.List;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import static core.LabelServiceObject.trelloLabelRequestBuilder;
import static core.ListServiceObject.getListObject;
import static core.BasicTrelloServiceObject.goodResponseSpecification;

public class ListStep {

    @Step("Create list")
    public String createListInBoard(String boardId, List list) {
        Response response = trelloLabelRequestBuilder()
                .setMethod(Method.POST)
                .setName(list.getName())
                .setIdBoard(boardId)
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
        return getListObject(response).getId();
    }

    @Step("Get list")
    public List getListFromTrello(String listId) {
        Response response = trelloLabelRequestBuilder()
                .setMethod(Method.GET)
                .setId(listId)
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
        return getListObject(response);
    }
}