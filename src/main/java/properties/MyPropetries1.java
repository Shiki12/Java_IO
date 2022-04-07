package properties;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MyPropetries1 {
    public static void main(String[] args) throws IOException {

        //创建对象
        Properties properties = new Properties();
        //加载配置文件
        properties.load(new FileReader("src/main/resources/ mysql.properties"));
        //输出
        properties.list(System.out);
        System.out.println("---------------------");

        //根据 key 得到 value
        System.out.println(properties.getProperty("ip"));
        System.out.println(properties.getProperty("user"));
    }

    @Test
    public void  propertiesCreate() throws IOException {
        //创建对象
        Properties properties = new Properties();

        //创建k v
        properties.setProperty("id","shiki");
        properties.setProperty("user","shiki");
        properties.setProperty("pwd","123456789");

        //保存到相应的位置
        properties.store(new FileOutputStream("src/main/resources/ mysql2.properties"),
                "这是注释");

    }
}
