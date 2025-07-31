# basic-spring-boot-app

[![Build](https://github.com/TakuyaFukumura/basic-spring-boot-app/workflows/Build/badge.svg)](https://github.com/TakuyaFukumura/basic-spring-boot-app/actions)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue)](https://maven.apache.org/)

SpringBootアプリ開発の元となるリポジトリ

## 資料
- https://spring.pleiades.io/spring-boot/docs/current/reference/html/getting-started.html

## Docker開発環境セットアップ

### 前提条件
- Docker
- Docker Compose

### 起動手順
1. リポジトリをクローン
    ```bash
    git clone https://github.com/TakuyaFukumura/basic-spring-boot-app.git
    ```
    ```bash
    cd basic-spring-boot-app
    ```
2. Docker Composeでアプリケーションを起動
    ```bash
    docker compose up --build
    ```
3. ブラウザでアクセス

    http://localhost:8080

4. H2データベースコンソールへのアクセス（開発用）

    http://localhost:8080/h2-console

5. ヘルスチェックエンドポイント

    http://localhost:8080/actuator/health

### Docker コマンド

#### アプリケーションの停止
```bash
docker compose down
```

#### ログの確認
```bash
docker compose logs -f app
```

#### イメージの再ビルド
```bash
docker compose build --no-cache
```

## 従来の起動方法（Docker不使用）

### 起動
```bash
./mvnw spring-boot:run
```

### コンパイルと実行
```bash
./mvnw clean package
```
```bash
java -jar target/myproject.jar
```