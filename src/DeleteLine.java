import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class DeleteLine {

    public static void DeleteLine(String s) throws IOException {
        String result="";
        String table_name ="";
        String s_analysis="(?<=where ).+(?=;)";
        String s_property="(.+)=(.+)";
        String analysis_result="";
        String values="";//语句中说明要删除的值
        String property="";//语句中要说明要删除的属性（值）
        String line="";
        int weizhi=-1,count=0;

        String[] x=s.split(" ");
        table_name=x[2];
        System.out.println(table_name);
        File file = new File("E:\\"+table_name+".txt");
        if(file.exists()){

            //此版块实现获取表名 以及语句中涉及到的属性和值
            Pattern p = Pattern.compile(s_analysis);
            Matcher m = p.matcher(s);
            m.find();
            analysis_result=m.group().toString();
            System.out.println(analysis_result);

            Pattern p1 = Pattern.compile(s_property);
            Matcher m1 = p1.matcher(analysis_result);
            while(m1.find()){
                property=m1.group(1).toString();
                values=m1.group(2).toString();
            }
            System.out.println(property+"属性");
            System.out.println(values+"值");

            //读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            result+=br.readLine();//读取第一行
            line=br.readLine();//读取第二行
            result+="\r\n"+line;
            System.out.println(line);

            //比对文件中属性出现的位置
            String[] h=line.split(" ");
            for(int j=0;j<h.length;j++){
                //得到定位属性的位置
                if(property.equals(h[j].replaceAll("\\(.*?\\)",""))){
                    weizhi=j;

                }
            }
            System.out.println(weizhi+"");

            //依次从第三行开始读起 如果找到了删除的行 则不再写入文件
            while((line=br.readLine())!=null){
                String[] k=line.split(" ");
                if(k==null){
                    System.out.println("此表中没有值");
                }
                //System.out.println(k[weizhi+1]+"是");
                else if(k[weizhi].equals(values)){
                    //如果属性相同 即是要改的行
                    count++;
                    System.out.println("xiangtong");
                }else{
                    //没有则按照原来情况正常写入
                    result+="\r\n"+line;
                }
            }
            if(count==0){
                System.out.println("此表中没有找到要删除的行");
            }
            else{
                System.out.println(result);
                FileWriter fileWriter=new FileWriter("E:\\"+table_name+".txt", false);
                fileWriter.write(result+"");
                fileWriter.close();
                System.out.println("成功删除");
            }

            System.out.println("成功执行");
        }else{
            System.out.println("此表不存在");
        }
    }
}
