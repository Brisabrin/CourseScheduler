package com.example.myapplication;
import java.util.HashMap;
import java.util.ArrayList;
import com.example.myapplication.Assignments;
import com.example.myapplication.Exams;
import com.example.myapplication.ClassDetails;

public class tempstore {

    //Class Details, Exams, Assignments
    public static HashMap<String, HashMap<String,Object>> coursedata;
    public static String classId; //when pressed set class id

    public static void addCourseDetails(ClassDetails data) {
        if (coursedata == null) {
            coursedata = new HashMap<>();
        }

        //ClassDetails : id, title, datetime, instructor
        HashMap<String, Object> classdata = new HashMap<>();
        classdata.put("Class", data);
        coursedata.put(data.id, classdata);
    }

    public static void addAssignments(Assignments data) {
        ArrayList<Assignments> a;
        if (!coursedata.get(classId).containsKey("Assignments")) {
            a = new ArrayList<Assignments>();
            coursedata.get(classId).put("Assignments", a);

        } else {
            a = (ArrayList<Assignments>) coursedata.get(classId).get("Assignments");
        }
        a.add(data);
    }

    public static void addExams(Exams data) {
        ArrayList<Exams> a;
        if (!coursedata.get(classId).containsKey("Exams")) {
            a = new ArrayList<Exams>();
            coursedata.get(classId).put("Exams", a);

        } else {
            a = (ArrayList<Exams>) coursedata.get(classId).get("Exams");
        }
        a.add(data);
    }

}
