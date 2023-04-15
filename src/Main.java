import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        /*
         * 输入代码,请保留
         * 数组inputs对应控制台输入
         * inputs[0]: 0 - 年级、班级、学生等数据在程序内由createObjectStructure方法创建；
         * 1 - 年级、班级、学生等数据从控制台输入并经过parseInputs方法分析得到；
         * inputs[1]:
         * {g01:{c01:{s01-Jack-80,s02-Tom-70},c02:{s03-Green-90,s04-Smith-70}},g02:{c03:
         * {s05-Jones-75,s06-Jerry-85}}}
         */

        Scanner sc = new Scanner(System.in);
        String[] inputs = new String[2];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = sc.nextLine();
        }
        sc.close();

        Visitor visitor = new PrintVisitor(); // 创建访问者
        Grade[] grades = null;

        // 根据输入情况来选择采用何种方式创建年级、班级、学生等对象，并将其组合。
        if (Integer.parseInt(inputs[0]) == 0)
            grades = createObjectStructure();
        else
            grades = parseInputs(inputs[1]);

        for (Grade grade : grades) {
            grade.accept(visitor); // 接受访问者对象，输出结果
        }
        System.out.print(visitor.getResult());

        // ...
    }

    /* 创建年级、班级、学生对象并将其组合起来 */
    public static Grade[] createObjectStructure() {
        /*
         * 创建的年级、班级、学生对象如下：
         * 
         * 年级：g01，该年级有以下班级：
         * 班级：c101，该班级有以下学生
         * 学生：s01,Jack,70
         * ----s04,Green,50
         * --c102
         * ----s02,Tom,80
         * ----s05,Smith,60
         * g02
         * --c103
         * ----s03,Jerry,90
         * ----s06,Jones,70
         */

        /*
         * g01
         * --c101(2)
         * ----s01,Jack,70,passed
         * ----s04,Green,50,failed
         * --c102(2)
         * ----s02,Tom,80,passed
         * ----s05,Smith,60,passed
         * g02
         * --c103(2)
         * ----s03,Jerry,90,passed
         * ----s06,Jones,70,passed
         */

        Grade[] grades = new Grade[2];
        Grade grade1 = new Grade("g01");
        Grade grade2 = new Grade("g02");
        Class class1 = new Class("c101");
        Class class2 = new Class("c102");
        Class class3 = new Class("c103");
        grade1.addClass(class1);
        grade1.addClass(class2);
        grade2.addClass(class3);
        Student student1 = new Student("s01", "Jack", 70);
        Student student2 = new Student("s04", "Green", 50);
        Student student3 = new Student("s02", "Tom", 80);
        Student student4 = new Student("s05", "Smith", 60);
        Student student5 = new Student("s03", "Jerry", 90);
        Student student6 = new Student("s06", "Jones", 70);
        class1.addStudent(student1);
        class1.addStudent(student2);
        class2.addStudent(student3);
        class2.addStudent(student4);
        class3.addStudent(student5);
        class3.addStudent(student6);
        grades[0] = grade1;
        grades[1] = grade2;
        return grades;

    }

    /* 通过分析strInput字符串创建年级、班级、学生对象并将其组合起来 */
    public static Grade[] parseInputs(String strInput) {
        strInput = strInput.replace(" ", "");
        // 提取grade的正则
        // String pattern = "(\\w+):((\\{\\w+:\\{\\w+-\\w+-\\w+(,\\w+-\\w+-\\w+)*\\})*(,\\w+:\\{\\w+-\\w+-\\w+(,\\w+-\\w+-\\w+)*\\})*\\})";
        String pattern = "(\\w+):(\\{(\\w+:\\{(\\w+-\\w+-\\w+)*(,\\w+-\\w+-\\w+)*\\}(,\\w+:\\{(\\w+-\\w+-\\w+)*(,\\w+-\\w+-\\w+)*\\})*)*\\})";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(strInput);
        List<Grade> arrayList = new ArrayList<Grade>();
        while (m.find()) {
            Grade one = new Grade(m.group(1));
            
            try {
                for (Class class1 : parseClass(m.group(2))) {
                one.addClass(class1);
            }
            } catch (Exception e) {
                // TODO: handle exception
                // System.out.println("ERROR: parseInputs error");
            }
            arrayList.add(one);
        }
        Grade[] grades = new Grade[arrayList.size()];
        arrayList.toArray(grades);
        return grades;
    }

    public static Class[] parseClass(String strInput) {
        // 提取class的正则
        // String pattern = "(\\w+):((\\{\\w+-\\w+-\\w+)*(,\\w+-\\w+-\\w+)*\\})";
        String pattern = "(\\w+):(\\{(\\w+-\\w+-\\w+)*(,\\w+-\\w+-\\w+)*\\})";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(strInput);
        List<Class> arrayList = new ArrayList<Class>();
        while (m.find()) {
            Class one = new Class(m.group(1));
            try {
                for (Student student : parseStudent(m.group(2))) {
                    one.addStudent(student);
                }
            } catch (Exception e) {
                // TODO: handle exception
                // System.out.println("ERROR: parseClass error");
            }
            arrayList.add(one);
        }
        Class[] classes = new Class[arrayList.size()];
        arrayList.toArray(classes);
        return classes;
    }

    public static Student[] parseStudent(String strInput) {
        // 提取student的正则
        String pattern = "(\\w+)-(\\w+)-(\\w+)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(strInput);
        List<Student> arrayList = new ArrayList<Student>();
        while (m.find()) {
            Student one = new Student(m.group(1),m.group(2), Integer.parseInt(m.group(3)));
            arrayList.add(one);
        }
        Student[] students = new Student[arrayList.size()];
        arrayList.toArray(students);
        return students;
    }
}
