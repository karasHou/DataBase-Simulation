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
    //删除指定行
    String sqldeleteLine="delete from (\\w+) where .+";
    //添加属性
    String sqlalterA="alter table (\\w+) add (.+) (.+)";
    //删除属性
    String sqlalterD="alter table (\\w+) drop (.+) (.+)";
    //建立索引
    String sqlindexU="create index (.+) on (.+) (.+)";
    //投影
    String sqlselecT="select (.+) from (\\w+)";
    //选择
    String sqlselecX="select \\* from (.+) where (.+) (.+) (.+)";
    //选择和投影
    String sqlselecXT="select (.+) from (.+) where (.+) (.+) (.+)";
    //连接
    String sqlselecL="select \\* from (.+) where (.+)";




    /*
    *   测试语句
    *   1.  create table student (name char,id int,grade int)
    *       create table course (name char,id int,grade int)
    *
    *   2.  insert into student (name,id,grade) values('houwei','6386','2')
    *       insert into student (name,id,grade) values('zouhao','1006','2')
    *       insert into student (name,id,grade) values('hahaha','6388','1')
    *
    *       insert into course (name,id,grade) values('DataBase','0001','2')
    *       insert into course (name,id,grade) values('Math','0002','2')
    *       insert into course (name,id,grade) values('Computer','0003','1')
    *
    *   3.  drop table student
    *   4.  select * from student
    *   5.  delete from student where name=zouhao;
    *   6.  update student set id=6666,grade=1 where name=houwei;
    *   7.  alter table student add sex char
    *   8.  alter table student drop id int
    *   9.  create index student_index on student name(char)
    *  10.  create index student_index on student id(int)
    *
    *  select语句：
    *
    *  11.  select * from student where grade > '1'                 选择
    *  12.  select name from student                                投影
    *  13.  select name from student where grade = '2'              选投
    *  14.  select * from student,course where s.grade=c.grade      连接（多表）
    * */


    public static void main(String[] args) throws IOException {
        System.out.println("欢迎使用数据库系统");

        System.out.println("请输入用户名:");
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
                DBMain db = new DBMain();

                while(true) {

                    BufferedReader brd1 = new BufferedReader(new InputStreamReader(System.in));
                    String sql = brd1.readLine();
                    if (sql.matches("quit") || sql.matches("QUIT")) {
                        System.out.println("欢迎下次使用，再见！\n");
                        break;
                    }

                    db.login(sql);
                }

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


                while(true) {
                    BufferedReader brd3 = new BufferedReader(new InputStreamReader(System.in));
                    String sql = brd3.readLine();
                    if(sql.matches("quit")||sql.matches("QUIT")){
                        System.out.println("欢迎下次使用，再见！\n");
                        break;
                    }

                    DBMain db = new DBMain();
                    db.user(sql);

                }
            }
            else
                System.out.println("密码错误");
        }

    }



    public void user(String s) throws IOException {
        if (s.matches(sqlselect)) {
            //只能查询
            SelectAll.SelectAll(s);
        } else {
            System.out.println("您没有此权限！");
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
        }else if(s.matches(sqlupdate)) {
            Update.UpdateTable(s);
        }else if(s.matches(sqldeleteLine)){
            DeleteLine.DeleteLine(s);
        }else if(s.matches(sqlalterA)){
            AlterAdd.AlterAdd(s);
        }else if(s.matches(sqlalterD)){
            AlterDrop.AlterDrop(s);
        }else if(s.matches(sqlindexU)){
            IndexTable.IndexKeyTable(s);
        }else if(s.matches(sqlselecX)){
            SelectX.SelectX(s);
        }else if(s.matches(sqlselecT)){
            SelectT.SelectT(s);
        }else if(s.matches(sqlselecXT)){
            SelectXT.SelectXT(s);
        }else if(s.matches(sqlselecL)){
            SelectL.SelectL(s);
        }
        else {
            System.out.println("输入的语句有错误");
        }
    }
}