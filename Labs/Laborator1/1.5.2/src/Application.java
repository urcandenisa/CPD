import read_and_write.Data;
import read_and_write.Reader;
import read_and_write.Writer;

import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        if(!file.createNewFile()){
            System.out.println("File already exists");
        }

        Data data = new Data(file);

        Reader reader = new Reader(5000, data);
        Writer writer = new Writer(1000, data);

        writer.start();
        reader.start();
        
    }
}
