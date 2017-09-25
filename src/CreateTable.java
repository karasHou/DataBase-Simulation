
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTable {

    public static void CreateTable(String s) throws IOException{

        //例 create table student (name char,ssn int,grade char)
        String table_name="";//表名
        String parm_shuxing="";//属性名
        String s_parm_shuxing="(?<=\\().+(?=\\))";
        File file = new File("D:\\"+table_name+".txt");
        FileWriter file_system=new FileWriter("D://system_table.txt",true);

        String []x=s.split(" ");//利用空格分割
        table_name=x[2];//表名  事例 student

        if(file.exists()){
            System.out.println("该表已经存在");
        }
        else{
            FileWriter fileWriter=new FileWriter("D:\\"+table_name+".txt", true);
            fileWriter.write(table_name+"\r\n");//将表名写入文件中
            file_system.write("创建表:"+table_name+" ");
            //利用正则表达式匹配 得到括号中的内容
            Pattern p = Pattern.compile(s_parm_shuxing);
            Matcher m = p.matcher(s);
            // System.out.println(m);
            m.find();
            parm_shuxing  = m.group().toString();
            System.out.println(parm_shuxing);//事例  name char,ssn int,grade char

            String[] y=parm_shuxing.split(",");
            String z[]=new String[y.length*2];

            for(int i=0;i<y.length;i++){
                z=y[i].split(" ");
                System.out.println(z[0]);//得到按照,分割的第一部分
                //事例 name ssn grade
                fileWriter.write(z[0]+"("+z[1].charAt(0)+") ");
                //事例 name(c)
                file_system.write("属性 "+z[0]+" 类型  "+z[1]);
            }
            System.out.println("创建表成功\n");
            file_system.write("\r\n");
            fileWriter.close();
            file_system.close();
        }

    }
}
