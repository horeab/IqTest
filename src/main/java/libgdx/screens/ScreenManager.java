package libgdx.screens;

import libgdx.campaign.CampaignLevel;
import libgdx.campaign.LettersCampaignLevelEnum;
import libgdx.screen.AbstractScreenManager;
import libgdx.screens.ScreenTypeEnum;

public class ScreenManager extends AbstractScreenManager {

    @Override
    public void showMainScreen() {
        showScreen(ScreenTypeEnum.MAIN_MENU_SCREEN);
//        showScreen(ScreenTypeEnum.GAME_SCREEN, LettersCampaignLevelEnum.LEVEL_0_0);
//        showCampaignScreen();
    }

}
