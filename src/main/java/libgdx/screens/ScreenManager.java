package libgdx.screens;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import libgdx.screen.AbstractScreenManager;

public class ScreenManager extends AbstractScreenManager {

    @Override
    public void showMainScreen() {
        showScreen(ScreenTypeEnum.MAIN_MENU_SCREEN);
//        showCorrectAnswers(Arrays.asList(3, 7, 9));
    }

    public void showGameOver(Map<Integer, Integer> questionWithAnswer) {
        showScreen(ScreenTypeEnum.GAME_OVER_SCREEN, questionWithAnswer);
    }

    public void showCorrectAnswers(List<Integer> correctAnswers) {
        showScreen(ScreenTypeEnum.CORRECT_ANSWERS, correctAnswers);
    }
}
