package print;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class MyPrint {
    public static void main(String[] args) throws IOException {

        PrintStream out =System.out;

        /*
        *  下面两种是等价的  print 就是底层调用了write方法， 默认是打印到标准设备上面的
        * */
        out.println("通过print方法打印");
        out.write("通过write方法打印".getBytes());


        //指定输出的位置
        String path ="src/main/resources/MyPrint.txt";
        System.setOut(new PrintStream(path));
       System.out.println("这是通过printStream打印到指定位置的demo");

        out.close();

    }

    @Test
    public void  MyPrintInputWriter() throws IOException {
        String path ="src/main/resources/MyPrintPrintWriter.txt";
        //开启追加模式 并且将文件输出到指定位置
        PrintWriter printWriter = new PrintWriter(new FileWriter(path,true));
        printWriter.println("这是通过printWriter打印到指定位置的demo");
        //字符流 一般是close 或者刷新的时候才会生效的
        printWriter.close();
    }

}
