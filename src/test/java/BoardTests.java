import beans.Board;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.Constants.*;

public class BoardTests extends TrelloBasicTest {
    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void createBoardTest(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board createdBoard = boardStep.getBoardFromTrello(boardId);
        assertThat(createdBoard.getName(), equalTo(board.getName()));
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void deleteBoardTest(Board board) {
        boardId = boardStep.createBoardInTrello(board);
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void updateBoardDescriptionTest(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board boardToUpdate = boardStep.updateBoardFieldInTrello(boardId, BOARD_DESC);
        assertThat(boardToUpdate.getDesc(), equalTo(BOARD_DESCRIPTION));
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void closeBoardTest(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board boardToUpdate = boardStep.updateBoardFieldInTrello(boardId, BOARD_CLOSED);
        assertThat(boardToUpdate.getClosed().toString(), equalTo(IS_BOARD_CLOSED));
    }

    @Test(dataProvider = "testBoardDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void updateBoardNameTest(Board board) {
        boardId = boardStep.createBoardInTrello(board);
        Board boardToUpdate = boardStep.updateBoardFieldInTrello(boardId, NAME);
        assertThat(boardToUpdate.getName(), equalTo(NEW_BOARD_NAME));
    }
}