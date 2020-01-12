package libgdx.screens.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.Map;

import libgdx.controls.button.builders.BackButtonBuilder;
import libgdx.game.Game;
import libgdx.game.Question;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.dimen.MainDimen;
import libgdx.screens.AbstractScreen;

public class CorrectAnswersScreen extends AbstractScreen {

    private ScrollPane scrollPane;
    private Map<Integer, Integer> questionWithAnswer;

    public CorrectAnswersScreen(Map<Integer, Integer> questionWithAnswer) {
        this.questionWithAnswer = questionWithAnswer;
    }

    @Override
    public void buildStage() {
        scrollPane = new ScrollPane(createAllTable());
        scrollPane.setScrollingDisabled(true, false);
        Table table = new Table();
        table.setFillParent(true);
        table.add(scrollPane).expand();
        addActor(table);
        new BackButtonBuilder().addHoverBackButton(this);
    }


    private Table createAllTable() {
        Table table = new Table();
        float dimen = MainDimen.horizontal_general_margin.getDimen();
        for (Question question : Question.values()) {
            Table qaTable = new Table();
            Image questionImage = GraphicUtils.getImage(Game.getInstance().getMainDependencyManager().createResourceService().getByName("q" + question.getQuestionNr()));
            float questionDimen = dimen * 40;
            float sideRatio = questionImage.getHeight() / ((float) questionImage.getWidth());
            qaTable.add(questionImage).width(questionDimen).height(questionDimen * sideRatio).pad(dimen * 2).row();

            qaTable.add();
            table.add(qaTable).row();
        }
        return table;
    }

    @Override
    public void onBackKeyPress() {
        screenManager.showGameOver(questionWithAnswer);
    }
}
