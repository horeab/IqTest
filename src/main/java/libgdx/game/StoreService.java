package libgdx.game;

import java.util.ArrayList;
import java.util.List;

import libgdx.preferences.PreferencesService;


public class StoreService {

    private static String SHARED_PREFS_NAME = "IqGameStoreService";
    
    private PreferencesService preferencesService = new PreferencesService(SHARED_PREFS_NAME);

    public static String CURRENT_QUESTION = "CURRENT_QUESTION";
    public static String CORRECT_ANSWERS = "CORRECT_ANSWERS";
    public static String SKIPPED_QUESTIONS = "SKIPPED_QUESTIONS";
    public static String ONLY_SKIPPED = "ONLY_SKIPPED";
    public static String CURRENT_LANGUAGE = "CURRENT_LANGUAGE";


    public int getCurrentQuestion() {
        return preferencesService.getPreferences().getInteger(CURRENT_QUESTION, 0);
    }

    public int getCorrectAnswers() {
        return preferencesService.getPreferences().getInteger(CORRECT_ANSWERS, 0);
    }

    public boolean getOnlySkipped() {
        return preferencesService.getPreferences().getBoolean(ONLY_SKIPPED, false);
    }

    public void putCurrentLanguage(String currentLanguage) {
        putValue(CURRENT_LANGUAGE, currentLanguage);
    }

    public void putCurrentQuestion(int currentQuestion) {
        putValue(CURRENT_QUESTION, currentQuestion);
    }

    public void putCorrectAnswers(int correctAnswers) {
        putValue(CORRECT_ANSWERS, correctAnswers);
    }

    public void putSkippedQuestions(List<Integer> skippedQuestions) {
        saveList(skippedQuestions, SKIPPED_QUESTIONS);
    }

    public void putOnlySkipped(boolean onlySkipped) {
        preferencesService.putBoolean(ONLY_SKIPPED, onlySkipped);
    }

    public List<Integer> getSkippedQuestions() {
        return loadList(SKIPPED_QUESTIONS);
    }

    public void reset() {
        putCurrentQuestion(0);
    }

    private void putValue(String fieldName, int value) {
        preferencesService.putInteger(fieldName, value);
    }

    private void putValue(String fieldName, String value) {
        preferencesService.putString(fieldName, value);
    }

    private List<Integer> loadList(String listName) {
        int size = preferencesService.getPreferences().getInteger(listName + "_size", 0);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++)
            list.add(preferencesService.getPreferences().getInteger(listName + "_" + i, 0));
        return list;
    }

    private void saveList(List<Integer> list, String listName) {
        preferencesService.putInteger(listName + "_size", list.size());
        for (int i = 0; i < list.size(); i++) {
            preferencesService.putInteger(listName + "_" + i, list.get(i));
        }
    }
}
