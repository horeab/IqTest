package libgdx.game;

import java.util.HashMap;
import java.util.Map;

import libgdx.preferences.PreferencesService;
import libgdx.utils.PreferencesUtils;


public class StoreService {

    private static String SHARED_PREFS_NAME = "IqGameStoreService";

    private PreferencesService preferencesService = new PreferencesService(SHARED_PREFS_NAME);

    public static String CURRENT_QUESTION = "CURRENT_QUESTION";
    public static String QUESTION_WITH_ANSWER = "QUESTION_WITH_ANSWER";


    public int getCurrentQuestion() {
        return preferencesService.getPreferences().getInteger(CURRENT_QUESTION, 0);
    }

    public void putCurrentQuestion(int currentQuestion) {
        putValue(CURRENT_QUESTION, currentQuestion);
    }

    public Map<Integer, Integer> getQuestionWithAnswer() {
        return PreferencesUtils.loadMap(QUESTION_WITH_ANSWER, preferencesService);
    }


    public void putQuestionWithAnswer(Map<Integer, Integer> questionWithAnswer) {
        PreferencesUtils.saveMap(questionWithAnswer, QUESTION_WITH_ANSWER, preferencesService);
    }

    public void reset() {
        preferencesService.clear();
    }

    private void putValue(String fieldName, int value) {
        preferencesService.putInteger(fieldName, value);
    }

    private void putValue(String fieldName, String value) {
        preferencesService.putString(fieldName, value);
    }

}
