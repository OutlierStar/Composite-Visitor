public class PrintVisitor implements Visitor {
    private StringBuilder sb = new StringBuilder();

    public String getResult() {
        return sb.toString();
    }

    @Override
    public void visit(Grade grade) {
        sb.append(grade.getName()).append("\n");
    }

    @Override
    public void visit(Class cls) {
        sb.append("--").append(cls.getName()).append("(").append(cls.getNumberOfStudents()).append(")").append("\n");
    }

    @Override
    public void visit(Student student) {
        sb.append("----").append(student.getId()).append(",").append(student.getName()).append(",").append(student.getScore()).append(",").append(student.getResult());
    }
}
