package com.example.myapplication;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Tempstore {

    //Class Details, Exams, Assignments
    private static Tempstore instance;
    public static HashMap<String, HashMap<String,Object>> coursedata;
    public static String classId; //when pressed set class id
    private Tempstore() {
        // Private constructor to prevent instantiation
    }

    public static synchronized Tempstore getInstance() {
        if (instance == null) {
            instance = new Tempstore();
        }
        return instance;
    }


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

    public static void addExams(Exams data, String classId) {
        ArrayList<Exams> a;
        if (!coursedata.get(classId).containsKey("Exams")) {
            a = new ArrayList<Exams>();
            coursedata.get(classId).put("Exams", a);

        } else {
            a = (ArrayList<Exams>) coursedata.get(classId).get("Exams");
        }
        a.add(data);
    }


    public static List<ClassDetails> getClassList() {
        List<ClassDetails> classList = new ArrayList<>();

        if (coursedata != null) {
            for (HashMap<String, Object> classData : coursedata.values()) {
                if (classData.containsKey("Class")) {
                    ClassDetails classDetails = (ClassDetails) classData.get("Class");
                    classList.add(classDetails);
                }
            }
        }

        return classList;
    }
}
