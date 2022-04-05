package writer;

import java.io.*;

public class MyBufferedCopy {
    public static void main(String[] args) throws IOException {

        String srcFile="src/main/resources/hello.txt";
        String tarFile="src/main/resources/MyBufferedCopy.txt";

        //创建缓冲变量
        String data;

        //创建流

        BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tarFile));

        while ( (data = bufferedReader.readLine()) != null){
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();


    }
}
