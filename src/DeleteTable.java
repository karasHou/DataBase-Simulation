/**
 *
 *  删除表：drop table student
 *
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DeleteTable
{
    public static void DeleteTable(String s) throws IOException{

        String name="";
        String[] temp =s.split(" ");
        name=temp[2];
        File file = new File("E:\\"+name+".txt");
        if(file.exists()){
            //删除成功
            if(file.delete()){


                FileWriter data_dict=new FileWriter("E://data_dictionary.txt",true);
                data_dict.write("删除表："+name);
                data_dict.write("\r\n");

                data_dict.close();

                System.out.println("成功删除");
            }else{
                System.out.println("删除失败，请重试！");
            }

        }else{
            System.out.println("不存在此表");
        }

    }
}
