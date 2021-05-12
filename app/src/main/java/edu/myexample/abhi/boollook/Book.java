package edu.myexample.abhi.boollook;

public class Book {

    public Book() {
    }

    private String Author;

    public Book(String author, String department, String name) {
        Author = author;
        Department = department;
        Name = name;
    }

    private String Department;
    private String Name;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

   



}
