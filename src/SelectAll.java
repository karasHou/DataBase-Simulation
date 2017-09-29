import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * 选择显示某个表的全部内容
 *
 *  e.g:  select * from student
 */
public class SelectAll {

    public static void SelectAll(String s) throws IOException{
        //表名
        String name="";
        //暂存每一行的数据
        String line="";

        String[] attribute =s.split(" ");
        //从字符串中取出名字
        name = attribute[3];
        File file = new File("E:\\"+name+".txt");
        //文件存在
        if(file.exists()){
            //读数据
            BufferedReader br = new BufferedReader(new FileReader(file));
            //先读一行
            line=br.readLine();
            //显示一行
            System.out.println(line);

            while((line=br.readLine())!=null){
                String[] y =line.split(" ");

                for(int i=0;i<y.length;i++){
                    System.out.print(y[i]+"\t\t");
                }
                System.out.println();
            }

            System.out.println();
            System.out.println("Order Complete！");
        }else{
            System.out.println("此表不存在");
        }


    }
}
