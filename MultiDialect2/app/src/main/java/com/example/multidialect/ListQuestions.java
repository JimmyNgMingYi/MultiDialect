package com.example.multidialect;

public class ListQuestions {

    String quizQuestion;
    int answerRight;
    String choice1;
    String choice4;
    String choice3;
    String choice2;

    public ListQuestions(String quizQuestion, int answerRight, String choice1, String choice2, String choice3, String choice4) {
        this.quizQuestion = quizQuestion;
        this.answerRight = answerRight;
        this.choice1 = choice1;
        this.choice3 = choice3;
        this.choice2 = choice2;
        this.choice4 = choice4;
    }

    public int getAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(int answerRight) {
        this.answerRight = answerRight;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }
}
