# 数据库模拟系统
一个用`java`实现的简单数据库模拟系统。

## 用法
下载后运行`DBMain.java`文件，输入sql语句。

## 系统环境
* jdk1.8.0
* JetBrains idea
* win10

## 实现的功能
* 创建表，支持整形，字符型数据
* 插入、删除、修改数据库记录
* 表的主属性建立索引并且按照有序排列
* 实现全关系的选择、单关系的投影和选择、多关系的连接操作
* 创建`数据字典`
* 对数据库设置口令和密码。有用户和管理员的权限。


## 关键技术
1. 匹配`sql`语句
```javascript
//创建
String sqlcreate = "create table \\w+ \\(.+\\)";
//插入
String sqlinsert = "insert into (\\w+) \\((.+)\\) values\\((.+)\\)";
//修改
String sqlupdate = "update .+set .+(where .+)?";
//删除
String sqldelete = "delete from (\\w+) where .+";
```
2.连接操作中的`笛卡尔积`：
```javascript
while((line1 = bf1.readLine())!=null){
            s_line1 = line1.split(" ");
            while((line2 = bf2.readLine())!=null) {
                s_line2 = line2.split(" ");
                if(s_line1[weizhi1].equals(s_line2[weizhi2])){
                    result+= line1 + line2 +"\r\n";
                }
            }
            //回到文件指针的起始位置
            bf2 =new BufferedReader(new FileReader(list.get(1)));
            bf2.readLine();bf2.readLine();
        }
```
