package libgdx.game;

import java.util.ArrayList;
import java.util.List;

public class CurrentGame {

    private int currentQuestion;

    private int correctAnswers;

    private boolean onlySkippedQuestionsLeft;

    private List<Integer> skippedQuestions = new ArrayList<Integer>();

    public CurrentGame() {
    }

    public CurrentGame(int currentQuestion, int correctAnswers, List<Integer> skippedQuestions, boolean onlySkippedQuestionsLeft) {
        this.currentQuestion = currentQuestion;
        this.correctAnswers = correctAnswers;
        this.skippedQuestions = skippedQuestions;
        this.onlySkippedQuestionsLeft = onlySkippedQuestionsLeft;
    }

    public void reset() {
        this.currentQuestion = 0;
        this.correctAnswers = 0;
        this.skippedQuestions = new ArrayList<Integer>();
        this.onlySkippedQuestionsLeft = false;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public int getCurrentQuestionToDisplay() {
        return currentQuestion + 1;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<Integer> getSkippedQuestions() {
        return skippedQuestions;
    }

    public boolean areOnlySkippedQuestionsLeft() {
        return onlySkippedQuestionsLeft;
    }

    public void setOnlySkippedQuestionsLeft(boolean onlySkippedQuestionsLeft) {
        this.onlySkippedQuestionsLeft = onlySkippedQuestionsLeft;
    }

}
