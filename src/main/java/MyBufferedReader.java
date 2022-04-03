public class MyBufferedReader extends  Reader {

    private Reader reader;  //属性是reader的类型

    public MyBufferedReader(Reader reader) {
        this.reader = reader;
    }

    public  void  readFiles(int num){

        for (int i = 0; i < num; i++) {
            reader.readFile();
        }
    }

    public  void  readString(int num){

        for (int i = 0; i < num; i++) {
            reader.readString();
        }
    }



}
