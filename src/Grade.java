import java.util.ArrayList;
import java.util.List;

public class Grade implements Component {
    private String name;
    private List<Component> classes = new ArrayList<>();

    public Grade(String name) {
        this.name = name;
    }

    public Grade() {
    }


    public void addClass(Component cls) {
        classes.add(cls);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Component cls : classes) {
            cls.accept(visitor);
        }
    }
}



