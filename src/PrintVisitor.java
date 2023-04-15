public class PrintVisitor implements Visitor {
    private StringBuilder sb = new StringBuilder();

    public String getResult() {
        return sb.toString();
    }

    @Override
    public void visit(Grade grade) {
        if (sb.length()>0) {
            sb.append("\n");
        }
        sb.append(grade.getName());
    }

    @Override
    public void visit(Class cls) {
        sb.append("\n").append("--").append(cls.getName()).append("(").append(cls.getNumberOfStudents()).append(")");
    }

    @Override
    public void visit(Student student) {
        sb.append("\n").append("----").append(student.getId()).append(",").append(student.getName()).append(",").append(student.getScore()).append(",").append(student.getResult());
    }
}
