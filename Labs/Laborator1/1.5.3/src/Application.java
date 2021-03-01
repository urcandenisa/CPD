import office.DocumentHandler;
import office.Functionary;
import office.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {
    public static void main(String[] args) {
        DocumentHandler documentHandler = new DocumentHandler();
        List<Functionary> functionaryList = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < 8; i++) {
            int delay = random.ints(1000, 5000).findFirst().getAsInt();
            functionaryList.add(new Functionary(delay, documentHandler));
        }

        for(Functionary functionary : functionaryList) {
            System.out.println("functionary thread started");
            functionary.start();
        }

        int delay = random.ints(1000, 5000).findFirst().getAsInt();
        Printer printer = new Printer(delay, documentHandler);

        System.out.println("printer thread started");
        printer.start();
    }
}
