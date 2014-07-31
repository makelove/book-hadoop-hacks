hadoop-hacks
============

the source code of 《Hadoop Hacks》中文版，专家使用的实践技巧<br>
Now，Only has the Chapter 2:应用程序开发技巧<br>

Links：<br>
1.《Hadoop Hacks》（中文版）<br>
http://www.oreilly.com.cn/index.php?func=book&isbn=978-7-5123-4635-2 <br>
2.《Hadoop Hacks》――プロフェッショナルが使う実践テクニック<br>
http://www.oreilly.co.jp/books/9784873115467/ <br>
目录

前言<br>

第1章 系统架构/运用技巧<br>
运行HDFS环境的参数<br>
运行MapReduce环境需要的参数<br>
总结<br>
文件描述符的设置<br>
Java的安装<br>
总结<br>
本技巧中介绍的HA的构成<br>
HA集群的构建过程<br>
疑难解答<br>
总结<br>
可以获取的统计信息<br>
总结<br>
关于CDH3同一版本间的更新总结<br>
准备<br>
理解操作<br>
使用Oracle的操作确认<br>
总结<br>
Sqoop的PostgreSQL联合功能<br>
在PostgreSQL中的使用<br>
PostgreSQL联合的挑战<br>
总结<br>
什么是Azkaban<br>
Azkaban的安装<br>
总结<br>
作业的定制<br>
总结<br>


第2章 应用程序开发技巧<br>
将Hadoop配置文件拷贝到外部应用程序<br>
从应用程序操作MapReduce<br>
总结<br>
什么是InMapperCombiner<br>
InMapperCombiner的实现方法<br>
总结<br>
自定义Writable类型主要分为两种<br>
如何生成一个Writable接口的子类<br>
总结<br>
自定义Partitioner的实现方法<br>
总结<br>
什么是DistributedCache<br>
DistributedCache的使用<br>
总结<br>
自定义CombineFileInputFormat的实现<br>
自定义CombineFileInputFormat的使用<br>
总结<br>
获取MRUnit Jar文件<br>
Map的测试<br>
Reduce的测试<br>
测试MapReduce作业<br>
总结<br>
生成自定义Writable<br>
生成GroupingComparatorClass<br>
生成PartitionerClass<br>
生成SortComparatorClass<br>
设置各个Comparator<br>
总结<br>
数据的内容<br>
Map端连接的代码<br>
输出结果<br>
单独读入文件进行连接<br>
总结<br>
数据的内容<br>
Map的定义<br>
Reduce的定义<br>
作业的定义<br>
输出结果<br>
总结<br>
定义作业<br>
总结<br>
InputFormat类<br>
OutputFormat类<br>
总结<br>
Cassandra的hadoop程序包内容<br>
Map类<br>
Reduce类<br>
定义作业<br>
总结<br>
获取文件系统信息<br>
操作HDFS的API<br>
总结<br>
分析的要点<br>
用计数器可以确认的项目<br>
处理结束后的确认方法<br>
经过一定时间后的确认方法<br>
作业历史的管理方法<br>
总结<br>
Hadoop中可以处理的压缩格式<br>
Hadoop中处理lzo压缩的准备<br>
在MapReduce程序中处理压缩文件<br>
在Hive中处理压缩文件<br>
总结<br>


第3章 HBase技巧<br>
importtsv工具<br>
completebulkload工具<br>
总结<br>
sqoop import<br>
自定义PutTransformer<br>
总结<br>
用于HFileOutputFormat的Mapper<br>
作业的设置<br>
总结<br>
Java API<br>
HBase shell<br>
总结<br>
什么是协处理器<br>
Observer<br>
Endpoint<br>
总结<br>
创建自定义Filter<br>
配置自定义Filter<br>
总结<br>
export工具<br>
import工具<br>
总结<br>
复制机制<br>
配置<br>
数据的确认<br>
总结<br>


