package transformation;

import org.junit.Test;

import java.io.*;

public class MyTransFormation {

    public static void main(String[] args) throws IOException {
        String path ="src/main/resources/file.txt";

        InputStreamReader inputStreamReader =
                new InputStreamReader(new FileInputStream(path),"UTF-16");
       // BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
    }
    @Test
    public void demo() throws IOException {
        String path ="src/main/resources/write.txt";

        BufferedWriter bw  = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));

        bw.write("这是通过转换流按照utf-8的格式写入的测试demo");

        bw.close();
    }
}
