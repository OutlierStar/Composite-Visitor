public interface Visitor {
    void visit(Grade grade);
    void visit(Class cls);
    void visit(Student student);
}