第4章 Hive技巧<br>
SQL语句和HiveQL语句的书写方法的不同<br>
SELECT句中不存在的列不能排序<br>
NULL的处理<br>
ROWNUM关联<br>
总结<br>
标点字符、带圈字符的存在<br>
PK的存在<br>
加载数据<br>
Join时的数据保持方法<br>
WHERE句中的比较条件<br>
置换处理和UDF<br>
key数和Out Of Memory<br>
总结<br>
处理单位<br>
ORDER BY语句和SORT BY语句<br>
输出中间连接表<br>
MapReduce任务不发生的处理<br>
一次连接多个表<br>
总结<br>
查询高速化的探讨<br>
总结<br>
Hive的用户定义函数<br>
UDF的安装<br>
UDAF的实现<br>
使用用户定义函数<br>
总结<br>
什么是SerDe<br>
文本数据的情况（使用RegexSerDe）<br>
文本数据的情况（使用CSV用的非标准SerDe）<br>
结构化/二进制数据的场合（使用JSON用的非标准SerDe）<br>
总结<br>
使用TRANSFORM句<br>
使用UDAF<br>
总结<br>
使用注释<br>
使用模式<br>
使用锁定机制<br>
总结<br>
流程<br>
使用方法<br>
总结<br>
Hive的日志的种类<br>
通过Hive会话日志可以确认的内容<br>
总结<br>
Hive-HDFS-RDBMS的关系<br>
向Hive的直接导入<br>
文件形式和直接导入的关系<br>
列指定·WHERE条件指定<br>
指定了Hive的分区key的导入<br>
总结<br>
Hive→Oracle的差异数据的交换<br>
总结<br>


第5章 Pig技巧<br>
进行LOAD处理要实现的项目<br>
进行STORE处理需要实现的项目<br>
在一个UDF中实现LOAD和STORE的处理<br>
总结<br>
数据的保存方法<br>
MapReduce作业的评价方法<br>
用户单独的处理<br>
输出的日志<br>
总结<br>
Pig的用户定义函数<br>
用户定义函数中可以使用的数据类型<br>
用户定义函数的实现和使用方法<br>
总结<br>
Java中的PigLatin嵌入方法<br>
执行范例<br>
总结<br>
DESCRIBE：确认数据结构<br>
EXPLAIN：确认MapReduce的执行计划<br>
DUMP：显示处理内容<br>
总结<br>
数学函数<br>
字符串操作函数<br>
总结<br>


第6章 Mahout技巧<br>
可以做什么<br>
推荐（Recommendation）<br>
聚类（Clustering）<br>
分类（Classification）<br>
其他<br>
总结<br>
安装<br>
执行方法<br>
总结<br>
总结<br>
输入数据的形式<br>
处理的执行<br>
执行命令的内容<br>
与文本数据的对应<br>
总结<br>
输入数据<br>
执行关联分析<br>
执行结果文件<br>
总结<br>
从学习（预测模型）到预测的过程<br>
预测模型的构建和应用<br>
总结<br>
分布式并行化的必要性<br>
随机森林的分布式并行图像<br>
In-memory模式<br>
总结<br>
应用例子<br>
创建方法<br>
创建驱动程序<br>
使用驱动程序<br>
总结<br>
K平均法<br>
输入数据的形式<br>
输入数据的变换<br>
执行kmeans<br>
获取执行结果<br>
结果文件的内容<br>
总结<br>


第7章 ZooKeeper技巧<br>
客户端程序库<br>
简单的API<br>
与ZooKeeper的连接和断开<br>
znode的创建<br>
确认znode的存在<br>
从znode读取数据<br>
znode中写入数据<br>
删除znode<br>
获取子节点的列表<br>
总结<br>
认证方案<br>
ACL和权限<br>
ACL的设置（setACL）<br>
获取ACL（getACL）<br>
总结<br>
操作<br>
总结<br>
启动<br>
连接<br>
操作数据<br>
尝试监视<br>
完成<br>
总结<br>
体系结构<br>
Rest服务器的启动<br>
总结<br>
C客户端程序库<br>
总结<br>
什么是tickTime？<br>
设置方法<br>
设置其他tickTime影响的项目<br>
以其他tickTime为基准进行的处理<br>
总结<br>
什么是ZAB<br>
使用ZAB的ZooKeeper内部处理<br>
两阶段提交<br>
总结<br>
构成ensemble<br>
leader选出运算法则<br>
总结<br>
状态<br>
总结<br>
分组<br>
权重<br>
总结<br>
分布并行队列的实现<br>
总结<br>
什么是BookKeeper<br>
BookKeeper的基本操作<br>
尝试BookKeeper<br>
有效利用BookKeeper<br>
总结<br>
实现介绍<br>
使用方法<br>
使用示例<br>
总结<br>
<br>
