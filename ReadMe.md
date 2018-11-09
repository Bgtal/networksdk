## 简单的网络请求库
>> 实现了和具体网络请求分离，如果以后需要修改请求框架 只需要修改库里的请求框架，具体代码不需要修改

## 对外暴露内容
* NetworkManager.java 初始化和注册服务器切换等功能
* ServiceToggleable.java 服务器切换接口
* NetProxy.java 具体使用的请求包装类
* AbsJsonCallBack.java 请求的回调类

## 使用方法
1.初始化
一般在Application 中注册([demo](../NetWork/app/src/main/java/com/blq/network/MApplication.java))
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
