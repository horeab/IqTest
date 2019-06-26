package libgdx.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.game.CurrentGame;
import libgdx.game.Game;
import libgdx.game.GameCreator;
import libgdx.game.StoreService;
import libgdx.graphics.GraphicUtils;
import libgdx.implementations.iq.SkelDimen;
import libgdx.implementations.iq.SkelGameButtonSize;
import libgdx.resources.FontManager;
import libgdx.resources.MainResource;
import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;
import libgdx.screens.AbstractScreen;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.model.RGBColor;

public class MainMenuScreen extends AbstractScreen {

    private StoreService storeService;
    private CurrentGame currentGame;

    @Override
    public void buildStage() {
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
