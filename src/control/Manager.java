package control;

import data.Report;
import data.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Manager {


    //Allow user create new student
    public static void createStudent(int count, ArrayList<Student> ls) {

        //if number of students greater than 10 ask user continue or not
        if (count >= 10) {
            System.out.print("Do you want to continue (Y/N): ");
            if (!Validation.checkInputYN()) {
                return;
            }

        }
        //loop until user input not duplicate
        while (true) {
            System.out.print("Enter id: ");
            String id = Validation.checkInputString();
            System.out.print("Enter name student: ");
            String name = Validation.checkInputString();
            if (!Validation.checkIdExist(ls, id, name)) {
                System.err.println("Id has exist student. Pleas re-input.");
                continue;
            }
            System.out.print("Enter semester: ");
            String semester = Validation.checkInputString();
            System.out.print("Enter name course: ");
            String course = Validation.checkInputCourse();
            //check student exist or not
            if (Validation.checkStudentExist(ls, id, name, semester, course)) {
                ls.add(new Student(id, name, semester, course));
                count++;
                System.out.println("Add student success.");
                return;
            }
            System.err.println("Duplicate.");

        }
    }
    //Allow user create find and sort

    public static void findAndSort(ArrayList<Student> ls) {
        //check list empty
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }

        // Sử dụng HashSet để theo dõi các tên đã xuất hiện
        HashSet<String> uniqueNames = new HashSet<>();
        ArrayList<Student> listStudentFindByName = new ArrayList<>();

        System.out.print("Enter name to search: ");
        String name = Validation.checkInputString();

        for (Student student : ls) {
            // Kiểm tra sinh viên có tên chứa tên tìm kiếm không
            if (student.getStudentName().contains(name) && !uniqueNames.contains(student.getStudentName())) {
                uniqueNames.add(student.getStudentName());
                listStudentFindByName.add(student);
            }
        }

        if (listStudentFindByName.isEmpty()) {
            System.err.println("Not exist.");
        } else {
            Collections.sort(listStudentFindByName);
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");

            // Lặp qua danh sách sinh viên đã sắp xếp và không có bản sao
            for (Student student : listStudentFindByName) {
                student.print();
            }
        }
    }

    //Allow user update and delete

    public static void updateOrDelete(int count, ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }

        System.out.print("Enter ID: ");
        String id = Validation.checkInputString();
        ArrayList<Student> listStudentFindById = getListStudentById(ls, id);

        if (listStudentFindById.isEmpty()) {
            System.err.println("No student found with that ID.");
            return;
        } else {
            Student student = getStudentByListFound(listStudentFindById);
            System.out.print("Do you want to update (U) or delete (D) student: ");

            if (Validation.checkInputUD()) {
                System.out.print("Enter new ID: ");
                String newId = Validation.checkInputString();
                System.out.print("Enter new name student: ");
                String newName = Validation.checkInputString();
                System.out.print("Enter new semester: ");
                String newSemester = Validation.checkInputString();
                System.out.print("Enter new course name: ");
                String newCourse = Validation.checkInputCourse();

                if (!Validation.checkChangeInfomation(student, newId, newName, newSemester, newCourse)) {
                    System.err.println("No changes made.");
                } else {
                    // If you want to update the student's information
                    student.setId(newId);
                    student.setStudentName(newName);
                    student.setSemester(newSemester);
                    student.setCourseName(newCourse);
                    System.err.println("Update success.");
                }
            } else {
                ls.remove(student);
                count--;
                System.err.println("Delete success.");
            }
        }
    }

    //Get list student find by id
    public static ArrayList<Student> getListStudentById(ArrayList<Student> ls, String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    //Get student user want to update/delete in list found
    public static Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name",
                "semester", "Course Name");
        //display list student found
        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = Validation.checkInputIntLimit(1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }


    //Print report
    public static void report(ArrayList<Student> ls) {


        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();

        for (Student student : ls) {
            String id = student.getId();
            String courseName = student.getCourseName();
            String studentName = student.getStudentName();

            // Kiểm tra xem báo cáo cho sinh viên đã tồn tại trong danh sách lr chưa
            boolean reportExists = false;
            for (Report report : lr) {
                if (report.getStudentName().equalsIgnoreCase(studentName) &&
                        report.getCourseName().equalsIgnoreCase(courseName)) {
                    report.setTotalCourse(report.getTotalCourse() + 1);
                    reportExists = true;
                    break;
                }
            }

            // Nếu báo cáo cho sinh viên chưa tồn tại, thêm nó vào danh sách lr
            if (!reportExists) {
                lr.add(new Report(studentName, courseName, 1));
            }
        }

        // In ra báo cáo
        System.out.println("Student Name   | Course Name | Total");
        System.out.println("---------------------------------");
        for (Report report : lr) {
            System.out.printf("%-15s | %-12s | %d\n", report.getStudentName(),
                    report.getCourseName(), report.getTotalCourse());
        }
    }

}
