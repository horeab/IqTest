package libgdx.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.List;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.controls.popup.ProVersionPopup;
import libgdx.graphics.GraphicUtils;
import libgdx.implementations.iq.SkelDimen;
import libgdx.implementations.iq.SkelGame;
import libgdx.implementations.iq.SkelGameButtonSize;
import libgdx.implementations.iq.SkelGameLabel;
import libgdx.implementations.iq.SkelGameSpecificResource;
import libgdx.resources.FontManager;
import libgdx.resources.MainResource;
import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.Utils;

public class GameCreator {

    private final static String MAIN_TABLE_NAME = "MAIN_TABLE_NAME";

    private QuestionService service;
    private CurrentGame currentGame;

    public GameCreator(CurrentGame currentGame) {
        this.currentGame = currentGame;
        this.service = new QuestionService();
        addQuestionScreen(currentGame.getCurrentQuestion());
    }

    private void addQuestionScreen(int questionNr) {
        Table table = new Table();
        table.setName(MAIN_TABLE_NAME);
        int screenWidth = ScreenDimensionsManager.getScreenWidth();
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        table.add(createHeader()).pad(verticalGeneralMarginDimen).width(screenWidth).row();
        table.add().growY().row();
        table.add(createQuestionImage(Game.getInstance().getMainDependencyManager().createResourceService().getByName("q" + questionNr))).fillX().row();
        table.add().growY().row();
        table.add(createAnswersImages(questionNr)).padBottom(verticalGeneralMarginDimen * 2).width(screenWidth);
        table.setFillParent(true);
        Game.getInstance().getAbstractScreen().addActor(table);
    }

