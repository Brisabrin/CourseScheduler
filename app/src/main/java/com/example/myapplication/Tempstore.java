package com.example.myapplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
public class Tempstore {
    private static Tempstore instance;
    public static HashMap<String, HashMap<String,Object>> coursedata;
    public static String classId;
    public static ArrayList<Task> tasks;
    private static boolean isInputCardVisible = false;

    public static boolean isInputCardVisible() {
        return isInputCardVisible;
    }

    public static void setInputCardVisibility(boolean isVisible) {
        isInputCardVisible = isVisible;
    }


    private Tempstore() {
        tasks = new ArrayList<>();

        ClassDetails sampleClass = new ClassDetails("Math Class", "", "");

        Calendar dueDate1 = Calendar.getInstance();
        dueDate1.set(2022, Calendar.FEBRUARY, 15);
        Assignments assignment1 = new Assignments("Assignment 1", dueDate1, "Description 1");

        Calendar dueDate2 = Calendar.getInstance();
        dueDate2.set(2022, Calendar.FEBRUARY, 20);
        Assignments assignment2 = new Assignments("Assignment 2", dueDate2, "Description 2");

        Tempstore.addCourseDetails(sampleClass);
        Tempstore.addAssignments(assignment1, sampleClass.id);
        Tempstore.addAssignments(assignment2, sampleClass.id);

        Calendar examDate1 = Calendar.getInstance();
        examDate1.set(2022, Calendar.FEBRUARY, 25);
        Exams exam1 = new Exams("Midterm Exam", examDate1, "Room 101");

        Calendar examDate2 = Calendar.getInstance();
        examDate2.set(2022, Calendar.MARCH, 15);
        Exams exam2 = new Exams("Final Exam", examDate2, "Room 202");

        Tempstore.addExams(exam1, sampleClass.id);
        Tempstore.addExams(exam2, sampleClass.id);
    }


    public static synchronized Tempstore getInstance() {
        if (instance == null) {
            instance = new Tempstore();
            coursedata = new HashMap<>();
        }
        return instance;
    }


    public static void addCourseDetails(ClassDetails data) {
        if (coursedata == null) {
            coursedata = new HashMap<>();
        }

        HashMap<String, Object> classdata = new HashMap<>();
        classdata.put("Class", data);
        coursedata.put(data.id, classdata);
    }

    public static void addAssignments(Assignments data, String classId) {
        ArrayList<Assignments> a;
        if (!coursedata.containsKey(classId)) {
            coursedata.put(classId, new HashMap<>());
        }

        if (!coursedata.get(classId).containsKey("Assignments")) {
            a = new ArrayList<>();
            coursedata.get(classId).put("Assignments", a);
        } else {
            a = (ArrayList<Assignments>) coursedata.get(classId).get("Assignments");
        }
        a.add(data);
    }

    public static void addExams(Exams data, String classId) {
        ArrayList<Exams> a;
        if (!coursedata.containsKey(classId)) {
            coursedata.put(classId, new HashMap<>());
        }

        if (!coursedata.get(classId).containsKey("Exams")) {
            a = new ArrayList<>();
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
    public static void deleteClass(String classId) {
        coursedata.remove(classId);
    }

    public static void editClass(String classId, ClassDetails info) {
        if (coursedata != null && coursedata.containsKey(classId)) {
            HashMap<String, Object> classData = coursedata.get(classId);

            if (classData != null) {
                classData.put("Class", info);
            } else {
                System.out.println("ClassData is null for classId: " + classId);
            }
        } else {
            System.out.println("ClassId not found: " + classId);
        }
    }

    public static void deleteAssignment(String assignmentId, String classId) {
        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Assignments")) {
            List<Assignments> assignmentsList = (List<Assignments>) coursedata.get(classId).get("Assignments");
            assignmentsList.removeIf(assignment -> assignment.id.equals(assignmentId));

            HashMap<String, Object> classData = coursedata.get(classId);
            classData.put("Assignments", assignmentsList);
        }
    }

    public static void editAssignment(String assignmentId, String classId, Assignments updatedAssignment) {
        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Assignments")) {
            List<Assignments> assignmentsList = (List<Assignments>) coursedata.get(classId).get("Assignments");

            for (Assignments assignment : assignmentsList) {
                if (assignment.id.equals(assignmentId)) {
                    assignment.title = updatedAssignment.title;
                    assignment.dueDate = updatedAssignment.dueDate;
                    assignment.description = updatedAssignment.description;
                    break;
                }
            }
        }
    }

    public static List<Assignments> getAssignmentList(String classId) {
        List<Assignments> assignmentsList = new ArrayList<>();

        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Assignments")) {
            assignmentsList = (List<Assignments>) coursedata.get(classId).get("Assignments");
        }

        return assignmentsList;
    }

    public static Assignments getAssignment(String classId, String assignmentId) {
        Assignments assignment = null;

        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Assignments")) {
            List<Assignments> assignmentsList = (List<Assignments>) coursedata.get(classId).get("Assignments");

            for (Assignments a : assignmentsList) {
                if (a.id.equals(assignmentId)) {
                    assignment = a;
                    break;
                }
            }
        }

        return assignment;
    }

    public static void deleteExam(String examId, String classId) {
        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Exams")) {
            List<Exams> examsList = (List<Exams>) coursedata.get(classId).get("Exams");
            examsList.removeIf(exam -> exam.id.equals(examId));

            HashMap<String, Object> classData = coursedata.get(classId);
            classData.put("Exams", examsList);
        }
    }

    public static void editExam(String examId, String classId, Exams updatedExam) {
        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Exams")) {
            List<Exams> examsList = (List<Exams>) coursedata.get(classId).get("Exams");

            for (Exams exam : examsList) {
                if (exam.id.equals(examId)) {
                    exam.title = updatedExam.title;
                    exam.datetime = updatedExam.datetime;
                    exam.location = updatedExam.location;
                    break;
                }
            }
        }
    }

    public static List<Exams> getExamList(String classId) {
        List<Exams> examsList = new ArrayList<>();
        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Exams")) {
            examsList = (List<Exams>) coursedata.get(classId).get("Exams");
        }
        return examsList;
    }

    public static void sortByDueDate(String classId) {
        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Assignments")) {
            List<Assignments> assignmentsList = (List<Assignments>) coursedata.get(classId).get("Assignments");
            assignmentsList.sort((assignment1, assignment2) -> assignment1.dueDate.compareTo(assignment2.dueDate));
            coursedata.get(classId).put("Assignments", assignmentsList);
        }
    }

    public static void sortByTitle(String classId) {
        if (coursedata.containsKey(classId) && coursedata.get(classId).containsKey("Assignments")) {
            List<Assignments> assignmentsList = (List<Assignments>) coursedata.get(classId).get("Assignments");
            assignmentsList.sort((assignment1, assignment2) -> assignment1.title.compareTo(assignment2.title));
            coursedata.get(classId).put("Assignments", assignmentsList);
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static void setTasks(ArrayList<Task> newTasks) {
        tasks = newTasks;
    }

    public static int getTasksSize() {
        return tasks.size();
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void removeTask(int position) {
        tasks.remove(position);
    }

    public static void updateTask(int position, Task updatedTask) {
        tasks.set(position, updatedTask);
    }
}







