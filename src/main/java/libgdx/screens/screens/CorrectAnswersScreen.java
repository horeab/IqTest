package libgdx.screens.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.Map;

import libgdx.controls.button.builders.BackButtonBuilder;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.game.Game;
import libgdx.game.Question;
import libgdx.graphics.GraphicUtils;
import libgdx.implementations.iq.SkelDimen;
import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.screens.AbstractScreen;
import libgdx.utils.Utils;
import libgdx.utils.model.FontConfig;
import libgdx.utils.model.RGBColor;

public class CorrectAnswersScreen extends AbstractScreen {

    private ScrollPane scrollPane;
    private Map<Integer, Integer> questionWithAnswer;

    public CorrectAnswersScreen(Map<Integer, Integer> questionWithAnswer) {
        this.questionWithAnswer = questionWithAnswer;
    }

    @Override
    public void buildStage() {
        setBackgroundColor(RGBColor.WHITE);
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
            qaTable.add(new MyWrappedLabel((question.getQuestionNr() + 1) + "", FontConfig.FONT_SIZE * 2)).padBottom(dimen / 2).padTop(dimen * 2).row();
            qaTable.add(questionImage).width(questionDimen).height(questionDimen * sideRatio).padBottom(dimen / 2).row();
            qaTable.add();
            table.add(qaTable).row();

            Image correctAnswer = GraphicUtils.getImage(Game.getInstance().getMainDependencyManager().createResourceService().getByName("q" + question.getQuestionNr() + "a" + question.getAnwser()));
            Image userAnswer = null;
            if (questionWithAnswer.get(question.getQuestionNr()) != question.getAnwser()) {
                userAnswer = GraphicUtils.getImage(Game.getInstance().getMainDependencyManager().createResourceService().getByName("q" + question.getQuestionNr() + "a" + questionWithAnswer.get(question.getQuestionNr())));
            }
            Table answerTable = new Table();
            float padRight = 0;
            if (userAnswer != null) {
                padRight = dimen * 4;
                answerTable.add(new MyWrappedLabel(MainGameLabel.l_correct_answer.getText())).padRight(padRight);
                answerTable.add(new MyWrappedLabel(MainGameLabel.l_your_answer.getText()));
            } else {
                answerTable.add(new MyWrappedLabel(MainGameLabel.l_your_answer_is_correct.getText()));
            }
            answerTable.row();
            GraphicUtils.getImage(Game.getInstance().getMainDependencyManager().createResourceService().getByName("q" + question.getQuestionNr() + "a" + question.getAnwser()));
            sideRatio = correctAnswer.getHeight() / ((float) correctAnswer.getWidth());
            correctAnswer.setWidth(SkelDimen.side_answer_img.getDimen());
            correctAnswer.setHeight(SkelDimen.side_answer_img.getDimen());
            answerTable.add(correctAnswer).padRight(padRight).height(correctAnswer.getHeight() * sideRatio).width(correctAnswer.getWidth());
            answerTable.setBackground(GraphicUtils.getNinePatch(MainResource.green_background));
            if (userAnswer != null) {
                answerTable.setBackground(GraphicUtils.getNinePatch(MainResource.red_background));
                answerTable.add(userAnswer).height(correctAnswer.getHeight() * sideRatio).width(correctAnswer.getWidth());
            }
            table.add(answerTable).padBottom(dimen * 4).row();
        }
        return table;
    }

    @Override
    public void onBackKeyPress() {
        screenManager.showGameOver(questionWithAnswer);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Utils.createChangeLangPopup();
    }
}
