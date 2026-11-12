package com.biman.ruines.game;

public class Puzzle {

    private static final int MAX_ATTEMPTS = 3;

    private final String question;
    private final String answer;
    private final String hint;
    private final int trapDamage;
    private int attemptsRemaining = MAX_ATTEMPTS;
    private boolean hintUsed;
    private boolean solved;
    private boolean trapTriggered;

    public Puzzle(String question, String answer, String hint) {
        this(question, answer, hint, 10);
    }

    public Puzzle(String question, String answer, String hint, int trapDamage) {
        this.question = question;
        this.answer = answer;
        this.hint = hint;
        this.trapDamage = Math.max(0, trapDamage);
    }

    public String getQuestion() {
        return question;
    }

    public String getHint() {
        return hint;
    }

    public int getAttemptsRemaining() {
        return attemptsRemaining;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public boolean isSolved() {
        return solved;
    }

    public boolean isTrapTriggered() {
        return trapTriggered;
    }

    public int getTrapDamage() {
        return trapDamage;
    }

    public String requestHint() {
        hintUsed = true;
        return hint;
    }

    public boolean submitAnswer(String submittedAnswer) {
        if (solved || attemptsRemaining == 0 || submittedAnswer == null) {
            return false;
        }

        if (answer.equalsIgnoreCase(submittedAnswer.trim())) {
            solved = true;
            return true;
        }

        attemptsRemaining--;
        if (attemptsRemaining == 0) {
            trapTriggered = true;
        }
        return false;
    }

    public void resetAfterTrap() {
        attemptsRemaining = MAX_ATTEMPTS;
        trapTriggered = false;
    }
}
