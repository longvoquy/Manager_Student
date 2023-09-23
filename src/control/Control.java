package control;

import data.Student;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;
//--------------------------------------------------------

public class Control extends Menu<String> {

    ArrayList<Student> ls = new ArrayList<>();


    Validation val = new Validation();
    Scanner sc = new Scanner(System.in);
    public final Manager mn = new Manager();
    //--------------------------------------------------------
    static String[] menu = {"Create", "Find and Sort", "Update/Delete", "Report", "EXIT (0)"};

    public Control() {
        super("\n----------!!Control System!!----------", menu);

    }

    @Override
    public void execute(int n) {
        ls.add(new Student("1", "Pham Ngoc Hoa", "Spring", "java"));
        ls.add(new Student("2", "Do Quang Hiep", "Summer", ".net"));
        ls.add(new Student("3", "Nguyen Xuan Cuong", "Spring", "c/c++"));
        ls.add(new Student("4", "P Hoa", "Spring", "java"));
        ls.add(new Student("5", "Dop", "Summer", ".net"));
        ls.add(new Student("6", "N Cuong", "Spring", "c/c++"));
        ls.add(new Student("8", "Pham  Hoa", "Spring", "java"));
        ls.add(new Student("7", "Nong", "Spring", "c/c++"));
        ls.add(new Student("9", "DHiep", "Summer", ".net"));
        ls.add(new Student("10", "Nng", "Spring", "c/c++"));
        ls.add(new Student("11", "Ban", "Spring", "c/c++"));
        int count = 10;
        switch (n) {

            case 1 -> Manager.createStudent(count, ls);
            case 2 -> Manager.findAndSort(ls);
            case 3 -> Manager.updateOrDelete(count, ls);
            case 4 -> Manager.report(ls);

        }
    }


}