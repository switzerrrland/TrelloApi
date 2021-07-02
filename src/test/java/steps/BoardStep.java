package steps;

import beans.Board;
import core.BoardServiceObject.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.qameta.allure.Step;
import static utils.Constants.*;
import static utils.PropertiesReader.getProperty;
import static core.BoardServiceObject.getBoardObject;
import static core.BoardServiceObject.trelloBoardRequestBuilder;
import static core.BasicTrelloServiceObject.goodResponseSpecification;

public class BoardStep {

    @Step("Create board")
    public String createBoardInTrello(Board board) {
        Response response = trelloBoardRequestBuilder()
                .setMethod(Method.POST)
                .setName(board.getName())
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
        return getBoardObject(response).getId();
    }

    @Step("Get board")
    public Board getBoardFromTrello(String boardId) {
        Response response = trelloBoardRequestBuilder()
                .setMethod(Method.GET)
                .setId(boardId)
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
        return getBoardObject(response);
    }

    @Step("Delete board")
    public void deleteBoardFromTrello(String boardId) {
        Response response = trelloBoardRequestBuilder()
                .setMethod(Method.DELETE)
                .setId(boardId)
                .buildRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(goodResponseSpecification());
    }

    @Step("Update board")
    public Board updateBoardFieldInTrello(String boardId, String field) {
        TrelloBoardRequestBuilder builder = trelloBoardRequestBuilder()
                .setMethod(Method.PUT)
                .setId(boardId);
        switch (field) {
            case NAME:
                builder.setName(getProperty("newBoardName"));
                break;
            case BOARD_CLOSED:
                builder.setClosed(getProperty("boardClosed"));
                break;
            case BOARD_DESC:
                builder.setDesc(getProperty("boardDescription"));
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
        return getBoardObject(response);
    }

}
