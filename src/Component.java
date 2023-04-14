public interface Component {
    String getName();
    void accept(Visitor visitor);
}
