import string.Data;
import string.Reader;

public class Application {
    public static void main(String[] args) {
        Data data = new Data("string");

        Reader beginning = new Reader(100, data, true);
        beginning.start();

        Reader end = new Reader(100, data, false);
        end.start();
    }
}
