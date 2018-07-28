import org.json.simple.JSONArray;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Student {
    private long id;
    private String initials;

    private Group group;
    private ArrayList<Float> marks;

    public Student(long id, String initials) {
        this.id = id;
        this.initials = initials;
        marks = new ArrayList<>();
    }


    public void setMark(float mark) {
        marks.add(mark);
    }

    public void setMark(float ... mark) {
        for (float m: mark) {
            marks.add(m);
        }
    }

    public void setMark(JSONArray array) {
        for (int i = 0; i < array.size(); i++) {
            marks.add((Float) array.get(i));
        }
    }

    public float getMiddleMark() {
        float amount = 0f;
        float res;
        try {
            for (int i = 0; i < marks.size(); i++) {
                amount += marks.get(i);
            }
            res = new BigDecimal(String.valueOf(amount)).divide(new BigDecimal(String.valueOf(marks.size())), 1).floatValue();
            return res;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new UnsupportedOperationException(new ArrayIndexOutOfBoundsException("No such marks yet!"));
        }
    }

    public long getId() {
        return id;
    }

    public String getInitials() {
        return initials;
    }

    public Group getGroup() {
        return group;
    }

    public ArrayList<Float> getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nInitials: %s\nGroup: %s\nMarks: %s\nMiddle mark: %.1f\n", id,
                initials, group.title, marks.toString(),getMiddleMark());
    }

    public void setGroup(Group group) {
        this.group = group;

    }

}