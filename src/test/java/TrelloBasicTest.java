import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.BoardStep;
import steps.LabelStep;
import steps.ListStep;

public class TrelloBasicTest {
    BoardStep boardStep;
    LabelStep labelStep;
    ListStep listStep;
    String boardId;

    @BeforeMethod
    public void beforeMethod() {
        boardStep = new BoardStep();
    }

    @AfterMethod
    public void afterMethod() {
        boardStep.deleteBoardFromTrello(boardId);
    }
}