package com.example.entity;


public class StudentCourse {
    public int relationID;
    public int courseID;
    public int studentID;
    public int studentGrade;
    public String studentAnswer="";//学生的答案

    public StudentCourse(int relationID, int courseID, int studentID, int studentGrade, String studentAnswer) {
        this.relationID = relationID;
        this.courseID = courseID;
        this.studentID = studentID;
        this.studentGrade = studentGrade;
        this.studentAnswer = studentAnswer;
    }

    public StudentCourse() {
    }

    public int getRelationID() {
        return relationID;
    }

    public void setRelationID(int relationID) {
        this.relationID = relationID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "relationID=" + relationID +
                ", courseID=" + courseID +
                ", studentID=" + studentID +
                ", studentGrade=" + studentGrade +
                ", studentAnswer='" + studentAnswer + '\'' +
                '}';
    }
}
