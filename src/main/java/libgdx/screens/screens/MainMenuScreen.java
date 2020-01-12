package libgdx.screens.screens;

import com.badlogic.gdx.Gdx;

import libgdx.game.CurrentGame;
import libgdx.game.GameCreator;
import libgdx.game.StoreService;
import libgdx.implementations.iq.SkelGameRatingService;
import libgdx.screens.AbstractScreen;
import libgdx.utils.Utils;
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
            currentGame = new CurrentGame(storeService.getCurrentQuestion(), storeService.getQuestionWithAnswer());
        } else {
            storeService.reset();
            currentGame = new CurrentGame();
        }
        GameCreator creator = new GameCreator(currentGame);
        creator.refreshLevel();
    }

    @Override
    public void onBackKeyPress() {
        Gdx.app.exit();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Utils.createChangeLangPopup();
    }
}
