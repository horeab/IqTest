package libgdx.screens.screens;

import com.badlogic.gdx.Gdx;

import libgdx.game.CurrentGame;
import libgdx.game.GameCreator;
import libgdx.implementations.iq.SkelGameRatingService;
import libgdx.screens.AbstractScreen;
import libgdx.utils.Utils;
import libgdx.utils.model.RGBColor;

public class MainMenuScreen extends AbstractScreen {

    private CurrentGame currentGame;

    @Override
    public void buildStage() {
        new SkelGameRatingService(this).appLaunched();
        setBackgroundColor(RGBColor.WHITE);
        currentGame = new CurrentGame();
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
