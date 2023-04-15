import java.util.ArrayList;
import java.util.List;

public class Class implements Component {
    private String name;
    private List<Component> students = new ArrayList<>();

    public Class(String name) {
        this.name = name;
    }

    public Class() {
    }

    public void addStudent(Component student) {
        students.add(student);
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Component student : students) {
            student.accept(visitor);
        }
    }

    public int getNumberOfStudents() {
        return students.size();
    }
}
