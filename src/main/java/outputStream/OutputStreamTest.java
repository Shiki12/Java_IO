package outputStream;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamTest {

    /*
    *   输出流 写入文件
    * */

    @Test
    public void write(){
        OutputStream outputStream =null;
        String path ="src/main/resources/writeFile.txt";

        try {
            outputStream = new FileOutputStream(path,true);
            String he ="hello world";
            outputStream.write(he.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public  void test1(){
        int i = 101;
        String he="hello";
        System.out.println(he.getBytes());
        System.out.println((char) i);
        System.out.println( i);
    }

}
