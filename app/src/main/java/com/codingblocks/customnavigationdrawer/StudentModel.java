package com.codingblocks.customnavigationdrawer;

/**
 * Created by Sachin on 9/3/2016.
 */
public class StudentModel {
    int id;
    String student_name;
    int batch_id;
    String user_name;

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public StudentModel(int id, String student_name,String user_name, int batch_id)
    {
        this.id=id;
        this.student_name=student_name;
        this.user_name=user_name;
        this.batch_id=batch_id;
    }

}
