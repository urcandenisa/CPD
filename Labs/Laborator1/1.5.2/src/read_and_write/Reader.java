package read_and_write;

import java.io.FileNotFoundException;

public class Reader extends Thread{

    private long delay;
    private Data data;

    public Reader(long delay, Data data) {
        this.delay = delay;
        this.data = data;
    }

    @Override
    public void run() {
        String string = null;
        while(!"end".equals(string)){
            System.out.println("Reader asking for string");

            try {
                string = data.read();
                sleep(delay);
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
