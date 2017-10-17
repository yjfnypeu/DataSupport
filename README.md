# DataSupport
一款简单、轻量、易扩展的数据处理框架。

### 为什么写

在平时开发过程中。经常都会遇到。诸如当你开发时需要使用数据时。数据提供方(如api接口)由于各种原因。不能及时为你提供相应数据使用。导致耽误开发进度的情况。
    
又或者。当你本来业务已经测试完毕。可能某些原因。数据提供方可能突然变动了数据提供的格式，从而导致crash。放心，锅始终在你这边。。。
    
这个时候。如果我们能首先。在数据提供者未来得及的时候。进行方便的模拟数据。或者当提供者。提供给你数据的时候。能够方便的对数据格式进行检查。想想是不是爽歪歪？

### 使用场景

从上面可以看出。此库的使用场景分为两类：
- 当数据提供者未能及时提供数据时。根据配置的规则创建模拟数据并测试
- 当数据提供者返回数据之后。根据配置的规则进行数据检查。

### 依赖

lastestVersion = [![](https://jitpack.io/v/yjfnypeu/DataSupport.svg)](https://jitpack.io/#yjfnypeu/DataSupport)

```groovy
// 添加jitPack仓库使用
maven { url 'https://jitpack.io' }

// 添加依赖
compile "com.github.yjfnypeu:DataSupport:$lastestVersion"
```

### 更新日志

[releases page](https://github.com/yjfnypeu/DataSupport/releases)

### 用法

[请参考wiki](https://github.com/yjfnypeu/DataSupport/wiki)

### 联系作者

Email:470368500@qq.com
QQ group:108895031


## License

[Apache v2.0](./LICENCE)