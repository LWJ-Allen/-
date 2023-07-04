# 测试说明

### 1、测试文件1.txt说明

- 商家初始化

```python
# 商家注册store2 123456
7
store2 123456

# 商家store2登录
8
store2 123456

# 查看所有窗口
2

# 查看窗口2菜品
3
2

# store2添加两个菜品
9
2 food1 12.5
9
2 food2 10

# 查看store2的菜品
3
2
```

- 用户初始化

```python
# 用户注册/登录
0
student1 123456
1
student1 123456

# 查看所有窗口
2

# 查看窗口2菜品
3
2

# 下单food1和food2
4
2 2 food1
4
2 2 food2

# 查看历史订单
5
2

```

- 商家完成订单后

```python
# store2完成订单1和2
6
1
6
2

# student2查看订单信息
5
2

# 退出系统
-1
```

### 2、测试结果展示

直接运行`test.bat`脚本即可。

```bash
C:\Users\28790\Desktop\软件工程   课程项目-7.4>cd bin

C:\Users\28790\Desktop\软件工程   课程项目-7.4\bin>java test ../testcase/1.txt
create UserManager
create an user:user1
create StoreManager
create a store:store1
create Interface
输入指令
7
商家注册：输入用户名和密码
store2 123456
create a store:store2
注册成功

输入指令
8
商家登入：输入用户名和密码
store2 123456
登录成功

输入指令
2
所有商家：
1 store1
2 store2

输入指令
3
查看窗口，输入窗口号:
2

输入指令
9
商家：输入商家id,菜品名，价格
2 food1 12.5
添加成功

输入指令
9
商家：输入商家id,菜品名，价格
2 food2 10
添加成功

输入指令
2
所有商家：
1 store1
2 store2

输入指令
3
查看窗口，输入窗口号:
2
food1 12.5
food2 10.0

输入指令
3
查看窗口，输入窗口号:
2
food1 12.5
food2 10.0

输入指令
0
student1 123456
1
注册输入用户名和密码
create an user:student1
注册成功

输入指令
输入用户名和密码
student1 123456
登录成功

输入指令
2
所有商家：
1 store1
2 store2

输入指令
3
查看窗口，输入窗口号:
2
food1 12.5
food2 10.0

输入指令
4
2 2 food1
4
点单，输入用户id，窗口号和菜名
成功

输入指令
点单，输入用户id，窗口号和菜名
2 2 food2
成功

输入指令
5
查看历史订单，输入用户id
2
1 false
2 false

输入指令
6
完成订单，输入订单号
1

输入指令
6
完成订单，输入订单号
2

输入指令
5
查看历史订单，输入用户id
2
1 true
2 true

输入指令
-1


C:\Users\28790\Desktop\软件工程   课程项目-7.4\bin>cd ..

C:\Users\28790\Desktop\软件工程   课程项目-7.4>pause
请按任意键继续. . .
```

