package office;

import java.util.Random;

public class Functionary extends Thread {

    private long delay;
    private DocumentHandler documentHandler;

    public Functionary(long delay, DocumentHandler documentHandler) {
        this.delay = delay;
        this.documentHandler = documentHandler;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            int nrOfDocuments = random.nextInt(5);
            for (int i = 0; i <= nrOfDocuments; i++) {
                sleep(delay);
                Document document = new Document(random.nextInt(100));
                documentHandler.elaborateDocument(document);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
