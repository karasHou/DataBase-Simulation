
import java.io.BufferedReader;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;

public class DataBase {

    /*
    *
    *
    * */

    String sqlcreate="create table \\w{1,16} \\(.+\\)";
  /*  String sqlinsert="insert into (\\w{1,16}) \\((.+)\\) values\\((.+)\\)";
    String sqlupdate="update .+set .+(where .+)?";
    String sqldelete="delete from (\\w{1,16}) where .+";
    String sqlselect="select \\* from (\\w{1,16})";
    String sqlselecT="select (.+) from (\\w{1,16})";
    String sqlselecX="select \\* from (.+) where (.+) (.+) (.+)";
    String sqlselecXT="select (.+) from (.+) where (.+) (.+) (.+)";
    */
    //5、实现两个关系和多个关系的连接操作（select * from 关系名列表 where 连接条件）。
    //选择条件是指“属性名 操作符 属性名”形式的条件 select * from A,B whereA.ID=B.ID
   /*
    String sqlselecL="select \\* from (.+) where (.+)";
    String sqlalterA="alter table (\\w{1,16}) add (.+) (.+)";
    String sqlalterD="alter table (\\w{1,16}) drop (.+) (.+)";
    String sqldrop  ="drop table (\\w{1,16})";
    String sqlindexU="create \\[(unique|clustered)\\] index (.+) on (.+)";
    */
    public static void main(String[] args) throws IOException {

        //String s="create table student (name char,ssn int,grade char)";
        //String s="insert into student (name,ssn,grade) values('天',' ','大一年级')";
        //String s="update student set name=xiaohua,ssn=2016,grade=大二年级  where grade=大一年级;";
        //String s ="delete from student where name=小红;";
        //String s="select * from student";
        //String s = "alter table student add sex char";
        //String s = "alter table student drop ssn int";
        //String s="drop table student";
        //String s = "create [unique] index student_index on student";
        //String s="create [clustered] index student_index on student";
        // String s ="select name from student";
        //String s = "select * from student where ssn > '2034'";
        //String s="select grade from student where ssn = '2034'";

        //String s = "select * from student,course where s.ssn=c.ssn";
        String s="create table course (c_name char,ssn int)";
        //String s="insert into course (c_name,ssn) values('数据结构','2734')";
        //String s="create table study (grade char,ssn int)";
        //String s="insert into study (grade,ssn) values('大一年级','2734')";
        //FileWriter file_system=new FileWriter("D://system_table.txt",true);
        //System.out.println(s.matches(sqlcreate));
        //String s = "select * from student,course,study where s.ssn=c.ssn=s.ssn";
        System.out.println("欢迎你");
        System.out.println("用户名：");
        BufferedReader brd = new BufferedReader(new InputStreamReader(System.in));
        String username = brd.readLine();

        //判断用户类型，首先判断是否为管理员
        if("admin".equals(username)){
            System.out.println("密码：");
            BufferedReader brd2 = new BufferedReader(new InputStreamReader(System.in));
            String psw = brd2.readLine();
            //
            if("admin".equals(psw)){
                System.out.println("登录成功，欢迎你管理员");
                System.out.println("请操作");
                DataBase db = new DataBase();
                db.admin(s);

            }
            else
                System.out.println("密码错误");
            return ;

        }

        //判断是否为用户
        else
            if("user".equals(username)){
                System.out.println("密码：");
                BufferedReader brd2 = new BufferedReader(new InputStreamReader(System.in));
                String psw = brd2.readLine();
                if("user".equals(psw)){
                    System.out.println("登录成功，欢迎你用户");
                    System.out.println("请操作");
                    DataBase db = new DataBase();

               //     db.user(s);


                }
                else
                    System.out.println("密码错误");
            }


    }
/*
    public void user(String s) throws IOException{
        if(s.matches(sqlselect)){
            System.out.println("444");
            //file_system.write(s);
            Select.Select(s);
            //file_system.close();
        }else{
            System.out.println("您没有此权限！");
        }
    }
*/
    public void admin(String s) throws IOException{
        if(s.matches(sqlcreate)){
            System.out.println("000");
            //file_system.write(s);
            CreateTable.CreateTable(s);
            //file_system.close();
        }
      /*
        else if(s.matches(sqlinsert)){
            System.out.println("111");
            //file_system.write(s);
            InsertValues.InsertValues(s);
            //file_system.close();
        }
        else if(s.matches(sqlupdate)){
            //根据一个属性更新表
            System.out.println("222");
            //file_system.write(s);
            UpdateTable.UpdateTable(s);
            //file_system.close();
        }
        else if(s.matches(sqldelete)){
            System.out.println("333");
            //file_system.write(s);
            DeleteLine.DeleteLine(s);
            //file_system.close();
        }
        else if(s.matches(sqlselect)){
            System.out.println("444");
            //file_system.write(s);
            Select.Select(s);
            //file_system.close();
        }
        else if(s.matches(sqlalterA)){
            System.out.println("555");
            //file_system.write(s);
            AlterAdd.AlterAdd(s);
            //file_system.close();
        }
        else if(s.matches(sqlalterD)){
            System.out.println("666");
            //file_system.write(s);
            AlterDrop.AlterDrop(s);
            //file_system.close();
        }
        else if(s.matches(sqldrop)){
            System.out.println("777");
            //file_system.write(s);
            DropTable.DropTable(s);
            //file_system.close();
        }
        else if(s.matches(sqlindexU)){
            System.out.println("888");
            //file_system.write(s);
            IndexTable.IndexTable(s);;
            //file_system.close();
        }
        else if(s.matches(sqlselecT)){
            System.out.println("999");
            Select_2.Select_2(s);
        }
        else if(s.matches(sqlselecX)){
            System.out.println("101010");
            Select_x.Select_x(s);
        }else if(s.matches(sqlselecXT)){
            System.out.println("111111");
            Select_xt.Select_xt(s);
        }
        else if(s.matches(sqlselecL)){
            System.out.println("121212");
            Select_L.Select_L(s);
        }
        */
        else{
            System.out.println("输入的语句有错误");
        }
    }
}
