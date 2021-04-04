package com.starishko.pdd;

public class Q{
    private int Answer_count;
    private int Correct_answer_id;
    private String Answers;
    private String Questions;
    private int id_questions;

    public Q() {
    }

    public Q(int answer_count, int correct_answer_id, String answers, String questions, int id_questions) {
        this.Answer_count = answer_count;
        this.Correct_answer_id = correct_answer_id;
        this.Answers = answers;
        this.Questions = questions;
        this.id_questions = id_questions;
    }

    public int getAnswer_count() {
        return Answer_count;
    }

    public void setAnswer_count(int answer_count) {
        Answer_count = answer_count;
    }

    public int getCorrect_answer_id() {
        return Correct_answer_id;
    }

    public void setCorrect_answer_id(int correct_answer_id) {
        Correct_answer_id = correct_answer_id;
    }

    public String getAnswers() {
        return Answers;
    }

    public void setAnswers(String answers) {
        Answers = answers;
    }

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        Questions = questions;
    }

    public int getId_questions() {
        return id_questions;
    }

    public void setId_questions(int id_questions) {
        this.id_questions = id_questions;
    }
}
