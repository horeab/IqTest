package libgdx.implementations.iq;


import libgdx.constants.GameIdEnum;
import libgdx.game.Game;
import libgdx.game.external.AppInfoService;
import libgdx.login.GuestUserLoginService;
import libgdx.screens.AbstractScreen;
import libgdx.screens.ScreenManager;

public class SkelGame extends Game<AppInfoService,
        SkelGameMainDependencyManager,
        SkelGameDependencyManager,
        AbstractScreen,
        ScreenManager,
        GameIdEnum
        > {

    public SkelGame(AppInfoService appInfoService) {
        super(appInfoService, new SkelGameMainDependencyManager());
    }

    @Override
    public void create() {
        super.create();
        loginService = new GuestUserLoginService();
    }

    public SkelGameDependencyManager getDependencyManager() {
        return getSubGameDependencyManager();
    }

    public static SkelGame getInstance() {
        return (SkelGame) Game.getInstance();
    }

    @Override
    protected void displayScreenAfterAssetsLoad() {
        ScreenManager screenManager = getScreenManager();
        screenManager.showMainScreen();
    }
}
