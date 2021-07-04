### GC的默认版本
JDK 6，7，8这几个版本中，并行GC都是默认GC   
JDK 9，以及后面的版本默认GC是G1

### GC不同参数使用的算法
GC参数 | 新生代|老年代|适合场景
---|---|---|---|---
UseSerialGC | Serial| Serial Old|单CPU环境下的client模式
UseParallelGC | Parallel Scaveng| Parallel Old(1.5:Serial Old)|吞吐优先，互联网的BS程序web应用
UseConcMarkSweepGC | ParNew | CMS(Serial Old)|适合对响应速度暂停时间有要求的系统
UseG1GC | G1 | G1|适合对响应速度暂停时间有要求的系统
Serial关键字：DefNew    
Serial Old关键字：Tenured   
Parallel Scavenge关键字：PSYoungGen 
Parallel Old关键字：ParOldGen   
ParNew关键字：ParNew    
CMS关键字：CMS  
G1关键字：G1  




### 日志分析
分析一条GC日志：
2021-06-29T22:09:28.291-0800: [GC (Allocation Failure) [PSYoungGen: 262144K->43518K(305664K)] 262144K->79232K(1005056K), 0.0435587 secs] [Times: user=0.04 sys=0.05, real=0.04 secs]
第一段，2021-06-29T22:09:28.291-0800：表示时间戳
第二段，[GC (Allocation Failure) [PSYoungGen: 262144K->43518K(305664K)] 262144K->79232K(1005056K), 0.0435587 secs]：表示我们的堆内存变化情况
Allocation Failure：表示GC发生的原因，是分配内存失败导致的
[PSYoungGen: 262144K->43518K(305664K)] 262144K->79232K(1005056K), 0.0435587 secs]，表示在0.0435587秒也就是43毫秒，是GC并行执行使用的时间，或者叫GC暂停的时间。在43毫秒内，young区大小从262144K->43518K，大概被压缩了219M，同时这个那个young区当前的容量，也就是最大的容量305664K，262144K->79232K(1005056K)表示的是整个堆内存的变化情况，大概被压缩了183M，括号里面的就是最大的容量。
第一次执行GC来集回收的时候，整个堆内存里对象占用的这个内存的数量跟young区的对象占用的内存的数量是一致，因为这个时候old区还没有数据，还没有对象可以晋升到old区。young区从262144K->43518K(压缩约219M)，整体从262144K->79232K(压缩约183M)，中间差了36M的数据，这36M的数据是什么呢？说明这一次GC有36M的数据从young区晋升到了old区。
第三段，[Times: user=0.04 sys=0.05, real=0.04 secs]：表示CPU使用的情况

### 对象直接进入老年代
1.大对象（-XX:PretenureSizeThreshold=3m，大于 3m），
2.动态年龄判断
3.minor gc后，survivor区空间不能容纳全部存活对象，
4.存活对象达到年龄阈值。比如15

### 数据类型选择
包装类型占用的内存比基本类型的要多很多，尽量选择使用基本类型  
多维数组占用的内存也想多的多，尽量避免使用多维数组，可以的话使用技巧进行降维

### JVM怎么在GC的时候能够控制让所有的线程都暂停下来，只让GC的线程干活呢？
代码里面插入了很多做检查的所谓的安全点，线程在运行的时候，都会不断的检查自己的所谓的安全点

### GC日志分析工具
GCEasy ：gceasy.io  
GCViewer：一个jar包

### 怎么通过工具通过命令去查看当前JVM内部所有线程的状态，
可以用jstack，也可以用jcmd，jconsole， jvisualvm，jmc甚至我们可以在linux和Mac上直接用kill -3，等等

### 线程分析工具
fastthread.io
 
### 内存分析工具
JOL











