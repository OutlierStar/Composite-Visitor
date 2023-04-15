public interface Visitor {
    String getResult();
    void visit(Grade grade);
    void visit(Class cls);
    void visit(Student student);
}
