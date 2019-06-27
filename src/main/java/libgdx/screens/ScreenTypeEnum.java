package libgdx.screens;

import libgdx.screen.ScreenType;
import libgdx.screens.screens.GameOverScreen;
import libgdx.screens.screens.MainMenuScreen;

public enum ScreenTypeEnum implements ScreenType {

    MAIN_MENU_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },
    GAME_OVER_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new GameOverScreen((Integer) params[0]);
        }
    },

}