package libgdx.implementations.iq;


import libgdx.constants.GameIdEnum;
import libgdx.game.Game;
import libgdx.screens.ScreenManager;
import libgdx.game.external.AppInfoService;
import libgdx.game.external.BillingService;
import libgdx.game.external.FacebookService;
import libgdx.screens.AbstractScreen;

public class SkelGame extends Game<AppInfoService,
        SkelGameMainDependencyManager,
        SkelGameDependencyManager,
        AbstractScreen,
        ScreenManager,
        GameIdEnum
        > {

    public SkelGame(FacebookService facebookService,
                  BillingService billingService,
                  AppInfoService appInfoService) {
        super(facebookService, billingService, appInfoService, new SkelGameMainDependencyManager());
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
