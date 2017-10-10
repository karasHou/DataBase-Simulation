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
        //update student set name=houwei,grade=3 where id=6666;
        String result="";
        String s_analysis="(?<=set ).+(?=;)";
        String s_property="(.+)( where )(.+)";
        String s_update_values="(.+)=(.+),(.+)=(.+)";
        String table_name="";//表名
        String find = "" ;
        String values="";//where前面的属性
        String values1="";//where后面的属性
        String []x=s.split(" ");
        table_name=x[1];//表名
//        System.out.println(table_name);     //student

        //此版块实现判断是否有此表
        File file = new File("E:\\"+table_name+".txt");
        if(file.exists()){
            //此版块实现将set后语句识别出来
            Pattern p = Pattern.compile(s_analysis);
            Matcher m = p.matcher(s);
            m.find();
            find= m.group().toString();
//            System.out.println(find);       //set id=6666,grade=3  where name=houwei
            //实现将where语句前后属性分解出来
            Pattern p1 = Pattern.compile(s_property);
            Matcher m1 = p1.matcher(find);
            while(m1.find()){

                values=m1.group(1);     //存要设置的新值
                values1=m1.group(3);    //存定位到修改行的依据值
            }
//            System.out.println(values);      //id=6666,grade=3   (下标为1的那部分)
//            System.out.println(values1);     //where name=houwei (下标为3的那部分)
            //此版块实现将需要修改的属性及其值得到
            int weizhi=-1;//
            String line="",attr = "";

            String[] y=values1.split("=");
            String y_alter_property=y[0];//需要修改的属性
            String y_alter_values=y[1];//需要修改的属性的值
//            System.out.println(y_alter_values+"需要修改");  // houwei
            //此版块实现获取更改的属性和值
            BufferedReader br = new BufferedReader(new FileReader(file));
            line=br.readLine();//读取第一行
            result+=line+"\r\n";
           // list.add(line+"\r\n");//添加第一行
            attr =  line = br.readLine();//读取第二行      属性

            //===================================================
            String[] sAttr=attr.split(" "); //保存每个属性的值
            String tmp [];
            for(int i = 0;i<sAttr.length;i++){

                tmp = sAttr[i].split("\\(");
                sAttr[i] = tmp[0];

//                System.out.println("sAttr[i]"+sAttr[i]);

            }

            //用来保存每一行的值
            ArrayList<String> list=new ArrayList<String>();


            result+=line+"";
          //  list.add(line+"\r\n");//添加第二行
            //将第二行数据用空格分开
            String[] h=line.split(" ");


            for(int j=0;j<h.length;j++){
                //得到定位属性的位置
                if(y_alter_property.equals(h[j].replaceAll("\\(.*?\\)",""))){
                    weizhi=j;
//                    System.out.println(y_alter_property);   //name
                }
            }
//            System.out.println("要修改的位置是："+weizhi);  //0  根据哪个属性要修改值得下标

            //筛选出修改的值的内容
            String z ="";
            Pattern p2 = Pattern.compile(s_update_values);
            Matcher m2 = p2.matcher(values);
            m2.find();
//            System.out.println(m2.group().toString()+"==================");


            //===========================================================

//            for(int b=2;b<5;){
//                System.out.println(m2.group(b));
//                z+=m2.group(b)+" ";
//                b+=2;
//            }
//            z+=y_alter_values;
//            System.out.println(z+"lalal");

            //存第一个要修改属性的值 houwei
            String a = m2.group(2);
            //存第二个要修改属性的值 6666
            String b = m2.group(4);

            //存第一个要修改的属性（含类型）
            String c = m2.group(1);
            //存第二个要修改的属性（含类型）
            String d = m2.group(3);





//            System.out.println("111111111111111111111");
//            System.out.println("a "+a);
//            System.out.println("b "+b);
//            System.out.println("c "+c);
//            System.out.println("d "+d);

            //从第三行开始读
            while((line=br.readLine())!=null){

//                System.out.println("读取的行是："+line);
                String[] k=line.split(" ");

                if(k==null){

//                    System.out.println("此表中没有值");

                }

                //读到的那一行的属性值为 where的属性
                else if(k[weizhi].equals( y_alter_values )){

                    result+="\r\n";

                    //建立一个动态数组
                    // 1. 将修改的值变成新的值 填入动态数组对应的位置
                    // 2. 将未修改的值也存入对应位置
                    // 3. 将该动态数组拼接成一个字符串（最后加\r\n)
                    // 4. 接着读取下面的行，重复以上操作

                    //先将原一行原数据放入数组中
                    for(int i = 0;i<k.length;i++) {
                        list.add(k[i]);
                    }

                    for(int i = 0;i<k.length;i++){

                       // if(c == sAttr[i]){
                        if(sAttr[i].equals(c)){
                            list.set(i,a);
                        }


                        //if(d == sAttr[i]){
                        if(d.equals(sAttr[i])){
                            list.set(i,b);

                        }

                    }

                   // result+="\r\n";

                    for(int i = 0;i<k.length;i++){

                        result+=list.get(i)+" ";

                    }


                }else{
                    //没有则按照原来情况正常写入
//                    System.out.println("正常");
                    result+="\r\n"+line;

                }
            }
            //将字符串写入文件中
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
