import beans.Board;
import beans.Label;
import beans.List;
import org.testng.annotations.DataProvider;
import static utils.PropertiesReader.getProperty;

public class DataProviderForTrello {
    @DataProvider
    public Object[][] testBoardDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(getProperty("boardName"));
        return new Object[][]{
                {trelloBoard}
        };
    }

    @DataProvider
    public Object[][] testLabelDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(getProperty("boardName"));
        Label label = new Label();
        label.setColor(getProperty("labelColor"));
        label.setName(getProperty("labelName"));
        return new Object[][]{
                {trelloBoard, label}
        };
    }

    @DataProvider
    public Object[][] testListDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(getProperty("boardName"));
        List list = new List();
        list.setName(getProperty("listName"));
        return new Object[][]{
                {trelloBoard, list}
        };
    }
}