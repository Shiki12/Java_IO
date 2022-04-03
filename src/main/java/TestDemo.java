public class TestDemo {

    public static void main(String[] args) {
        MyBufferedReader bufferedReader =new MyBufferedReader(new MyFileReader());

        bufferedReader.readFiles(10);


    }
}
