package string;

public class Reader extends Thread{

    private long delay;
    private Data data;
    boolean beginning = false;

    public Reader(long delay, Data data, boolean beginning) {
        this.delay = delay;
        this.data = data;
        this.beginning = beginning;
    }

    @Override
    public void run() {
        char character = 1;
        while (character != 0) {
            try {
                if (beginning) {
                    character = data.readFromBeginning(data.string);
                    System.out.println("Reader beginning is reading " + character);
                } else {
                    character = data.readFromEnd(data.string);
                    System.out.println("Reader end is reading " + character);
                }
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
