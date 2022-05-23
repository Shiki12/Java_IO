# 根据教程视频自己记的笔记 算是自己的总结吧

# Java_IO
==数据以文件的形式保存，数据的传输以流的形式传输。==

##    分类

###  按数据流的方向

  输入流，输出流。

### 按处理数据单位不同

字节流（InputStream,OutputStream），字符流(Reader,Writer)。
（1） 字节流：数据流中最小的数据单元是字节。
（2） 字符流：数据流中最小的数据单元是字符， Java中的字符是Unicode编码，一个字符占用两个字节。

### 按功能不同

  节点流，处理流。
（1）程序用于直接操作目标设备所对应的类叫节点流。
（2）程序通过一个间接流类去调用节点流类，以达到更加灵活方便地读写各种类型的数据，这个间接流类就是处理流。

wxz

字节流一般用来传输图片，视频音频。字符流用来传输一些文本文件

- 字节流

​     ![image-20220402154632398](https://s2.loli.net/2022/04/16/2DAnRmHojNXGQBr.png)

- 字符流

​      ![image-20220402154711779](https://s2.loli.net/2022/04/16/R85oYN9yIAw1L2J.png)

## 1.文件操作

   略

## 2.字符流 字节流

**读文件的时候一定要使用缓冲变量接收，不然容易出问题。**

### 1.字节流

#### 1.1文件读取

==**read(byte b)**，读取完之后会返回 -1，没有读取完会返回该字节数。==

==**read(byte[] buff)**，读取完之后会返回 -1，没有读取完会返回**本次读取的长度**。==（可以自己打断点）

注意int 类型和 char类型之间可以强制转换。

1. 指定文件路径
2. 添加缓冲变量  可以是数组也可以是单个变量
3. 将某个文件解析成输入流
4. 通过缓冲变量挨个读取 ，输出       
5. 最后关闭

单个字节读取，新建file.txt文件 随便输入一些

```java
/*
 *  第一种读取文件的方式
 *   单个字节读取
 *   通过  -> 使用 read(byte b)
 * */
@Test
public void readFile_1(){
    //文件的位置
    String path ="src/main/resources/file.txt";
    int readData = 0;
    FileInputStream fileInputStream = null;
    try {
        //将某个文件 设置为流准备传输
        fileInputStream =new FileInputStream(path);
        while ((readData = fileInputStream.read()) != -1)
        {
            System.out.print((char)readData);//转成 char 显示
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

缺点

由于是单个字节的读取，所以说效率非常的低。并且万一出现中文字符那么就会出现乱码（基本上都会）。

所以我们采用第二种 利用数组来读取。

使用缓冲区读取的时候

```java
public void readFile_2(){
    //文件的位置
    String path ="src/main/resources/file.txt";
    byte[] buf = new byte[8]; //一次读取 8 个字节  
    int readLen = 0;
    FileInputStream fileInputStream = null;
    try {
        //将某个文件 设置为流准备传输
        fileInputStream =new FileInputStream(path);
        while ((readLen = fileInputStream.read(buf)) != -1)
        {
            System.out.print((new String(buf, 0, readLen)));//转成 char 显示
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

这种方法还是会出现乱码的情况，原因是一个中文字符 被分成了两个或者三个字节，万一我们缓冲区的大小刚好把这个字符分成了两部分，那么就会出现乱码。所以读取文本一般使用字符流。

#### 1.2文件输出

注意：==**文件输出是有两种模式的！！！，一种是覆盖模式，一种是追加模式。**==，在创建输出流的时候

默认是覆盖模式，根据需求自行确定

```java
 outputStream = new FileOutputStream(path); //覆盖模式

 outputStream = new FileOutputStream(path,true); //开启追加模式
```

1. 指定文件路径
2. 添加缓冲变量  可以是数组也可以是单个变量
3. 将某个文件解析成输出流
4. **将创建的字符串转换成 字节的形式，通过write方法写入**   
5. 最后关闭

```java
@Test
public void write(){
    OutputStream  =null;
    String path ="src/main/resources/writeFile.txt";
    try {
        outputStream = new FileOutputStream(path,true);
        String he ="hello world";
        outputStream.write(he.getBytes());
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
```

#### 1.3案例

   将一张图片拷贝到resource目录下

需求分析  

1. 根据路径读取文件，将文件转换成二进制流
2. 通过java程序将读取的文件写入到指定目录

```java
@Test
public void copy(){
    //读文件 地址 以及 写文件地址
    String srcFile= "D:\\图片\\1.jpg";
    String tarFile="src/main/resources/1.jpg";  //目标地址
    //缓冲区  增加读取效果
    byte[] buff =new byte[1024];
    int readLen=0; //记录本次读取的长度
    //创建 输入输出流
    FileInputStream fileInputStream= null ;
    FileOutputStream fileOutputStream =null ;
    //读取文件
    try {
        fileInputStream =new FileInputStream(srcFile);
        fileOutputStream =new FileOutputStream(tarFile);
        
        while ((readLen=fileInputStream.read(buff)) != -1 ){
            fileOutputStream.write(buff,0,readLen);   //编写边读
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
```

### 2.字符流

#### 2.1读取字符

   读取的流程和上面字节流的读取时一样的。

   新建一个hello.txt文档随便复制一些

单个字符读取

```java
@Test
public  void  read(){
    String  srcPath="src/main/resources/hello.txt";
    int data =0;
    FileReader reader =null;
    try {
      reader = new FileReader(srcPath);
      while ( (data = reader.read()) != -1){
          System.out.print( (char) data);
      }
    } catch (IOException e) {

    }finally {
        if (reader !=null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

字符数组读取

```java
@Test
public  void  read_2(){
    String  srcPath="src/main/resources/hello.txt";
    char [] buff =new char[1024];
    int readLen =0;
    FileReader reader =null;
    try {
        reader = new FileReader(srcPath);
        while ( (readLen = reader.read(buff)) != -1){
            System.out.print(new String(buff,0,readLen));
        }
    } catch (IOException e) {

    }finally {
        if (reader !=null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
```

#### 2.2输出字符

```java
@Test
public void write(){

    String  srcPath="src/main/resources/hello222.txt";

    FileWriter fileWriter =null;

    try {
        fileWriter =new FileWriter(srcPath);

        String str ="你好世界！！！你好神尾";
        fileWriter.write(str);

    } catch (IOException e) {
        e.printStackTrace();
    }

}
```

测试之后，发现文件创建了但是字符并没有写入。![image-20220402163915461](https://s2.loli.net/2022/04/16/ZCEtMgVK4ro97Rx.png)

debug一下发现，这些数据是在内存中的。

这里就需要说明一下了，我们在使用writer的时候，一定要关闭或者刷新（关闭的时候会强制刷新）不然是不会生效的。

关闭之后字符写入成功。修改 字符内容看看内容发现会被覆盖，所以这里就跟上面一样的。需要我们手动开启追加模式了。

Write方法存在大量的重载

- write(int args)：写入数字，利用 ASCII 码表 或者 Unicode 表转化
- write(String str)：写入字符串
- write(String str,int offset,int count)：写入字符串的一部分
- write(char[] array)：写入字符数组
- write(char[] array,int offset,int count)：写入字符数组的一部分

这里存在一个问题，我们在不开启追加模式的情况下，使用上述各种重载的方法，会发生覆盖吗？？？

方法之间不会覆盖，但是每一次会覆盖。因为本质是不同的方法嘛，并且是一次执行的，所以方法之间会追加，但是每一次之后，就会覆盖了。

追源码自己去康康

追加源码的时候发现，是在close 方法里面调用了  writeBytes 方法才真正开始写文件的。

flush方法也是调用了  writeBytes  方法。 

## 3.节点流 处理流

节点流：节点流可以从一个特定的数据源读写数据例如之前例子的，filereader 和 filewriter

处理流：也叫包装流是连接在已经存在的流上（节点或者包装），为程序提供了更为强大的读写功能

更加灵活，例如==**BufferedReader  BufferedWriter**。==

**处理流是对一个已存在的流的连接和封装，通过所封装的流的功能调用实现数据读写。如BufferedReader.处理流的构造方法总是要带一个其他的流对象做参数。一个流对象经过其他流的多次包装，称为流的链接。**

![image-20220402175239011](https://s2.loli.net/2022/04/16/FWbCvMiwfZLDREH.png)

字节流和处理流的关系

- 处理流是字节流的进一步封装，不与数据源相联系。
- 字节流直接与数据源联系
- 修饰器模式的应用。继承了Writer（Reader）并且内部有一个 Writer（Reader）。说明Writer（Reader）的任何子类都可以用来构造。

使用处理流的好处

1．性能的提高:主要以增加**缓冲的方式来提高输入输出的效率**。

2．操作的便捷:**处理流可能提供了一系列便捷的方法来一次输入输出大批量的数据**,**使用更加灵活方便**

3.消除不同节点流的实现差异，也可以提供跟岗方便的方法来完成输入输出。

处理流-BufferedReader 和 BufferedWrite

关闭处理流的时候只需要关闭外层的流即可。

### 3.1字符处理流

#### 3.1.1 读取字符文件

```java
public static void main(String[] args) throws IOException {
    String  srcPath="src/main/resources/hello.txt";
    String data;
    BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath));
    while ( (data = bufferedReader.readLine()) != null){
        System.out.println(data);
    }
    bufferedReader.close();
}
```

#### 3.1.2 写字符文件

```java
public static void main(String[] args) throws IOException {

    String  srcPath="src/main/resources/MyBufferedWriter.txt";

    //开启追加模式
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(srcPath,true));

    bufferedWriter.write("hello shiki");
    bufferedWriter.write("------------->hello shiki");
    bufferedWriter.newLine();
    bufferedWriter.write("----------------------->hello shiki");
    bufferedWriter.close();

    /*
        bufferedWriter.newLine();  换行
        开启追加模式
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(srcPath,true));
    */
}
```

#### 3.1.3字符文件复制

```java
public static void main(String[] args) throws IOException {

    String srcFile="src/main/resources/hello.txt";
    String tarFile="src/main/resources/MyBufferedCopy.txt";
    //创建缓冲变量
    String data;
    //创建流
    BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tarFile));

    while ( (data = bufferedReader.readLine()) != null){
        bufferedWriter.write(data);
        bufferedWriter.newLine();
    }
    bufferedReader.close();
    bufferedWriter.close();
}
```

注意：字符处理流只能处理字符文件，并不能处理二进制文件，如果拷贝一个音乐，视频那么文件会出错的。

### 3.2字节处理流

 BufferedInputStream    BufferedOutputStream

#### 3.2.1复制一个video

```java
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
```

## 4.对象处理流

 引入

 我们有时候存储文件的时候，不仅仅只需要他的数据，更需要他的数据类型。所以这里引入了对象处理流。

如何引入序列化和反序列化

### 4.1序列化和反序列化

- 序列化就是在保存数据的同时，保存数据的值和数据的类型
- 反序列化就是 从文件恢复 数据的值和数据的类型

### 4.2如何实现序列化

   需要让某个类具有序列化机制就需要该类是可序列化的，为了让某个类具有可序列化的能力该类就需要**实现下面两个接口。**

- ```java
  Serializable  //推荐使用  他是一个标记方法 里面没有任何方法
  ```

- ```java
  Externalizable  //有两个方法   需要实现
  ```

### 4.3序列化

```java
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
```

```java
public class Dog implements Serializable {

    private String name;
    private  int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### 4.4反序列化

```java
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
```

### 4.5实现细节

- 读写顺序要一致
- 要求序列化或反序列化对象,需要实现Serializable
- 序列化的类中建议添加SerialVersionUID,为了提高版本的兼容性
- 序列化对象时，默认将里面所有属性都进行序列化，但除了**static或transient修饰的成员**
- **序列化对象时，要求里面属性的类型也需要实现序列化接口**
- 序列化具备可继承性,也就是如果某类已经实现了序列化，则它的所有子类也已经默认实现了序列化

## 5.标准输入输出流

|            | 类型         | 对应设备 |
| ---------- | ------------ | -------- |
| System.in  | inputStream  | 键盘     |
| System.out | outputStream | 显示器   |

所以以前我们在控制台输入是如下定义

现在看来就比较清楚了，将键盘对象放进去，扫描。

```
Scanner scanner = new Scanner(System.in);
```

## 6.转换流

需求：这是为了解决乱码的问题。

将一个文档转码成 除utf-8之外的东西。中文出现乱码

![image-20220406212315072](https://s2.loli.net/2022/04/16/BvlZsb9gjCdRQWn.png)

字节流可以指定编码格式，将字节流转化为想要的格式。然后再传给字符处理流。

转换流相当于一个中间缓冲区，将指定的数据格式在交给**字符处理流处理**。

### 6.1 InputStreamReader

Reader的子类，可以将InputStream(字节流) 包装成 (转换) Reader (字符流)

初始

```java
public static void main(String[] args) throws IOException {
    String path ="src/main/resources/file.txt";

    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    System.out.println(bufferedReader.readLine());
    bufferedReader.close();
}
```

出现乱码。代码优化使用转换流

```java
public static void main(String[] args) throws IOException {
    String path ="src/main/resources/file.txt";

    InputStreamReader inputStreamReader =
            new InputStreamReader(new FileInputStream(path),"UTF-16");//指定你设置的格式
   // BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    System.out.println(bufferedReader.readLine());
    bufferedReader.close();
}
```

### 6.2 OutputStreamWriter

  将写入的文件字节流转换成想要格式的字符流。

  最后我们还是通过 bufferedWriter 写入。

```java
@Test
public void demo() throws IOException {
    String path ="src/main/resources/write.txt";

    BufferedWriter bw  = new BufferedWriter
            (new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));

    bw.write("这是通过转换流按照utf-8的格式写入的测试demo");

    bw.close();
}
```

## 7.打印流

#### 7.1 **PrintStream** 字节流

打印流只有输出流并没有输入流。

打印流不仅仅可以打印到控制台上面还可以打印到一个文件上。

**在默认情况下，打印流printStream 打印的输出数据是标准输出即显示器。**  **可以指定输出位置**

打印流是输出流的子类，print的本质就是调用了write 方法。

```java
public static void main(String[] args) throws IOException {

    PrintStream out =System.out;

    /*
    *  下面两种是等价的  print 就是底层调用了write方法， 默认是打印到标准设备上面的
    * */
    out.println("通过print方法打印");
    out.write("通过write方法打印".getBytes());


    //指定输出的位置
    String path ="src/main/resources/MyPrint.txt";
    System.setOut(new PrintStream(path));

}
```

### 7.2 **PrintWriter**   字符流

字符流一定要注意必须关闭才生效

```java
@Test
public void  MyPrintInputWriter() throws IOException {
    String path ="src/main/resources/MyPrintPrintWriter.txt";
    //开启追加模式 并且将文件输出到指定位置
    PrintWriter printWriter = new PrintWriter(new FileWriter(path,true));
    printWriter.println("这是通过printWriter打印到指定位置的demo");
    //字符流 一般是close 或者刷新的时候才会生效的
    printWriter.close();
}
```

## 8.Properties类

### 8.1引入

定义一个数据库的配置文件 mysql.properties 

```properties
ip=192.168.100.1
user=root
pwd=123456 
```

常规读取

```java
public static void main(String[] args) throws IOException {

    BufferedReader br =
            new BufferedReader(new FileReader("src/main/resources/ mysql.properties"));
    String data;
    while ((data = br.readLine()) != null){
        System.out.println(data);
    }
    br.close();
}
```

![image-20220407221002362](https://s2.loli.net/2022/04/16/3kuVsYhUJ4oLIce.png)

代码优化 通过split处理

```java
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
```

测试成功！！

但是这里会存在问题，如果我们需要修改这个配置文件呢？所以就变的非常复杂了，这里我们就引出了Properties类。

### 8.2Properties操作

常用方法

- **load**:    加载配置文件的键值对到Properties对象list:将数据显示到指定设备
- getProperty(key):   根据键获取值
- setProperty(key,value):   设置键值对到Properties对象
- **store**:  将Properties中的键值对存储到配置文件,在idea中，保存信息到配置文件，**如果含有中文，会存储为unicode码**

#### 8.2.1读取Properties文件

```java
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
```

#### 8.2.2 创建、修改Properties

中文的会转成  **unicode** 编码，store方法第二个参数 是注释

setProperty方法 如果key 存在 就是修改值，不存在就新建。

```java
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
```
## 9.XML

### 简介

​     称可扩展标记语言，标准通用标记语言的子集，简称XML。 是一种用于标记电子文件使其具有结构性的标记语言。

### 生成xml

导包：我导的xom的包。也可以用Java自带的。Javax

#### 1.在类中编写getXML方法

 目的是生成节点，所以返回的是个节点
#### 2.编写 format方法

 目的是设置  输出的格式
 测试代码
 ##因为有些代码 直接在里面写着了所以就不写了。
