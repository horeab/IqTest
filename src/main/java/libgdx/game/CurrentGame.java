package libgdx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentGame {

    private int currentQuestion;

    private Map<Integer, Integer> questionWithAnswer = new HashMap<Integer, Integer>();

    public CurrentGame() {
        resetQA();
    }

    public CurrentGame(int currentQuestion, Map<Integer, Integer> questionWithAnswer) {
        this.currentQuestion = currentQuestion;
        this.questionWithAnswer = questionWithAnswer;
    }

    public void reset() {
        this.currentQuestion = 0;
        resetQA();
    }

    private void resetQA() {
        for (Question question : Question.values()) {
            questionWithAnswer.put(question.getQuestionNr(), -1);
        }
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

    public Map<Integer, Integer> getQuestionWithAnswer() {
        return questionWithAnswer;
    }

    public void setQuestionWithAnswer(Map<Integer, Integer> questionWithAnswer) {
        this.questionWithAnswer = questionWithAnswer;
    }
}
