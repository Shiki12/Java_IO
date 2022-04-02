package reader;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

public class ReaderTest {

    /*
    * 单个字符读取
    *
    * */
    @Test
    public  void  read(){
        String  srcPath="src/main/resources/hello.txt";
        int data =0;
        FileReader reader =null;
        try {
          reader = new FileReader(srcPath);

          while ( (data = reader.read()) != -1){
              System.out.print( (char) data);
          }

        } catch (IOException e) {


        }finally {
            if (reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /*
    * 数组行书读取
    * */

    @Test
    public  void  read_2(){
        String  srcPath="src/main/resources/hello.txt";
        char [] buff =new char[1024];
        int readLen =0;
        FileReader reader =null;
        try {
            reader = new FileReader(srcPath);
            while ( (readLen = reader.read(buff)) != -1){
                System.out.print(new String(buff,0,readLen));
            }

        } catch (IOException e) {


        }finally {
            if (reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
