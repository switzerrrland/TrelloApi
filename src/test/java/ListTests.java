import beans.Board;
import beans.List;
import core.DataProviderForTrello;
import org.testng.annotations.Test;
import steps.ListStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ListTests extends TrelloBasicTest {
    @Test(dataProvider = "testListDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testListCreation(Board board, List list) {
        listStep = new ListStep();
        boardId = boardStep.createBoardInTrello(board);
        String listId = listStep.createListInBoard(boardId, list);
        List createdList = listStep.getListFromTrello(listId);
        assertThat(createdList.getName(), equalTo(list.getName()));
    }
}
