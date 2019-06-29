package libgdx.screens.screens;

import com.badlogic.gdx.Gdx;

import libgdx.game.CurrentGame;
import libgdx.game.GameCreator;
import libgdx.game.StoreService;
import libgdx.implementations.iq.SkelGameRatingService;
import libgdx.screens.AbstractScreen;
import libgdx.utils.model.RGBColor;

public class MainMenuScreen extends AbstractScreen {

    private StoreService storeService;
    private CurrentGame currentGame;

    @Override
    public void buildStage() {
        new SkelGameRatingService(this).appLaunched();
        setBackgroundColor(RGBColor.WHITE);
        initCurrentGameWithStateManager();
    }

    private void initCurrentGameWithStateManager() {
        storeService = new StoreService();
        if (storeService.getCurrentQuestion() != 0) {
            currentGame = new CurrentGame(storeService.getCurrentQuestion(), storeService.getCorrectAnswers(),
                    storeService.getSkippedQuestions(), storeService.getOnlySkipped());
        } else {
            storeService.reset();
            currentGame = new CurrentGame();
        }
        GameCreator creator = new GameCreator(currentGame);
        creator.refreshLevel();
    }

    private void saveCurrentState() {
        storeService.putCorrectAnswers(currentGame.getCorrectAnswers());
        storeService.putCurrentQuestion(currentGame.getCurrentQuestion());
        storeService.putSkippedQuestions(currentGame.getSkippedQuestions());
        storeService.putOnlySkipped(currentGame.areOnlySkippedQuestionsLeft());
    }

    @Override
    public void onBackKeyPress() {
        saveCurrentState();
        Gdx.app.exit();
    }

}
