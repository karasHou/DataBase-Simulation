import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectX{

    public static void SelectX(String s) throws IOException{
        //select * from student where grade > '1'
        String table_name="";
        String s_analys="select \\* from (.+) where (.+) (.+) (.+)";
        String property="";//属性
        String operator="";//运算符
        String prop_value="";//属性值
        String line = "";
        String result="";
        int weizhi=-1,ssn;

        Pattern p = Pattern.compile(s_analys);
        Matcher m = p.matcher(s);
        //select * from student where grade > 1
        while(m.find()){
            table_name = m.group(1).toString();
            property=m.group(2).toString();
            operator=m.group(3).toString();
            prop_value=m.group(4).substring(1,m.group(4).length()-1);
        }

        System.out.println(table_name+" "+property+" "+operator+" " +prop_value);
        File file = new File("E://"+table_name+".txt");

        if(file.exists()){
            BufferedReader bf = new BufferedReader(new FileReader(file));
            bf.readLine();//读取第一行
            line=bf.readLine();
            String[] x= line.split(" ");
            for(int i=0;i<x.length;i++){
                if(property.equals(x[i].replaceAll("\\(.*?\\)",""))){
                    weizhi=i;
                }
            }
            ssn=Integer.parseInt(prop_value);
            while((line=bf.readLine())!=null){
                String[] y =line.split(" ");
                if(operator.equals("=")){
                    if(Integer.parseInt(y[weizhi])==ssn){
                        result+=line+"\r\n";
                    }
                }else if(operator.equals(">=")){
                    if(Integer.parseInt(y[weizhi])>=ssn){
                        result+=line+"\r\n";
                    }
                }else if(operator.equals("<=")){
                    if(Integer.parseInt(y[weizhi])<=ssn) {
                        result += line + "\r\n";
                    }
                }else if(operator.equals("<")){
                    if(Integer.parseInt(y[weizhi])<ssn){
                        result+=line+"\r\n";
                    }
                }else if(operator.equals(">")){
                    if(Integer.parseInt(y[weizhi])>ssn){
                        result+=line+"\r\n";
                    }
                }else if(operator.equals("!=")){
                    if(Integer.parseInt(y[weizhi])!=ssn){
                        result+=line+"\r\n";
                    }
                }else{
                    System.out.println("暂时不支持此比较符的查询");
                }
            }
            System.out.println(result);
        }else{
            System.out.println("该表不存在");
        }
    }
}
