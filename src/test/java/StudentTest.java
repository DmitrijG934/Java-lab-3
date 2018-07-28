import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentTest {

    private static Student student;

    static {
        student = new Student(1, "Wilson J.S");
    }

    @Test
    public void setMark() {
        // check regular method
        student.setMark(15.2f);
        assertEquals(15.2f, student.getMarks().get(0), 0.1f);

        student.getMarks().removeAll(student.getMarks());

        // check method with free count parameters
        student.setMark(15.2f, 13f, 2f, 6f);
        float[] expectedMarks = {15.2f, 13f, 2f, 6f};
        int assertCount = 0;
        for (int i = 0; i < student.getMarks().size(); i++) {
            inner: for (int j = 0; j < expectedMarks.length; j++) {
                if(expectedMarks[j] == student.getMarks().get(i)) assertCount++;
                continue inner;
            }
        }
        assertEquals(student.getMarks().size(), assertCount);
        student.getMarks().removeAll(student.getMarks());

    }

    @Test
    public void getMiddleMark() {
        student.setMark(2.2f, 6.3f, 10.1f);
        assertEquals(6.2f,  student.getMiddleMark(), 0.1f);
    }

    @Test
    public void setGroup() {
        student.setGroup(new Group("F13"));
        assertEquals("F13", student.getGroup().title);
    }
}