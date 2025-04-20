package model;

public class Student {
    private String prn;
    private String name;
    private double marks;

    public Student(String prn, String name, double marks) {
        this.prn = prn;
        this.name = name;
        this.marks = marks;
    }

    public String getPrn() { return prn; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "PRN: " + prn + ", Name: " + name + ", Marks: " + marks;
    }
}
