package libgdx.screens.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Map;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MainButtonSize;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.controls.button.builders.BackButtonBuilder;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.game.Game;
import libgdx.game.QuestionService;
import libgdx.graphics.GraphicUtils;
import libgdx.implementations.iq.SkelGameLabel;
import libgdx.implementations.iq.SkelGameSpecificResource;
import libgdx.resources.FontManager;
import libgdx.resources.dimen.MainDimen;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.screens.AbstractScreen;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.Utils;
import libgdx.utils.model.FontColor;
import libgdx.utils.model.RGBColor;

public class GameOverScreen extends AbstractScreen {

    private Map<Integer, Integer> questionWithAnswer;
    private int finalScore;
    private int screenWidth = ScreenDimensionsManager.getScreenWidth();
    private int screenHeight = ScreenDimensionsManager.getScreenHeight();

    public GameOverScreen(Map<Integer, Integer> questionWithAnswer) {
        QuestionService questionService = new QuestionService();
        int totalCorrectAnswers = 0;
        this.questionWithAnswer = questionWithAnswer;
        for (Map.Entry<Integer, Integer> entry : questionWithAnswer.entrySet()) {
            if (questionService.isAnswerCorrect(entry.getKey(), entry.getValue())) {
                totalCorrectAnswers++;
            }
        }
        this.finalScore = questionService.calculateIq(totalCorrectAnswers);

    }

    @Override
    public void buildStage() {
        setBackgroundColor(RGBColor.WHITE);
        Table table = new Table();
        table.setFillParent(true);
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            MyButton backButton = new BackButtonBuilder().createScreenBackButton(this);
            backButton.setX(MainDimen.horizontal_general_margin.getDimen());
            backButton.setY(ScreenDimensionsManager.getScreenHeight() - MainDimen.vertical_general_margin.getDimen() - backButton.getHeight());
            addActor(backButton);
        }
        table.add(createQuestionImage()).bottom().height(screenHeight / 2).row();
        table.add(createInfoLabel()).center().height(screenHeight / 2).pad(MainDimen.vertical_general_margin.getDimen()).growX().row();

        addActor(table);
        addLineTable();
    }

    private Table createInfoLabel() {
        Table table = new Table();
        MyWrappedLabel infoLabel = new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText((SkelGameLabel.finalscoreexplanation.getText())).setFontScale(FontManager.getSmallFontDim()).build());
        table.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText("" + finalScore).setSingleLineLabel().setTextColor(FontColor.RED).setFontScale(FontManager.getBigFontDim()).build())).growX().row();
        table.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText("(" + new QuestionService().getLevelForScore(finalScore) + ")").setSingleLineLabel().setFontScale(FontManager.getNormalFontDim()).build())).growX().row();
        table.add(infoLabel).row();
        MyButton correctAnswers = new ButtonBuilder(MainGameLabel.l_showanswers.getText(), FontManager.getNormalBigFontDim()).setButtonSkin(MainButtonSkin.DEFAULT).setFixedButtonSize(MainButtonSize.TWO_ROW_BUTTON_SIZE).build();
        correctAnswers.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Utils.isValidExtraContent()) {
                    screenManager.showCorrectAnswers(questionWithAnswer);
                } else {
                    displayInAppPurchasesPopup(new Runnable() {
                        @Override
                        public void run() {
                            screenManager.showCorrectAnswers(questionWithAnswer);
                        }
                    });
                }
            }
        });
        table.add(correctAnswers).

                width(correctAnswers.getWidth()).

                height(correctAnswers.getHeight());
        table.add().

                growY();
        return table;
    }

    public static void displayInAppPurchasesPopup(Runnable redirectAfterBoughtScreen) {
        Game.getInstance().getInAppPurchaseManager().displayInAppPurchasesPopup(MainGameLabel.l_showanswers.getText() + "\n+" + MainGameLabel.billing_remove_ads.getText(), redirectAfterBoughtScreen);
    }

    private Table createQuestionImage() {
        Image questionImage = GraphicUtils.getImage(SkelGameSpecificResource.finalscore);
        Table table = new Table();
        float sideRatio = questionImage.getHeight() / ((float) questionImage.getWidth());
        int screenWidth = ScreenDimensionsManager.getScreenWidth();
        table.add().growY().row();
        table.add(questionImage).bottom().width(screenWidth).height(screenWidth * sideRatio);
        return table;
    }

    private void addLineTable() {
        float leftMarginPercent = (finalScore - QuestionService.MIN_SCORE)
                / (float) (QuestionService.MAX_SCORE - QuestionService.MIN_SCORE);
        Table lineTable = new Table();
        lineTable.setWidth(screenWidth / 100f);
        lineTable.setHeight(screenHeight / 4.4f);
        lineTable.setX(leftMarginPercent * screenWidth);
        lineTable.setY(screenHeight / 1.81f);
        lineTable.setBackground(GraphicUtils.getNinePatch(SkelGameSpecificResource.red_background));
        addActor(lineTable);
    }

    @Override
    public void onBackKeyPress() {
        Game.getInstance().getScreenManager().showMainScreen();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Utils.createChangeLangPopup();
    }
}
