package objectInputAndOutput;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MyBufferedInputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String path ="src/main/resources/MyBufferedOutputStream.dat";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

        // 2.读取， 注意顺序
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());

        System.out.println(ois.readObject().getClass());

    }
}
