package writer;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class WriterTest {
    @Test
    public void write(){
        String  srcPath="src/main/resources/hello222.txt";
        FileWriter fileWriter =null;

        try {
            fileWriter =new FileWriter(srcPath,true);
            String str ="hello shiki";
            fileWriter.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
