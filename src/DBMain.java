import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 *  数据库主函数
 *  数据字典的组成：
     1、数据项
     2、数据结构
     3、数据流
     4、数据存储
     5、处理过程

 */



public class DBMain {

    //创建
    String sqlcreate = "create table \\w+ \\(.+\\)";
    //插入
    String sqlinsert = "insert into (\\w+) \\((.+)\\) values\\((.+)\\)";
    //修改
    String sqlupdate = "update .+set .+(where .+)?";
    //删除
    String sqldelete = "delete from (\\w+) where .+";
    //显示数据库结构和内容
    String sqlselect = "select \\* from (\\w+)";
    //删除
    String sqldrop  ="drop table (\\w+)";


    /*
    *   测试语句
    *   1.  create table student (s_name char,id int)
    *   2.  insert into student (s_name,id) values('houwei','6386')
    *       insert into student (s_name,id) values('zouhao','1006')
    *   3.  drop table student
    *   4.  select * from student
    *   5.  delete from student2 where name=小红;
    *
    * */


    public static void main(String[] args) throws IOException {
        System.out.println("欢迎使用数据库系统");
        while(true) {
            BufferedReader brd = new BufferedReader(new InputStreamReader(System.in));
            String sql = brd.readLine();
            if(sql.matches("quit")||sql.matches("QUIT")){
                System.out.println("欢迎下次使用，再见！\n");
                break;
            }
            DBMain db = new DBMain();
            db.login(sql);
        }
    }

    public void login(String s) throws IOException{

        //待加入管理员权限的功能


        if(s.matches(sqlcreate)){
            Create.Create(s);
        }else if(s.matches(sqlinsert)) {
            Insert.Insert(s);
        }else if(s.matches(sqldrop)){
            DeleteTable.DeleteTable(s);
        }else if(s.matches(sqlselect)){
            SelectAll.SelectAll(s);
        }
        else {
            System.out.println("输入的语句有错误");
        }
    }
}