import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Dekanat {
    private ArrayList<Student> students;
    private ArrayList<Group> groups;

    private JSONParser parser;

    public Dekanat() {
        students = new ArrayList<>();
        groups = new ArrayList<>();
        parser = new JSONParser();
        parseJsonStudents();
        parseJsonGroups();
        setRandomMarks();
        for (Group g: groups) {
            g.setPraepostor();
            if(g.title.equals("U10"))
                break;
        }
    }

    private void parseJsonStudents() {
        Student student;
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader("students_main.json"));
            Iterator<JSONObject> iterator = (Iterator<JSONObject>) array.iterator();
            while (iterator.hasNext()) {
                JSONObject obj = (JSONObject) iterator.next();
                student = new Student((long) obj.get("id"), obj.get("initials").toString());
                student.setGroup(new Group(obj.get("group").toString()));
                students.add(student);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonGroups() {
        Group group;
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader("groups_main.json"));
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext()) {
                JSONObject obj = (JSONObject) iterator.next();
                group = new Group(obj.get("title").toString());
                for(Student s: students) {
                    if(s.getGroup().title.equals("F13") && group.title.equals("F13")) {
                        s.setGroup(group);
                        group.setStudent(s);
                    } else if(s.getGroup().title.equals("A10") && group.title.equals("A10")) {
                        s.setGroup(group);
                        group.setStudent(s);
                    } else if(s.getGroup().title.equals("G15") && group.title.equals("G15")) {
                        s.setGroup(group);
                        group.setStudent(s);
                    }
                }
                groups.add(group);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray setJSONMarks() {
        JSONArray array = new JSONArray();
        Random random = new Random();
        int rand = 2 + random.nextInt(8 - 3);
        for (int i = 0; i < rand; i++) {
            array.add(i, (float) Math.rint((Math.random() * 8) * 10) / 10);
        }
        return array;
    }

    public void badStudentsTranslate() {
        for(Student s: students) {
            if(s.getMiddleMark() > 2.5f && s.getMiddleMark() < 3.5f) {
                System.out.println(s.getInitials() + " translated from " + s.getGroup().title + " to " + groups.get(3).title);
                System.out.printf("Middle mark: %.1f\n", s.getMiddleMark());
                s.getGroup().removeStudent(s);
                s.setGroup(groups.get(3));
                groups.get(3).setStudent(s);
            }
        }
    }

    public void translate(String initials, int groupIndex) {
       for(Student s: students) {
           if(s.getInitials().equals(initials)) {
               System.out.println(s.getInitials() + " translated from " + s.getGroup().title + " to " + groups.get(groupIndex).title);
               s.getGroup().removeStudent(s);
               s.setGroup(groups.get(groupIndex));
               groups.get(groupIndex).setStudent(s);
           }
           break;
       }
    }

    public void expel() {
        for (Student s: students) {
            if(s.getMiddleMark() <= 2.5f) {
                s.getGroup().removeStudent(s);
                System.out.println(s.getInitials() + " was expelled!");
                System.out.printf("Middle mark: %.1f\n", s.getMiddleMark());
            }
        }
    }

    public void setRandomMarks() {

        try {
            JSONParser parser = new JSONParser();
            JSONArray students_array = (JSONArray) parser.parse(new FileReader("students_main.json"));

            JSONObject object;
            Iterator<JSONObject> iterator = students_array.iterator();
            Iterator<Student> studentIterator = students.iterator();

            while (iterator.hasNext() && studentIterator.hasNext()) {
                object = (JSONObject) iterator.next();
                // set random marks
                object.remove("marks");
                object.put("marks", setJSONMarks());
                studentIterator.next().setMark((JSONArray) object.get("marks"));
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }


    public void studentsInfo() {
        for (Student s: students) System.out.println(s);
    }

    public void groupsInfo() {
        for (Group group: groups) System.out.println(group);
    }

    public void updateJSONFiles() {
        try(
                FileWriter writer = new FileWriter("students_update.json");
                FileWriter writer1 = new FileWriter("groups_update.json");
                ) {
            JSONArray studentsData = new JSONArray();
            JSONObject student = new JSONObject();

            JSONArray groupData = new JSONArray();
            JSONObject group = new JSONObject();

            for(Student s: students) {
                student.remove("ID", s.getId());
                student.remove("initials", s.getInitials());
                student.remove("marks", s.getMarks().toString());
                student.remove("group", s.getGroup().title);
                student.put("ID", s.getId());
                student.put("initials", s.getInitials());
                student.put("marks", s.getMarks().toString());
                student.put("group", s.getGroup().title);
                studentsData.add(student.toString());
            }

            for(Group g: groups) {
                group.put("title", g.title);
                group.put("students", g.getStudents().toString());
                group.put("praepostor", g.getPraepostor());
                groupData.add(group.toString());
            }

            writer.write(studentsData.toString());
            writer.flush();

            writer1.write(groupData.toString());
            writer1.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}