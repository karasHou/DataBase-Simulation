
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlterAdd {


    public static void AlterAdd(String s) throws IOException {
        String result="";
        String table_name="";
        String property="";
        String property_type="";
        String s_analys="(\\w+) add (.+) (.+)";
        String line= "";
        int count=0;

        Pattern p = Pattern.compile(s_analys);
        Matcher m = p.matcher(s);
        while(m.find()){
            table_name = m.group(1).toString();
            property=m.group(2).toString();
            property_type=m.group(3).toString();
        }
        System.out.println(table_name+"    "+property+"("+property_type+")");

        File file = new File("E:\\"+table_name+".txt");
        if(file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));

            result+=br.readLine();
            line = br.readLine();
            String[] y =line.split(" ");
            for(int i=0;i<y.length;i++){
                if(y[i].equals(property+"("+property_type+")")){
                    System.out.println("该属性已经存在");
                    result+="\r\n"+line;

                }else{
                    count++;
                }

            }
            if(count==y.length){
                result+="\r\n"+line+property+"("+property_type+")";
                System.out.println("属性成功加入表中");
            }
            while((line=br.readLine())!=null){
                result+="\r\n"+line+" null";
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
