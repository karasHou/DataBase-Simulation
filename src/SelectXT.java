
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectXT {

    public static void SelectXT(String s) throws IOException{

        String table_name="";
        String s_analys="select (.+) from (.+) where (.+) (.+) (.+)";
        String proper="";//投影的属性
        String property="";//属性
        String operator="";//运算符
        String prop_value="";//属性值
        String line = "";
        String result="";
        int weizhi=-1,weizhi2=-1,ssn;

        Pattern p = Pattern.compile(s_analys);
        Matcher m = p.matcher(s);
        while(m.find()){
            proper=m.group(1).toString();
            table_name = m.group(2).toString();
            property=m.group(3).toString();
            operator=m.group(4).toString();
            prop_value=m.group(5).substring(1,m.group(5).length()-1);
        }

       // System.out.println(proper+" "+table_name+" "+property+" "+operator+" " +prop_value);

        File file = new File("E://"+table_name+".txt");
        if(file.exists()){
            BufferedReader bf = new BufferedReader(new FileReader(file));
            bf.readLine();//读取第一行
            //读取第二行 并找到属性在文件中的位置
            line=bf.readLine();
            String[] x= line.split(" ");
            for(int i=0;i<x.length;i++){
                if(property.equals(x[i].replaceAll("\\(.*?\\)",""))){
                    weizhi=i;
                }
            }
            for(int i=0;i<x.length;i++){
                if(proper.equals(x[i].replaceAll("\\(.*?\\)",""))){
                    weizhi2=i;
                }
            }
            result=proper+"\r\n";
            ssn=Integer.parseInt(prop_value);
            while((line=bf.readLine())!=null){
                String[] y =line.split(" ");
                if(operator.equals("=")){
                    if(Integer.parseInt(y[weizhi])==ssn){
                        result+=y[weizhi2]+"\r\n";
                    }
                }else if(operator.equals(">=")){
                    if(Integer.parseInt(y[weizhi])>=ssn){
                        result+=y[weizhi2]+"\r\n";
                    }
                }else if(operator.equals("<=")){
                    if(Integer.parseInt(y[weizhi])<=ssn){
                        result+=y[weizhi2]+"\r\n";
                    }
                }else if(operator.equals("<")){
                    if(Integer.parseInt(y[weizhi])<ssn){
                        result+=y[weizhi2]+"\r\n";
                    }
                }else if(operator.equals(">")){
                    if(Integer.parseInt(y[weizhi])>ssn){
                        result+=y[weizhi2]+"\r\n";
                    }
                }else if(operator.equals("!=")){
                    if(Integer.parseInt(y[weizhi])!=ssn){
                        result+=y[weizhi2]+"\r\n";
                    }
                }else{
                    System.out.println("该运算符非法");
                }

            }
            System.out.println(result);
        }else{
            System.out.println("不存在此表");
        }
    }
}
