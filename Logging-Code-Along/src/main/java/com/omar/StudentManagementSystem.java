package com.omar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class StudentManagementSystem {

    private static final Logger logger = Logger.getLogger(StudentManagementSystem.class.getName());
    private List<String> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        setupLogger();
    }

    private static void setupLogger() {
        // TODO: Configure the logger
        // 1. Set the global logging level.
        logger.setLevel(Level.ALL);
        // 2. Add a ConsoleHandler to log messages to the console.
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);

        // 3. Add a FileHandler to log messages to a file named "students.log".
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("students.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        // 4. Use SimpleFormatter to format the log messages.
    }

    public void addStudent(String studentName) {
        // TODO: Add the student to the list and log an INFO message.
        logger.log(Level.INFO,"Student with name {0} added to management system",studentName);
        students.add(studentName);
    }

    public void removeStudent(String studentName) {
        // TODO: Remove the student from the list if they exist.
        // Log a WARNING message if the student does not exist.
        if(students.contains(studentName)){
            students.remove(studentName);
            logger.log(Level.INFO,"Student Removed");
        }
        else{
            logger.log(Level.WARNING,"Student with name {0} is not in system",studentName);
        }
    }

    public int getStudentCount() {
        // TODO: Log a FINE message indicating the count is being fetched.
        logger.log(Level.FINE, "Fetching all students count...");
        return students.size();
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();


        // TODO: Add test cases to:
        // 1. Add students.
        sms.addStudent("Aron");
        sms.removeStudent("Aron");
        sms.getStudentCount();
        // 2. Remove students.
        // 3. Print the total number of students.
        // Observe how logs are generated at different levels.
    }
}