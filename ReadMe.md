## 简单的网络请求库
>> 实现了和具体网络请求分离，如果以后需要修改请求框架 只需要修改库里的请求框架，具体代码不需要修改
> maven 地址
        maven { url 'https://dl.bintray.com/bgtal/maven/'}
        //由于不稳地就弃用了 maven{url 'http://106.15.194.131:8821/repository/android/'}
> version ‘com.blq.network:networksdk:2.0.1’
//因为上传bintray的某些原因，不得将 ‘netsdk’ 改名为 ’networksdk‘ 'com.blq.network:netsdk:2.0.0'

> 服务器返回结构必须是以下结构
```Json
对象
  {
  "status":10000,
  "msg":"请求成功",
  "data":{}
  }
数组
  {
  "status":10000,
  "msg":"请求成功",
  "data":[]
  }
```

## 对外暴露内容
* NetworkManager.java 初始化和注册服务器切换等功能
* ServiceToggleable.java 服务器切换接口
* NetProxy.java 具体使用的请求包装类
* AbsJsonCallBack.java 请求的回调类

## 使用方法
1.初始化
一般在Application 中注册 ([ 查看例子](app/src/main/java/com/blq/network/MApplication.java))
```java
  public void initNetWork(){
    //1.先初始化
    NetworkManager.initNetwork(this);
    //2.将接口注册到NetWorkManger
    NetworkManager.registerServiceToggleable(DemoHttpInterface.class);
    //3.切换服务器
    NetworkManager.serviceToggle("106.15.194.131",3000,BuildConfig.DEBUG);
  }
```
2.[接口注册实现](app/src/main/java/com/blq/network/DemoHttpInterface.java) 直接查看例子里面有说明

3.最后的使用   
使用NetProxy的get 和post 方法，实现get和post 的请求体Builder
然后填入参数
最后调用execute() 方法

[例子](app/src/main/java/com/blq/network/MainActivity.java)

>要注意的是 如果继承 AbsJsonCallBack类 如果重写构造函数的的话需要实现super()方法

```java
public abstract class MyJsonCallBack extends AbsJsonCallBack{

        public MyJsonCallBack(String xxx){
          //如果实现构造函数一定要加super哦
            super();
        }
    }
```