    private Table createHeader() {
        Table table = new Table();
        float btnFontScale = FontManager.calculateMultiplierStandardFontSize(0.8f);
        MyButton newGame = new ButtonBuilder(SkelGameLabel.new_game.getText(), btnFontScale).setButtonSkin(MainButtonSkin.DEFAULT).setFixedButtonSize(SkelGameButtonSize.HEADER_BUTTON).build();
        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startNewGame();
            }

        });
        MyButton skip = new ButtonBuilder(SkelGameLabel.skip.getText(), btnFontScale).setButtonSkin(MainButtonSkin.DEFAULT).setFixedButtonSize(SkelGameButtonSize.HEADER_BUTTON).build();
        skip.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Game.getInstance().getAppInfoService().screenShotMode()) {
                    Utils.createChangeLangPopup();
                } else {
                    goToNextLevel(true);
                }
            }
        });
        float dimen = MainDimen.horizontal_general_margin.getDimen();
        table.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText((currentGame.getCurrentQuestionToDisplay() + "/" + Question.values().length)).setFontScale(FontManager.getBigFontDim()).setSingleLineLabel().build())).pad(dimen);
        table.add().growX();
        table.add(newGame).width(newGame.getWidth()).height(newGame.getHeight());
        table.add(skip).pad(dimen).width(skip.getWidth() + skip.getWidth() / 2).height(skip.getHeight());
        return table;
    }

    private Table createAnswersImages(int questionNr) {
        Table table = new Table();
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        table.add(new MyWrappedLabel(
                new MyWrappedLabelConfigBuilder().setText((SkelGameLabel.answerchoice.getText())).setFontScale(FontManager.getSmallFontDim()).setSingleLineLabel().build())).center()
                .pad(verticalGeneralMarginDimen)
                .growX()
                .colspan(4)
                .row();
        table.setBackground(GraphicUtils.getNinePatch(SkelGameSpecificResource.popup_background));
        int answerNr = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                Image questionImage = GraphicUtils.getImage(Game.getInstance().getMainDependencyManager().createResourceService().getByName("q" + questionNr + "a" + answerNr));
                float sideRatio = questionImage.getHeight() / ((float) questionImage.getWidth());
                questionImage.setWidth(SkelDimen.side_answer_img.getDimen());
                questionImage.setHeight(SkelDimen.side_answer_img.getDimen());
                final int btnAnswerNr = answerNr;
                questionImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        answerClick(btnAnswerNr);
                    }

                });
                table.add(questionImage).pad(verticalGeneralMarginDimen / 3).height(questionImage.getHeight() * sideRatio).width(questionImage.getWidth());
                answerNr++;
            }
            table.row();
        }
        return table;
    }

    private Table createQuestionImage(Res questionRes) {
        Image questionImage = GraphicUtils.getImage(questionRes);
        Table table = new Table();
        float sideRatio = questionImage.getHeight() / ((float) questionImage.getWidth());
        int screenWidth = ScreenDimensionsManager.getScreenWidth();
        table.add(questionImage).width(screenWidth).height(screenWidth * sideRatio);
        return table;
    }


    public void refreshLevel() {
        Group root = Game.getInstance().getAbstractScreen().getStage().getRoot();
        root.findActor(MAIN_TABLE_NAME).remove();
        addQuestionScreen(currentGame.getCurrentQuestion());
    }

    private void answerClick(int answerNr) {

        int currentQuestion = currentGame.getCurrentQuestion();
        processForCorrectAnswer(answerNr, currentQuestion);

        goToNextLevel(false);
    }

    private boolean isGameOver(int nextQuestion) {
        boolean isGameOver;
        isGameOver = currentGame.getSkippedQuestions().isEmpty() && currentGame.areOnlySkippedQuestionsLeft();
        if (isGameOver) {
            return true;
        }
        isGameOver = noNextQuestion(nextQuestion) && currentGame.getSkippedQuestions().isEmpty();
        return isGameOver;
    }

    private boolean noNextQuestion(int nextQuestion) {
        return nextQuestion > Question.values().length - 1;
    }

    private void processForCorrectAnswer(int answerNr, int currentQuestion) {
        if (service.isAnswerCorrect(currentQuestion, answerNr)) {
            currentGame.setCorrectAnswers(currentGame.getCorrectAnswers() + 1);
        }
    }

    private void goToLevel(int level) {
        if (level == 10 && !Game.getInstance().getAppInfoService().isProVersion()) {
            new ProVersionPopup(Game.getInstance().getAbstractScreen()).addToPopupManager();
        } else if (level == 20 || level == 30) {
            Game.getInstance().getAppInfoService().showPopupAd();
        }

        if (isGameOver(level)) {
            goToGameOver();
        } else if (noNextQuestion(level)) {
            level = currentGame.getSkippedQuestions().get(0);
            currentGame.getSkippedQuestions().remove(0);
            currentGame.setOnlySkippedQuestionsLeft(true);
        } else {
            currentGame.setCurrentQuestion(level);
            refreshLevel();
        }
    }

    private void goToGameOver() {
        SkelGame.getInstance().getScreenManager().showGameOver(service.calculateIq(currentGame.getCorrectAnswers()));
    }

    private void startNewGame() {
        Game.getInstance().getAppInfoService().showPopupAd();
        new StoreService().reset();
        currentGame.reset();
        refreshLevel();
    }

    private void goToNextLevel(boolean isSkipped) {

        int currentQuestion = currentGame.getCurrentQuestion();
        List<Integer> skippedQuestions = currentGame.getSkippedQuestions();

        int nextQuestion = currentQuestion + 1;

        if (isSkipped) {
            if (skippedQuestions.contains(currentQuestion)) {
                skippedQuestions.remove(Integer.valueOf(currentQuestion));
            }
            skippedQuestions.add(currentQuestion);
        }

        if (noNextQuestion(nextQuestion) && !skippedQuestions.isEmpty()) {
            currentGame.setOnlySkippedQuestionsLeft(true);
        }

        if (currentGame.areOnlySkippedQuestionsLeft() && !skippedQuestions.isEmpty()) {
            int indx = skippedQuestions.indexOf(currentQuestion);
            int nextIndx = indx + 1;
            if (nextIndx > skippedQuestions.size() - 1) {
                nextIndx = 0;
            }
            nextQuestion = skippedQuestions.get(nextIndx);
        }

        if (skippedQuestions.contains(currentQuestion) && !isSkipped) {
            skippedQuestions.remove(Integer.valueOf(currentQuestion));
        }

        goToLevel(nextQuestion);
    }
}
