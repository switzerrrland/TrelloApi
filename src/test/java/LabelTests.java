import beans.Board;
import beans.Label;
import core.DataProviderForTrello;
import org.testng.annotations.Test;
import steps.LabelStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.Constants.COLOR;
import static utils.Constants.NAME;
import static utils.PropertiesReader.getProperty;

public class LabelTests extends TrelloBasicTest {
    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testLabelCreation(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        Label createdLabel = labelStep.getLabelFromTrello(labelId);
        assertThat(createdLabel.getName(), equalTo(label.getName()));
    }

    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testLabelDeletion(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        labelStep.deleteLabelFromTrello(labelId);
        labelStep.getDeletedLabelFromTrello(labelId);
    }

    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testLabelNameUpdate(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        Label labelToUpdate = labelStep.updateLabelFieldInTrello(labelId, NAME);
        assertThat(labelToUpdate.getName(), equalTo(getProperty("testLabelNewName")));
    }

    @Test(dataProvider = "testLabelDataProvider", dataProviderClass = DataProviderForTrello.class)
    public void testLabelColorUpdate(Board board, Label label) {
        labelStep = new LabelStep();
        boardId = boardStep.createBoardInTrello(board);
        String labelId = labelStep.createLabelInBoard(boardId, label);
        Label labelToUpdate = labelStep.updateLabelFieldInTrello(labelId, COLOR);
        assertThat(labelToUpdate.getColor(), equalTo(getProperty("testLabelNewColor")));
    }
}
