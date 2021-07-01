package core;

import beans.Board;
import beans.Label;
import beans.List;
import org.testng.annotations.DataProvider;
import static utils.PropertiesReader.getProperty;

public class DataProviderForTrello {
    @DataProvider
    public Object[][] testBoardDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(getProperty("testBoardName"));
        return new Object[][]{
                {trelloBoard}
        };
    }

    @DataProvider
    public Object[][] testLabelDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(getProperty("testBoardName"));
        Label label = new Label();
        label.setColor(getProperty("testLabelColor"));
        label.setName(getProperty("testLabelName"));
        return new Object[][]{
                {trelloBoard, label}
        };
    }

    @DataProvider
    public Object[][] testListDataProvider() {
        Board trelloBoard = new Board();
        trelloBoard.setName(getProperty("testBoardName"));
        List list = new List();
        list.setName(getProperty("testListName"));
        return new Object[][]{
                {trelloBoard, list}
        };
    }
}