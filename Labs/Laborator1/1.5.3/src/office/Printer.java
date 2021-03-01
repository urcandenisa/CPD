package office;

public class Printer extends Thread{

    private long delay;
    private DocumentHandler documentHandler;

    public Printer(long delay, DocumentHandler documentHandler) {
        super();
        this.delay = delay;
        this.documentHandler = documentHandler;
    }

    @Override
    public void run() {
        try {
            while (!documentHandler.hasDocuments()) {
                System.out.println("Nothing to print");
                Thread.sleep(500);
            }
            while (documentHandler.hasDocuments()) {
                documentHandler.printDocument();
                sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
