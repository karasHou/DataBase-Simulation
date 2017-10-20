
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class IndexTable {

//        create index student_index on student name(char)
//        create index student_index on student id(int)


    public static void IndexKeyTable(String s) throws IOException{
        String s_analyis ="index (.+) on (.+) (.+)";
        String table_name="";
        String index_name="";
        String attribute="";
        String line="";
        String [] dline;

        String result;

        ArrayList<String> arraylist = new ArrayList<String>();

        int location = -1;
        int line_count = 1;

        Pattern p = Pattern.compile(s_analyis);
        Matcher m = p.matcher(s);
        while(m.find()){
            System.out.println("索引表名："+m.group(1).toString());
            System.out.println("源表名"+m.group(2).toString());
            System.out.println("属性"+m.group(3).toString());
            table_name = m.group(2).toString();
            index_name = m.group(1).toString();
            attribute = m.group(3).toString();
        }



        File file = new File("E:\\"+table_name+".txt");
        File file2= new File("E:\\"+index_name+".txt");

        if(file.exists()){//判断表是否存在



            //读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();//读第一行
            line = br.readLine();//读第二行

            String [] t = line.split(" ");

            //找出属性所在的位置
            for(int i = 0;i<t.length;i++){
                if(t[i].equals(attribute))
                    location = i;

            }

            System.out.println("属性的位置："+location);

            if(location == -1){
                System.out.println("没有该属性，请重新输入！");
            }else {
                //true是追加方式
                FileWriter fileWriter=new FileWriter("E:\\"+index_name+".txt", false);


                //读第三行（真正数据的第一行）

                result = index_name + "\r\n" + attribute + " " + "line_count" + "\r\n";

                String r = "";

                while ((line = br.readLine()) != null) {

                    dline = line.split(" ");
                    r = dline[location] + " " + line_count + "\r\n";
                    line_count++;
                    arraylist.add(r);
                }

                Collections.sort(arraylist);

                //将排好序的结果写入result字符串中
                for (String a : arraylist) {
                    result += a;
                }


                System.out.println(result);

                fileWriter.write(result);

                fileWriter.close();

            }
        }else{
            System.out.println("此表不存在");
        }
    }
}
