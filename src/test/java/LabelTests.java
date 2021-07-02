import beans.Board;
import beans.Label;
import org.testng.annotations.Test;
import steps.LabelStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.Constants.*;

public class LabelTests extends TrelloBasicTest {
    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void createLabelTest(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        Label createdLabel = labelStep.getLabelFromTrello(labelId);
        assertThat(createdLabel.getName(), equalTo(label.getName()));
    }

    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void deleteLabelTest(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        labelStep.deleteLabelFromTrello(labelId);
        labelStep.getDeletedLabelFromTrello(labelId);
    }

    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void updateLabelNameTest(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        Label labelToUpdate = labelStep.updateLabelFieldInTrello(labelId, NAME);
        assertThat(labelToUpdate.getName(), equalTo(NEW_LABEL_NAME));
    }

    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void updateLabelColorTest(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        Label labelToUpdate = labelStep.updateLabelFieldInTrello(labelId, COLOR);
        assertThat(labelToUpdate.getColor(), equalTo(NEW_LABEL_COLOR));
    }
}
