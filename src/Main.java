import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        /* 
         * 输入代码,请保留  
         * 数组inputs对应控制台输入
         * inputs[0]: 0 - 年级、班级、学生等数据在程序内由createObjectStructure方法创建；
         *            1 - 年级、班级、学生等数据从控制台输入并经过parseInputs方法分析得到；
         * inputs[1]: {g01:{c01:{s01-Jack-80,s02-Tom-70},c02:{s03-Green-90,s04-Smith-70}},g02:{c03:{s05-Jones-75,s06-Jerry-85}}}
         *  */
        
        Scanner sc = new Scanner(System.in);
        String[] inputs = new String[2];
        for(int i=0;i<inputs.length;i++){
            inputs[i]=sc.nextLine();
        }
        sc.close();
        
        Visitor visitor = new PrintVisitor();  //创建访问者
        Grade[] grades=null;
        
        // 根据输入情况来选择采用何种方式创建年级、班级、学生等对象，并将其组合。
        if (Integer.parseInt(inputs[0])==0) 
            grades = createObjectStructure();
        else
            grades = parseInputs(inputs[1]);
        
        for (Grade grade : grades) {
            grade.accept(visitor);  //接受访问者对象，输出结果
        }
        
        // ...
    }
    
    /* 创建年级、班级、学生对象并将其组合起来 */
    public static Grade[] createObjectStructure() {
        /*  创建的年级、班级、学生对象如下：
        
        年级：g01，该年级有以下班级：
                班级：c101，该班级有以下学生
                        学生：s01,Jack,70
        ----s04,Green,50
        --c102
        ----s02,Tom,80
        ----s05,Smith,60
        g02
        --c103
        ----s03,Jerry,90
        ----s06,Jones,70    
        */
        ...
    }    
    
    /* 通过分析strInput字符串创建年级、班级、学生对象并将其组合起来 */
    public static Grade[] parseInputs(String strInput) {
        ...
    }    

}
