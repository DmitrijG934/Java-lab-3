import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class GroupTest {

    private static Group group;
    private static Student student;
    private static ArrayList<Student> students;

    static {
        group = new Group("F13");
        students = new ArrayList<>();
        student = new Student(1, "Jack Wilson");
        student.setGroup(group);
    }



    @Test
    public void setStudent() {
        assertEquals("F13", student.getGroup().title);
    }

    @Test
    public void setPraepostor() {
        Group group = new Group("FS1");
        Student s = new Student(1, "Jack Wilson");
        Student s1 = new Student(2, "Alex Nolan");
        Student s2 = new Student(3, "Jessie Norington");

        s.setGroup(group);
        s1.setGroup(group);
        s2.setGroup(group);

        s.setMark(2,3,2,5);
        s1.setMark(3,3,2,5);
        s2.setMark(4,5,5,5);

        group.setStudent(s);
        group.setStudent(s1);
        group.setStudent(s2);

        for (int i = 0; i < group.getStudents().size(); i++) {
            System.out.println(group.getStudents().get(i));
        }

        group.setPraepostor();
        assertEquals(s2, group.getPraepostor());
    }

    @Test
    public void getStudent() {
        Group group = new Group("FS1");
        Student s = new Student(1, "Jack Wilson");
        Student s1 = new Student(2, "Alex Nolan");
        Student s2 = new Student(3, "Jessie Norington");

        s.setGroup(group);
        s1.setGroup(group);
        s2.setGroup(group);

        group.setStudent(s);
        group.setStudent(s1);
        group.setStudent(s2);

        try {
            group.getStudent(s.getId(), s.getInitials());
            group.getStudent(s.getId(), "Jack Wilson");
        } catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getStudent1() {
        Group group = new Group("FS1");
        Student s = new Student(1, "Jack Wilson");
        Student s1 = new Student(2, "Alex Nolan");
        Student s2 = new Student(3, "Jessie Norington");

        s.setGroup(group);
        s1.setGroup(group);
        s2.setGroup(group);

        group.setStudent(s);
        group.setStudent(s1);
        group.setStudent(s2);

        try {
            group.getStudent(2);
        } catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getStudent2() {
        Group group = new Group("FS1");
        Student s = new Student(1, "Jack Wilson");
        Student s1 = new Student(2, "Alex Nolan");
        Student s2 = new Student(3, "Jessie Norington");

        s.setGroup(group);
        s1.setGroup(group);
        s2.setGroup(group);

        group.setStudent(s);
        group.setStudent(s1);
        group.setStudent(s2);

        try {
            group.getStudent(s.getInitials());
        } catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getMiddleGroupMark() {
        Group group = new Group("FS1");
        Student s = new Student(1, "Jack Wilson");
        Student s1 = new Student(2, "Alex Nolan");
        Student s2 = new Student(3, "Jessie Norington");

        s.setGroup(group);
        s1.setGroup(group);
        s2.setGroup(group);

        group.setStudent(s);
        group.setStudent(s1);
        group.setStudent(s2);

        float[] marks = new float[]{3.2f, 6.5f, 4.4f, 1.0f};

        for (int i = 0; i < group.getStudents().size(); i++) {
            group.getStudents().get(i).setMark(marks);
        }

        assertEquals(3.6, group.getMiddleGroupMark(), .1f);

    }

    @Test
    public void removeStudent() {
        Group group = new Group("FS1");
        Student s = new Student(1, "Jack Wilson");
        Student s1 = new Student(2, "Alex Nolan");
        Student s2 = new Student(3, "Jessie Norington");

        s.setGroup(group);
        s1.setGroup(group);
        s2.setGroup(group);

        group.setStudent(s);
        group.setStudent(s1);
        group.setStudent(s2);

        group.removeStudent(s2);

        assertEquals(2, group.getStudents().size());

    }

    @Test
    public void getStudents() {
    }

    @Test
    public void getPraepostor() {
    }
}