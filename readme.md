运行maven package命令，打包。
在target目录生成文件：CalcStorageTool-jar-with-dependencies.jar

在命令行窗口运行
java -jar CalcStorageTool-jar-with-dependencies.jar [ip] [port] [username] [password]

运行结果如下：

```
E:\work\tdengine-test\target>java -jar CalcStorageTool-jar-with-dependencies.jar 1.2.3.4 6041 root taosdata
db: test
st: weather
summary:
         5th=[0], 10th=[0], 20th=[0], 30th=[0], 40th=[0], 50th=[0]
         60th=[0], 70th=[0], 80th=[0], 90th=[0], 95th=[0], 99th=[0]
         Min=[0(Rows)] Max=[0(Rows)] Avg=[0(Rows)] Stddev=[0.00]
         Rows=[0], Blocks=[0], SmallBlocks=[0], Size=[0.000(Kb)] Comp=[1]
         RowsInMem=[10000]

Size: 0.000(Kb)
 ****************  此处省略N行日志  ***************
st: vgroups_info
summary:
         5th=[0], 10th=[0], 20th=[0], 30th=[0], 40th=[0], 50th=[0]
         60th=[0], 70th=[0], 80th=[0], 90th=[0], 95th=[0], 99th=[0]
         Min=[0(Rows)] Max=[0(Rows)] Avg=[0(Rows)] Stddev=[0.00]
         Rows=[0], Blocks=[0], SmallBlocks=[0], Size=[0.000(Kb)] Comp=[1]
         RowsInMem=[676]

Size: 0.000(Kb)


db: test                size: 0.0
db: log                 size: 0.0
db: demo                size: 0.0

E:\work\tdengine-test\target>

```

日志中的最后部分即为db以及占用的存储大小。

end~