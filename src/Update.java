/**
 *
 * 更新数据库数据
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update {

    public static void UpdateTable(String s) throws IOException {
        //update student set name=xiaohua,ssn=2016,grade=大二年级  where grade=大一年级;
        String result="";
        ArrayList<String> list=new ArrayList<String>();
        String s_analysis="(?<=set ).+(?=;)";
        String s_property="(.+)( where )(.+)";
        //String s_property_values="(?<=')(?=')";//分解属性值
        String s_update_values="(.+)=(.+),(.+)=(.+),(.+)=(.+)";
        String table_name="";//表名
        String find = "" ;
        String values="";//where前面的属性
        String values1="";//where后面的属性
        String []x=s.split(" ");
        table_name=x[1];//表名
        System.out.println(table_name);

        //此版块实现判断是否有次表
        File file = new File("D:\\"+table_name+".txt");
        if(file.exists()){
            //此版块实现将set后语句识别出来
            Pattern p = Pattern.compile(s_analysis);
            Matcher m = p.matcher(s);
            m.find();
            find= m.group().toString();
            System.out.println(find);
            //此版块实现将where语句前后属性分解出来
            Pattern p1 = Pattern.compile(s_property);
            Matcher m1 = p1.matcher(find);
            while(m1.find()){

                values=m1.group(1);
                values1=m1.group(3);
            }
            System.out.println(values);
            System.out.println(values1);
            //此版块实现将需要修改的属性及其值得到
            int weizhi=-1;//
            String line="";
            String[] y=values1.split("=");
            String y_alter_property=y[0];//需要修改的属性
            String y_alter_values=y[1];//需要修改的属性的值
            System.out.println(y_alter_values+"需要修改");
            //此版块实现获取更改的属性和值
            BufferedReader br = new BufferedReader(new FileReader(file));
            line=br.readLine();//读取第一行
            result+=line+"\r\n";
            list.add(line+"\r\n");//添加第一行
            line=br.readLine();//读取第二行
            result+=line+"";
            list.add(line+"\r\n");//添加第二行
            //将第二行数据用空格分开
            String[] h=line.split(" ");


            for(int j=0;j<h.length;j++){
                //得到定位属性的位置
                if(y_alter_property.equals(h[j].replaceAll("\\(.*?\\)",""))){
                    weizhi=j;
                    System.out.println(y_alter_property);
                }
            }
            System.out.println(weizhi+"");

            //筛选出修改的值的内容
            String z ="";
            Pattern p2 = Pattern.compile(s_update_values);
            Matcher m2 = p2.matcher(values);
            m2.find();
            for(int b=2;b<7;){
                z+=m2.group(b)+" ";
                b+=2;
            }
            System.out.println(z+"lalal");

            //从第三行开始读
            while((line=br.readLine())!=null){
                System.out.println(line);
                String[] k=line.split(" ");
                if(k==null){
                    System.out.println("此表中没有值");
                }
                //System.out.println(k[weizhi+1]+"是");
                else if(k[weizhi].equals(y_alter_values)){
                    //如果属性相同 即是要改的行
                    System.out.println("xiangtong");
                    list.add(z+"\r\n");
                    result+="\r\n"+z;
                }else{
                    //没有则按照原来情况正常写入
                    list.add(line+"\r\n");
                    result+="\r\n"+line;
                }
            }
            //将字符串写入文件中
            System.out.println(result);
            FileWriter fileWriter=new FileWriter("D:\\"+table_name+".txt", false);
            fileWriter.write(result+"");
            fileWriter.close();
            System.out.println("成功执行");
        }else{
            System.out.println("此表不存在");
        }




    }
}
