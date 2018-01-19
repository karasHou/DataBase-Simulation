# 数据库模拟系统
一个用java实现的简单数据库模拟系统。

## 实现的功能
* 创建表，支持整形，字符型数据
* 输入、删除、修改数据库记录
* 表的主属性建立索引并且按照有序排列
* 实现全关系的选择、单关系的投影和选择、多关系的连接操作。

## 关键技术
1. 匹配sql语句
>   //创建<br>
    String sqlcreate = "create table \\w+ \\(.+\\)";<br>
    //插入<br>
    String sqlinsert = "insert into (\\w+) \\((.+)\\) values\\((.+)\\)";<br>
    //修改<br>
    String sqlupdate = "update .+set .+(where .+)?";<br>
    //删除<br>
    String sqldelete = "delete from (\\w+) where .+";<br>
    
2.连接操作中的笛卡尔积：
>
