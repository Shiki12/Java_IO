package inputStream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamTest {
    /*
     *  第一种读取文件的方式
     *   单个字节读取
     *   通过  -> 使用 read(byte b)
     * */
    @Test
    public void readFile_1(){
        //文件的位置
        String path ="src/main/resources/file.txt";
        int readData = 0;
        FileInputStream fileInputStream = null;
        try {
            //将某个文件 设置为流准备传输
            fileInputStream =new FileInputStream(path);
            while ((readData = fileInputStream.read()) != -1)
            {
                System.out.print((char)readData);//转成 char 显示
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void readFile_2(){
        //文件的位置
        String path ="src/main/resources/file.txt";
        byte[] buf = new byte[8]; //一次读取 8 个字节  如果存在中文
        int readLen = 0;
        FileInputStream fileInputStream = null;
        try {
            //将某个文件 设置为流准备传输
            fileInputStream =new FileInputStream(path);
            while ((readLen = fileInputStream.read(buf)) != -1)
            {
                System.out.print((new String(buf, 0, readLen)));//转成 char 显示
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}