import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectL {
    //select * from student,course where s.grade=c.grade
    public static void SelectL(String s) throws IOException{
        String s_analys="select \\* from (.+) where (.+)";
        String s_connect="(=|<|>|<=|>=|!=)";
        String table_list="";//表集合
        String connect_con="";//例如 s.grade=c.grade
        ArrayList<File> list = new ArrayList();//List中保存File对象
        String line1="";
        String line2="";
        int weizhi1=-1,weizhi2=-1;

        //存放最后的结果
        String result="";

        Pattern p = Pattern.compile(s_analys);
        Matcher m = p.matcher(s);
        while(m.find()){
            table_list=m.group(1).toString();
            connect_con=m.group(2).toString();
        }
        String[] x=table_list.split(",");//将表集合分开
        String[] y=connect_con.split(s_connect);//将关系分开

        //将表的路径信息加入到list链表中
        for(int i=0;i<x.length;i++){
            System.out.println();
            File file = new File("E://"+x[i]+".txt");
            list.add(file);

        }

        BufferedReader bf1 = new BufferedReader(new FileReader(list.get(0)));
        BufferedReader bf2 =new BufferedReader(new FileReader(list.get(1)));
        //从第二行属性行开始读
        bf1.readLine();
        bf2.readLine();

        line1 = bf1.readLine();
        line2 = bf2.readLine();

        //得到属性的分割
        String attr1[] =  line1.split(" ");
        String attr2[] =  line2.split(" ");

        //第一张表
        for(int i=0;i<attr1.length;i++){
            if((attr1[i].replaceAll("\\(.*?\\)","")).equals(y[0].substring(2,y[0].length()))){
                weizhi1=i;
               // System.out.println("位置1:"+weizhi1);
            }
        }
        if(weizhi1 == -1){
            System.out.println(y[0]+"该属性不存在");
            return;
        }

        //第二张表
        for(int i=0;i<attr2.length;i++){
            if((attr2[i].replaceAll("\\(.*?\\)","")).equals(y[1].substring(2,y[0].length()))){
                weizhi2=i;
              //  System.out.println("位置2:"+weizhi2);
            }
        }
        if(weizhi2 == -1){
            System.out.println(y[0]+"该属性不存在");
            return;
        }

        //以上定位完成

        String s_line1 [];
        String s_line2 [];

        while((line1 = bf1.readLine())!=null){
            s_line1 = line1.split(" ");
            while((line2 = bf2.readLine())!=null) {
                s_line2 = line2.split(" ");
                if(s_line1[weizhi1].equals(s_line2[weizhi2])){
                    result+= line1 + line2 +"\r\n";

                }

            }
            //回到文件指针的起始位置
            bf2 =new BufferedReader(new FileReader(list.get(1)));
            bf2.readLine();bf2.readLine();
        }
        System.out.println(result);
    }

}