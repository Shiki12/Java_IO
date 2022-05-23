package xml;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String age;
    private String address;
    //一般的构造函数
    public Person(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    // XML解析数据 参数传递Element对象
    public Person(Element person){
        //赋值
        name = person.getFirstChildElement("name").getValue();
        age = person.getFirstChildElement("age").getValue();
        address = person.getFirstChildElement("address").getValue();

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    //方法生成一个Element节点
    public Element getXML(){
        // 生成一个person节点 它下面还有三个子节点 name age address
        Element Node = new Element("person");
        // 生成一个name节点 并使用appendChild（）填充数据
        Element Node_name = new Element("name2222");//节点的名称
        Node_name.appendChild(name); //这个意思是把当前person类的值赋给
        Element Node_age = new Element("age");
        Node_age.appendChild(age);
        Element Node_address = new Element("address");
        Node_address.appendChild(address);


        // 将三个子节点 name age address添加到父节点Person中
        Node.appendChild(Node_name);
        Node.appendChild(Node_age);
        Node.appendChild(Node_address);
        return Node;
    }
    public static void format(OutputStream os, Document doc) throws Exception{
        // Serializer参数 第一个为输出流  第二个为编码格式
        Serializer serializer = new Serializer(os,"UTF-8");
        serializer.setIndent(6);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args) throws Exception {
        //1.序列化生成XML
        List<Person> people = new ArrayList<Person>();
        people.add(new Person("zhengyan","24","wuhan"));
        people.add(new Person("wukong","26","beijing"));
        people.add(new Person("livvy","21","shenzheng"));
        people.add(new Person("susan","24","hk"));
        // 生成XML的根节点people 调用对象P的方法getXML() 组装成完整的XML数据
        Element root = new Element("people");  //生成根节点
        for(Person p : people){
            root.appendChild(p.getXML());
        }
        // 生成要存储XML文件的File对象
        File file = new File("person.xml");
        FileOutputStream fos = new FileOutputStream(file);
        //将Element对象的root传入Document的构造器中
        Document doc = new Document(root);
        // 序列化生成XML
        Person.format(fos, doc);
//        List<Person> people = new ArrayList<Person>();
//        File file = new File( "person.xml");
//        Document doc = new Builder().build(file);
//        Elements elements = doc.getRootElement().getChildElements();
//        for(int i = 0; i < elements.size(); i++){
//            people.add(new Person(elements.get(i)));
//        }
//        //直接打印List容器中的数据即可
//        for (Person person : people) {
//            System.out.println(person.toString());
//        }

    }
}