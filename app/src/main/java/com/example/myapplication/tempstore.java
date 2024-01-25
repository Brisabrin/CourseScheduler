package com.example.myapplication;
import java.util.HashMap;
import java.util.ArrayList;
import com.example.myapplication.Assignments;
import com.example.myapplication.Exams;
import com.example.myapplication.ClassDetails;

public class tempstore {

    //Class Details, Exams, Assignments
    public static HashMap<String, HashMap<String,Object>> coursedate;
    public static String classId; //when pressed set class id

    public static void addCourseDetails(ClassDetails data) {
        if (coursedate == null) {
            coursedate = new HashMap<>();
        }

        //ClassDetails : id, title, datetime, instructor
        HashMap<String, Object> classdata = new HashMap<>();
        classdata.put("Class", data);
        coursedate.put(data.id, classdata);
    }

    public static void addAssignments(Assignments data) {
        ArrayList<Assignments> a;
        if (!coursedate.get(classId).containsKey("Assignments")) {
            a = new ArrayList<Assignments>();
            coursedate.get(classId).put("Assignments", a);

        } else {
            a = (ArrayList<Assignments>) coursedate.get(classId).get("Assignments");
        }
        a.add(data);
    }

    public static void addExams(Exams data) {
        ArrayList<Exams> a;
        if (!coursedate.get(classId).containsKey("Exams")) {
            a = new ArrayList<Exams>();
            coursedate.get(classId).put("Exams", a);

        } else {
            a = (ArrayList<Exams>) coursedate.get(classId).get("Exams");
        }
        a.add(data);
    }

}
