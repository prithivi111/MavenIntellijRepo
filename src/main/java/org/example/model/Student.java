package org.example.model;

public class Student {
    private double studentId;
    private String studentName;
    private double sub1Score;
    private double sub2Score;
    private double sub3Score;
    private double finalScore;

    public double getStudentId() {
        return studentId;
    }

    public void setStudentId(double studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getSub1Score() {
        return sub1Score;
    }

    public void setSub1Score(double sub1Score) {
        this.sub1Score = sub1Score;
    }

    public double getSub2Score() {
        return sub2Score;
    }

    public void setSub2Score(double sub2Score) {
        this.sub2Score = sub2Score;
    }

    public double getSub3Score() {
        return sub3Score;
    }

    public void setSub3Score(double sub3Score) {
        this.sub3Score = sub3Score;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }


}
