package libgdx.screens;

import java.util.List;
import java.util.Map;

import libgdx.screen.ScreenType;
import libgdx.screens.screens.CorrectAnswersScreen;
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
            return new GameOverScreen((Map<Integer, Integer>) params[0]);
        }
    },
    CORRECT_ANSWERS {
        public AbstractScreen getScreen(Object... params) {
            return new CorrectAnswersScreen((Map<Integer, Integer>) params[0]);
        }
    },

}