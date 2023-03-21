# basic-spring-boot-app
SpringBootアプリ開発の元となるリポジトリ

## 資料
- https://spring.pleiades.io/spring-boot/docs/current/reference/html/getting-started.html

## 起動
```bash
$ ./mvnw spring-boot:run
```
※IDEのターミナルでは起動できないもよう。Windows Terminalでは実行できている。原因は未特定

## コンパイルと実行
```bash
$ ./mvnw clean package
$ java -jar target/basic-java-app-0.1.0.jar
```
