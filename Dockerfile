# --- ビルド用ステージ ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- 実行用ステージ ---
## 実行用の軽量イメージ
FROM eclipse-temurin:21-jre-alpine

## 非rootユーザーでの実行
RUN addgroup -g 1001 spring && adduser -u 1001 -G spring -s /bin/sh -D spring

USER spring:spring

## 作業ディレクトリの設定
WORKDIR /app

## ビルドされたJARファイルをコピー
COPY --chown=spring:spring --from=build /app/target/*.jar app.jar

## アプリケーションのポート
EXPOSE 8080

## アプリケーションの起動
ENTRYPOINT ["java", "-jar", "app.jar"]
