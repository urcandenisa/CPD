package read_and_write;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {

    private File file;
    boolean writing = false;

    public Data(File file) {
        this.file = file;
    }

    public synchronized void write(String string) throws IOException {
        while(!writing){
            try{
                System.out.println("Data writer is waiting");
                wait();
                System.out.println("Data writer was notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Data is writing");

        FileWriter fileWriter = new FileWriter(file.getName());
        fileWriter.write(string);
        fileWriter.close();

        writing = false;

        notifyAll();
    }

    public synchronized String read() throws FileNotFoundException {
        while(writing){
            try{
                System.out.println("Data reader is waiting");
                wait();
                System.out.println("Data reader was notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Data is reading");

        Scanner scanner = new Scanner(file);

        String string = "";
        while(scanner.hasNext()){
            string += scanner.next();
            System.out.println("Data is reading " + string);
        }

        notifyAll();

        return string;
    }

}
