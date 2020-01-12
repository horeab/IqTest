package libgdx.screens;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import libgdx.game.Question;
import libgdx.screen.AbstractScreenManager;

public class ScreenManager extends AbstractScreenManager {

    @Override
    public void showMainScreen() {
        showScreen(ScreenTypeEnum.MAIN_MENU_SCREEN);
//        HashMap<Integer, Integer> questionWithAnswer = new HashMap<>();
//        for (Question question : Question.values()) {
//            questionWithAnswer.put(question.getQuestionNr(), new Random().nextInt(8));
//        }
//        showGameOver(questionWithAnswer);
    }

    public void showGameOver(Map<Integer, Integer> questionWithAnswer) {
        showScreen(ScreenTypeEnum.GAME_OVER_SCREEN, questionWithAnswer);
    }

    public void showCorrectAnswers(Map<Integer, Integer> questionWithAnswer) {
        showScreen(ScreenTypeEnum.CORRECT_ANSWERS, questionWithAnswer);
    }
}
