/**
 *
 * 创建数据库类
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create {

    //传入执行创建的语句
    public static void Create(String s) throws IOException {
        //表名
        String name = "";
        //存放所有属性和类型
        String attribute = "";

        /*
        *  反向肯定预查： 匹配 （ .任意字符 +多个  匹配）
        *  也就是匹配 左右括号 之间的任意东西
        *  create table student (s_name char,id int)
        *  取出上面语句的s_name char,id int
        *  */
        String reg = "(?<=\\().+(?=\\))";       //正则解析属性

        File file = new File("E:\\"+name+".txt");
        //每次添加到文件末尾而不是覆盖
        FileWriter data_dict=new FileWriter("E://data_dictionary.txt",true);

        //空格分割字符串并存入temp数组中
        String []temp = s.split(" ");
        //从分割出的字符串中取出表名
        name = temp[2];

        //判断表是否存在
        if(file.exists()){

            System.out.println(name+"表已存在，error！");

        }else{

            FileWriter fileWriter = new FileWriter("E:\\"+name+".txt",true);
            fileWriter.write(name+"\r\n");

            data_dict.write("创建表："+name+" ");

            //正则表达式编译
            Pattern p = Pattern.compile(reg);
            //匹配
            Matcher m = p.matcher(s);
            //判断s是否包含reg中的子串
            m.find();
            //返回包含的属性
            attribute = m.group().toString();
            System.out.println(attribute);

            //存每一组属性和类型 e.g name char
            String []group = attribute.split(",");
            //存具体的属性和类型
            String []single ;

            for(int i = 0;i<group.length;i++){
                single = group[i].split(" ");
                //e.g  name(char)
                fileWriter.write(single[0]+"("+single[1]+") ");
                //写入数据字典
                data_dict.write("属性："+single[0]+"类型："+single[1]);
            }


            System.out.println("创建表"+name+" 成功！\n");
            data_dict.write("\r\n");
            //关闭相应文件
            data_dict.close();
            fileWriter.close();
        }

    }
}















