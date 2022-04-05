package outputStream;

import java.io.*;

public class MyBufferedCopy {

    public static void main(String[] args) throws IOException, FileNotFoundException {

        String tarPath ="src/main/resources/1.flv";
        String srcPath ="E:\\1.flv";

        //创建缓冲变量
        byte[] bytes = new byte[1024];

        int readLen = 0;

        //创建流
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcPath));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tarPath));

        while ((readLen= bufferedInputStream.read(bytes)) != -1 ){
            bufferedOutputStream.write(bytes,0,readLen);
        }
        System.out.println("copy success!!!");

        bufferedInputStream.close();
        bufferedOutputStream.close();


    }
}
