package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyBufferedWriter {

    public static void main(String[] args) throws IOException {

        String  srcPath="src/main/resources/MyBufferedWriter.txt";

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(srcPath,true));

        bufferedWriter.write("hello shiki");
        bufferedWriter.write("------------->hello shiki");
        bufferedWriter.newLine();
        bufferedWriter.write("----------------------->hello shiki");
        bufferedWriter.close();

        /*
            bufferedWriter.newLine();  换行
            开启追加模式
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(srcPath,true));
        */
    }
}
