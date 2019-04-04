# DynamicProxy

###### 開発環境
- java 8
- maven: 4.0.4

##### 検証

- オリジナルJDKに提供してくれたもの

> \src\test\java\jp\whisper\proxy\OrderServiceTests.java⇒testFix

```java
Proxy.newProxyInstance
```
- JDKの動きを参考し、自らが実現したプロキシクラスを生成する機能

>\src\test\java\jp\whisper\proxy\OrderServiceTests.java⇒testFix2

```java
OrderServiceProxy.newProxyInstance
```

