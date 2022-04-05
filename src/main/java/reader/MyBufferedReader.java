package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyBufferedReader {
    public static void main(String[] args) throws IOException {
        String  srcPath="src/main/resources/hello.txt";
        String data;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath));
        while ( (data = bufferedReader.readLine()) != null){
            System.out.println(data);
        }
        bufferedReader.close();


    }
}
