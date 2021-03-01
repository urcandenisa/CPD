package read_and_write;

import java.io.IOException;

public class Writer extends Thread{

    private String[] strings = {"word1", "word2", "word3", "word4", "word5", "end"};
    private long delay;
    private Data data;

    public Writer(long delay, Data data) {
        this.delay = delay;
        this.data = data;
    }

    @Override
    public void run() {
        for(String string: strings){

            try {
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Writer is writing " + string);

            try {
                data.write(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
