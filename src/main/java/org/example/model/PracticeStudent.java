package org.example.model;

public class PracticeStudent {
    private int studentId;
    private String studentName;
    private int sub1Score;
    private int sub2Score;
    private int sub3Score;
    private int finalScore;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSub1Score() {
        return sub1Score;
    }

    public void setSub1Score(int sub1Score) {
        this.sub1Score = sub1Score;
    }

    public int getSub2Score() {
        return sub2Score;
    }

    public void setSub2Score(int sub2Score) {
        this.sub2Score = sub2Score;
    }

    public int getSub3Score() {
        return sub3Score;
    }

    public void setSub3Score(int sub3Score) {
        this.sub3Score = sub3Score;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}
