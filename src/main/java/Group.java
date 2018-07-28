import java.util.ArrayList;

public class Group {
    public String title;

    private ArrayList<Student> students;
    private Student praepostor;

    public Group(String title) {
        this.title = title;
        students = new ArrayList<>();
    }

    public void setStudent(Student student) {
        students.add(student);
    }

    public void setPraepostor() {
        for (Student s: students) {
            if(s.getMiddleMark() >= 4.5f) praepostor = s;
        }
    }

    public Student getStudent(long id, String initials) throws UnsupportedOperationException, NullPointerException {
        for (Student s: students) {
            if(id == s.getId() && initials.equals(s.getInitials())) return s;
        }
        throw new UnsupportedOperationException("No such student in group!");
    }

    public Student getStudent(String initials) throws UnsupportedOperationException, NullPointerException {
        int i;
        for (i = 0; i < students.size(); i++) {
            if(initials.equals(students.get(i).getInitials())) return students.get(i);
        }
        throw new UnsupportedOperationException("No such students with current initials!");
    }

    public Student getStudent(long id) throws UnsupportedOperationException {
        int i;
        for (i = 0; i < students.size(); i++) {
            if(id == students.get(i).getId()) return students.get(i);
        }
        throw new UnsupportedOperationException("No such students with current ID!");
    }

    public float getMiddleGroupMark() {
        float amount = 0;
        for (Student s: students) {
            amount += s.getMiddleMark();
        }
        return (float) Math.rint((amount * 10) / 10) / students.size();
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getPraepostor() {
        return praepostor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Student s: students) {
            sb.append(s.getInitials());
            sb.append("\n");
            sb.append("\t\t");
        }
        sb.toString();
        return String.format("Title: %s\nPraepostor: %s\nStudents: %s\nMiddle mark: %.1f\nTotal: %d\n----------",
                title, getPraepostor(),sb, getMiddleGroupMark(), students.size());
    }

}
