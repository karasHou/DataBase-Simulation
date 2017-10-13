import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlterDrop {

    public static void AlterDrop(String s) throws IOException{

        String result="";//存放最后的结果
        String table_name = "";//表名
        String s_analys="(\\w+) drop (.+) (.+)";
        String property="";
        String property_type="";
        String line ="";
        int weizhi=-1,count=0,count2=0;

        //分解字符串 将表名以及要删除的属性得到
        Pattern p = Pattern.compile(s_analys);
        Matcher m = p.matcher(s);
        while(m.find()){
            table_name = m.group(1).toString();
            property=m.group(2).toString();
            property_type=m.group(3).toString();
        }
        System.out.println(table_name+" "+property+"  "+property_type);

        File file = new File("E:\\"+table_name+".txt");
        if(file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            result+=br.readLine()+"\r\n";//读取第一行
            line = br.readLine();
            //思路 如果找到该属性则不将其写入文件 否则写入文件
            String[] y =line.split(" ");
            for(int i=0;i<y.length;i++){
                System.out.println("==================================");
                System.out.println("y[i]"+y[i]+"=="+property+"("+property_type.charAt(0)+")");

                if(y[i].equals(property+"("+property_type+")")){
//                if(y[i].equals(property+"("+property_type.charAt(0)+")")){
                    System.out.println("找到该属性");
                    weizhi=i;//记下删除属性出现的位置
                    count++;
                }else{
                    result+=y[i]+" ";
                }
            }
            System.out.println(weizhi+"位置");

            System.out.println(result);
            if(count==0){
                System.out.println("在此表中没有该属性");
            }
            while((line=br.readLine())!=null){
                String[] z= line.split(" ");
                result+="\r\n";//每一行前都有换行
                count2++;
                if(line==""){
                    System.out.println("此表中没有数据");
                }
                if(count==0){
                    result+=line;
                    //按照原来情况写入文件
                }else{
                    for(int hh=0;hh<z.length;hh++){
                        if(hh==weizhi){

                        }else{
                            result+=z[hh]+" ";
                        }
                    }
                }


            }
            System.out.println(result);
            if(count2==0){
                System.out.println("此表中没有数据");
            }

            System.out.println(result);
            FileWriter fileWriter=new FileWriter("E:\\"+table_name+".txt", false);
            fileWriter.write(result+"");
            fileWriter.close();
            System.out.println("成功执行");
        }else{
            System.out.println("此表不存在");
        }
    }
}
