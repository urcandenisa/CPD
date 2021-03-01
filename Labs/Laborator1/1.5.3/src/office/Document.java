package office;

public class Document {

    private int number;

    public Document(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Document " +
                 number +
                ' ';
    }
}
