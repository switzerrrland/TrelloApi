package steps;

import beans.Label;
import core.LabelServiceObject.*;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import static utils.Constants.*;
import static utils.PropertiesReader.getProperty;
import static core.LabelServiceObject.getLabelObject;
import static core.LabelServiceObject.trelloLabelRequestBuilder;
import static core.BasicTrelloServiceObject.goodResponseSpecification;
import static core.BasicTrelloServiceObject.notFoundResponseSpecification;

public class LabelStep {

    @Step("Create label")
    public String createLabelInBoard(String boardId, Label label) {
        Response response = trelloLabelRequestBuilder()
                .setMethod(Method.POST)
                .setName(label.getName())
                .setColor(label.getColor())
                .setIdBoard(boardId)
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
        return getLabelObject(response).getId();
    }

    @Step("Get label")
    public Label getLabelFromTrello(String labelId) {
        Response response = trelloLabelRequestBuilder()
                .setMethod(Method.GET)
                .setId(labelId)
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
        return getLabelObject(response);
    }

    @Step("Delete label")
    public void deleteLabelFromTrello(String labelId) {
        Response response = trelloLabelRequestBuilder()
                .setMethod(Method.DELETE)
                .setId(labelId)
                .buildRequest()
                .sendRequest();
        response.then().assertThat()
                .spec(goodResponseSpecification());
    }

    @Step("Try to get deleted label")
    public void getDeletedLabelFromTrello(String labelId) {
        Response response = trelloLabelRequestBuilder()
                .setMethod(Method.GET)
                .setId(labelId)
                .buildRequest()
                .sendRequest();
        response.then().assertThat()
                .spec(notFoundResponseSpecification());
    }

    @Step("Update label")
    public Label updateLabelFieldInTrello(String labelId, String field) {
        TrelloLabelRequestBuilder builder = trelloLabelRequestBuilder()
                .setMethod(Method.PUT)
                .setId(labelId);
        switch (field) {
            case NAME:
                builder.setName(getProperty("newLabelName"));
                break;
            case COLOR:
                builder.setColor(getProperty("newLabelColor"));
                break;
            default:
                break;
        }
        Response response = builder
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
        return getLabelObject(response);
    }
}