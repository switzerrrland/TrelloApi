import beans.Board;
import beans.Label;
import beans.List;
import org.testng.annotations.DataProvider;
import static utils.Constants.*;

public class DataProviderForTrello {
    @DataProvider
    public Object[][] testBoardDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(BOARD_NAME);
        return new Object[][]{
                {trelloBoard}
        };
    }

    @DataProvider
    public Object[][] testLabelDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(BOARD_NAME);
        Label label = new Label();
        label.setColor(LABEL_COLOR);
        label.setName(LABEL_NAME);
        return new Object[][]{
                {trelloBoard, label}
        };
    }

    @DataProvider
    public Object[][] testListDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(BOARD_NAME);
        List list = new List();
        list.setName(LIST_NAME);
        return new Object[][]{
                {trelloBoard, list}
        };
    }
}