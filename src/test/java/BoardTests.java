import beans.Board;
import core.DataProviderForTrello;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.Constants.*;
import static utils.PropertiesReader.getProperty;

public class BoardTests extends TrelloBasicTest {
    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testBoardCreation(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board createdBoard = boardStep.getBoardFromTrello(boardId);
        assertThat(createdBoard.getName(), equalTo(board.getName()));
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testBoardDeletion(Board board) {
        boardId = boardStep.createBoardInTrello(board);
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testBoardDescUpdate(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board boardToUpdate = boardStep.updateBoardFieldInTrello(boardId, BOARD_DESC);
        assertThat(boardToUpdate.getDesc(), equalTo(getProperty("testBoardDesc")));
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testBoardClosedUpdate(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board boardToUpdate = boardStep.updateBoardFieldInTrello(boardId, BOARD_CLOSED);
        assertThat(boardToUpdate.getClosed().toString(), equalTo(getProperty("testBoardClosed")));
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testBoardNameUpdate(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board boardToUpdate = boardStep.updateBoardFieldInTrello(boardId, NAME);
        assertThat(boardToUpdate.getName(), equalTo(getProperty("testBoardNewName")));
    }
}