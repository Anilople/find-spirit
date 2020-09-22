# find-spirit
找到一种让精灵无处可躲的策略

你在森林里发现了**五**个神秘的罐子。

在罐子中藏着一个精灵，只要把他放出来就可以让他帮你实现一个愿望。

但是这个精灵很调皮，他并不想让你那么容易地抓住他。
在一开始，精灵会随机地藏在其中的一个罐子里。

每一个晚上，你都可以选择打开任何一个罐子来看看精灵是不是在里面。

如果你没有找到精灵，那么在第二天的白天，精灵必须移动到他原先躲藏的罐子旁边的另一个罐子里。

你一共可以尝试**六**个晚上。

请问，要怎么样利用这**六次机会**才能保证最后一定可以抓住精灵？

---

这个难点在于精灵可以移动

有一种暴力搜索的思路

将五个罐子进行编号，分别为0、1、2、3、4

这样6次打开罐子的顺序，可以用一串长为6位的数字来表示，例如

* `012344`表示第一次打开编号为0的罐子，第二次打开编号为1的罐子。。。最后一次打开编号为4的罐子

同理，精灵的6次移动，如果初始位置是3，可以表示成

```mermaid
graph LR
  3 --> |第一次移动|4;
```

```mermaid
graph LR
  4 --> |第二次移动|3;
```

```mermaid
graph LR
  3 --> |第三次移动|2;
```

```mermaid
graph LR
  2 --> |第四次移动|1;
```

```mermaid
graph LR
  1 --> |第五次移动|2;
```

```mermaid
graph LR
  2 --> |第六次移动|3;
```

由于每次精灵都在罐子打开，并且关闭后，才移动，所以我们只关心精灵移动之前的位置即可，根据上面的例子，精灵的移动也可以用一串数字`343212`进行表示

现在来看几种**可以找到**精灵的场景

* 罐子打开的顺序为`001122`，精灵的移动为`012343`。第**一**次打开罐子就找到了精灵。
* 罐子打开的顺序为`001122`，精灵的移动为`101234`。第**二**次打开罐子就找到了精灵。
* 罐子打开的顺序为`001122`，精灵的移动为`321012`。第**三**次打开罐子就找到了精灵。

再来看几种**不可以找到**精灵的场景

* 罐子打开的顺序为`012343`，精灵的移动为`101010`。
* 罐子打开的顺序为`343434`，精灵的移动为`012101`。
* 罐子打开的顺序为`232323`，精灵的移动为`434343`。

所以问题可以进一步转为，是否存在一种罐子的打开顺序`abcdef`，使得精灵不论采用哪种初始位置和移动策略，都会被找到？

---

直接找很难，可以尝试暴力搜索

5个罐子，可以打开6次，总共有`5^6=15625`种罐子的打开策略，使用代码

```java
List<List<Integer>> strategies = GenerateUtil.combination(5, 6);
```

生成

精灵移动策略也可以枚举出来（有点难算，但是给定一种移动策略，我们可以判断这个策略是否符合精灵每次只能移动到旁边罐子的规则）

所以在使用了代码生成`5^6=15625`种罐子的打开策略后，可以基于这些策略，来判断哪些可以被精灵使用（罐子的打开顺序和精灵的移动在本质上并没有区别，都是6位数字）

```java
List<List<Integer>> spiritMoves = strategies.stream().filter(JudgeUtil::isSpiritMove)
				.collect(Collectors.toList());
```

总共找出来精灵的移动策略有**72**种。

接下来就是暴力搜索的时候了，对于`15625`种罐子的打开策略，进行遍历，对于每一个罐子的打开策略a，

查看**72**种精灵的移动策略是否存在能够不被找到的移动策略，

如果**存在**一种精灵的移动策略b，可以让精灵无法被罐子的打开策略a找到，说明这个罐子的打开策略**没法**保证100%找到精灵；

相反，如果**不存在**一种精灵的移动策略b，可以让精灵无法被罐子的打开策略a找到，说明这个罐子的打开策略**可以**保证100%找到精灵；

