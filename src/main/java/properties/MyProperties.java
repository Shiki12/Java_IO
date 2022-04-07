package properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyProperties {
    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new FileReader("src/main/resources/ mysql.properties"));
        String data;
        while ((data = br.readLine()) != null){
            String[] split = data.split("=");
            //指定 ip 或者其他参数
            if ("ip".equals(split[0]))
            {
                System.out.println(split[1]);
            }
        }
        br.close();
    }
}
