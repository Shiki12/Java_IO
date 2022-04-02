package outputStream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

    //拷贝图片 测试一下下
    @Test
    public void copy(){


        //读文件 地址 以及 写文件地址
        String srcFile= "D:\\图片\\1.jpg";
        String tarFile="src/main/resources/1.jpg";

        //缓冲区  增加读取效果
        byte[] buff =new byte[1024];

        int readLen=0;

        //创建 输入输出流
        FileInputStream fileInputStream= null ;
        FileOutputStream fileOutputStream =null ;

        //读取文件

        try {
            fileInputStream =new FileInputStream(srcFile);
            fileOutputStream =new FileOutputStream(tarFile);
            while ((readLen=fileInputStream.read(buff)) != -1 ){
                fileOutputStream.write(buff,0,readLen);
            }
            System.out.println( "复制成功！！！");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }

                if (fileOutputStream !=null){
                    fileOutputStream.close();
                }
            }catch (Exception e){
              e.printStackTrace();
            }


        }


    }

}
