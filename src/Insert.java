/**
 *  向数据库中插入数据
 *
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert {
    //传入执行插入的语句
    public static void Insert(String s) throws IOException {

        /*
         *  测试语句：insert into student (s_name,id) values('houwei','2015')
         *
         */
        //提取（）中属性的值
        String reg = "(?<=').+?(?=')";
        //存属性的值
        String attribute = "";
        //表名
        String name = "";
        //按空格分割，存属性
        String []attr = s.split(" ");
        name=attr[2];


        File file = new File("E:\\"+name+".txt");
        FileWriter data_dictionary=new FileWriter("E://data_dictionary.txt",true);
        data_dictionary.write("表"+name+"插入数据 ");

        //表存在时才能插入数据
        if(file.exists()) {

            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(s);

            while (m.find()) {

                attribute += m.group().toString();
              //  System.out.println(attribute);
            }

            System.out.println(attribute+"1");

            //分割出最后插入的数据 e.g houwei 2015
            String []result=attribute.split(",");
            FileWriter fileWriter=new FileWriter(file, true);
            fileWriter.write("\r\n");

            //将值加入到文件中
            for(int i=0;i<result.length;i++){
                fileWriter.write(result[i]+" ");
                data_dictionary.write(result[i]+" ");
            }

            fileWriter.close();
            data_dictionary.write("\r\n");
            data_dictionary.close();

            System.out.println("插入数据成功！");
        }
        else{
            System.out.println("不存在此表");
        }
    }

}
