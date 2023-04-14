
public class Student implements Component {
    private String id;
    private String name;
    private int score;

    public Student(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResult(){
        if (score>=60)
            return "passed";
        return "failed";
    }

    public int getScore() {
        return score;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}