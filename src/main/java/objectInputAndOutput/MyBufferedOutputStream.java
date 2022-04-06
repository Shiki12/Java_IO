package objectInputAndOutput;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MyBufferedOutputStream {

    public static void main(String[] args) throws IOException {
        String path ="src/main/resources/MyBufferedOutputStream.dat";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

        oos.writeInt(1000);
        oos.writeBoolean(true);// boolean -> Boolean (实现了 Serializable)
        oos.writeChar('a');// char -> Character (实现了 Serializable)
        oos.writeDouble(9.5);// double -> Double (实现了 Serializable)
        oos.writeUTF("shiki");//String
        oos.writeObject(new Dog("dog",15));
        oos.close();




    }



}
