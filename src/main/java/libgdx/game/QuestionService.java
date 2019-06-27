package libgdx.game;

import libgdx.implementations.iq.SkelGameLabel;

public class QuestionService {

    public static final int MIN_SCORE = 57;
    public static final int MAX_SCORE = 143;

    public boolean isAnswerCorrect(int questionNr, int answer) {
        return Question.getQuestion(questionNr).getAnwser() == answer;
    }

    public int calculateIq(int correctAnswers) {
        float ratio = 2.2f;
        return (int) Math.ceil(MIN_SCORE + (correctAnswers * ratio));
    }

    public String getLevelForScore(int score) {
        String level = "";
        if (score < 70) {
            level = SkelGameLabel.verylow.getText();
        } else if (score < 90) {
            level = SkelGameLabel.low.getText();
        } else if (score < 110) {
            level = SkelGameLabel.normal.getText();
        } else if (score < 130) {
            level = SkelGameLabel.high.getText();
        } else {
            level = SkelGameLabel.veryhigh.getText();
        }
        return level;
    }

}
